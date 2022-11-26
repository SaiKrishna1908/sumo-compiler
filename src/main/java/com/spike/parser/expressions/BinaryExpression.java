package com.spike.parser.expressions;

import com.spike.exceptions.UnSupportedOperatorException;
import com.spike.lexer.Tag;
import com.spike.lexer.Token;
import com.spike.lexer.Word;

public class BinaryExpression extends Expression {
    private NumberExpression left;
    private Token operator;
    private NumberExpression right;

    public BinaryExpression(NumberExpression left, Token operator, NumberExpression right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public Tag getTag() {
        return Tag.BINARY_EXPRESSION;
    }

    public int evaluate() throws UnSupportedOperatorException {
        int leftValue = left.getValue();
        int rightValue  = right.getValue();

        String operator = ((Word) this.operator).lexeme;
        switch (operator) {
            case "+": return leftValue + rightValue;
            case "-": return leftValue - rightValue;
        }

        throw new UnSupportedOperatorException(String.format("Unknown operator %s", operator));
    }
}