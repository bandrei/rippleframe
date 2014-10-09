/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.septacore.ripple.preprocess;

import com.septacore.ripple.preprocess.types.PPType;
import org.antlr.runtime.tree.CommonTree;


public abstract class PPError extends Exception {

    public PPError(Throwable cause) {
        super(cause);
    }
    public PPError(String message) {
        super(message);       
    }
    public PPError(String message, Throwable cause) {
        super(message, cause);
    }
    public static class PPParseError extends PPError {

        public PPParseError(String message) {
            super(message);
        }

        public PPParseError(String message, Throwable cause) {
            super(message, cause);
        }
        
    }
    public static class PPSemanticError extends PPError {
        public PPSemanticError(String message) {
            super(message);
        }
        public PPSemanticError(String message, PPError inner) {
            super(String.format("%s - %s", message, inner.getMessage()), inner);
        }
    }
    public static class PPTypeError extends PPSemanticError {
        public PPTypeError(PPType type, PPType newType) {
            super("Type error: " + type.toString() + " cannot be cast to " + newType.toString());
        }
    }
    public static class PPNodeError extends PPError {

        public PPNodeError(CommonTree node, String message) {
            super(String.format("Line: %d Col: %d - %s", node.getLine(),node.getCharPositionInLine(),message));
        }
        public PPNodeError(CommonTree node, PPError inner) {
            super(String.format("Line: %d Col: %d - %s", node.getLine(),node.getCharPositionInLine(),inner.getMessage()), inner);
        }
    }
}
