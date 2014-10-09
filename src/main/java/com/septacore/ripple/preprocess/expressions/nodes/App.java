package com.septacore.ripple.preprocess.expressions.nodes;

import com.septacore.ripple.preprocess.*;
import com.septacore.ripple.preprocess.apps.PPAppBase;
import com.septacore.ripple.preprocess.apps.PPAppRegistry;
import com.septacore.ripple.preprocess.apps.PPNodeInterface;
import com.septacore.ripple.preprocess.expressions.PPExprTable;
import com.septacore.ripple.preprocess.expressions.PPExpressionGen;
import org.antlr.runtime.tree.CommonTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Vulkum
 * Date: 5/22/14
 * Time: 3:23 PM
 * To change this template use File | Settings | File Templates.
 */
@Component(value="APP")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class App implements PPNodeInterface {

    @Autowired
    private PPExpressionGen expressionGen;

    @Autowired
    private PPAppRegistry appRegistry;

    @Override
    public PPBase gen(CommonTree node, PPVariableTable varTable, PPExprTable nodeTable) throws PPError {
        int c = node.getChildCount();
        if (c > 1) {
            CommonTree appName = (CommonTree) node.getChild(0);
            if ((appName.getText().compareTo("PPID") == 0) && (appName.getChild(0) != null)) {

                String appNameStr = appName.getChild(0).getText();


                PPAppBase app = null;
                try {
                    app = appRegistry.getFunction(appNameStr).create();
                } catch (IllegalArgumentException ex) {
                    throw new PPError.PPNodeError(appName, "No such preprocessor '" + appNameStr + "'");
                }

                ArrayList<PPBase> argList = new ArrayList<PPBase>();
                CommonTree arg;
                for (int i = 1; i < c; i++) {
                    arg = (CommonTree)node.getChild(i);
                    argList.add(expressionGen.visitGen(arg,varTable, nodeTable));
                }
                try {
                    app.bind(argList.toArray(new PPBase[0]));
                } catch (PPError.PPSemanticError ex) {
                    throw new PPError.PPNodeError(node, new PPError.PPSemanticError(appNameStr, ex));
                }
                return app;
            } else {
                throw new PPError.PPNodeError(appName, "Bad preprocessor application");
            }
        } else {
            throw new PPError.PPNodeError(node, "APP tree error");
        }
    }
}
