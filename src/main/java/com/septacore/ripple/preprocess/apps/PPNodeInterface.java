package com.septacore.ripple.preprocess.apps;

import com.septacore.ripple.preprocess.PPBase;
import com.septacore.ripple.preprocess.PPError;
import com.septacore.ripple.preprocess.expressions.PPExprTable;
import com.septacore.ripple.preprocess.PPVariableTable;
import org.antlr.runtime.tree.CommonTree;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: Vulkum
 * Date: 5/22/14
 * Time: 3:26 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public interface PPNodeInterface {
    public PPBase gen(CommonTree node, PPVariableTable varTable, PPExprTable nodeTable) throws PPError;
}
