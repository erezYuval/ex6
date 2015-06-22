package oop.ex6.main.exceptions.variable_exceptions;

import oop.ex6.variables.Variable;

/**
 an exception representing a state where an uninitialized variable is attempted assignment into another variable
 */
public class AssignUnintializedVariableException extends VariableException {

    private final String TYPE_ERROR_MESSAGE = "cannot assign to variable - source variable is not initialized";

    /**
     * constructor for exception. holds an error message according to the type of exception,
     * and a unique message updated according to the uninitialized variable for which it was thrown.
     * @param variable
     */
    public AssignUnintializedVariableException(Variable variable) {
        ERROR_MESSAGE = TYPE_ERROR_MESSAGE;
        uniqueMessage = "variable -" + variable.toString() + "- is not initialized";
    }
}
