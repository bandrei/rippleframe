package com.septacore.ripple.preprocess.types;

import com.septacore.ripple.preprocess.PPError;
import com.septacore.ripple.preprocess.types.PPType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: Vulkum
 * Date: 5/21/14
 * Time: 10:21 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class PPTypeRegistry {

    @Autowired
    private ApplicationContext context;


    public void setContext(ApplicationContext context) {
        context = context;
    }

    public PPType getType(String type) throws PPError.PPParseError {
        PPType res = null;
        try {
           res = context.getBean(type,PPType.class);
        } catch (Exception ex) {
            throw new PPError.PPParseError("Invalid type name " + type);
        }finally {
            return res;
        }
    }

    public PPType[] parseTypeList(String[] types) throws PPError.PPParseError {
        PPType[] res = new PPType[types.length];
        for (int i = 0; i < types.length; i++) {
            try {
               res[i]=context.getBean(types[i],PPType.class);
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new PPError.PPParseError("Invalid type name " + types[i]);
            }
        }
        return res;
    }
}
