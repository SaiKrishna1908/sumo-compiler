package com.spike.lexer;

/*
    A token contains a tag, which is used to identify which type of value this token points to.
    Every possible token should extend this class
 */

public class Token {
    private final Tag tag;
    private final Object value;

    public Token(Tag tag, Object value) {
        this.tag = tag;
        this.value = value;
    }


    public Object getValue() {
        return value;
    }

    public Tag getTag() {
        return tag;
    }

    @Override
    public String toString() {
        return "Tag: "+tag.name();
    }
}