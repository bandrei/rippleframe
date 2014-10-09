package com.septacore.ripple.preprocess.apps.functions;

import com.septacore.ripple.preprocess.algos.StringProcessors;
import com.septacore.ripple.preprocess.apps.PPAppBase;
import com.septacore.ripple.preprocess.apps.PPAppFunction;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: Vulkum
 * Date: 5/22/14
 * Time: 6:14 PM
 * To change this template use File | Settings | File Templates.
 */
@Component(value = "SKEWLEN")
public class GetSkewLen implements PPAppFunction {
    @Override
    public PPAppBase create() {
        return new StringProcessors.PPSkewLength();
    }
}
