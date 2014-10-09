package com.septacore.ripple.preprocess.types;

import com.septacore.ripple.preprocess.PPBase;
import com.septacore.ripple.preprocess.PPCast;
import com.septacore.ripple.preprocess.PPError;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: Vulkum
 * Date: 5/21/14
 * Time: 9:01 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class PPTypeDouble extends PPType {
    @Override
    public PPBase cast(PPType newType, PPBase value) throws PPError.PPSemanticError {
        switch (newType.getClass().getSimpleName()) {
            case "PPTypeDouble":
                return value;
            case "PPTypeString":
                return new PPCast<Double, String>(value, new PPTypeString()) {
                    @Override
                    protected String cast(Double val) {
                        return val.toString();
                    }
                };
            case "PPTypeBoolean":
                return new PPCast<Double, Boolean>(value, new PPTypeBoolean()) {
                    @Override
                    protected Boolean cast(Double val) {
                        return ( ! (val == 0.0) );
                    }
                };
            default:
                throw new PPError.PPTypeError(this, newType);
        }
    }

    @Override
    public Object defaultValue() {
       return new Double(0.0);
    }
}
