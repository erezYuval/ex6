package oop.ex6.main;

import oop.ex6.main.exceptions.SjavaException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Created by yuvalavrami on 6/18/15.
 *
 * main method of the S-java verifier - receives a file, which if legal will be converted to a Scanner
 * that will be sent to a parser, that will read it line by line.
 */
public class Sjavac {

    public static Scanner fileScanner;

    public static void Main(String[] Args) throws FileNotFoundException{
        File sourceFile = new File(Args[1]);
        if (!sourceFile.exists()){
            throw new FileNotFoundException();
        }
        else{
            fileScanner = new Scanner(sourceFile);
        }
        try {
            oop.ex6.parser.Parser.parseFile(fileScanner);
        }catch(SjavaException e) {
        System.err.println(e.getErrorMessage());
        };

    }
}
