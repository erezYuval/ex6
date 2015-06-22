package oop.ex6.main.exceptions.parser_exceptions;

import oop.ex6.main.exceptions.SjavaException;

/**
 an exception representing a state where the global scope of the code contains a return statement, not in accordance
 with S-Java specifications.
 */
public class ReturnStatementInGlobalScopeException extends SjavaException{
    private final static String TYPE_ERROR_MESSAGE = "unexpected return statement in global scope";

    /**
     * constructor for exception. holds an error message according to the type of exception.
     * @param linenumber
     */
    public ReturnStatementInGlobalScopeException(int linenumber) {
        ERROR_MESSAGE = TYPE_ERROR_MESSAGE;
        this.addLineNumber(linenumber);
    }


}
