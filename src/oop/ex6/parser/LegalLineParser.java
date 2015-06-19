package oop.ex6.parser;

import oop.ex6.scopes.Scope;

/**
 * Created by yuvalavrami on 6/15/15.
 *
 * receives legal lines from the parser (excluding illegal, empty and comment lines)
 * and extracts methods and variables from them
 */
public class LegalLineParser {

    static void parseLine(String line, Scope currentScope){
        if(line.matches(JavaSPatterns.METHOD_SIGNATURE)){

        }

    }

    public static void main(String[] args) {
        String line = "void shits (String asd, boolean true) {";

    }
}
