package com.spike.parser;

import com.spike.exceptions.InvalidExpression;
import com.spike.lexer.*;
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
    public TokenNode parse() throws InvalidExpression {
        return parseWithPrecedence(0);
    }

    private Expression parseWithPrecedence(int parentPrecedance) throws  InvalidExpression {
        var left = parseExpression();

        while(true) {
            var currentOperator = tryParseNextWord();
            int precedence = getOperatorPrecedence(currentOperator);

            if (precedence == 0 || precedence <= parentPrecedance) {
                break;
            }
            
            match(Tag.OPERATOR);
            var right = parseWithPrecedence(precedence);

            if (currentOperator.lexeme == "-") {
                left = new BinaryExpression(right, currentOperator, left);
            } else {
                left = new BinaryExpression(left, currentOperator, right);
            }            
        }

        return left;
    }

    private int getOperatorPrecedence(Word currentOperator) throws InvalidExpression {
        if  (currentOperator.getTag() == Tag.OPERATOR) {
            switch (currentOperator.lexeme) {
                case "+":                    
                case "-":
                    return 2;
                case "*":
                case "/":
                    return 3;
                case "(":
                    return 4;
                default:
                    return 0;
            }
        } else if (currentOperator.getTag() == Tag.EOF) {
            return 0;
        }

        throw new InvalidExpression(String.format("Invalid expression, expected operator got %s", currentOperator.getClass()));
    }

    private Word tryParseNextWord() throws InvalidExpression {
            try {
                return  new Word( lookahead.getTag(), (String) lookahead.getValue());
            } catch (ClassCastException classCastException) {
                throw  new InvalidExpression(String.format("Expected word, got %s", lookahead.getClass()));
            }
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
