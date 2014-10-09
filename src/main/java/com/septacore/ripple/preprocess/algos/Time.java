package com.septacore.ripple.preprocess.algos;

import com.septacore.ripple.preprocess.apps.PPAppBase;
import com.septacore.ripple.preprocess.types.PPType;
import com.septacore.ripple.preprocess.types.PPTypeString;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;

public class Time {
    
    private static final int MAX_ENTRIES = 100;
    private static Map<Integer, Integer> sourceConnections = new HashMap<Integer, Integer>();
    private static LinkedList<Integer> ordered100Source = new LinkedList<Integer>();
    private static Map<Integer, Integer> destConnections = new HashMap<Integer, Integer>();
    private static LinkedList<Integer> ordered100Dest = new LinkedList<Integer>();
    
    public static class PPSource100 extends PPAppBase {
        
        public PPSource100() {
            super(new PPTypeString(), new PPType[] {new PPTypeString()});
        }
        
        public static Integer source100(String ip) {
            Integer ipAddress = Integer.parseInt(ip);
            if (!sourceConnections.containsKey(ipAddress)) {
                Integer i = 1;
                sourceConnections.put(ipAddress, i);
            } else {
                Integer i = (Integer) sourceConnections.get(ipAddress);
                i++;
                sourceConnections.put(ipAddress, i);
            }
            ordered100Source.add(ipAddress);
            if (ordered100Source.size() > MAX_ENTRIES) {
                    Integer oldIP = ordered100Source.removeLast();
                    Integer i = (Integer) sourceConnections.get(oldIP);
                    i--;
                    sourceConnections.put(oldIP, i);
            }
        
            return (Integer) sourceConnections.get(ipAddress);
        }
        @Override
        public Object applyPreprocessor(Object[] argVals) {
            return (Object) source100((String)argVals[0]);
        }
        
    }
    
    public static class PPDest100 extends PPAppBase {
        
        public PPDest100() {
            super(new PPTypeString(), new PPType[] {new PPTypeString()});
        }
        
        public static Integer dest100(String ip) {
            Integer ipAddress = Integer.parseInt(ip);
            if (!destConnections.containsKey(ipAddress)) {
                Integer i = 1;
                destConnections.put(ipAddress, i);
            } else {
                Integer i = (Integer) destConnections.get(ipAddress);
                i++;
                destConnections.put(ipAddress, i);
            }
            ordered100Dest.add(ipAddress);
            if (ordered100Dest.size() > MAX_ENTRIES) {
                    Integer oldIP = ordered100Dest.removeLast();
                    Integer i = (Integer) destConnections.get(oldIP);
                    i--;
                    destConnections.put(oldIP, i);
            }
            
            return (Integer) destConnections.get(ipAddress);
        }
        @Override
        public Object applyPreprocessor(Object[] argVals) {
            return (Object) dest100((String)argVals[0]);
        }
    }
}
