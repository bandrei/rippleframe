package com.septacore.ripple.preprocess.expressions;

import com.septacore.ripple.preprocess.PPBase;
import com.septacore.ripple.preprocess.PPError;
import com.septacore.ripple.preprocess.PPVariableTable;
import com.septacore.ripple.preprocess.apps.PPNodeInterface;
import org.antlr.runtime.tree.CommonTree;
import com.septacore.ripple.preprocess.PPError.PPNodeError;
import com.septacore.ripple.preprocess.PPError.PPSemanticError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Creates PPBase expression trees from antlr ASTs
 * @author rory
 */
@Component
public class PPExpressionGen {

    @Autowired
    private ApplicationContext context;

    public PPBase visitGen(CommonTree node, PPVariableTable varTable, PPExprTable nodeTable) throws PPError {
            try {
                PPNodeInterface genNode = null;
                PPBase genBase = null;
                final String genExpr = node.toStringTree();
                try {
                    genNode = (PPNodeInterface)context.getBean(node.getText());
                    //genNode = PPNode.valueOf(node.getText());
                } catch (IllegalArgumentException ex) {
                    throw new PPNodeError(node, "Unknown token :" + node.getText());
                }
                if ((genBase = nodeTable.get(genExpr)) == null) {
                    genBase = genNode.gen(node, varTable, nodeTable);
                    nodeTable.put(genExpr, genBase);
                } else {
                    //System.out.println("HIT " + genExpr);
                }
                return genBase;
            } catch (PPSemanticError ex) {
                throw new PPNodeError(node, ex);
            }
    }
}
