/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.septacore.ripple.preprocess;

import java.util.ArrayList;

import com.septacore.ripple.preprocess.expressions.PPExprTable;
import com.septacore.ripple.preprocess.expressions.PPExpressionGen;
import com.septacore.ripple.preprocess.parser.*;
import com.septacore.ripple.preprocess.types.PPType;
import org.antlr.runtime.*;
import org.antlr.runtime.tree.CommonTree;
import com.septacore.ripple.preprocess.PPError.PPParseError;
import com.septacore.ripple.preprocess.PPError.PPSemanticError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PPMain {

    @Autowired
    private PPExpressionGen expressionGen;

    public PPBase[] parseTuples(String expression, PPType[] types, PPVariableTable varTable) throws PPError {
        PPBase[] tuples = parseTuples(expression, varTable);
        if (tuples.length != types.length) {
            throw new RuntimeException("Actual and formal tuple lists differ in length");
        }
        PPBase[] ret = new PPBase[tuples.length];
        for (int i = 0; i < tuples.length; i++) {
            try {
            ret[i] = tuples[i].returnType.cast(types[i], tuples[i]);
            } catch (PPSemanticError ex) {
                throw new PPSemanticError("Tuple " + i +" : semantic error",ex);
            }
        }
        return ret;
    }
    
    /**
     * @param expression
     * The tuples expression to parse
     * for example "potato(3,4);99;" will return a <UTF8String,Number> tuple
     * @return 
     * Array of PPBase tuples
     */
    public PPBase[] parseTuples(String expression, PPVariableTable varTable) throws PPError {
        // TODO: accept an inputstream here?
        ArrayList<PPBase> tuples = new ArrayList<PPBase>();
        
        try {
            PPLogger.reset();
            // Parse file
            PPParserLexer lex = new PPParserLexer(new ANTLRStringStream(expression));
            CommonTokenStream tokens = new CommonTokenStream(lex);
            PPParserParser parser = new PPParserParser(tokens);

                PPParserParser.tuple_return t = parser.tuple();


            // Errors encountered while parsing - terminate execution
            if (lex.getNumberOfSyntaxErrors() > 0
            || parser.getNumberOfSyntaxErrors() > 0 || !PPLogger.empty()) {
                throw new PPParseError("Parse errors: \n" + PPLogger.getLog());
            } else {
                CommonTree tree = (CommonTree) t.getTree();
                PPExprTable nodeTable = new PPExprTable();
                int c = tree.getChildCount();
                CommonTree tuple;
                PPBase parsed;
                for (int i = 0 ; i <c; i++) {
                    
                    tuple = (CommonTree) tree.getChild(i);
                    //System.out.println("Parsing: " + tuple.toStringTree());
                    parsed = expressionGen.visitGen(tuple,varTable, nodeTable);
                    parsed.setExprString(tuple.toStringTree());
                    tuples.add(parsed);
                }
                return tuples.toArray(new PPBase[0]);
            }

        } catch (RecognitionException ex) {
            throw new PPParseError("Recognition exception: " + ex.getMessage(), ex);
        }
    }
}
