package oop.ex6.main;

import oop.ex6.main.exceptions.SjavaException;
import oop.ex6.parser.JavaSPatterns;
import oop.ex6.scopes.Scope;

import java.io.File;
import java.io.IOException;

import java.util.Scanner;

/***
 * main method of the S-java verifier - receives a file, which if legal will be converted to a Scanner
 * that will be sent to a parser, that will read it line by line. and decide rather it is a legal compilable
 * sJava file or Not.
 */
public class Sjavac {

    private final static int PATH = 0;

    // printed values
    private final static int ALL_OK = 0, SOMETHING_WRONG = 1, IO_ISSUE = 2;

    /**
     * run the java verifier on a specific file.
     * @param args should be an array of one string - the path of the file to be checked
     */
    public static void main(String[] args) {
            JavaSPatterns.compilePatterns();
            Scope global = new Scope(); //create global scope
            Scanner fileScanner;
            try {
                File sourceFile = new File(args[PATH]);
                fileScanner = new Scanner(sourceFile);
                try {
                    oop.ex6.parser.Parser.parseFile(fileScanner, global);
                    fileScanner.reset();
                    fileScanner = new Scanner(sourceFile);
                    oop.ex6.parser.Parser.parseDeep(fileScanner, global);
                }catch(SjavaException e) {
                    System.out.println(SOMETHING_WRONG);
                    System.err.println(e.getErrorMessage());
                    return;
                }
            } catch (IOException e) {
                System.out.println(IO_ISSUE);
                return;
            }
            System.out.println(ALL_OK);
    }
}
