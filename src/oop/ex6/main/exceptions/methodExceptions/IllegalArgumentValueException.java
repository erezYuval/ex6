package oop.ex6.main.exceptions.methodExceptions;

import oop.ex6.main.exceptions.SjavaException;
import oop.ex6.methods.Method;
import oop.ex6.variables.Variable;

/**
 * Created by yuvalavrami on 6/14/15.
 */
public class IllegalArgumentValueException extends MethodException {
    private final static String TYPE_ERROR_MESSAGE = "cannot assign argument with this value: value is illegal";

    public IllegalArgumentValueException(Method method, int argumentIndex, String value) {
        uniqueMessage = "in " + method.getName() + " method the value " + value + " as the " +argumentIndex + " is " +
                "illegal";
        ERROR_MESSAGE = TYPE_ERROR_MESSAGE;
    }
}
