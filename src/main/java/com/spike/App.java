package com.spike;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

import com.spike.parser.Parser;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        Parser parser = new Parser();
        parser.expr();

        BufferedReader bin = new BufferedReader(new InputStreamReader(System.in));

        String input;

        do {

            input = bin.readLine();


        } while (Objects.isNull(input) || input.isEmpty() || input.isBlank());
    }
}
