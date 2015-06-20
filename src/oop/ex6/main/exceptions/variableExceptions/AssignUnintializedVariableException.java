package oop.ex6.main.exceptions.variableExceptions;

import oop.ex6.variables.Variable;


public class AssignUnintializedVariableException extends VariableException {

    private final String TYPE_ERROR_MESSAGE = "cannot assign to variable - source variable is not initialized";

    public AssignUnintializedVariableException(Variable variable) {
        ERROR_MESSAGE = TYPE_ERROR_MESSAGE;
        uniqueMessage = "variable -" + variable.toString() + "- is not initialized";
    }
}
