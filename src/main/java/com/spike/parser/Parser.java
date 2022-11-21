package com.spike.parser;

import java.io.IOException;
import java.util.Scanner;

/*
 * A simple parser
 *
 * Schema
 *
 * expr -> term rest
 * rest -> + term {print('+')} rest
 *      |  - term {print('-')} rest
 *      |  none
 *
 * term -> 0 {print('0')}
 *      |  1 {print('1')}
 *      |  2 {print('2')}
 *      |  3 {print('3')}
 *      |  4 {print('4')}
 *      ...
 *      |  9 {print('9')}
 */
public class Parser {
    static int lookahead;
    Scanner scan;

    public Parser() throws IOException {
        scan = new Scanner(System.in);
        lookahead = scan.nextInt();
    }

    public void expr() throws IOException {
        term();
        while (true) {
            System.out.println(((char) lookahead));
            if (lookahead == '+') {
                match('+');
                term();
                System.out.println("+");
            } else if (lookahead == '-') {
                match('-');
                term();
                System.out.println('-');
            } else {
                return;
            }
        }
    }

    void term() throws IOException {
        if (Character.isDigit((char) lookahead)) {
            System.out.println((char) lookahead);
            match(lookahead);
        } else throw new Error("Syntax error");
    }

    void match(int t) throws IOException {
        if (lookahead == t) lookahead = scan.nextInt();
        else throw new Error("Syntax Error");
    }
}
