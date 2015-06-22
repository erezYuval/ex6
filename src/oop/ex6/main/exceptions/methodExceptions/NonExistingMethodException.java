package oop.ex6.main.exceptions.methodExceptions;

/**
 * Created by yuvalavrami on 6/14/15.
 */
public class NonExistingMethodException extends MethodException {
    private final static String TYPE_ERROR_MESSAGE =
            "can't call unexisting method.";

    /**
     * constructor for exception. holds an error message according to the type of exception,
     * and a unique message updated according to the illegal method name for which it was thrown.
     */
    public NonExistingMethodException(String methodName) {
        ERROR_MESSAGE = TYPE_ERROR_MESSAGE;
        uniqueMessage = "the method -" + methodName + "- doesn't exist";
    }
}
