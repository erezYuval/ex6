package oop.ex6.parser;

import oop.ex6.main.exceptions.parserExceptions.IllegalLineException;
import oop.ex6.main.exceptions.parserExceptions.unbalancedScopeException;
import oop.ex6.scopes.Scope;

import java.util.Scanner;

/**
 * a parser class that parses a java-s file.
 */
public class Parser{

    final static String EMPTY_LINE = "\\s*";
    final static String COMMENT_LINE = "\\//.*";

    static int balancedBracketCounter = 0;

    private static Scope currentScope;

    //TODO some sort of a collection of the available methods

    /**
     * a method that goes through each line of the java-s file, translates the lines to commands and executes
     * the wanted actions on Scopes.
     * @param fileScanner - a scanner that runs on a legal file
     */
    public static void parseFile(Scanner fileScanner) throws IllegalLineException, unbalancedScopeException {
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
                    LegalLineParser.parseLine(currentLine);
                }
            } // line is not empty, comment or legal - i.e illegal line
            throw new IllegalLineException();
        }
        if(balancedBracketCounter != 0){ // reached end of file, number of opening and closing brackets does not match
            throw new unbalancedScopeException();
        }
    }

}