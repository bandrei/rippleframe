package com.septacore.ripple.node.impl;

import com.septacore.ripple.node.*;
import com.septacore.ripple.preprocess.types.PPTypeDouble;


public abstract class MachineLearningBox extends Box<MachineLearningBox.MLBoxMode> {
    
    public static enum MLBoxMode {
        //Offline, - represented by null
        ClassifyLivePackets,
        TrainFromLivePackets
    }
    
    protected final BoxConfig config;
    
    
    protected MachineLearningBox(BoxConfig config, BoxPin[] inputPins, boolean skipNulls) {
        super(inputPins, new BoxPin[] {new BoxPin(new PPTypeDouble(), "MLResult")}, "MachineLearningBox", MachineLearningBox.MLBoxMode.class, skipNulls);
        this.config = config;
    }

    @Override
    protected final void startupOrSwitch(BoxMode newMode, MLBoxMode newConnectedMode, BoxMode prevMode, MLBoxMode prevConnectedMode) throws com.septacore.ripple.node.BoxError {
        try {
            MLstartupOrSwitch(newMode == BoxMode.Connected ? newConnectedMode : null,
                    prevMode == BoxMode.Connected ? prevConnectedMode : null );
        } catch (BoxError ex) {
            throw new com.septacore.ripple.node.BoxError.DGMLBoxError(ex);
        }
    }
    

    @Override
    protected final void shutdown(boolean afterError, MLBoxMode finishingMode) throws com.septacore.ripple.node.BoxError {
        try {
            MLshutdown(afterError, finishingMode);
        } catch (BoxError ex) {
            throw new com.septacore.ripple.node.BoxError.DGMLBoxError(ex);
        }
    }

    @Override
    protected final Object[] execStep(Object[] input, MLBoxMode currentMode) throws com.septacore.ripple.node.BoxError, InterruptedException {
        try {
            return new Object[] { MLexecStep(input, currentMode) };
        } catch (BoxError ex) {
            throw new BoxError.DGMLBoxError(ex);
        }
    }
    
    /**
     * We want the return type to be final.
     * @return 
     */
    public abstract String[] getRequiredAttributeNames();
    
    /**
     * The main worker function of an MLBox mapping an MLInput to an MLResult
     * Preprocessor function calls inside this function
     * @param input
     * @return 
     */
    protected abstract Double MLexecStep(Object[] input, MLBoxMode currentMode) throws BoxError;
    
    
    /**
     * Startup the MLBox or switch the MLBox into another mode
     * @throws BoxError
     */
    protected abstract void MLstartupOrSwitch(MLBoxMode newMode, MLBoxMode prevMode) throws BoxError;
    
    /**
     * Called on shutdown, so the MLBox can save state
     * @param afterError
     * @throws BoxError
     */
    protected abstract void MLshutdown(boolean afterError, MLBoxMode finishingMode) throws BoxError;
    
}
