package com.spike.lexer;

/*
    A Num is child of Token
 */
public class Num extends Token {
    public final Integer value;

    public Num(int v) {
        super(Tag.NUM, v);
        value = v;
    }

    @Override
    public String toString() {
        return "Tag: "+super.getTag()+" value: "+value;
    }
}