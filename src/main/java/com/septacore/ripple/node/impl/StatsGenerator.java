/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.septacore.ripple.node.impl;

import com.septacore.ripple.node.Box;
import com.septacore.ripple.node.BoxError;
import com.septacore.ripple.node.BoxPin;

import java.util.ArrayList;

/**
 * Tool for generating statistics with regards to the received packets.
 * @author andrei
 */
public class StatsGenerator extends Box<StatsGenerator.StatsGeneratorMode> {

    private int threats = 0;
    private int nonThreats = 0;
    private int totalPackets = 0;
    private ArrayList<Object[]> packetsWithThreats;
    
    protected static int globalInc = 1;

    public StatsGenerator(BoxPin[] inputTypes, boolean skipNulls) {
        super(inputTypes, new BoxPin[0], "StatsGenerator", StatsGeneratorMode.class, skipNulls);
        //this.inPins = inputTypes;
        // this.silent = silent;
        packetsWithThreats = new ArrayList<Object[]>();
        this.displayName = "Stats Generator " + (globalInc++);

    }

    protected void resetGenerator() {
        packetsWithThreats.clear();
    }

    protected void printResults() {
        System.out.println("===========================================");
        System.out.println("Packets analyzed: " + totalPackets);
        System.out.println("Threats detected: " + threats);
        System.out.println("Classified as non-threats: " + nonThreats);
        System.out.println("Packets that have been identified as threats:");
        for (Object[] packet : packetsWithThreats) {
            for (Object p : packet) {
                System.out.print((String) p + ", ");
            }
            System.out.print(";");
            System.out.println();
        }
        System.out.println("===========================================");
    }

    @Override
    protected void startupOrSwitch(BoxMode newMode, StatsGeneratorMode newConnectedMode, BoxMode prevMode, StatsGeneratorMode prevConnectedMode) throws BoxError {
        if (newConnectedMode != null) {
            switch (newConnectedMode) {
                case ResetData:
                    resetGenerator();
                    break;
                case PrintResults:
                    printResults();
                    break;
                default:
                    break;
            }
        }
        //super.startupOrSwitch(newMode, newConnectedMode, prevMode, prevConnectedMode);
    }

    @Override
    protected void shutdown(boolean afterError, StatsGeneratorMode finishingMode) throws BoxError {
    }

    @Override
    protected Object[] execStep(Object[] input, StatsGeneratorMode currentMode) throws BoxError, InterruptedException {
        Double evaluation = (Double) input[0];
        if (evaluation != null) {
            if (evaluation == 1.0) {
                nonThreats++;
            } else {
                threats++;
                Object[] info = new Object[input.length - 1];
                for (int i = 1; i < input.length; i++) {
                    info[i - 1] = input[i];
                }
                packetsWithThreats.add(info);
            }

            totalPackets++;
        }

        return new Object[]{};
        //return super.execStep(input, currentMode);
    }

    public enum StatsGeneratorMode {
        ResetData,
        PrintResults
    }
}
