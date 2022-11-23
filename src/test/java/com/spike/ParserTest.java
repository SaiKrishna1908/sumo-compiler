package com.spike;

import org.junit.Test;

import com.spike.lexer.Lexer;
import com.spike.parser.Parser;

public class ParserTest {

    @Test()
    public void testPraser() {
        Lexer lex = new Lexer("1 + 2");
        Parser parser = new Parser(lex);
        parser.expr();

    }
}
