/*
 * Started by Rory Allford (rda10)
 */
package com.septacore.ripple.node;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import com.septacore.ripple.preprocess.PPBase;

/**
 * Represents a Payload producer with an output queue
 */
public class PinSource extends PPBase {
    
    /**
     * The maximum amount of values to buffer before blocking the producer
     */
    public static final int QueueSize = 16;
    
    /**
     * The pin corresponding to this value source
     */
    protected final BoxPin pin;
    
    
    /**
     * Whether we have pushed
     */
    private boolean pushed = false;
    
    /**
     * Pushed synchronisation object;
     */
    private final Object pushedSync = new Object();
    
    /**
     * The underlying blocking queue of DGValues
     */
    protected final LinkedBlockingQueue<Payload> queue;
    
    /**
     * For Data dumping:
     */
    protected volatile Payload lastPushed = null;

    /**
     * Create a value source with the named pin
     * @param pin 
     * The output pin
     */
    protected PinSource(BoxPin pin) {
        super(pin.type);
        this.pin = pin;
        this.queue = new LinkedBlockingQueue<Payload>(QueueSize);
    }

    /**
     * Get the next result - blocking (thread safe)
     * @return 
     * The next Payload in the queue
     */
    @Override
    public Payload getPinValue() throws InterruptedException {
        return queue.take();
    }
    
    /**
     * Not implemented for this - use getPinValue() instead
     * @param evalStep
     * Ignored
     * @return 
     */
    @Override
    public Object getValue(int evalStep) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    /**
     * Enqueue a result once - blocking (thread safe)
     * @param value
     * The Payload to be enqueued
     */
    protected void push(final Payload value) throws InterruptedException {
        synchronized (pushedSync) {
            if (!pushed) {
                queue.put(value);
                lastPushed = value;
                pushed = true;
            }
        }
    }
    
    protected void pushPoison(Throwable poison) {
        synchronized (pushedSync) {
            try {
                queue.offer(new Payload(poison), 2, TimeUnit.SECONDS);
            } catch (InterruptedException ex) {
                // Abort the poison
            }
        }
    }
    /**
     * Notify that enqueuing is finished
     */
    protected void advance() {
        synchronized (pushedSync) {
            pushed = false;
        }
    }
    
    /**
     * The last value pushed onto this ValueSource
     * (thread safe)
     * @return Payload
     */
    protected Payload getLastPushed() {
        return lastPushed;
    }
    
}
