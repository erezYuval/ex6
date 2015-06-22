package oop.ex6.main.exceptions.variable_exceptions;

/**
 an exception representing an attempted assignment of a variable with an illegal name.
 */
public class IllegalNameException extends VariableException {
    private final static String TYPE_ERROR_MESSAGE = "cannot assign this name to variable: name is illegal";

    /**
     * constructor for exception. holds an error message according to the type of exception,
     * and a unique message updated according to the illegal name for which it was thrown.
     * @param name relevant illegal name
     */
    public IllegalNameException(String name) {
        ERROR_MESSAGE = TYPE_ERROR_MESSAGE;
        uniqueMessage = "The name -" + name + "- is illegal";
    }

}
