package oop.ex6.main.exceptions.methodExceptions;

import oop.ex6.methods.Method;

/**
 * Created by yuvalavrami on 6/14/15.
 */
public class NonExistingMethodException extends MethodException {
    private final static String TYPE_ERROR_MESSAGE =
            "can't call unexisting method.";
    public NonExistingMethodException(String methodName) {
        ERROR_MESSAGE = TYPE_ERROR_MESSAGE;
        uniqueMessage = "the method -" + methodName + "- doesn't exist";
    }
}
