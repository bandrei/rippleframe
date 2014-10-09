package com.septacore.ripple.preprocess.expressions.nodes;

import com.septacore.ripple.preprocess.PPBase;
import com.septacore.ripple.preprocess.PPError;
import com.septacore.ripple.preprocess.expressions.PPExprTable;
import com.septacore.ripple.preprocess.PPVariableTable;
import com.septacore.ripple.preprocess.apps.PPNodeInterface;
import org.antlr.runtime.tree.CommonTree;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: Vulkum
 * Date: 5/22/14
 * Time: 3:41 PM
 * To change this template use File | Settings | File Templates.
 */
@Component(value="IDENT")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Ident implements PPNodeInterface {

    @Override
    public PPBase gen(CommonTree node, PPVariableTable varTable, PPExprTable nodeTable) throws PPError {
        if (node.getChild(0) == null) throw new PPError.PPNodeError(node, "IDENT tree error");
        try {
            return varTable.get(node.getChild(0).getText());
        } catch (PPError.PPSemanticError ex) {
            throw new PPError.PPNodeError(node, ex);
        }
    }
}
