package oop.ex6.main.exceptions.methodExceptions;

import oop.ex6.main.exceptions.SjavaException;
import oop.ex6.methods.Method;
import oop.ex6.variables.Variable;

/**
 * Created by yuvalavrami on 6/14/15.
 */
public class IllegalArgumentTypeException extends MethodException {
    private final static String TYPE_ERROR_MESSAGE = "cannot assign argument with this type: type is illegal";

    public IllegalArgumentTypeException(Method method, int argumentIndex, Variable variable) {
        uniqueMessage = "in " + method.getName() + " method the variable " + variable +
                "("+variable.getVariableType().toString() +") as the " +argumentIndex + " argument is illegal";
        ERROR_MESSAGE = TYPE_ERROR_MESSAGE;
    }
}
