package com.septacore.ripple.preprocess.apps;

import com.septacore.ripple.preprocess.PPBase;
import com.septacore.ripple.preprocess.PPError.PPSemanticError;
import com.septacore.ripple.preprocess.types.PPType;

/**
 * Base class for apps (preprocessors) with lazy evaluation
 * Override and implement applyPreprocessor
 * calling super() with the appropriate types
 * @author rory
 */
public abstract class PPAppBase extends PPBase {
    
    private final PPType argTypes[];
    protected PPBase args[];
    
    private Object valCache;
    private int valCacheStep;
    
    public PPAppBase(PPType returnType, PPType[] argTypes) {
        super(returnType);
        this.argTypes = argTypes;
        this.args = new PPBase[argTypes.length];
        valCache = null;
        valCacheStep = Integer.MIN_VALUE;
    }
    
    
    @Override
    public String getTypeString() {
        StringBuilder ts = new StringBuilder();
        ts.append(super.getTypeString());
       ts.append(" ( ");
       boolean fparam = false;
        for (PPBase arg : args) {
            if (fparam) {
                ts.append(",");
            } else {
                fparam = true;
            }
            ts.append(arg.getTypeString());
        }
        ts.append(" )");
        
        return ts.toString();
    }
    
    @Override
    public Object getValue(int evalStep) {
        if (valCache != null && valCacheStep == evalStep) {
            //System.out.println("CACHE HIT " + getTypeString(evalStep));
            // CACHE HIT
            return valCache;
        } else {
            //System.out.println("CACHE MISS: " + getTypeString(evalStep));
            Object argVals[] = new Object[args.length];
            for (int i = 0; i < argVals.length; i++) {
                argVals[i] = args[i].getValue(evalStep);
            }
            valCacheStep = evalStep;
            return valCache = applyPreprocessor(argVals);
        }
    }
    
    public final void bind(PPBase[] toArgs) throws PPSemanticError
    {
        int c = args.length;
        if (toArgs.length != c) {
            throw new PPSemanticError("Actual and formal argument lists differ in length");
        }
        for (int i = 0 ; i<c; i++ ) {
            try {
                args[i] = toArgs[i].returnType.cast(argTypes[i], toArgs[i]);
            } catch (PPSemanticError ex) {
                throw new PPSemanticError("Argument " + i, ex);
            }
        }
    }
    
    public abstract Object applyPreprocessor(Object[] argVals);
}
