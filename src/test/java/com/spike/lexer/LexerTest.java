package com.spike.lexer;

import org.junit.Test;

public class LexerTest {

    @Test()
    public void test_lexer_scan_for_line_numbers() {

        String data = "\n\n\n";
        Lexer lexer = new Lexer(data);
        lexer.scan();
        assert lexer.line == 4;
    }

    @Test()
    public void test_lexer_scan_for_input_string() {

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
    }

    @Test()
    public void test_lexer_scan_for_input_number() {

        String data = "256";
        Lexer lexer = new Lexer(data);
        Num num = (Num) lexer.scan();
        assert num != null;
        assert num.value == 256;
    }

    @Test()
    public void test_lexer_scan_for_relational_operator() {

        String data = ">= <= == !=";
        Lexer lexer = new Lexer(data);
        Word word = (Word) lexer.scan();
        assert word != null;
        assert word.getTag() == Tag.REL_OP;
        assert word.lexeme.equals(">=");
        word = (Word) lexer.scan();
        assert word.lexeme.equals("<=");
        word = (Word) lexer.scan();
        assert word.lexeme.equals("==");
        word = (Word) lexer.scan();
        assert word.lexeme.equals("!=");
    }

    @Test
    public void test_lexer_binary_operator() {
        String data = "+";

        Lexer lexer = new Lexer(data);
        Word word = (Word) lexer.scan();

        assert word.getTag() == Tag.BINARY_OP;
        assert word.lexeme.equals("+");
    }

    @Test()
    public void test_lexer_expression_tokenization() {
        String data = "3  +  4";
        Lexer lexer = new Lexer(data);
        Token currentToken;
        while ((currentToken = lexer.scan()).getTag() != Tag.EOF) {
            System.out.println(currentToken);
        }
    }

    @Test()
    public void test_lexer_expression_with_letter_and_number() {
        String data = "3 t 4";
        Lexer lexer = new Lexer(data);
        Token currentToken;
        while ((currentToken = lexer.scan()).getTag() != Tag.EOF) {
            System.out.println(currentToken);
        }
    }
}