package oop.ex6.parser;

import oop.ex6.main.exceptions.SjavaException;
import oop.ex6.main.exceptions.methodExceptions.*;
import oop.ex6.main.exceptions.parserExceptions.IllegalLineException;
import oop.ex6.main.exceptions.parserExceptions.ReturnStatementInGlobalScopeException;
import oop.ex6.main.exceptions.parserExceptions.UnbalancedScopeException;
import oop.ex6.main.exceptions.parserExceptions.UnexpectedExpressionAfterReturnException;
import oop.ex6.main.exceptions.variableExceptions.MultipleAssignmentWithoutDecleration;
import oop.ex6.main.exceptions.variableExceptions.VariableException;
import oop.ex6.methods.Method;
import oop.ex6.scopes.Scope;
import oop.ex6.variables.*;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * a parser class that parses a java-s file.
 */
public class Parser{

    /**
     * a method that goes through each line of the java-s file, translates the lines to commands and executes
     * the wanted actions on Scopes.
     * @param fileScanner - a scanner that runs on a legal file
     */
    public static void parseFile(Scanner fileScanner, Scope globalScope) throws SjavaException {
        int balancedBracketCounter = 0;
        int curLineNumber = 0;
        boolean lastRowIsReturn = true;
        while (fileScanner.hasNext()) {
            curLineNumber++;
            if (balancedBracketCounter < 0) { //there is a closing bracket in the file that does not correspond to a
                //valid opening bracket
                throw new UnbalancedScopeException();
            }
            String currentLine = fileScanner.nextLine();


            if (balancedBracketCounter == 0) { //i.e in global scope: read lines

                if (currentLine.matches(JavaSPatterns.EMPTY_LINE) || currentLine.matches(JavaSPatterns.COMMENT_LINE)) {
                    continue; // ignore empty and comment lines
                } else if (currentLine.matches(JavaSPatterns.VARIABLE_LINE) ||
                        currentLine.matches(JavaSPatterns.METHOD_SIGNATURE)) { // i.e line is legal
                    try {
                        parseLine(currentLine, curLineNumber, globalScope);
                    } catch (SjavaException e) {
                        e.addLineNumber(curLineNumber);
                        throw e;
                    }
                } else if (currentLine.matches(JavaSPatterns.RETURN)) { //no return statements expected in global scope
                    throw new ReturnStatementInGlobalScopeException();
                    // line is not empty, comment or legal - i.e illegal line
                } else {
                    throw new IllegalLineException();
                }
            }
            if (balancedBracketCounter != 0) { //inside a method declaration
                if (currentLine.matches(JavaSPatterns.RETURN)) {
                    lastRowIsReturn = true;
                }
                else if (currentLine.matches(JavaSPatterns.VARIABLE_LINE)||currentLine.matches(JavaSPatterns.METHOD_CALL)) { //line contains an expression other than empty line
                    lastRowIsReturn = false;
                }
            }

            //find opening and closing brackets, and update their counter accordingly
            if (currentLine.contains("{")) {
                balancedBracketCounter++;
            }
            if (currentLine.contains("}")) {
                balancedBracketCounter--;
                if(balancedBracketCounter == 0) {
                    if (!lastRowIsReturn) {
                        throw new UnexpectedExpressionAfterReturnException();
                    }
                }
            }
        }



            if (balancedBracketCounter != 0) { // reached end of file, number of opening and closing brackets does not match
                throw new UnbalancedScopeException();
            }
    }


    static void parseLine(String line, int lineNumber, Scope currentScope) throws SjavaException {
        if(line.matches(JavaSPatterns.METHOD_SIGNATURE)) {
            Method newMethod = Parser.parseMethodSignature(line);
            currentScope.addMethod(newMethod);}
        if(line.matches(JavaSPatterns.VARIABLE_LINE)){
            Parser.dealWithVariableLine(line, currentScope);}
    }

    public static void parseBlock(Scanner fileScanner, Scope scope, int lineNumber) throws SjavaException{
        String line;
        while (fileScanner.hasNext()) {
            lineNumber++;
            line = fileScanner.nextLine();
            try {
                if (line.matches(JavaSPatterns.COMMENT_LINE)||line.matches(JavaSPatterns.EMPTY_LINE)) {
                    continue;
                } else if (line.matches(JavaSPatterns.VARIABLE_LINE)) {
                    dealWithVariableLine(line, scope);
                } else if (line.matches(JavaSPatterns.METHOD_CALL)) {
                    dealWithMethodCall(line, scope);
                } else if (line.matches(JavaSPatterns.CONDITION_AND_BOOLEAN_IN_PARENTHESIS)) {
                    Scope innerScope = new Scope(lineNumber, scope);
                    parseBlock(fileScanner, innerScope, lineNumber);
                } else if (line.matches(JavaSPatterns.END_BLOCK)) {
                    return;
                } else {
                    throw new IllegalLineException();
                }
            } catch (SjavaException e) {
                e.addLineNumber(lineNumber);
                throw e;
            }

        }
    }

    static void dealWithVariableLine(String line, Scope scope) throws SjavaException{
        if(line.matches(JavaSPatterns.RETURN)) return;
        final int FINAL_GROUP = 3, TYPE_GROUP = 5, NAME_AND_VALUES_GROUP = 7, NAME_SUBGROUP = 2, VALUE_SUBGROUP = 4;
        Matcher lineMatcher = Pattern.compile(JavaSPatterns.VARIABLE_LINE).matcher(line);
        if (lineMatcher.matches()) {
            Matcher variablesMatcher = Pattern.compile(JavaSPatterns.VARIABLE_OR_ASSIGNMENT).matcher(lineMatcher.group(NAME_AND_VALUES_GROUP));
            boolean isFinal = lineMatcher.group(FINAL_GROUP) != null;
            boolean isDeclaration = lineMatcher.group(TYPE_GROUP) != null;
            if (isDeclaration) {
                Variable newVariable;
                VARIABLE_TYPES type = VariableUtils.stringToType(lineMatcher.group(TYPE_GROUP));
                while(variablesMatcher.find()) {
                    String name = variablesMatcher.group(NAME_SUBGROUP);
                    String value = variablesMatcher.group(VALUE_SUBGROUP);
                    if (VariableUtils.isNameLegal(value) && scope.searchVariableUpwards(value)!=null) {
                        newVariable = VariableFactory.produceVariable(type,name);
                        Variable oldVariable = scope.searchVariableUpwards(value);
                        oldVariable = VariableUtils.deepCopyVariable(oldVariable);
                        newVariable.setValue(oldVariable);
                    } else {
                        newVariable = VariableFactory.produceVariable(type, name, value);
                    } if (isFinal) {
                        FinalVariable newFinalVariable = new FinalVariable(newVariable);
                        scope.addVariable(newFinalVariable);
                    } else {
                        scope.addVariable(newVariable);
                    }
                }
            } else {
                if (variablesMatcher.matches()) {
                    String name = variablesMatcher.group(NAME_SUBGROUP);
                    String value = variablesMatcher.group(VALUE_SUBGROUP);
                    scope.updateVariable(name, value);
                } else {
                    throw new MultipleAssignmentWithoutDecleration(line);
                }
            }
            }
    }

    static void dealWithMethodCall(String line, Scope scope) throws MethodException{
        final int NAME_GROUP = 1, VALUES_GROUP = 2;
        Matcher lineMatcher = Pattern.compile(JavaSPatterns.METHOD_CALL).matcher(line);
        if(lineMatcher.matches()) {
            Matcher valuesMatcher = Pattern.compile(JavaSPatterns.VALUE).matcher(lineMatcher.group(VALUES_GROUP));
            String methodName = lineMatcher.group(NAME_GROUP);
            Method method = scope.searchMethod(methodName);
            if (method == null) {
                throw new NonExistingMethodException(methodName);
            }
            int valueIndex = -1;
            while (valuesMatcher.find()) {
                valueIndex++;
                String value = valuesMatcher.group();
                Variable variable;
                if (VariableUtils.isNameLegal(value)) {
                    variable = scope.searchVariableUpwards(value);
                    if (variable != null) {
                        try {
                            method.checkArgumentInIndex(valueIndex, variable);
                        } catch (VariableException e) {
                            throw new IllegalArgumentTypeException(method, valueIndex, variable);
                        }
                        continue;
                    }
                }
                try {
                    method.checkArgumentInIndex(valueIndex,value);
                } catch (VariableException e) {
                    throw new IllegalArgumentValueException(method, valueIndex, value);
                }
            }
            if (valueIndex != method.getNumOfArguments() - 1) {
                throw new WrongArgumentsNumberException(method, valueIndex + 1);
            }
        }
    }



    public static boolean parseDeep(Scanner fileScanner, Scope globalScope) throws SjavaException{
        int lineIndex = 0;
        while (fileScanner.hasNext()) {
            lineIndex++;
            String line = fileScanner.nextLine();
            if(line.matches(JavaSPatterns.METHOD_SIGNATURE)) {
                Method newMethod = parseMethodSignature(line);
                Scope scope = new Scope(lineIndex, globalScope, newMethod.getVariables());
                parseBlock(fileScanner, scope, lineIndex);
            }
        }
        return false;
    }

    static Method parseMethodSignature(String methodSignature) throws MethodException {
        final int NAME_GROUP = 3, ARGUMENTS_GROUP = 5, TYPE_SUB_GROUP = 2, NAME_SUB_GROUP = 3;
        Matcher methodMatcher = Pattern.compile(JavaSPatterns.METHOD_SIGNATURE).matcher(methodSignature);
        if (methodMatcher.matches()) {
            String methodName = methodMatcher.group(NAME_GROUP);
            String arguments = methodMatcher.group(ARGUMENTS_GROUP);
                if (arguments!=null) {
                Matcher variableMatcher = Pattern.compile(JavaSPatterns.VARIABLE_TYPE_NAME).matcher(arguments);
                    Method newMethod = new Method(methodName);
                    while (variableMatcher.find()) {
                        String name = variableMatcher.group(NAME_SUB_GROUP);
                        VARIABLE_TYPES type = VariableUtils.stringToType(variableMatcher.group(TYPE_SUB_GROUP));
                        try {
                            Variable newVariable = VariableFactory.produceVariable(type, name);
                            newMethod.addVariable(newVariable);
                        } catch (VariableException e) {
                            throw new IllegalArgumentNameException(name);
                        }
                    }
                    return newMethod;
            }
            return new Method(methodName);
        }
        return null;
    }

    public static void main(String[] args) throws SjavaException{
        JavaSPatterns.compilePatterns();
        Scope scope = new Scope(0);
        String line = "5,7";
        System.out.println(line.matches(JavaSPatterns.VALUE));

    }
}
