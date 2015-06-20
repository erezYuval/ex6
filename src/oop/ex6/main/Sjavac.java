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

    private static boolean TESTING = true;

    public static void main(String[] args) throws FileNotFoundException {
        JavaSPatterns.compilePatterns();
        if (TESTING) {
            File testDirectory = new File("C:/ex6try2/tests");
            File[] testFiles = testDirectory.listFiles();
            int[] tests = new int[]{114,205,206,207,208,216,217,217,223,224,225,226,236,238,244,245,252,254,257,262,264,270,306,307,308,309,310,314,316,402,406,407,408,409,429,430,431,434,435,439,440,441,452,453,454,456,467,468,474,503,504};
//            tests = new int[]{473};
            for (int test : tests){

        for (File file : testFiles) {
            int a = test;
            if (file.getName().equals("test" + a + ".sjava")) {
//                if(true){
                System.out.println("TEST NUMBER " + file.getName());
                testOneFile(file);
            }
        }
    }
        }
        else {
            testOneFile(args[0]);
        }
    }

    private static void testOneFile(File file){
        Scope global = new Scope(0); //create global scope
        JavaSPatterns.compilePatterns();
        Scanner fileScanner;
        try {
//            File sourceFile = new File(Args[0]);
            File sourceFile = file;
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

    private static void testOneFile(String filePath){
        Scope global = new Scope(0); //create global scope
        JavaSPatterns.compilePatterns();
        Scanner fileScanner;
        try {
            File sourceFile = new File(filePath);
            fileScanner = new Scanner(sourceFile);
            try {
                oop.ex6.parser.Parser.parseFile(fileScanner, global);
                fileScanner.reset();
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
    }
}
