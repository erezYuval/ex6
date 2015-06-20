package oop.ex6.main.exceptions.methodExceptions;

import oop.ex6.main.exceptions.SjavaException;
import oop.ex6.main.exceptions.variableExceptions.IllegalNameException;

/**
 * Created by yuvalavrami on 6/14/15.
 */
public class IllegalArgumentNameException extends MethodException{
    private final static String TYPE_ERROR_MESSAGE = "cannot use argument with this name: name is illegal";

    public IllegalArgumentNameException(String name) {
        ERROR_MESSAGE = TYPE_ERROR_MESSAGE;
        uniqueMessage = "the name -" + name + "- is illegal for an argument";
    }
}
