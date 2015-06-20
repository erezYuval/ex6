package oop.ex6.main.exceptions.parserExceptions;

import oop.ex6.main.exceptions.SjavaException;

/**
 * Created by yuvalavrami on 6/14/15.
 */
public class UnbalancedScopeException extends SjavaException{
    protected final static String ERROR_MESSAGE = "could not parse file - mismatched number of opening and closing brackets";
}
