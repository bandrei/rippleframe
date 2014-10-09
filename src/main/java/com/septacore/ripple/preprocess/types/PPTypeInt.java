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
public class PPTypeInt extends PPType {
    @Override
    public PPBase cast(PPType newType, PPBase value) throws PPError.PPSemanticError {
        switch (newType.getClass().getSimpleName()) {
            case "PPTypeInt":
                return value;
            case "PPTypeString":
                return new PPCast<Integer, String>(value, new PPTypeString()) {
                    @Override
                    protected String cast(Integer val) {
                        return val.toString();
                    }
                };
            case "PPTypeBoolean":
                return new PPCast<Integer, Boolean>(value, new PPTypeBoolean()) {
                    @Override
                    protected Boolean cast(Integer val) {
                        return ( ! (val == 0.0) );
                    }
                };
            case "PPTypeDouble":
                return new PPCast<Integer, Double>(value, new PPTypeDouble()) {
                    @Override
                    protected Double cast(Integer val) {
                        return val * 1.0;
                    }
                };
            default:
                throw new PPError.PPTypeError(this, newType);
        }
    }

    @Override
    public Object defaultValue() {
        return new Integer(0);
    }
}
