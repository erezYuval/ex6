package oop.ex6.main.exceptions.variableExceptions;

import oop.ex6.variables.Variable;

/**
 an exception representing an attempted assignment of a variable with a mismatching type (wither a value that is not
 valid for the declared type of the variable, or another variable whose type does match the declared type).
 */
public class TypeMismatchException extends VariableException {

    private final String TYPE_ERROR_MESSAGE = "cannot assign to variable - assigned type does not match required type";

    /**
     * constructor for exception. holds an error message according to the type of exception,
     * and a unique message updated according to the illegal assignment for which it was thrown.
     */
    public TypeMismatchException(Variable intoVariable, String value) {
        ERROR_MESSAGE = TYPE_ERROR_MESSAGE;
        String type = intoVariable.getVariableType().toString();
        String variableName = intoVariable.toString();
        uniqueMessage = "cannot assign variable " + variableName + " of type " +type + " with the value " + value;
    }

    /**
     * constructor for exception. holds an error message according to the type of exception,
     * and a unique message updated according to the illegal assignment for which it was thrown.
     */
    public TypeMismatchException(Variable intoVariable, Variable variable) {
        ERROR_MESSAGE = TYPE_ERROR_MESSAGE;
        String intoType = intoVariable.getVariableType().toString();
        String intoName = intoVariable.toString();
        String ofType = variable.getVariableType().toString();
        String ofName = variable.toString();
        uniqueMessage = "cannot assign variable " + intoName + " of type "+intoType + " with the variable" + ofName +
                " of type " + ofType;
    }
}
