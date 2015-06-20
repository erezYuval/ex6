package oop.ex6.main.exceptions.methodExceptions;

import oop.ex6.main.exceptions.SjavaException;

/**
 * Created by yuvalavrami on 6/14/15.
 */
public class IllegalMethodNameException extends SjavaException {
    protected final String ERROR_MESSAGE = "cannot assign this name to method: name is illegal";
}
