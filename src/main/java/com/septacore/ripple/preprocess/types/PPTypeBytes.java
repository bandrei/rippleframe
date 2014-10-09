package com.septacore.ripple.preprocess.types;

import com.septacore.ripple.preprocess.PPBase;
import com.septacore.ripple.preprocess.PPCast;
import com.septacore.ripple.preprocess.PPError;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

import java.awt.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created with IntelliJ IDEA.
 * User: Vulkum
 * Date: 5/21/14
 * Time: 8:31 PM
 * To change this template use File | Settings | File Templates.
 */
@org.springframework.stereotype.Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class PPTypeBytes extends PPType {
    public PPBase cast(PPType newType, PPBase value) throws PPError.PPSemanticError {
        switch (newType.getClass().getSimpleName()){
            case "PPTypeBytes":
                return value;
            case "PPTypeDouble":
                return new PPCast<byte[],Double>(value, new PPTypeDouble()) {
                    @Override
                    protected Double cast(byte[] val) {
                        if(val==null) return null;
                        ByteBuffer buff = ByteBuffer.allocate(8);
                        int buffL=8;
                        for(int i=val.length-1;i>=0;i--){
                            buff.put(--buffL,val[i]);
                        }
                        buff.order(ByteOrder.BIG_ENDIAN);
                        return buff.getDouble();
                    }
                };
            case "PPTypeInt":
                return new PPCast<byte[],Integer>(value, new PPTypeInt()) {
                    @Override
                    protected Integer cast(byte[] val) {
                        if(val == null)
                            return null;
                        ByteBuffer buff = ByteBuffer.allocate(4);
                        int buffL=4;
                        for(int i=val.length-1;i>=0;i--){
                            buff.put(--buffL,val[i]);
                        }
                        buff.order(ByteOrder.BIG_ENDIAN);
                        return buff.getInt();
                    }
                };
            case "PPTypeString":
                return new PPCast<byte[],String>(value, new PPTypeString()) {
                    @Override
                    protected String cast(byte[] val) {
                        if(val==null)return null;
                        return new String(val);
                    }
                };
            default:
                throw new PPError.PPTypeError(this, newType);
        }
    }

    @Override
    public Object defaultValue() {
        return new Object();
    }

    public Color guiColor() {
        return Color.RED;
    }
}
