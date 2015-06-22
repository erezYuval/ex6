package oop.ex6.main.exceptions.variable_exceptions;

/**
 an exception representing an attempted declaration of a final variable without initialization with a value
 */
public class DeclaredFinalIntWithoutInitializationException extends FinalVariableException{
    private final static String TYPE_ERROR_MESSAGE = "cannot declare final variable without initialization";

    /**
     * constructor for exception. holds an error message according to the type of exception,
     * and a unique message updated according to the final variable for which it was thrown.
     */
    public DeclaredFinalIntWithoutInitializationException(String variableName) {
        ERROR_MESSAGE = TYPE_ERROR_MESSAGE;
        uniqueMessage = "the final variable " + variableName + " is initialized without a value";
    }
}
