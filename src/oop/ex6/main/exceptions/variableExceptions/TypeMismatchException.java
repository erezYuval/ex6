package oop.ex6.main.exceptions.variableExceptions;

import oop.ex6.main.exceptions.SjavaException;

/**
 * Created by yuvalavrami on 6/14/15.
 */
public class TypeMismatchException extends SjavaException {
    protected final String ERROE_MESSAGE = "cannot assign to variable - assigned type does not match required type";
}
