package com.spike;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

import com.spike.exceptions.InvalidExpression;
import com.spike.exceptions.UnSupportedOperatorException;
import com.spike.lexer.Lexer;
import com.spike.parser.Parser;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException, UnSupportedOperatorException, InvalidExpression {
        
        BufferedReader bin = new BufferedReader(new InputStreamReader(System.in));

        String input;

        Lexer lexer;

        System.out.println(">>> Sumo Compiler <<<");
        System.out.print("> ");
        input = bin.readLine();
        do {
            lexer = new Lexer(input);
            Parser parser = new Parser(lexer);
            parser.parse();

            System.out.print("> ");
            input = bin.readLine();
        } while (!Objects.isNull(input) && !input.isEmpty() && !input.isBlank());
    }
}
