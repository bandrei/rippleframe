package com.septacore.ripple.node;

import com.septacore.ripple.preprocess.types.PPType;
import org.springframework.stereotype.Component;

/**
 * Factory class to be used by Spring when creating the pins
 * to allow for dynamic PPTypes to be used.
 */
@Component
public class PinFactory {

    public static BoxPin getBoxPin(PPType type, String name){
        return new BoxPin(type,name);
    }
}
