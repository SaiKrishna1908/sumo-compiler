package com.spike.lexer;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class LexerTest {

    @Test()
    public void test_lexer_scan_for_line_numbers() {

        try {
            String data = "\n\n\n";
            Lexer lexer = new Lexer(data);
            lexer.scan();
            assert lexer.line == 4;
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    @Test()
    public void test_lexer_scan_for_input_string() {

        try {
            String data = "some string I say";
            Lexer lexer = new Lexer(data);
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

        try {
            String data = "256";
            Lexer lexer = new Lexer(data);
            Num num = (Num) lexer.scan();
            assert num != null;
            assert num.value == 256;
        } catch (IOException ioException) {

        }
    }

    @Test()
    public void test_lexer_scan_for_relational_operator() {

        try {

            String data = ">= <= == !=";
            Lexer lexer = new Lexer(data);
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

    @Test
    public void test_lexer_binary_operator() {
        try {
            String data = "+";

            Lexer lexer = new Lexer(data);
            Word word = (Word) lexer.scan();

            assert word.tag == Tag.BINARY_OP;
            assert word.lexeme.equals("+");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test()
    public void test_lexer_expression_tokenization() {
        try {
            String data = "3  +  4";
            Lexer lexer = new Lexer(data);
            Token currentToken;
            while ((currentToken = lexer.scan()).tag != Tag.EOF) {
                System.out.println(currentToken);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}