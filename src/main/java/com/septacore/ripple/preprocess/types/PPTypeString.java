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
 * Time: 9:13 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class PPTypeString extends PPType {
    @Override
    public PPBase cast(PPType newType, PPBase value) throws PPError.PPSemanticError {
        switch (newType.getClass().getSimpleName()) {
            case "PPTypeDouble":
                return new PPCast<String, Double>(value, new PPTypeDouble()) {
                    @Override
                    protected Double cast(String val) {
                        try {
                            return Double.parseDouble(val);
                        } catch (Exception ex) {
                            return Double.NaN;
                        }
                    }

                };
            case "PPTypeInt":
                return new PPCast<String, Integer>(value, new PPTypeInt()) {
                    @Override
                    protected Integer cast(String val) {
                        try {
                            return Integer.parseInt(val);
                        } catch (Exception ex) {
                            return null;
                        }
                    }

                };
            case "PPTypeString":
                return value;

            default:
                throw new PPError.PPTypeError(this, newType);
        }
    }

    @Override
    public Object defaultValue() {
        return "";
    }
}
