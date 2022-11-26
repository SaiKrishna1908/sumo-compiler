package com.spike;

import com.spike.exceptions.InvalidExpression;
import com.spike.exceptions.UnSupportedOperatorException;
import org.junit.Test;

import com.spike.lexer.Lexer;
import com.spike.parser.Parser;

public class ParserTest {

    @Test()
    public void test_parser() throws UnSupportedOperatorException, InvalidExpression {
        Lexer lex = new Lexer("1 + 2");
        Parser parser = new Parser(lex);
        parser.parse();
    }

    @Test(expected = UnSupportedOperatorException.class)
    public void test_parser_unsupported_operator_exception() throws UnSupportedOperatorException, InvalidExpression {
        Lexer lex  = new Lexer("1 t 3");
        Parser parser = new Parser(lex);
        parser.parse();
    }

    @Test(expected = InvalidExpression.class)
    public void test_parser_invalid_expression_exception() throws UnSupportedOperatorException, InvalidExpression {
        Lexer lex = new Lexer("1  1 111");
        Parser parser = new Parser(lex);
        parser.parse();
    }
}
