package com.spike.lexer;

/*
    A token contains a tag, which is used to identify which type of value this token points to.
    Every possible token should extend this class
 */
public class Token {
    public final Tag tag;

    public 	Token(Tag tag) {
        this.tag = tag;
    }

    public Token(int ascii) {
        this.tag = Tag.OTHER;
    }

    public Token(String token) {
        this.tag = Tag.OTHER;
    }
}