/*
 * Started by Rory Allford (rda10)
 */
package com.septacore.ripple.node;

import java.io.Serializable;

import com.septacore.ripple.preprocess.types.PPType;
import com.septacore.ripple.preprocess.types.PPTypeRegistry;

/**
 * Represents a pin of a Box
 */
public class BoxPin implements Serializable {
    /**
     * The type of the pin
     */
    protected final PPType type;
    /**
     * The pin name (uniquely identifies this BoxPin)
     */
    protected final String pinName;

    /**
     * Registry used for finding/instantiating types
     */
    protected PPTypeRegistry registry;

    public void setRegistry(PPTypeRegistry registry) {
        this.registry = registry;
    }


    /**
     * Creates a pin uniquely identifier by its name only
     * <P>
     * (two pins with different types but the same name are equal)
     * @param type
     * The type of the pin
     * @see PPType
     * @param pinName
     * The identifier of the pin

     */
    public BoxPin(PPType type, String pinName) {
        this.type = type;
        this.pinName = pinName;
    }

    
    
    ///////// For use in hashmaps:
    /**
     * For use in HashMap
     * @see java.util.HashMap
     * @return 
     */
    @Override
    public int hashCode() {
        return pinName.hashCode();
    }
    /**
     * For use in HashMap
     * @see java.util.HashMap
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BoxPin other = (BoxPin) obj;
        if ((this.pinName == null) ? (other.pinName != null) : !this.pinName.equals(other.pinName)) {
            return false;
        }
        return true;
    }

    

    public final PPType getPinType() {
        return this.type;
    }
    
    public final String getPinName(){
        return this.pinName;
    }
    
    public static PPType[] getPinsTypes(BoxPin[] pins) {
        if (pins != null) {
            PPType[] types = new PPType[pins.length];
            for (int i = 0; i < pins.length; i++) {
                types[i] = pins[i].type;
            }
            return types;
        } else {
            return null;
        }
    }
    
}
