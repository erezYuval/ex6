package oop.ex6.main;

import oop.ex6.main.exceptions.SjavaException;
import oop.ex6.parser.*;
import oop.ex6.scopes.Scope;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
/**
 * Created by yuvalavrami on 6/18/15.
 *
 * main method of the S-java verifier - receives a file, which if legal will be converted to a Scanner
 * that will be sent to a parser, that will read it line by line.
 */
public class Sjavac {

    private final static int PATH = 0;

    public static void main(String[] args) {
            JavaSPatterns.generatePatterns();
            Scope global = new Scope(); //create global scope
            Scanner fileScanner;
            try {
                File sourceFile = new File(args[PATH]);
                fileScanner = new Scanner(sourceFile);
                try {
                    Parser.parseGlobalScope(fileScanner, global);
                    fileScanner.reset();
                    fileScanner = new Scanner(sourceFile);
                    Parser.parseInsideMethods(fileScanner, global);
                }catch(SjavaException e) {
                    System.out.println(1);
                    System.err.println(e.getMessage());
                    return;
                }
            } catch (IOException e) {
                System.out.println(2);
                return;
            }
            System.out.println(0);
            return;
    }
}
