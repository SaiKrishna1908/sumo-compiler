package com.spike;

import java.io.IOException;

import com.spike.parser.Parser;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        Parser parser = new Parser();
        parser.expr();
    }
}
