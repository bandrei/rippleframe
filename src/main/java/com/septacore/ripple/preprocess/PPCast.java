package com.septacore.ripple.preprocess;

import com.septacore.ripple.node.Payload;
import com.septacore.ripple.preprocess.PPBase;
import com.septacore.ripple.preprocess.types.PPType;


public abstract class PPCast<F,T> extends PPBase {
    private final PPBase value;
    public PPCast(PPBase value, PPType castAs) {
        super(castAs);
        this.value = value;
        this.setExprString(value.getExprString());
    }
    protected abstract T cast(F val);
    @Override
    public final Object getValue(int evalStep) {
        return (Object) ( (T) cast( (F) value.getValue(evalStep) ) );
    }

    @Override
    public Payload getPinValue() throws InterruptedException {
        Payload dgv = value.getPinValue();
        return new Payload(
                (Object) ( (T) cast( (F) dgv.value ) )
                , dgv.sequenceNumber);
    }


    @Override
    public String getTypeString() {
        StringBuilder ts = new StringBuilder();
             /*
             if (value.returnType == returnType) {
                 throw new RuntimeException("Superfluous cast!");
             }*/

        ts.append(returnType.toString());
        ts.append(" from ");
        ts.append(value.getTypeString());
        return ts.toString();
    }

}