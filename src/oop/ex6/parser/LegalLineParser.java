package oop.ex6.parser;

import oop.ex6.main.exceptions.SjavaException;
import oop.ex6.main.exceptions.variableExceptions.VariableException;
import oop.ex6.methods.Method;
import oop.ex6.scopes.Scope;
import oop.ex6.variables.VARIABLE_TYPES;
import oop.ex6.variables.Variable;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yuvalavrami on 6/15/15.
 *
 * receives legal lines from the parser (excluding illegal, empty and comment lines)
 * and extracts methods and variables from them
 */
public class LegalLineParser {

    static void parseLine(String line, int lineNumber, Scope currentScope) throws SjavaException {
        if(line.matches(JavaSPatterns.METHOD_SIGNATURE)) {
            Parser.parseMethodSignature(line, lineNumber, currentScope, true);
        }
    }

    static void createMethod(String methodName, ArrayList<Variable> variableArray,
                             int lineNumber, Scope currentScope) throws VariableException {
        Method newMethod = new Method(methodName, variableArray, lineNumber, currentScope);
        currentScope.addMethod(newMethod);
    }




    }


/////// old style
    //find method name
//            String[] methodNameHelper = line.split("\\(")[0].split(" ");
//            String methodName = methodNameHelper[methodNameHelper.length - 1];
//
//            VARIABLE_TYPES[] varTypeArray = new VARIABLE_TYPES[0];
//            String[] namesArray = new String[0];
//
//            //if there are no arguments leave name and type arrays blank
//            if(line.matches(".*\\(\\s*\\).*")){
//            } //do nothing
//
//            else { //there is at least one argument
//                //find methods arguments' types and names
//                ArrayList<String> typesInOrderAsStrings = new ArrayList<>();
//                ArrayList<VARIABLE_TYPES> typesInOrder = new ArrayList<>();
//
//                ArrayList<String> namesInOrder = new ArrayList<>();
//
//                String[] arguments = line.split("\\(")[1].split(",");
//                for (String arg : arguments) { //for each separate argument - separate between type and name
//                    String argTrimmed = arg.trim();
//                    String[] splitArg = argTrimmed.split("\\s+");
//                    typesInOrderAsStrings.add(splitArg[0]); // add type to type array
//                    namesInOrder.add(splitArg[1].split("\\)")[0]); // add name to name array
//                }
//
//                for (String type : typesInOrderAsStrings) { // convert strings to variableType objects
//                    typesInOrder.add(VariableUtils.stringToType(type));
//                }
//                varTypeArray = new VARIABLE_TYPES[typesInOrder.size()];
//                varTypeArray = typesInOrder.toArray(varTypeArray); //convert arrayList to array
//
//                namesArray = new String[namesInOrder.size()];
//                namesArray = namesInOrder.toArray(namesArray); //convert arrayList to array
//            }

    }

