package com.spike.lexer;

import com.spike.exceptions.ReadException;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

/*
    The role of the lexer is to break the source code into tokens or "lexemes".
    The properties of lexemes are defined by the developer.
 */
public class Lexer {
    public int line = 1;
    private char peek = ' ';

    Hashtable<String, Word> wordHashtable = new Hashtable<>();

    // routine to add keywords
    void reserveKeywords(Word w) {
        wordHashtable.put(w.lexeme, w);
    }

    /*
      Initialize lexer with reserved keywords
     */
    public Lexer() {
        reserveKeywords(new Word(Tag.TRUE, "true"));
        reserveKeywords(new Word(Tag.FALSE, "false"));
    }

    private char readChar() {
        try {
            return (char) System.in.read();
        } catch (IOException e) {
            throw new ReadException("Error reading a symbol from input stream");
        }
    }


    public Token scan() throws IOException {
        for (; ; peek = (char) System.in.read()) {
            if (peek == ' ' || peek == '\t') {
                continue;
            } else if (peek == '\n' || isComment()) {
                line++;
            } else if (Character.isDigit(peek)) {
                int val = 0;
                do {
                    val = 10 * val + Character.getNumericValue(peek);
                    peek = (char) System.in.read();
                } while (Character.isDigit(peek));

                return new Num(val);
            } else if (Character.isLetter(peek)) {
                StringBuilder stringBuilder = new StringBuilder();
                do {
                    stringBuilder.append(peek);
                    peek = (char) System.in.read();
                } while (Character.isAlphabetic(peek));

                String wordString = stringBuilder.toString();

                Word hashTableWord = wordHashtable.get(wordString);

                if (hashTableWord != null) {
                    return hashTableWord;
                }

                hashTableWord = new Word(Tag.ID, wordString);

                wordHashtable.put(wordString, hashTableWord);

                return hashTableWord;
            } else if (peek == '<' || peek == '=' || peek == '!' || peek == '>') {
                StringBuilder stringBuilder = new StringBuilder();

                do {
                    stringBuilder.append(peek);
                    peek = readChar();
                } while (isPartRelOp(peek));

                if (List.of("<=", ">=", "<", ">", "==", "!=").contains(stringBuilder.toString())) {
                    return new Word(Tag.REL_OP, stringBuilder.toString());
                }
                return new Word(Tag.OTHER, stringBuilder.toString());
            } else {
                Token t = new Token(Tag.BAD_TOKEN);
                peek = ' ';
                return t;
            }
        }
    }

    private boolean isPartRelOp(char c) {
        return List.of('>', '<', '!', '=').contains(c);
    }

    private boolean isComment() {
        if (peek == '/') {
            peek = readChar();
            return peek == '/';
        }

        return false;
    }
}