package com.septacore.ripple.preprocess.algos;

import com.septacore.ripple.preprocess.apps.PPAppBase;
import com.septacore.ripple.preprocess.types.PPType;
import com.septacore.ripple.preprocess.types.PPTypeString;

/**
 * getqs(qs:string)
 * Get query string
 */
public class QueryString extends PPAppBase {
    public QueryString() {
        super(new PPTypeString(), new PPType[] {new PPTypeString()});
    }
    private static  String getQueryString(String q) {
        return q.substring(q.indexOf('?') + 1);
    }
    @Override
    public Object applyPreprocessor(Object[] argVals) {
        return (Object) getQueryString((String)argVals[0]);
    }
}
