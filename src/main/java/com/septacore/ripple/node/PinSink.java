/*
 * Started by Rory Allford (rda10)
 */
package com.septacore.ripple.node;

import com.septacore.ripple.preprocess.PPBase;
import com.septacore.ripple.preprocess.PPError.PPSemanticError;

/**
 * Represents a Payload consumer with PPType casting
 * Used internally in Box
 * @see PPBase
 */
public class PinSink {
    /**
     * The PinSource possibly cast, possibly null
     * @see PPBase
     */
    private PPBase source;
    
    /**
     * Source synchronisation object
     */
    private final Object sourceSync = new Object();
    
    /**
     * The head of the PinSource
     */
    private Payload head = null;
    
    /**
     * Head synchronisation object
     */
    private final Object headSync = new Object();
    /**
     * The input pin corresponding to this sink
     */
    protected final BoxPin pin;

    /**
     * Create a sink for this pin
     * @param pin 
     * The input pin
     */
    protected PinSink(BoxPin pin) {
        this.pin = pin;
    }
    
    /**
     * Retrieve the next value
     * @return
     * Payload representing the next value in the queue
     * @throws InterruptedException 
     */
    protected Payload head() throws InterruptedException {
        synchronized (headSync) {
            if (head == null) {
                PPBase sourceref;
                
                synchronized (sourceSync) {
                    sourceref = source;
                }
                // Interruptible statement:
                head = sourceref.getPinValue();
            }
            return head;
        }
    }
    /**
     * Notify the value of head() has been consumed and advance along the queue
     */
    protected void advance() {
        synchronized(headSync) {
            head = null;
        }
    }
    
    /**
     * Check if the sink is connected to a source
     * @return 
     * Boolean
     */
    protected boolean notConnected() {
        synchronized(sourceSync) {
            return source == null;
        }
    }
    
    /**
     * Connect to the specified source, try and cast
     * @param newSource
     * The source to connect to
     * @throws com.septacore.ripple.preprocess.PPError.PPSemanticError
     * Type error
     */
    protected void connectSource(PinSource newSource) throws PPSemanticError {
        synchronized (sourceSync) {
            if (newSource == null) {
                source = null;
            } else {
                source = newSource.pin.type.cast(pin.type, newSource);
            }
        }
    }
}
