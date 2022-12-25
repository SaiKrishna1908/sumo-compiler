package com.spike;

import com.spike.exceptions.InvalidExpression;
import com.spike.exceptions.UnSupportedOperatorException;
import com.spike.utils.CompilerUtils;

import org.junit.Test;

import com.spike.lexer.Lexer;
import com.spike.parser.Parser;

public class ParserTest {

    @Test()
    public void test_parser() throws UnSupportedOperatorException, InvalidExpression {
        Lexer lex = new Lexer("1 + 2");
        Parser parser = new Parser(lex);
        var tokenNode =parser.parse();
        Integer result = CompilerUtils.evaluateParseTree(tokenNode);

        assert result != null;
        assert result == 3;
    }

    @Test(expected = InvalidExpression.class)
    public void test_parser_unsupported_operator_exception() throws InvalidExpression {
        Lexer lex  = new Lexer("1 t 3");
        Parser parser = new Parser(lex);
        parser.parse();
    }

    @Test(expected = InvalidExpression.class)
    public void test_parser_invalid_expression_exception() throws InvalidExpression {
        Lexer lex = new Lexer("1  1 111");
        Parser parser = new Parser(lex);
        parser.parse();
    }

    @Test
    public void test_parser_with_multiply_operator() throws UnSupportedOperatorException, InvalidExpression {
        Lexer lexer = new Lexer("5 * 3");
        Parser parser = new Parser(lexer);
        var tokenNode = parser.parse();
        Integer result = CompilerUtils.evaluateParseTree(tokenNode);

        assert result != null;
        assert result == 15;
    }

    @Test
    public void test_parser_with_integer_division() throws UnSupportedOperatorException, InvalidExpression {
        Lexer lexer = new Lexer("6 / 2");
        Parser parser = new Parser(lexer);
        var tokenNode = parser.parse();
        Integer result = CompilerUtils.evaluateParseTree(tokenNode);

        assert result != null;
        assert result == 3;
    }


    @Test
    public void test_parser_with_multiple_operators_expression() throws UnSupportedOperatorException, InvalidExpression {
        Lexer lexer = new Lexer("3 + 2 * 2");
        Parser parser = new Parser(lexer);
        var tokenNode = parser.parse();
        Integer result = CompilerUtils.evaluateParseTree(tokenNode);

        assert result != null;
        assert result == 7;
    }
}
