package com.spike.lexer;

import java.util.Hashtable;
import java.util.List;

/*
    The role of the lexer is to break the source code into tokens or "lexemes".
    The properties of lexemes are defined by the developer.
 */
public class Lexer {
    public int line = 1;
    private char peek = ' ';

    private int currentPosition = 0;

    private final String sentence;

    Hashtable<String, Word> wordHashtable = new Hashtable<>();

    // routine to add keywords
    void reserveKeywords(Word w) {
        wordHashtable.put(w.lexeme, w);
    }

    /*
      Initialize lexer with reserved keywords
     */
    public Lexer(String sentence) {
        this.sentence = sentence;
        reserveKeywords(new Word(Tag.TRUE, "true"));
        reserveKeywords(new Word(Tag.FALSE, "false"));
    }


    private char readNextCharacter(String sentence, int idx) {
        char[] charArray = sentence.toCharArray();
        if (idx <= charArray.length - 1) {
            return charArray[idx];
        }

        return '\0';
    }

    public Token scan() {
        final char[] charArray = sentence.toCharArray();

        while (currentPosition < charArray.length) {
            peek = charArray[currentPosition];
            if (peek == ' ' || peek == '\t') {
                currentPosition++;
            } else if (peek == '\n') {

                currentPosition++;
                line++;

            } else if (Character.isDigit(peek)) {
                int val = 0;
                do {
                    val = 10 * val + Character.getNumericValue(peek);
                    currentPosition++;
                    peek = readNextCharacter(sentence, currentPosition);
                } while (peek != '\0' && Character.isDigit(peek));

                return new Num(val);
            } else if (Character.isLetter(peek)) {
                StringBuilder stringBuilder = new StringBuilder();
                do {
                    stringBuilder.append(peek);
                    currentPosition++;
                    peek = readNextCharacter(sentence, currentPosition);
                } while (peek != '\0' && Character.isAlphabetic(peek));

                String wordString = stringBuilder.toString();

                Word hashTableWord = wordHashtable.get(wordString);

                if (hashTableWord != null) {
                    return hashTableWord;
                }

                hashTableWord = new Word(Tag.ID, wordString);

                wordHashtable.put(wordString, hashTableWord);

                return new Word(Tag.OTHER,stringBuilder.toString());

            } else if (peek == '<' || peek == '=' || peek == '!' || peek == '>') {
                StringBuilder stringBuilder = new StringBuilder();

                do {
                    stringBuilder.append(peek);
                    currentPosition++;
                    peek = readNextCharacter(sentence, currentPosition);
                } while (isPartRelOp(peek));

                if (List.of("<=", ">=", "<", ">", "==", "!=").contains(stringBuilder.toString())) {
                    return new Word(Tag.REL_OP, stringBuilder.toString());
                }
                return new Word(Tag.OTHER, stringBuilder.toString());
            } else if (peek == '+' || peek == '-' || peek == '*' || peek == '/') {
                currentPosition++;
                return new Word(Tag.BINARY_OP, String.valueOf(peek));
            } else {
                currentPosition++;
                Token t = new Token(Tag.BAD_TOKEN);
                peek = ' ';
                return t;
            }
        }

        return new Token(Tag.EOF);
    }

    private boolean isPartRelOp(char c) {
        return List.of('>', '<', '!', '=').contains(c);
    }
}