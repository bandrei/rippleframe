package com.septacore.ripple.preprocess;

import com.septacore.ripple.preprocess.types.PPType;

/**
 * A variable
 * @author rory
 */
public class PPVariable extends PPBase {
    
    private Object value;

    public PPVariable(PPType returnType, Object initValue) {
        super(returnType);
        this.value = initValue;
    }
    
    @Override
    public Object getValue(int evalStep) {
        return value;
    }
    
    public void setValue(Object newValue) {
        this.value = newValue;
    }
    
}
