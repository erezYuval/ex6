package oop.ex6.main.exceptions.variableExceptions;

/**
 * Created by yuvalavrami on 6/14/15.
 */
public class AttemptedChangeFinalVarException extends FinalVariableException{
    protected final static String ERROR_MESSAGE = "cannot assign a value to final variable";

    public AttemptedChangeFinalVarException(String variableName) {
        uniqueMessage = "cannot change final variable " + variableName;
    }
}
