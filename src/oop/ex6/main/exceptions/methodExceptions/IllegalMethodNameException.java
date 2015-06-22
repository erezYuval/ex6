package oop.ex6.main.exceptions.methodExceptions;

/**
 * Created by yuvalavrami on 6/14/15.
 */
public class IllegalMethodNameException extends MethodException {
    private final static String TYPE_ERROR_MESSAGE = "cannot assign this name to method: name is illegal";

    /**
     * constructor for exception. holds an error message according to the type of exception,
     * and a unique message updated according to the illegal method name for which it was thrown.
     */
    public IllegalMethodNameException(String name) {
        ERROR_MESSAGE = TYPE_ERROR_MESSAGE;
        uniqueMessage = "the name -" + name + "- is an illegal name for a method.";
    }
}
