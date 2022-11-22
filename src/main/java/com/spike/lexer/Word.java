package com.spike.lexer;

public class Word extends Token {

    public final String lexeme;

    /*
         Tag -> NUM, ID, TRUE, FALSE
         lexeme -> identifier
     */
    public Word(Tag tag, String word) {
        super(tag);
        this.lexeme = word;
    }

    @Override
    public String toString() {
        return "Tag: "+ super.tag+" Lexeme: "+ lexeme;
    }
}