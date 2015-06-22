package oop.ex6.main.exceptions.parser_exceptions;

import oop.ex6.main.exceptions.SjavaException;

/**
 an exception representing a state where a scope contains expressions other than an empty line after the last
 return statement.
 */
public class UnexpectedExpressionAfterReturnException extends SjavaException{
    private final static String TYPE_ERROR_MESSAGE = "unexpected expression after return statement";

    /**
     * constructor for exception. holds an error message according to the type of exception.
     * @param lineNumber
     */
    public UnexpectedExpressionAfterReturnException(int lineNumber) {
        ERROR_MESSAGE = TYPE_ERROR_MESSAGE;
        this.addLineNumber(lineNumber);
    }
}
