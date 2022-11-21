package com.spike.lexer;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class LexerTest {

    @Test()
    public void test_lexer_scan_for_line_numbers() {
        Lexer lexer = new Lexer();
        try {
            String data = "\n\n\n";
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            lexer.scan();
            assert lexer.line == 4;
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    @Test()
    public void test_lexer_scan_for_input_string() {
        Lexer lexer = new Lexer();
        try {
            String data = "some string I say";
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            lexer.scan();
            assert lexer.wordHashtable.containsKey("some");
            lexer.scan();
            assert lexer.wordHashtable.containsKey("string");
            lexer.scan();
            assert lexer.wordHashtable.containsKey("I");
            lexer.scan();
            assert lexer.wordHashtable.containsKey("say");
        } catch (IOException e) {

        }
    }

    @Test()
    public void test_lexer_scan_for_input_number() {
        Lexer lexer = new Lexer();
        try {
            String i= "256";
            System.setIn(new ByteArrayInputStream(i.getBytes()));
            Num num = (Num) lexer.scan();
            assert num != null;
            assert num.value == 256;
        } catch (IOException ioException) {

        }
    }

    @Test()
    public void test_lexer_scan_for_relational_operator() {
        Lexer lexer = new Lexer();
        try {
            String i = ">= <= == !=";
            System.setIn(new ByteArrayInputStream(i.getBytes()));
            Word word = (Word) lexer.scan();
            assert word != null;
            assert word.tag == Tag.REL_OP;
            assert word.lexeme.equals(">=");
            word = (Word) lexer.scan();
            assert word.lexeme.equals("<=");
            word = (Word) lexer.scan();
            assert word.lexeme.equals("==");
            word = (Word) lexer.scan();
            assert word.lexeme.equals("!=");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}