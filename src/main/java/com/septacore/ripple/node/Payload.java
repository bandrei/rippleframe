package com.septacore.ripple.node;

import com.septacore.ripple.preprocess.types.PPType;

import java.io.Serializable;

/**
 * Immutable value used when message passing via the LinkedBlockingQueue of
 * a PinSource .
 * @see PinSource
 */
public final class Payload implements Serializable {
    /**
     * The underlying boxed value
     * @see PPType
     */
    public final Object value;
    /**
     * Sequence number of the passed object
     */
    public final int sequenceNumber;
    
    public final Throwable error;

    public Payload(Object value, int sequenceNumber) {
        this.value = value;
        this.sequenceNumber = sequenceNumber;
        this.error = null;
    }

    /**
     * Creates a new Pin with an error object. Usually used
     * as poison values
     * @param error
     */
    public Payload(Throwable error) {
        this.value = null;
        this.sequenceNumber = 0;
        this.error = error;
    }
    
    public final boolean isError() {
        return error != null;
    }
    
    
}
