/*
 * Started by Rory Allford (rda10)
 */
package com.septacore.ripple.node;


public abstract class BoxError extends Exception {
    public BoxError(Throwable cause) {
        super(cause);
    }
    public BoxError(String message) {
        super(message);       
    }
    public BoxError(String message, Throwable cause) {
        super(message, cause);
    }
    /**
     * Run time error in the data graph
     */
    public static class BoxRuntimeError extends BoxError {

        public BoxRuntimeError(String message) {
            super(message);
        }

        public BoxRuntimeError(String message, Throwable cause) {
            super(String.format("%s :\n%s", message, cause.getMessage()), cause);
        }

        public BoxRuntimeError(Throwable cause) {
            super(cause);
        }
        
    }
    
    public static class BoxDispatcherError extends BoxError {
        public BoxDispatcherError(String message){
            super(message);
        }
        
        public BoxDispatcherError(Throwable cause) {
            super(cause);
        }
    }
    
    public static class DGMLBoxError extends BoxError {
        public DGMLBoxError(String message){
            super(message);
        }
        
        public DGMLBoxError(Throwable cause) {
            super(cause);
        }
    }
    
    /**
     * Data graph pin error
     * @see BoxPin
     */
    public static class BoxPinError extends BoxError {

        public BoxPinError(String message, BoxPin pin) {
            super(String.format("Pin error in '%s' :\n%s", pin.pinName, message));
        }

        public BoxPinError(String message, BoxPin pin, Throwable cause) {
            super(String.format("Pin error in '%s' :\n%s :\n%s", pin.pinName, message, cause.getMessage()), cause);
        }
        
    }
    public static class BoxPinParseError extends BoxError {
        public BoxPinParseError(int pinIndex, String message) {
            super(String.format("Pin expression parse error at index %d :\n%s", pinIndex, message));
        }
        public BoxPinParseError(int pinIndex, String message, Throwable cause) {
            super(String.format("Pin expression parse error at index %d :\n%s :\n%s", pinIndex, message, cause.getMessage()), cause);
        }
    }

    public static class BoxConfigError extends BoxError {

        public BoxConfigError(String message, BoxConfig config) {
            super(String.format("Config error in '%s' :\n%s", config.getFilename(), message));
        }

        public BoxConfigError(String message, BoxConfig config, Throwable cause) {
            super(String.format("Config error in '%s' :\n%s :\n%s", config.getFilename(), message, cause.getMessage()), cause);
        }

    }

    public static class MLRuntimeError extends BoxError {

        public MLRuntimeError(String message) {
            super(message);
        }

        public MLRuntimeError(String message, Throwable cause) {
            super(String.format("%s :\n%s", message, cause.getMessage()), cause);
        }

        public MLRuntimeError(Throwable cause) {
            super(cause);
        }

    }
}