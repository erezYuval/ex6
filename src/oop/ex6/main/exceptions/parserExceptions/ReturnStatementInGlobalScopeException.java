package oop.ex6.main.exceptions.parserExceptions;

import oop.ex6.main.exceptions.SjavaException;

/**
 * Created by yuvalavrami on 6/14/15.
 */
public class ReturnStatementInGlobalScopeException extends SjavaException{
    private final static String TYPE_ERROR_MESSAGE = "unexpected return statement in global scope";

    public ReturnStatementInGlobalScopeException(int linenumber) {
        ERROR_MESSAGE = TYPE_ERROR_MESSAGE;
        this.addLineNumber(linenumber);
    }


}
