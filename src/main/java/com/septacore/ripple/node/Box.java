package com.septacore.ripple.node;

import com.septacore.ripple.node.BoxError.BoxPinError;
import java.util.Collection;
import java.util.LinkedHashMap;

import com.septacore.ripple.preprocess.PPError;
import org.apache.log4j.Logger;

/**
 * An implementation of Kahn process nodes.
 * <P>
 * Base class for a node in the Data Graph
 * <P>
 * Runs on its own thread
 * <P>
 * Contains an output queue for each output value
 * <P>
 * Each input is mapped to an output queue from another Box
 * <P>
 * Input and output queues are identified by BoxPins
 * 
 * @see BoxPin
 *
 */
public abstract class Box<SubMode> implements Runnable{
   
    
    /**
     * Input pins
     * <P>
     * References to pins in other instances
     */
    protected final LinkedHashMap<BoxPin, PinSink> inputPins;
    
    /**
     * Output pins
     * <P>
     * Exist in this instance
     */
    protected final LinkedHashMap<BoxPin, PinSource> outputPins;
    
    /**
     * SQL-style null semantics
     */
    protected final boolean skipNulls;
    
    /**
     * Implementation dependent mode
     */
    protected final Class<SubMode> connectedModeType;
    
    protected String displayName;

    private static Logger LOG =Logger.getLogger(Box.class);

    /**
     * Implementation called superclass constructor
     * @param inputTypes
     * The list of input pins
     * @param outputTypes
     * The list of output pins
     * @param displayName
     * GUI display name
     * @param connectedModeType
     * The type of the SubMode generic parameter (convenience method)
     * @param skipNulls 
     * SQL-style null semantics enabled
     */
    protected Box(BoxPin[] inputTypes, BoxPin[] outputTypes, String displayName, Class<SubMode> connectedModeType, boolean skipNulls) {
        this.skipNulls = skipNulls;
        this.displayName = displayName;
        this.connectedModeType = connectedModeType;
        

        
        int j = 2;
        
        // Set up the pins:
        this.inputPins = new LinkedHashMap<BoxPin, PinSink>(inputTypes.length);
        for (int i = 0; i < inputTypes.length; i++, j++) {
            this.inputPins.put(inputTypes[i], new PinSink(inputTypes[i]));
        }
        
        this.outputPins = new LinkedHashMap<BoxPin, PinSource>(outputTypes.length);
        for (int i = 0; i < outputTypes.length; i++, j++) {
            this.outputPins.put(outputTypes[i], new PinSource(outputTypes[i]));
        }
        

        //this.setName(displayName);
        
    }
    

    public void setInputPin(BoxPin pin, PinSource valueSource) throws BoxPinError {
        synchronized (inputPins) {
            PinSink valueSink = getInputPin(pin);
            try {
                valueSink.connectSource(valueSource);
            } catch (PPError.PPSemanticError ex) {
                throw new BoxPinError("Semantic Error", pin, ex);
            }
            // Update the connected / disconnected mode
            updateMode();
            //this.interrupt();
        }
    }
    
    /**
     * Enumerate the input pins
     * @return 
     * Array of input pins
     */
    public BoxPin[] listInputPins() {
        synchronized(inputPins) {
            return inputPins.keySet().toArray(new BoxPin[0]);
        }
    }
    

    public PinSource getOutputPin(BoxPin pin) throws BoxPinError {
        synchronized (outputPins) {
            PinSource valueSource = this.outputPins.get(pin);
            if (valueSource == null) {
                throw new BoxPinError("No such output pin", pin);
            }
            return valueSource;
        }
    }
    
    /**
     * Enumerate the output pins
     * @return 
     * Array of output pins
     */
    public BoxPin[] listOutputPins() {
        synchronized(outputPins) {
            return outputPins.keySet().toArray(new BoxPin[0]);
        }
    }
    
    /**
     * Set the implementation mode
     * @param newMode 
     * SubMode that is subclass dependent
     */
    public void setMode(SubMode newMode) {
        if (this.cmode_v != newMode) {
            this.cmode_v = newMode;
           // this.interrupt();
        }
    }
    
    /**
     * Return the implementation mode
     * @return 
     * SubMode that is subclass dependent
     */
    public SubMode getMode() {
        if (this.mode_v == BoxMode.Connected) {
            return this.cmode_v;
        } else {
            return null;
        }
    }
    
    /**
     * Return the implementation mode type 
     * @return 
     * Type of SubMode
     */
    public final Class<SubMode> getConnectedModeType() {
        return connectedModeType;
    }
    
    
    /**
     * Shut down the Box and blocks until completed
     */
    @Deprecated
    public void waitForShutdown() {
        if (running) {
            running = false;
        }
    }
    
    //////////////////////////////////////////////
    /// Thread code //////////////////////////
    /**
     * Thread state: Box running
     */
    private volatile boolean running = false;
    
    /**
     * Thread state: pin mode
     */
    private volatile BoxMode mode_v = BoxMode.Disconnected;
    
    /**
     * Thread state: implementation defined mode
     */
    private volatile SubMode cmode_v = null;
    
    /**
     * Thread state: GUI hooks enabled
     */
    private volatile boolean guiLogEnabled = false;
    

    /**
     * Check the inputPins and update the mode as appropriate
     * <P>
     * WARNING: not thread safe, must synchronize on inputPins.
     */
    private void updateMode() {
        Collection<PinSink> inputs = inputPins.values();
        for (PinSink inputPin: inputs) {
            if (inputPin.notConnected()) {
                mode_v = BoxMode.Disconnected;
                return;
            }
        }
        // POST: all pins must be connected:
        mode_v = BoxMode.Connected;
    }
    

    private PinSink getInputPin(BoxPin pin) throws BoxPinError {
        PinSink valueSink = this.inputPins.get(pin);
        if (valueSink == null) {
            throw new BoxError.BoxPinError("No such input pin", pin);
        }
        return valueSink;
    }

    /**
     * Main thread loop:
     * <P>
     * PRE: thread.start() is only ever called once
     * <P>
     * The main loop flow contains Interruptible sections
     * broken down into stages.
     * <P>
     * InterruptedException can be thrown by
     * a DoubleType of underlying blocking operations. This denotes that some
     * thread state such as mode has changed meanwhile and we must 
     * re-check such statuses.
     * <P>
     * Hence each stage keeps track of its completion somehow and therefore
     * can be interrupted without side effects.
     */
    @Override
    public void run() {
        /**
         * Local array copy of outputPins and inputPins, removing 
         * the need for synchronized{} blocks:
         */
        Collection<PinSink> inputs = inputPins.values();
        Collection<PinSource> outputs = outputPins.values();
        
        /**
         * Thread execution mode:
         */
        BoxMode mode = BoxMode.Disconnected;
        SubMode cmode = null;
        
        /**
         * Error flag for shutdown condition tracing:
         */
        boolean errorFlag = false;
        /**
         * The current sequence number (monotonic)
         */
        int curSeqNo = 0;
        /**
         * Buffer of output:
         * Initial null value is important (see below)
         */
        Object[] output = null;
        
        /**
         * Buffer of input:
         * Initial null value is important (see below)
         */
        Object[] input = null;
        
        boolean skipNext;
        
        BoxMode prevmode;
        SubMode prevcmode;
        
        updateMode();
        
        running = true;
        while(running) {
            try {
                if (mode_v != mode || cmode_v != cmode) {
                    // mode is changing
                    prevmode = mode;
                    mode = mode_v;
                    
                    prevcmode = cmode;
                    cmode = cmode_v;
                    
                    startupOrSwitch(mode, cmode, prevmode, prevcmode);
                }
                
                if (mode == BoxMode.Connected && cmode != null) {
                                        
                    /*
                     * If output is not null we have something produced
                     * that has not been sent so skip Stages 1 & 2
                     */
                    if (output == null) {
                        /**
                         * If input is not null and output is null
                         * we have already read input but not produced 
                         * something from it so skip stage 1
                         */
                        if (input == null) {

                           //STAGE ONE: Retrieve input
                            {
                                /**
                                 * Reassemble a set of values with matching sequence
                                 * numbers into input
                                 */

                                Payload inputVal;
                                
                                int i = 0;
                                
                                int maxSeqNo = curSeqNo;
                                
                                // STAGE ONE (a): Find highest sequence number
                                for (PinSink inputPin: inputs) {
                                    if (inputPin.notConnected()) {
                                        // An input pin was null, update the mode:
                                        updateMode();
                                        throw new InterruptedException("Pin was null");
                                    } else {
                                        inputVal = inputPin.head();
                                        maxSeqNo = Math.max(maxSeqNo, inputVal.sequenceNumber);
                                    }
                                }
                                
                                if (maxSeqNo > curSeqNo) {
                                    LOG.warn(String.format("Skipping %d partial packet(s)", input));
                                    curSeqNo = maxSeqNo;
                                }
                                
                                {
                                    Object[] bufInput = new Object[inputs.size()];
                                
                                
                                    for (PinSink inputPin: inputs) {
                                        if (inputPin.notConnected()) {
                                            // An input pin was null, update the mode:
                                            updateMode();
                                            throw new InterruptedException("Pin was null");
                                        } else {
                                            inputVal = inputPin.head();
                                            if (inputVal.isError()) {
                                                // Poison value, disconnect
                                                inputPin.advance(); // Make sure we consume the poison
                                                setInputPin(inputPin.pin, null);
                                                LOG.info("Disconnect using poison value");
                                                throw new InterruptedException("Pin disconnect");
                                            }
                                            while (inputVal.sequenceNumber < curSeqNo) {
                                                LOG.warn(String.format("Skipping sequence no %d in pin '%s'", inputVal.sequenceNumber, inputPin.pin.pinName));
                                                inputPin.advance();
                                                inputVal = inputPin.head();
                                                if (inputVal.isError()) {
                                                    // Poison value, disconnect
                                                    inputPin.advance(); // Make sure we consume the poison
                                                    setInputPin(inputPin.pin, null);
                                                    LOG.info("Disconnect using poison value");
                                                    throw new InterruptedException("Pin disconnect");
                                                }
                                            }
                                            // POST: inputVal.sequenceNumber >= curSeqNo
                                            // Possibly increment the sequence number:
                                        }
                                        bufInput[i] = inputVal.value;
                                        i++;
                                    }

                                    //STAGE ONE completed

                                    // Notify all Value Sinks we consumed the last value
                                    for (PinSink inputPin: inputs) {
                                        inputPin.advance();
                                    }

                                    input = bufInput;
                                }
                                
                                // POST: input is not null

                            }
                        } // End skip stage 1

                        // STAGE TWO: Process input to output
                        try {
                            
                            
                            skipNext = false;
                            // If skipNulls check if any of the input are nulls:
                            if (skipNulls) {
                                // Check for any null values and if so return all null values:
                                for (int i = 0; i < input.length; i++) {
                                    if (input[i] == null) {
                                        skipNext = true;
                                        break;
                                    }
                                }
                            }
                            
                            if (!skipNext) {
                                // Evaluate:
                                output = execStep(input, cmode);
                            } else {
                                // All Null output:
                                output = new Object[outputs.size()];
                            }

                            //////// STAGE TWO success point:
                            input = null;
                            // Clear the input buffer
                            
                            // POST: output is not null, input is null

                        } catch (BoxError e) {
                            LOG.error("Error while processing execStep",e);
                            // Generate a default (null) value:
                            output = new Object[outputs.size()];
                        }
                    } // End skip stages 1 & 2
                        
                    
                    // STAGE THREE: Push the output:
                    {
                        // Push the output
                       int i = 0;
                        //Collection<PinSource> outputs = outputPins.values();
                        for (PinSource outputPin : outputs) {
                            outputPin.push( new Payload(output[i], curSeqNo));
                            i++;
                        }
                        ////// STAGE THREE success point:
                        
                        // Clear output buffer:
                        output = null;
                        // Sequence number must increase by at least 1
                        curSeqNo++;
                        
                        // Notify all Value sources we pushed the last value
                        for (PinSource outputPin : outputs) {
                            outputPin.advance();
                        }
                        
                        // POST: output is null, input is null
                   }
                    
                } else {
                    //This should be event triggered
                    Thread.sleep(50);

                }
            } catch (BoxError ex) {
                LOG.error("The box has encountered an error",ex);
                errorFlag = true;
                running = false;
            } catch (InterruptedException ex) {
                running=false;
            }
        }
        try {
            shutdown(errorFlag, cmode);

            for (PinSource outputPin : outputs) {
                outputPin.pushPoison(new BoxError.BoxRuntimeError("Shutting down"));
            }
            
        } catch (BoxError ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * The state of the Box
     */
    public static enum BoxMode {
        Disconnected,
        Connected,
    }

    /**
     * Called when a Box is entering a new mode
     * @param newMode
     * The next thread mode
     * @param newSubMode
     * The next implementation mode
     * @param prevMode
     * The previous thread mode
     * @param prevSubMode
     * The previous implementation mode
     * @throws BoxError
     * Some startup error
     */
    protected abstract void startupOrSwitch(BoxMode newMode, SubMode newSubMode,
            BoxMode prevMode, SubMode prevSubMode) throws BoxError;

    /**
     * Called when a Box is shutting down
     * @param afterError
     * Whether this is due to an error condition
     * @param finishingMode
     * The last implementation mode
     * @throws BoxError
     */
    protected abstract void shutdown(boolean afterError, SubMode finishingMode) throws BoxError;
    
    /**
     * Process a given input to an output
     * <P>
     * Certain values may be null which indicates the ValueSource did not
     * produce a value.
     * <P>
     * PRE: input.length == inputPins.size()
     * <P>
     * POST: output.length == outputPins.size()
     * @param input
     * A well formed, well typed input array.
     * @param currentMode
     * The current implementation mode
     * @return
     * An output array of the correct size and types
     * @throws BoxError
     * Some Box error
     * @throws InterruptedException
     * Interruptible operations
     */
    protected abstract Object[] execStep(Object[] input, SubMode currentMode) throws BoxError, InterruptedException;

}
