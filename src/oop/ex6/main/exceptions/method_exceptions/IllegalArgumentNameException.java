package oop.ex6.main.exceptions.method_exceptions;

/**
 an exception representing a state where a method argument's name is illegal.
 */
public class IllegalArgumentNameException extends MethodException{
    private final static String TYPE_ERROR_MESSAGE = "cannot use argument with this name: name is illegal";

    /**
     * constructor for exception. holds an error message according to the type of exception,
     * and a unique message updated according to the illegal name for which it was thrown.
     * @param name argument's name
     */
    public IllegalArgumentNameException(String name) {
        ERROR_MESSAGE = TYPE_ERROR_MESSAGE;
        uniqueMessage = "the name -" + name + "- is illegal for an argument";
    }
}
