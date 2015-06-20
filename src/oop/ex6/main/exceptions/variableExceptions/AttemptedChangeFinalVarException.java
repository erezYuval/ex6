package oop.ex6.main.exceptions.variableExceptions;

/**
 * Created by yuvalavrami on 6/14/15.
 */
public class AttemptedChangeFinalVarException extends FinalVariableException{
    private final static String TYPE_ERROR_MESSAGE = "cannot assign a value to final variable";

    public AttemptedChangeFinalVarException(String variableName) {
        ERROR_MESSAGE = TYPE_ERROR_MESSAGE;
        uniqueMessage = "cannot change final variable " + variableName;
    }
}
