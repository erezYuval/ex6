package oop.ex6.main.exceptions.scopeExceptions;

import oop.ex6.main.exceptions.SjavaException;

/**
 * Created by yuvalavrami on 6/14/15.
 */
public class NonexistingVariableException extends SjavaException {
   protected final static String ERROR_MESSAGE = "variable does not exist";
}
