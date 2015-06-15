package oop.ex6.parser;

import oop.ex6.scopes.SCOPE_CLASSES;
import oop.ex6.scopes.Scope;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * a parser class that parse a java-s file.
 */
public class Parser{

    final static String EMPTY_LINE = "\\s*";
    final static String COMMENT_LINE = "\\//.*";


    private static Scope currentScope;
    //TODO some sort of a collection of the available methods

    /**
     * a method that goes through each line of the java-s file, translates the lines to commands and executes
     * the wanted actions on Scopes.
     * @param file
     */
    public static void parseFile(File file) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNext()) {
            String currentLine = fileScanner.nextLine();
            if (currentLine == EMPTY_LINE || currentLine == COMMENT_LINE) {
                continue;
            } // ignore empty and comment lines
        }
    }

    private static void createInnerScope(SCOPE_CLASSES type) {
    }

}