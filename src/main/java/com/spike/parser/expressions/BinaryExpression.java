package com.spike.parser.expressions;

import com.spike.exceptions.UnSupportedOperatorException;
import com.spike.lexer.Tag;
import com.spike.lexer.Token;
import com.spike.lexer.Word;
import com.spike.parser.TokenNode;

import java.util.List;

public class BinaryExpression extends Expression {
    private final Expression left;
    private final Token operator;
    private final Expression right;

    public BinaryExpression(Expression left, Token operator, Expression right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }


    @Override
    public Token getToken() {
        return operator;
    }

    @Override
    public List<TokenNode> getChildren() {
        return List.of(left,right);
    }

    @Override
    public Integer evaluate() throws UnSupportedOperatorException {
        Integer leftValue = (Integer) left.evaluate();
        Integer rightValue  = (Integer) right.evaluate();

        String operator = ((Word) this.operator).lexeme;
        switch (operator) {
            case "+": return leftValue + rightValue;
            case "-": return leftValue - rightValue;
            case "*": return leftValue * rightValue;
            case "/": return leftValue / rightValue;
        }

        throw new UnSupportedOperatorException(String.format("Unknown operator %s", operator));
    }
}