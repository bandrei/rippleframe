package com.septacore.ripple.preprocess.expressions.nodes;

import com.septacore.ripple.preprocess.*;
import com.septacore.ripple.preprocess.apps.PPNodeInterface;
import com.septacore.ripple.preprocess.PPConstantValue;
import com.septacore.ripple.preprocess.expressions.PPExprTable;
import com.septacore.ripple.preprocess.types.PPTypeDouble;
import org.antlr.runtime.tree.CommonTree;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: Vulkum
 * Date: 5/22/14
 * Time: 3:40 PM
 * To change this template use File | Settings | File Templates.
 */
@Component(value="LITFLOAT")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Litfloat implements PPNodeInterface {
    @Override
    public PPBase gen(CommonTree node, PPVariableTable varTable, PPExprTable nodeTable) throws PPError {
        if (node.getChild(0) == null) throw new PPError.PPNodeError(node, "LITFLOAT tree error");
        return new PPConstantValue(new PPTypeDouble(), Double.parseDouble(node.getChild(0).getText()));
    }
}
