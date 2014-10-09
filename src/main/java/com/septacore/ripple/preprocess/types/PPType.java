package com.septacore.ripple.preprocess.types;

import java.awt.Color;

import com.septacore.ripple.preprocess.PPBase;
import com.septacore.ripple.preprocess.PPError.PPSemanticError;

public abstract class PPType {

    public Color guiColor() {
        return Color.WHITE;
    }
    
    public abstract PPBase cast(PPType newType, PPBase value) throws PPSemanticError;
    public abstract Object defaultValue();

    @Override
    public String toString(){
        return this.getClass().getSimpleName();
    }
}
