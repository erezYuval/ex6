package oop.ex6.main.exceptions.variable_exceptions;

/**
 an exception representing an attempted change to a final variable.
 */
public class AttemptedChangeFinalVarException extends FinalVariableException{
    private final static String TYPE_ERROR_MESSAGE = "cannot assign a value to final variable";

    /**
     * constructor for exception. holds an error message according to the type of exception,
     * and a unique message updated according to the final variable for which it was thrown.
     * @param variableName relevant variable's name
     */
    public AttemptedChangeFinalVarException(String variableName) {
        ERROR_MESSAGE = TYPE_ERROR_MESSAGE;
        uniqueMessage = "cannot change final variable " + variableName;
    }
}
