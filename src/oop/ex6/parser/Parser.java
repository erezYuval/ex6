package oop.ex6.parser;

import oop.ex6.main.exceptions.SjavaException;
import oop.ex6.main.exceptions.methodExceptions.*;
import oop.ex6.main.exceptions.parserExceptions.*;
import oop.ex6.main.exceptions.variableExceptions.*;
import oop.ex6.methods.Method;
import oop.ex6.scopes.Scope;
import oop.ex6.variables.*;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * a parser class that have static methods the parse a java-s file.
 */
public class Parser{


    /**
     * a method that gets a Scope (the file's global scope), and a java-s file, and reads all the lines that
     * are qualified as legal lines in the global scope of the file, and executes the wanted java-s commands.
     * @param fileScanner - a scanner that runs on a legal file
     * @param globalScope the global scope of this java-s file.
     * @throws SjavaException if something went wrong attempting to execute the file.
     */
    public static void parseGlobalScope(Scanner fileScanner, Scope globalScope) throws SjavaException {
        int balancedBracketCounter = 0 , curLineNumber = 0;
        boolean lastRowIsReturn = true;
        while (fileScanner.hasNext()) {
            curLineNumber++;
            String currentLine = fileScanner.nextLine();
            if (balancedBracketCounter < 0) { //there is a closing bracket in the file that does not correspond to a
                //valid opening bracket
                throw new UnbalancedScopeException(curLineNumber);
            }
            if (balancedBracketCounter == 0) { //i.e in global scope: read lines
                if (currentLine.matches(JavaSPatterns.EMPTY_LINE) || currentLine.matches(JavaSPatterns.COMMENT_LINE)) {
                    continue; // ignore empty and comment lines
                } else if (currentLine.matches(JavaSPatterns.VARIABLE_LINE) ||
                        currentLine.matches(JavaSPatterns.METHOD_SIGNATURE)) { // i.e line is legal
                    try {
                        parseGlobalScopeLine(currentLine, globalScope);
                    } catch (SjavaException e) {
                        e.addLineNumber(curLineNumber);
                        throw e;
                    }
                } else if (currentLine.matches(JavaSPatterns.RETURN)) { //no return statements expected in global scope
                    throw new ReturnStatementInGlobalScopeException(curLineNumber);
                } else { // line is not empty, comment or legal - i.e illegal line
                    throw new IllegalLineException(currentLine, curLineNumber);
                }
            } else { //inside a method declaration
                if (currentLine.matches(JavaSPatterns.RETURN)) {
                    lastRowIsReturn = true;
                }
                else if (currentLine.matches(JavaSPatterns.VARIABLE_LINE)||
                        currentLine.matches(JavaSPatterns.METHOD_CALL)) {   //line contains an expression
                                                                            // other than empty line
                    lastRowIsReturn = false;
                }
            }

            if (currentLine.matches(JavaSPatterns.START_BLOCK)){ //find opening and closing brackets, and update counter
                balancedBracketCounter++;
            }
            if (currentLine.matches(JavaSPatterns.END_BLOCK)) {
                balancedBracketCounter--;
                if(balancedBracketCounter == 0 && !lastRowIsReturn) {
                        throw new UnexpectedExpressionAfterReturnException(curLineNumber);
                }
            }
        }
            if (balancedBracketCounter != 0) { // reached end of file, number of opening and closing brackets does not match
                throw new UnbalancedScopeException(curLineNumber);
            }
    }


    /*
     * parse a legal line of the global scope
     * @param line the line
     * @param globalScope the Scope object representing the global scope
     * @throws SjavaException if there is a problem with the execution of the code line
     */
    private static void parseGlobalScopeLine(String line, Scope globalScope) throws SjavaException {
        if(line.matches(JavaSPatterns.METHOD_SIGNATURE)) {
            Method newMethod = Parser.parseMethodSignature(line);
            globalScope.addMethod(newMethod);}
        if(line.matches(JavaSPatterns.VARIABLE_LINE)){
            Parser.dealWithVariableLine(line, globalScope);}
    }

    /**
     * a parser method that goes through all the lines in the file, and use parseInnerScopeBlock on each of the
     * methods in the java-s file
     * @param fileScanner a scanner that scans the java-s file
     * @param globalScope the Scope object representing the java-s global scope
     * @throws SjavaException if something goes wrong with the execution of the java-s file
     */
    public static void parseInsideMethods(Scanner fileScanner, Scope globalScope) throws SjavaException{
        final int NAME_GROUP = 3;
        int lineIndex = 0;
        while (fileScanner.hasNext()) {
            lineIndex++;
            String line = fileScanner.nextLine();
            Matcher methodMatcher = Pattern.compile(JavaSPatterns.METHOD_SIGNATURE).matcher(line);
            if(methodMatcher.matches()) {
                String methodName = methodMatcher.group(NAME_GROUP);
                Method parentMethod = globalScope.searchMethod(methodName);
                Scope scope = new Scope(globalScope, parentMethod.getVariables());
                parseInnerScopesBlock(fileScanner, scope, lineIndex);
            }
        }
    }

    /*
     * a parser method that parse and execute all commands of a single block, and all it's nested blocks
     * of a java-s file
     * @param fileScanner a scanner that runs through the file, currently looking at the scope's first line
     * @param scope the Scope object representing this scope.
     * @param lineNumber the line number of the scope's first line
     * @throws SjavaException if anything goes wrong with the execution of the java-s commands
     */
    private static void parseInnerScopesBlock(Scanner fileScanner, Scope scope, int lineNumber) throws SjavaException{
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
                    dealWithBooleanConditionLine(line, scope);
                    Scope innerScope = new Scope(scope);
                    parseInnerScopesBlock(fileScanner, innerScope, lineNumber);
                } else if (line.matches(JavaSPatterns.END_BLOCK)) {
                    return;
                } else {
                    throw new IllegalLineException(line);
                }
            } catch (SjavaException e) {
                e.addLineNumber(lineNumber);
                throw e;
            }

        }
    }

    /*\
     * a method that attempt to execute a variable line of java-s code
     * @param line the variable line
     * @param scope the current scope of the code
     * @throws SjavaException if anything goes wrong with the execution
     */
    private static void dealWithVariableLine(String line, Scope scope) throws SjavaException{
        final int FINAL_GROUP = 3, TYPE_GROUP = 5, NAME_AND_VALUES_GROUP = 7, NAME_SUBGROUP = 2, VALUE_SUBGROUP = 4;
        if(line.matches(JavaSPatterns.RETURN)) return;
        Matcher lineMatcher = Pattern.compile(JavaSPatterns.VARIABLE_LINE).matcher(line);
        if (lineMatcher.matches()) {
            Matcher variablesMatcher =
                    Pattern.compile(JavaSPatterns.VARIABLE_OR_ASSIGNMENT).matcher(
                                                                            lineMatcher.group(NAME_AND_VALUES_GROUP));
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

    /*
     * a method that deals with a java-s line that is formatted as a method call.
     * @param line the line
     * @param scope a Scope object of the current scope of the java-s file
     * @throws MethodException if anything goes wrong with the call execution
     */
    private static void dealWithMethodCall(String line, Scope scope) throws MethodException{
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
                if (VariableUtils.isNameLegal(value)) { // the given argument may be a variable
                    variable = scope.searchVariableUpwards(value);
                    if (variable != null) { // if there was a variable with this name
                        try {
                            method.checkArgumentInIndex(valueIndex, variable);
                        } catch (VariableException e) {
                            throw new IllegalArgumentTypeException(method, valueIndex, variable);
                        }
                        continue; // if it worked we continue to the next argument
                    }
                }
                try {  // try use the argument as a value.
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


    /*
     * deal with a boolean condition line (such as "if (a && b || 3) { ")
     * @param line the line
     * @param scope the Scope representing the current java-s file scope
     * @throws VariableException if any of the things inside the parenthesis aren't boolean conditions
     */
    private static void dealWithBooleanConditionLine(String line, Scope scope) throws VariableException{
        final int INSIDE_PARENTHESIS_GROUP = 1;
        final String DEFAULT_NAME_FOR_BOOLEAN = "bool";
        Matcher insideParenthesis = Pattern.compile(JavaSPatterns.INSIDE_PARENTHESIS).matcher(line);
        if (insideParenthesis.matches()) {
            Variable booleanTester = new VariableBoolean(DEFAULT_NAME_FOR_BOOLEAN);
            String theInside = insideParenthesis.group(INSIDE_PARENTHESIS_GROUP);
            Matcher valueOrNameMatcher = Pattern.compile(JavaSPatterns.VALUE).matcher(theInside);
            while (valueOrNameMatcher.find()) {
                String valueOrName = valueOrNameMatcher.group();
                Variable isVariable = scope.searchVariableUpwards(valueOrName);
                try {
                    if (isVariable != null) {
                        booleanTester.setValue(isVariable);
                    } else {
                        booleanTester.setValue(valueOrName);
                    }
                } catch (VariableException e) {
                    throw new IllegalBooleanCondition(valueOrName);
                }
            }
        }
    }

    /*
     * a method that gets line representing a method signature and create the appropriate Method object
     * @param methodSignature the String representing a method signature
     * @return a Method object representing the wanted method in the java-s file
     * @throws MethodException if anything went wrong with the creation of the method
     */
    private static Method parseMethodSignature(String methodSignature) throws MethodException {
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
}
