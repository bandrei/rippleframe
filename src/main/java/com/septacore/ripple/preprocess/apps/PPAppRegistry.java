package com.septacore.ripple.preprocess.apps;

import com.septacore.ripple.preprocess.apps.PPAppFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Dynamically retrieve the various functions which can be applied to
 * the pins
 */
@Component
public class PPAppRegistry {
    @Autowired
    private ApplicationContext applicationContext;

    public PPAppFunction getFunction(String name){
       return applicationContext.getBean(name.toUpperCase(),PPAppFunction.class);
    }
}
