package oop.ex6.main.exceptions.variableExceptions;

/**
 * Created by yuvalavrami on 6/14/15.
 */
public class IllegalBooleanCondition extends VariableException {
    private final static String TYPE_ERROR_MESSAGE = "cannot use this variable or value as a boolean condition";

    /**
     * constructor for exception. holds an error message according to the type of exception,
     * and a unique message updated according to the illegal boolean value for which it was thrown.
     */
    public IllegalBooleanCondition(String name) {
        ERROR_MESSAGE = TYPE_ERROR_MESSAGE;
        uniqueMessage = name + "- is not a boolean expression";
    }

}
