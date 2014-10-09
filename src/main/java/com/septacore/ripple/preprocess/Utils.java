package com.septacore.ripple.preprocess;

/**
 * User: Andrei Bara
 * Date: 5/23/14
 */

import com.septacore.ripple.node.BoxConfig;
import com.septacore.ripple.node.BoxError;
import com.septacore.ripple.node.BoxPin;
import com.septacore.ripple.preprocess.types.PPType;
import com.septacore.ripple.preprocess.types.PPTypeRegistry;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Utils class for managing the Type registry and parsing of
 * the pin expressions.
 */
@Component("Utils")
public class Utils {
    private static Logger LOG = Logger.getLogger(Utils.class);

    @Autowired
    private PPTypeRegistry registry;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private PPMain ppMain;

    public PPBase[] parseTuples(String expression, PPType[] types, PPVariableTable variableTable) throws PPError {
        return ppMain.parseTuples(expression,types,variableTable);
    }

    public PPType[] parseTypeList(String[] types) throws PPError.PPParseError {
        return registry.parseTypeList(types);
    }

    public BoxPin[] generatePins(BoxConfig config, boolean areOutputPins) throws BoxError.BoxConfigError {
        String names = "inputPinNames", types = "inputPinTypes";
        if (areOutputPins) {
            names = "outputPinNames";
            types = "outputPinTypes";
        }
        BoxPin[] pins = null;
        try {
            final PPType[] pinTypes;
            final String[] pinNames = config.getConfigMulti(names);
            final String[] pinTypesString = config.getConfigMulti(types);


            if (pinTypesString.length <= 0) {
                throw new BoxError.BoxConfigError("Invalid type string", config);
            }
            try {
                pinTypes = registry.parseTypeList(pinTypesString);
                System.out.println(pinTypes.length);
            } catch (PPError.PPParseError ex) {
                System.out.println("Huh?");
                throw new BoxError.BoxConfigError("Invalid type string", config, ex);
            }

            if (pinTypes.length != pinNames.length) {
                throw new BoxError.BoxConfigError("Invalid pin names", config);
            }

            pins = new BoxPin[pinTypes.length];

            for (int i = 0; i < pinTypes.length; i++) {
                pins[i] = (BoxPin)context.getBean("PinFactory", new Object[]{pinTypes[i], pinNames[i]});
            }
        } catch (NullPointerException ex) {
            LOG.error(String.format("The configuration field for %s could not be found",names));
            pins = new BoxPin[]{};
        }finally {
            return pins;
        }


    }
}

