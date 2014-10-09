package com.septacore.ripple.preprocess;

import com.septacore.ripple.node.Payload;
import com.septacore.ripple.preprocess.types.PPType;


public abstract class PPBase {
    public final PPType returnType;
    
    private String exprString = null;
    /**
     * @param returnType
     * the type of the output value
     */
    public PPBase(PPType returnType) {
        this.returnType = returnType;
        
    }
    
    public abstract Object getValue(int evalStep);
    
    public Payload getPinValue() throws InterruptedException {
        return null;
    }
    
    public String getTypeString() {
        StringBuilder ts = new StringBuilder();
        ts.append(returnType.toString());
        ts.append(" [");
        ts.append(this.getClass().toString());
        ts.append("@");
        ts.append(this.hashCode());
        ts.append("]");
        return ts.toString();
    }

    public String toString(int evalStep) {
        StringBuilder ts = new StringBuilder();
        ts.append(returnType.toString());
        ts.append(" : ");
        ts.append(getValue(evalStep).toString());
        return ts.toString();
    }

    /**
     * @return the exprString
     */
    public String getExprString() {
        return exprString;
    }

    /**
     * @param exprString the exprString to set
     */
    public void setExprString(String exprString) {
        this.exprString = exprString;
    }
    
}