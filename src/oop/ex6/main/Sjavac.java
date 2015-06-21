package oop.ex6.main;

import oop.ex6.main.exceptions.SjavaException;
import oop.ex6.parser.JavaSPatterns;
import oop.ex6.parser.LegalLineParser;
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

    private final static int PATH = 0;
    private final static boolean testing = true;

    public static void main(String[] args) {
        if (!testing) {
            JavaSPatterns.compilePatterns();
            Scope global = new Scope(0); //create global scope
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
                    System.out.println(1);
                    System.err.println(e.getErrorMessage());
    //                e.printStackTrace();
                    return;
                }
            } catch (IOException e) {
                System.out.println(2);
                return;
            }
            System.out.println(0);
            return;
        } else {
            int i = 0;
            int[] tests = new int[]{504};
//            tests = new int[]{tests[0]};
            for (int test : tests) {
                i++;
                String path = "C:\\ex6try2\\tests\\test" + test + ".sjava";
                System.out.println(path);
                runOneTest(path);
            }
            System.out.println("TOTAL TESTS: " + i);
        }
    }

    static void runOneTest(String filePath){
        JavaSPatterns.compilePatterns();
        Scope global = new Scope(0); //create global scope
        Scanner fileScanner;
        try {
            File sourceFile = new File(filePath);
            fileScanner = new Scanner(sourceFile);
            try {
                oop.ex6.parser.Parser.parseFile(fileScanner, global);
                fileScanner.reset();
                fileScanner = new Scanner(sourceFile);
                oop.ex6.parser.Parser.parseDeep(fileScanner, global);
            }catch(SjavaException e) {
                System.out.println(1);
//                System.err.println(e.getErrorMessage());
//                e.printStackTrace();
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
