package oop.ex6.main.exceptions.parserExceptions;

import oop.ex6.main.exceptions.SjavaException;

/**
 * Created by yuvalavrami on 6/14/15.
 */
public class UnexpectedExpressionAfterReturnException extends SjavaException{
    private final static String TYPE_ERROR_MESSAGE = "unexpected expression after return statement";

    /**
     * constructor for exception. holds an error message according to the type of exception.
     */
    public UnexpectedExpressionAfterReturnException(int lineNumber) {
        ERROR_MESSAGE = TYPE_ERROR_MESSAGE;
        this.addLineNumber(lineNumber);
    }
}
