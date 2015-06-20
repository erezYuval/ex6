package oop.ex6.main.exceptions.variableExceptions;

import oop.ex6.main.exceptions.SjavaException;

/**
 * Created by yuvalavrami on 6/14/15.
 */
public class IllegalNameException extends VariableException {
    private final static String TYPE_ERROR_MESSAGE = "cannot assign this name to variable: name is illegal";

    public IllegalNameException(String name) {
        ERROR_MESSAGE = TYPE_ERROR_MESSAGE;
        uniqueMessage = "The name -" + name + "- is illegal";
    }

}
