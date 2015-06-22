package oop.ex6.main.exceptions.methodExceptions;

import oop.ex6.methods.Method;
import oop.ex6.variables.Variable;

/**
 an exception representing a state where a method argument's type is illegal.
 */
public class IllegalArgumentTypeException extends MethodException {
    private final static String TYPE_ERROR_MESSAGE = "cannot assign argument with this type: type is illegal";

    /**
     * constructor for exception. holds an error message according to the type of exception,
     * and a unique message updated according to the illegal argument for which it was thrown.
     */
    public IllegalArgumentTypeException(Method method, int argumentIndex, Variable variable) {
        uniqueMessage = "in " + method.getName() + " method the variable " + variable +
                "("+variable.getVariableType().toString() +") as the " +argumentIndex + " argument is illegal";
        ERROR_MESSAGE = TYPE_ERROR_MESSAGE;
    }
}
