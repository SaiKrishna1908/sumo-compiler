package com.spike;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

import com.spike.exceptions.InvalidExpression;
import com.spike.exceptions.UnSupportedOperatorException;
import com.spike.lexer.Lexer;
import com.spike.parser.Parser;
import com.spike.parser.TokenNode;

import javax.management.InvalidAttributeValueException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException, UnSupportedOperatorException, InvalidExpression {
        
    }

    public static void prettyPrintParseTree(TokenNode root, int offset) {

        if (offset <= 0) {
            System.out.println("offset cannot be less than or equal to 0");
            return;
        }
        String prefix = "\t".repeat(offset);

        if (offset == 1) {
            System.out.println(root.getToken().getTag() + " ("+root.getToken().getValue()+") ");
        }

        for(var child : root.getChildren()) {
            System.out.println(prefix+"|---"+child.getToken().getTag()+" (" +child.getToken().getValue()+") ");
            prettyPrintParseTree(child, offset+1);
        }
    }
}
