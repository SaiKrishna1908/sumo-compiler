package com.spike.utils;

import com.spike.exceptions.UnSupportedOperatorException;
import com.spike.parser.TokenNode;
import com.spike.parser.expressions.Expression;

public class CompilerUtils {
    public static Integer evaluateParseTree(TokenNode root) throws UnSupportedOperatorException {
        try {
            Expression expression = (Expression) root;
            return (Integer) expression.evaluate();
        } catch (ClassCastException classCastException) {
            System.out.println("Cannot cast to expression type");
        }

        return null;
    }
}
