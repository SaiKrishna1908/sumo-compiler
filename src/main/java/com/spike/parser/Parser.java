package com.spike.parser;

import com.spike.exceptions.InvalidExpression;
import com.spike.exceptions.ReadException;
import com.spike.exceptions.UnSupportedOperatorException;
import com.spike.lexer.Lexer;
import com.spike.lexer.Num;
import com.spike.lexer.Tag;
import com.spike.lexer.Token;
import com.spike.lexer.Word;
import com.spike.parser.expressions.BinaryExpression;
import com.spike.parser.expressions.Expression;
import com.spike.parser.expressions.NumberExpression;

import java.util.List;

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

    /*
        Read the first token
     */
    public Parser(Lexer lexer) {
        this.lexer = lexer;
        this.lookahead = lexer.scan();
    }

    /*
        Returning the root token node is optional and useful for printing the parsetree.
     */
    public TokenNode parse() throws UnSupportedOperatorException, InvalidExpression {

        return term();
    }

    private Word tryParseNextWord() throws InvalidExpression {
            try {
                return  new Word( lookahead.getTag(), (String) lookahead.getValue());
            } catch (ClassCastException classCastException) {
                throw  new InvalidExpression(String.format("Expected word, got %s", lookahead.getClass()));
            }
    }

    Expression term() throws InvalidExpression {
        var left = factor();
        Word currentWord = tryParseNextWord();

        if (currentWord.getTag() != Tag.BINARY_OP && currentWord.getTag() != Tag.EOF) {
            throw new InvalidExpression("Invalid expression, binary operator expected");
        }

        while (currentWord.lexeme.equals("+")  || currentWord.lexeme.equals("-")) {

                Word word = new Word(Tag.BINARY_OP, currentWord.lexeme);
                match(Tag.BINARY_OP);
                Expression right = factor();

                left = new BinaryExpression(left, word, right);
                currentWord = tryParseNextWord();
        }

        return left;
    }

    Expression factor() throws InvalidExpression {
        var left = parseExpression();
        Word currentWord = tryParseNextWord();

        if (currentWord.getTag() != Tag.BINARY_OP && currentWord.getTag() != Tag.EOF) {
            throw new InvalidExpression("Invalid expression, binary operator expected");
        }

        while (currentWord.lexeme.equals("*")  || currentWord.lexeme.equals("/")) {

            Word factorWord = new Word(Tag.BINARY_OP, currentWord.lexeme);
            match(Tag.BINARY_OP);
            Expression right = parseExpression();

            left  = new BinaryExpression(left, factorWord, right);
            currentWord = tryParseNextWord();
        }

        return left;
    }

    Expression parseExpression() throws  InvalidExpression {
        if (lookahead.getTag() == Tag.NUM) {
            int value = ((Num) lookahead).value;
            match(Tag.NUM);
            return new NumberExpression(value);
        } else
            throw new Error("Syntax error");
    }

    void match(Tag tag) throws InvalidExpression {
        if (lookahead.getTag() == tag) {
            lookahead = lexer.scan();
        } else {
            throw new InvalidExpression(String.format("Expected %s Found %s", tag, lookahead.getTag()));
        }
    }
}
