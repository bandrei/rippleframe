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
 * Time: 9:16 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class PPTypeBoolean extends PPType {
    @Override
    public PPBase cast(PPType newType, PPBase value) throws PPError.PPSemanticError {
        switch (newType.getClass().getSimpleName()) {
            case "PPTypeBoolean":
                return value;
            case "PPTypeDouble":
                return new PPCast<Boolean, Double>(value, new PPTypeDouble()) {
                    @Override
                    protected Double cast(Boolean val) {
                        return val ? 1.0 : 0.0;
                    }
                };
            case "PPTypeString":
                return new PPCast<Boolean, String>(value, new PPTypeString()) {
                    @Override
                    protected String cast(Boolean val) {
                        return val.toString();
                    }
                };
            default:
                throw new PPError.PPTypeError(this, newType);
        }
    }

    @Override
    public Object defaultValue() {
        return false;
    }
}
