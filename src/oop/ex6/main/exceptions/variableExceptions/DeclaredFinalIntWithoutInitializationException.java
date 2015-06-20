package oop.ex6.main.exceptions.variableExceptions;

/**
 * Created by yuvalavrami on 6/14/15.
 */
public class DeclaredFinalIntWithoutInitializationException extends FinalVariableException{
    private final static String TYPE_ERROR_MESSAGE = "cannot declare final variable without initialization";

    public DeclaredFinalIntWithoutInitializationException(String variableName) {
        ERROR_MESSAGE = TYPE_ERROR_MESSAGE;
        uniqueMessage = "the final variable " + variableName + " is initialized without a value";
    }
}
