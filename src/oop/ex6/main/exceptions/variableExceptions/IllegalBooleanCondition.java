package oop.ex6.main.exceptions.variableExceptions;

/**
 * Created by yuvalavrami on 6/14/15.
 */
public class IllegalBooleanCondition extends VariableException {
    private final static String TYPE_ERROR_MESSAGE = "cannot use this variable or value as a boolean condition";

    public IllegalBooleanCondition(String name) {
        ERROR_MESSAGE = TYPE_ERROR_MESSAGE;
        uniqueMessage = name + "- is not a boolean expression";
    }

}
