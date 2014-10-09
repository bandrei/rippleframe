package com.septacore.ripple.preprocess;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
import com.septacore.ripple.preprocess.PPError.PPSemanticError;
import com.septacore.ripple.preprocess.types.PPType;

/**
 * A binding of variable identifers to values
 * Variable names and types must be created before parsing
 * using create() then their values can be modified before each
 * evaluation using set()
 * @author rory
 */
public class PPVariableTable {
    private final LinkedHashMap<String, PPVariable> varMap;

    public PPVariableTable() {
        varMap = new LinkedHashMap<String, PPVariable>();
    }
    
    /**
     * Fast set the whole variable table from an input array
     * PRE: array is of right length!
     * @param values
     * @throws algorithms.MLError.MLRuntimeError 
     */
    public final void setFromArray(Object[] values) { //throws MLRuntimeError {
        int i = 0;
        for (Entry<String, PPVariable> entry : varMap.entrySet()) {
            entry.getValue().setValue(
                    values[i]
                    );
            
            i++;
        }
    }

    public PPVariable get(String varname) throws PPSemanticError {
        varname = varname.toUpperCase();
        if (varMap.containsKey(varname)) {
            return (PPVariable) varMap.get(varname);
        } else {
            throw new PPSemanticError("No such variable: " + varname);
        }
    }

    public void create(String varname, PPType type) {
        varname = varname.toUpperCase();
        if (varMap.containsKey(varname)) {
            throw new RuntimeException("Variable already defined: " + varname);
        }
        varMap.put(varname, new PPVariable(type, type.defaultValue()));
    }

    public void set(String varname, Object value) {
        try {
        PPVariable var = get(varname);
        var.setValue(value);
        } catch (PPError ex) {
            throw new RuntimeException(ex);
        }
    }
    
}
