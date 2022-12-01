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

    /*
        Returning the root token node is optional and useful for printing the parsetree.
     */
    public TokenNode parse() throws UnSupportedOperatorException, InvalidExpression {

        Expression left = term();

        while (true) {
            if (lookahead instanceof Word) {
                Word currentWord = (Word) lookahead;

                if (currentWord.lexeme.equals("+")) {
                    Word word = new Word(Tag.BINARY_OP, "+");
                    match(Tag.BINARY_OP);
                    Expression right = term();

                    BinaryExpression binaryExpression = new BinaryExpression((NumberExpression) left, word, (NumberExpression) right);
                    System.out.println(binaryExpression.evaluate());
                    return binaryExpression;
                } else if (currentWord.lexeme.equals("-")) {
                    Word word = new Word(Tag.BINARY_OP, "-");
                    match(Tag.BINARY_OP);

                    Expression right = term();

                    BinaryExpression binaryExpression = new BinaryExpression((NumberExpression) left, word, (NumberExpression) right);
                    System.out.println(binaryExpression.evaluate());

                    return binaryExpression;
                } else {
                    throw new UnSupportedOperatorException(String.format("Unexpected binary operator %s", currentWord.lexeme));
                }
            } else if (lookahead.getTag() == Tag.EOF) {
                break;
            } else  {
                throw new InvalidExpression("Invalid expression, expected binary operator");
            }
        }

        return left;
    }

    Expression term() {
        if (lookahead.getTag() == Tag.NUM) {
            int value = ((Num) lookahead).value;
            match(Tag.NUM);
            return new NumberExpression(value);
        } else
            throw new Error("Syntax error");
    }

    void match(Tag tag) {
        if (lookahead.getTag() == tag) {
            lookahead = lexer.scan();
        }
    }
}
