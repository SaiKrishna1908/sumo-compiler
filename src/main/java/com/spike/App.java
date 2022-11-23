package com.spike;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

import com.spike.lexer.Lexer;
import com.spike.parser.Parser;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        
        BufferedReader bin = new BufferedReader(new InputStreamReader(System.in));

        String input;

        Lexer lexer;

        do {

            input = bin.readLine();
            lexer = new Lexer(input);
            
            
            Parser parser = new Parser(lexer);
            parser.expr();

        } while (Objects.isNull(input) || input.isEmpty() || input.isBlank());
    }
}
