package com.spike;

import com.spike.exceptions.InvalidExpression;
import com.spike.exceptions.UnSupportedOperatorException;
import com.spike.lexer.Lexer;
import com.spike.parser.Parser;
import org.junit.Test;

import java.io.*;
import java.util.Objects;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws IOException, UnSupportedOperatorException, InvalidExpression {

        System.setIn(new ByteArrayInputStream("3 + 4".getBytes()));
        BufferedReader bin = new BufferedReader(new InputStreamReader(System.in));
        String input;

        Lexer lexer;

        System.out.println(">>> Sumo Compiler <<<");
        System.out.print("> ");
        input = bin.readLine();
        do {
            lexer = new Lexer(input);
            Parser parser = new Parser(lexer);
            var rootNode = parser.parse();

            System.out.print("> ");
//            input = bin.readLine();
            input = "";

            App.prettyPrintParseTree(rootNode, 1);
        } while (!Objects.isNull(input) && !input.isEmpty() && !input.isBlank());
    }
}
