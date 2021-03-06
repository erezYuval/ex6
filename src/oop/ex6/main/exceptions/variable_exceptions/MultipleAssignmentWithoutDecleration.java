package oop.ex6.main.exceptions.variable_exceptions;

import oop.ex6.variables.Variable;

/**
 an exception representing an attempted assignment of multiple variables in the same line, without initialization.
 */
public class MultipleAssignmentWithoutDecleration extends VariableException {

    private final String TYPE_ERROR_MESSAGE = "cannot assign multiple variables in a non-declaring line";

    /**
     * constructor for exception. holds an error message according to the type of exception,
     * and a unique message updated according to illegal line variable for which it was thrown.
     * @param line relevant line
     */
    public MultipleAssignmentWithoutDecleration (String line) {
        ERROR_MESSAGE = TYPE_ERROR_MESSAGE;
        uniqueMessage = "the line - " + line + "- is illegal";
    }

    /**
     * constructor for exception. holds an error message according to the type of exception,
     * and a unique message updated according to illegal assignment for which it was thrown.
     * @param intoVariable variable assignment is made to
     * @param variable variable assignment is made from
     */
    public MultipleAssignmentWithoutDecleration(Variable intoVariable, Variable variable) {
        ERROR_MESSAGE = TYPE_ERROR_MESSAGE;
        String intoType = intoVariable.getVariableType().toString();
        String intoName = intoVariable.toString();
        String ofType = variable.getVariableType().toString();
        String ofName = variable.toString();
        uniqueMessage = "cannot assign variable " + intoName + " of type "+intoType + " with the variable" + ofName +
                " of type " + ofType;
    }
}
