package com.spike;

import java.io.IOException;
import java.util.Scanner;

import com.spike.exceptions.InvalidExpression;
import com.spike.exceptions.UnSupportedOperatorException;
import com.spike.lexer.Lexer;
import com.spike.parser.Parser;
import com.spike.parser.TokenNode;
import com.spike.utils.CompilerUtils;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException, UnSupportedOperatorException, InvalidExpression {
        String input = null;
        Scanner scan = new Scanner(System.in);

        boolean debugMode = false;
        
        while((input =  scan.nextLine()) != null && !input.isEmpty()) {
            if (input.equalsIgnoreCase("#debug")) {
                debugMode = true;
                continue;
            }
            Lexer lex = new Lexer(input);
            var parser = new Parser(lex);

            var rootNode =  parser.parse();

            var result = CompilerUtils.evaluateParseTree(rootNode);

            System.out.println(result);

            if (debugMode) {
                prettyPrintParseTree(rootNode, 1);
            }
            
        }

        scan.close();
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
