package oop.ex6.parser;

import oop.ex6.main.exceptions.parserExceptions.IllegalLineException;
import oop.ex6.main.exceptions.parserExceptions.unbalancedScopeException;
import oop.ex6.scopes.Scope;
import oop.ex6.parser.JavaSPatterns;

import java.util.Scanner;

/**
 * a parser class that parses a java-s file.
 */
public class Parser{

    final static String EMPTY_LINE = "\\s*";
    final static String COMMENT_LINE = "\\//.*";
    //TODO some sort of a collection of the available methods

    /**
     * a method that goes through each line of the java-s file, translates the lines to commands and executes
     * the wanted actions on Scopes.
     * @param fileScanner - a scanner that runs on a legal file
     */
    public static void parseFile(Scanner fileScanner, Scope globalScope) throws IllegalLineException, unbalancedScopeException {
        int balancedBracketCounter = 0;
        while (fileScanner.hasNext()) {

            if(balancedBracketCounter < 0){ //there is a closing bracket in the file that does not correspond to a
                //valid opening bracket
                throw new unbalancedScopeException();
            }
            String currentLine = fileScanner.nextLine();

            //find opening and closing brackets, and update their counter accordingly
            if (currentLine.contains("{")){balancedBracketCounter++;}
            if (currentLine.contains("}")){balancedBracketCounter--;}

            if (balancedBracketCounter == 0) { //i.e in global scope: read lines

                if (currentLine == EMPTY_LINE || currentLine == COMMENT_LINE) {
                    continue; // ignore empty and comment lines
                }
                if (currentLine == JavaSPatterns.VARIABLE_LINE ||
                        currentLine == JavaSPatterns.METHOD_SIGNATURE) { // i.e line is legal
                    LegalLineParser.parseLine(currentLine, globalScope);
                }
            } // line is not empty, comment or legal - i.e illegal line
            throw new IllegalLineException();
        }
        if(balancedBracketCounter != 0){ // reached end of file, number of opening and closing brackets does not match
            throw new unbalancedScopeException();
        }
    }

    public static void ParseBlock(Scanner fileScanner, Scope scope, int lineNumber) {
        String line;
        while (fileScanner.hasNext()) {
            lineNumber++;
            line = fileScanner.nextLine();
            if (line.matches(JavaSPatterns.VARIABLE_LINE)) {
                dealWithVariableLine(line);
            } else if (line.matches(JavaSPatterns.METHOD_CALL)) {
                dealWithMethodCall(line);
            } else if (line.matches(JavaSPatterns.CONDITION_BLOCK_STARTERS)) {
                Scope innerScope = new Scope(lineNumber, scope);
                ParseBlock(fileScanner, innerScope, lineNumber);
            } else if (line.matches(JavaSPatterns.RETURN)) {
                dealWithReturnStatement(line, scope);
            } else if (line.matches(JavaSPatterns.END_BLOCK)) {
                return;
            }

        }
    }

    private static void dealWithVariableLine(String line) {

    }

    private static void dealWithMethodCall(String line) {

    }

    private static void dealWithReturnStatement(String line, Scope scope) {

    }
}