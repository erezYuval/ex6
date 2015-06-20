package oop.ex6.parser;

import oop.ex6.scopes.Scope;

/**
 * Created by yuvalavrami on 6/15/15.
 *
 * receives legal lines from the parser (excluding illegal, empty and comment lines)
 * and extracts methods and variables from them
 */
public class LegalLineParser {

    static void parseLine(String line, int lineNumber, Scope currentScope){
//        if(line.matches(JavaSPatterns.METHOD_SIGNATURE)){
//            String[] methodNameHelper = line.split("\\(")[0].split(" ");
//            String methodName = methodNameHelper[methodNameHelper.length - 1];
//
//
//            Method newMethod = new Method(methodName, --vartypes--, --argtypes--, lineNumber);
//        }

    }

    public static void main(String[] args) {
        String line = "void shits     (String asd, boolean true, int a) {";

        String[] arguments = line.split("\\(")[1].split(",");
        for(String arg: arguments){
            String[] splitArg = arg.split("\\s");
            String argtype = splitArg[0];
            System.out.println("type=" + argtype);
            String argName = splitArg[1];
            System.out.println("name=" + argName);
        }



        }



    }

