package oop.ex6.parser;

import oop.ex6.scopes.Scope;

import java.util.ArrayList;

/**
 * Created by yuvalavrami on 6/15/15.
 *
 * receives legal lines from the parser (excluding illegal, empty and comment lines)
 * and extracts methods and variables from them
 */
public class LegalLineParser {

    static void parseLine(String line, int lineNumber, Scope currentScope){
        if(line.matches(JavaSPatterns.METHOD_SIGNATURE)){
            String[] methodNameHelper = line.split("\\(")[0].split(" ");
            String methodName = methodNameHelper[methodNameHelper.length - 1];


//            Method newMethod = new Method(methodName, --vartypes--, --argtypes--, lineNumber);
        }

    }

    public static void main(String[] args) {
        String line = "void shits     (String asd, boolean true, int a) {";

        ArrayList<String> typesInOrder = new ArrayList<>();
        ArrayList<String> namesInOrder = new ArrayList<>();

        String[] arguments = line.split("\\(")[1].split(",");
        for (String arg : arguments) {
            String argTrimmed = arg.trim();
            String[] splitArg = argTrimmed.split("\\s");
            typesInOrder.add(splitArg[0]);
            namesInOrder.add(splitArg[1].split("\\)")[0]);
        }
        for(String name:namesInOrder){
            System.out.println(name);
        }

    }


    }




