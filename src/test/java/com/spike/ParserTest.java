package com.spike;

import org.junit.Ignore;
import org.junit.Test;

import com.spike.parser.Parser;

public class ParserTest {
    @Test()
    @Ignore
    public void testPraser() {
        try {
            Parser parser = new Parser();
            parser.expr();            
        } catch (Exception e) {

        }
    }
}
