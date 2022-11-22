package com.spike.lexer;

/*
    A Num is child of Token
 */
public class Num extends Token {
    public final int value;

    public Num(int v) {
        super(Tag.NUM);
        value = v;
    }

    @Override
    public String toString() {
        return "Tag: "+super.tag+" value: "+value;
    }
}