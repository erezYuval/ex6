package oop.ex6.main;

import oop.ex6.main.exceptions.SjavaException;
import oop.ex6.parser.JavaSPatterns;
import oop.ex6.scopes.Scope;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
/**
 * Created by yuvalavrami on 6/18/15.
 *
 * main method of the S-java verifier - receives a file, which if legal will be converted to a Scanner
 * that will be sent to a parser, that will read it line by line.
 */
public class Sjavac {

    public static void main(String[] Args) throws FileNotFoundException{

        Scope global = new Scope(0); //create global scope
        JavaSPatterns.compilePatterns();
        Scanner fileScanner;
        try {
            File sourceFile = new File(Args[0]);
            fileScanner = new Scanner(sourceFile);
            try {
                oop.ex6.parser.Parser.parseFile(fileScanner, global);
                fileScanner.reset();
                oop.ex6.parser.Parser.parseDeep(fileScanner, global);
            }catch(SjavaException e) {
                System.out.println(1);
                System.err.println(e.getErrorMessage());
            }
        } catch (IOException e) {
            System.out.println(2);
        }
        System.out.println(0);
    }
}
