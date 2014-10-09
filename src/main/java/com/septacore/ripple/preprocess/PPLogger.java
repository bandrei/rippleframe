package com.septacore.ripple.preprocess;


import java.util.ArrayList;

    
/**
* Static class used to log errors
*/
public class PPLogger {
    public static void reset() {
        getInstance().logList.clear();
    }
    public static boolean empty() {
        return getInstance().logList.isEmpty();
    }
    public static void log(int line, int col) {
        log (line, col, "Unknown error");
    }
    public static void log(int line, int col, String msg) {
        getInstance().addEntry(line, col, msg);
    }
    public static String getLog() {
        return getInstance().toString();
    }
    
    private static PPLogger instance = null;
    private static PPLogger getInstance() {
        if (instance == null) {
            instance = new PPLogger();
        }
        return instance;
    }
    private ArrayList<PPLogEntry> logList;
    private PPLogger() {
        logList = new ArrayList<PPLogEntry>();
    }
    private void addEntry(int line, int col, String msg) {
        logList.add(new PPLogEntry(line,col,msg));
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (PPLogEntry entry : logList) {
            out.append(entry.toString());
            out.append("\n");
        }
        return out.toString();
    }
    
    private class PPLogEntry {
        private final int line;
        private final int col;
        private final String msg;
        public PPLogEntry(int line, int col, String msg) {
            this.line = line;
            this.col = col;
            this.msg = msg;
        }
        @Override
        public String toString() {
            return String.format("Line: %d Col: %d - %s", line,col,msg);
        }

        public int getLine() {
            return line;
        }
        public int getCol() {
            return col;
        }
        public String getMsg() {
            return msg;
        }
        
        
    }
}
