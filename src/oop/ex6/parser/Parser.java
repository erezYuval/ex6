package oop.ex6.parser;

import oop.ex6.scopes.SCOPE_CLASSES;
import oop.ex6.scopes.Scope;
import oop.ex6.main.exceptions.parserExceptions.IllegalLineException;

import java.util.Scanner;

/**
 * a parser class that parses a java-s file.
 */
public class Parser{

    final static String EMPTY_LINE = "\\s*";
    final static String COMMENT_LINE = "\\//.*";

    private static Scope currentScope;
    //TODO some sort of a collection of the available methods

    /**
     * a method that goes through each line of the java-s file, translates the lines to commands and executes
     * the wanted actions on Scopes.
     * @param fileScanner - a scanner that runs on a legal file
     */
    public static void parseFile(Scanner fileScanner) throws IllegalLineException {
        while (fileScanner.hasNext()) {
            String currentLine = fileScanner.nextLine();
            if (currentLine == EMPTY_LINE || currentLine == COMMENT_LINE) {
                continue; // ignore empty and comment lines
            }
            if (currentLine == JavaSPatterns.VARIABLE_LINE ||
                    currentLine == JavaSPatterns.METHOD_SIGNATURE){ // i.e line is legal
                LegalLineParser.parseLine(currentLine);
            }
        } // line is not empty, comment or legal - i.e illegal line
        throw new IllegalLineException();
    }

    private static void createInnerScope(SCOPE_CLASSES type) {
    }

}