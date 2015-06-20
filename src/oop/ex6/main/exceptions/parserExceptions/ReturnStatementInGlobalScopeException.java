package oop.ex6.main.exceptions.parserExceptions;

import oop.ex6.main.exceptions.SjavaException;

/**
 * Created by yuvalavrami on 6/14/15.
 */
public class ReturnStatementInGlobalScopeException extends SjavaException{
    protected final static String ERROR_MESSAGE = "unexpected return statement in global scope";
}
