package com.spike.parser.expressions;

import com.spike.lexer.Num;
import com.spike.lexer.Tag;
import com.spike.lexer.Token;

public class NumberExpression extends Expression {

    private final Tag tag = Tag.NUM;
    private final Token token;

    public NumberExpression(int value) {
        this.token = new Num(value);
    }

    @Override
    public Tag getTag() {
        return this.tag;
    }

    protected int getValue() {
        return ((Num) this.token).value;
    }
}