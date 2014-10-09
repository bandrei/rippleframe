
package com.septacore.ripple.node;


import java.util.Map;


public class BoxConfig {
   
    private String filename;
    private Map<String, String> properties;

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }
    
    public BoxConfig() {
       
    }
    
    public String getConfigDefault(String key, String strDefault) {
        String val = properties.get(key);
        if (val == null) {
            return strDefault;
        } else {
            return val;
        }
    }
    
    public String getConfig(String key) throws  NullPointerException{
        String val = properties.get(key);
        if (val == null) {
            throw new NullPointerException("No such config key");
        }
        return val;
    }
    
    public final String[] getConfigMulti(String key) {
        String val = getConfig(key);
        String[] params = val.split(";");
        
        for (int i = 0; i < params.length; i++) {
            params[i] = params[i].trim();
        }
        return params;
    }
    
    
    /**
     * @return the filename from which the configuration were loaded
     */
    public String getFilename() {
        return filename;
    }
    
}
