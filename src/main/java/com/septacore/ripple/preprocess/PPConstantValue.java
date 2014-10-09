package com.septacore.ripple.preprocess;

import com.septacore.ripple.preprocess.PPBase;
import com.septacore.ripple.preprocess.types.PPType;

/**
 * A constant value (an app with 0 arguments)
 * @author rory
 */
public class PPConstantValue extends PPBase {
    private final Object value;
    
    /**
     * 
     * @param type
     * The type of the value
     * @param value 
     * The value cast as an object
     */
    public PPConstantValue(PPType type, final Object value) {
        super(type);
        this.value = value;
    }

    @Override
    public Object getValue(int evalStep) {
        return value;
    }
    
}
