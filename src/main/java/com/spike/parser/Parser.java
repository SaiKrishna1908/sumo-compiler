package com.spike.parser;

import java.io.IOException;

import com.spike.exceptions.ReadException;
import com.spike.lexer.Lexer;
import com.spike.lexer.Num;
import com.spike.lexer.Tag;
import com.spike.lexer.Token;
import com.spike.lexer.Word;

/*
 * A simple parser
 *
 * Schema
 *
 * expr -> term rest
 * rest -> + term {print('+')} rest
 *      |  - term {print('-')} rest
 *      |  none
 *
 * term -> 0 {print('0')}
 *      |  1 {print('1')}
 *      |  2 {print('2')}
 *      |  3 {print('3')}
 *      |  4 {print('4')}
 *      ...
 *      |  9 {print('9')}
 *
 * Output of the parser
 *
 *
 *  parser outputs a tree, whose nodes contain a non-terminal,
 *  and leaves contain a terminal.
 *
 *      +
 *     / \
 *    1   3
 */
public class Parser {
    Token lookahead;

    // Lexer will keep outputing a token, when scan method is called
    Lexer lexer;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        this.lookahead = lexer.scan();
    }

    public void expr() {
        term();
        while (true) {
            if (lookahead instanceof Word) {
                Word currentWord = (Word) lookahead;

                if (currentWord.lexeme.equals("+")) {
                    match(currentWord);
                    term();
                    System.out.println("+");
                } else if (currentWord.lexeme.equals("-")) {
                    match(currentWord);
                    term();
                    System.out.println('-');
                } else {
                    throw new ReadException(String.format("Unexpected binary operator %s", currentWord.lexeme));
                }
            } else if (lookahead.tag == Tag.EOF) {
                break;
            } else  {
                System.out.println("Error while parsing string, unexpected token");
            }
        }
    }

    void term() {
        if (lookahead.tag == Tag.NUM) {
            System.out.println(((Num) lookahead).value);
            match(lookahead);
        } else
            throw new Error("Syntax error");
    }

    void match(Token token) {
        if (lookahead.tag == token.tag) {
            lookahead = lexer.scan();
        }
    }
}
