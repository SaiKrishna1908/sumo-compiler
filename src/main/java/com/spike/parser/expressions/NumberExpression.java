package com.spike.parser.expressions;

import com.spike.lexer.Num;
import com.spike.lexer.Tag;
import com.spike.lexer.Token;
import com.spike.parser.TokenNode;

import java.util.List;

public class NumberExpression extends Expression {

    private final Tag tag = Tag.NUM;
    private final Token token;

    public NumberExpression(int value) {
        this.token = new Num(value);
    }

    @Override
    public Token getToken() {
        return token;
    }

    @Override
    public List<TokenNode> getChildren() {
        return List.of();
    }

    @Override
    public Integer evaluate() {
        return ((Num) this.token).value;
    }
}