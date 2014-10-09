package com.septacore.ripple.preprocess.expressions;

import com.septacore.ripple.preprocess.PPBase;

import java.util.HashMap;
import java.util.Map;


public class PPExprTable {
    private final Map<String,PPBase> nodeMap;
    public PPExprTable() {
        nodeMap = new HashMap<String, PPBase>();
    }
    public PPBase get(String expr) {
        return nodeMap.get(expr);
    }
    public void put(String expr, PPBase node) {
        if (nodeMap.containsKey(expr))
            throw new RuntimeException ("Expression already defined!");
        nodeMap.put(expr, node);
    }
}