package oop.ex6.main;

import oop.ex6.main.exceptions.SjavaException;
import oop.ex6.parser.*;
import oop.ex6.scopes.Scope;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
/**
 * main method of the S-java verifier - receives a file, which if legal will be converted to a Scanner
 * that will be sent to a parser, that will read it line by line.
 */
public class Sjavac {

    // argument constant
    private final static int PATH = 0;

    // return values constants
    private final static int ALL_OK = 0, LANGUAGE_ISSUE = 1, IO_ISSUE = 2;
    private final static String IO_ERROR_MESSAGE = "there was a problem reading the file.";
    private final static String WRONG_FILE_TYPE_MESSAGE = "WARNING: Running on a non sjava file";

    private final static String S_JAVA_FILE = ".+\\.sjava";

    /**
     * main method. receives a text file, and returns one of the following:
     * 0: if file is a valid S-Java file;
     * 1: if file is an illegal S-Java file;
     * 2: if there are I/O exceptions.
     * @param args
     */
    public static void main(String[] args) {
            JavaSPatterns.generatePatterns();
            Scope global = new Scope(); //create global scope
            Scanner fileScanner;
            try {
                File sourceFile = new File(args[PATH]);
                fileScanner = new Scanner(sourceFile);
                try {
                    if (!sourceFile.getName().matches(S_JAVA_FILE)){ // these is from the school solution
                        System.err.println(WRONG_FILE_TYPE_MESSAGE);
                    }
                    Parser.parseGlobalScope(fileScanner, global);
                    fileScanner.reset();
                    fileScanner = new Scanner(sourceFile);
                    Parser.parseInsideMethods(fileScanner, global);
                }catch(SjavaException e) {
                    System.out.println(LANGUAGE_ISSUE);
                    System.err.println(e.getMessage());
                    return;
                }
            } catch (IOException e) {
                System.err.println(IO_ERROR_MESSAGE);
                System.out.println(IO_ISSUE);
                return;
            }
            System.out.println(ALL_OK);
            return;
    }
}
