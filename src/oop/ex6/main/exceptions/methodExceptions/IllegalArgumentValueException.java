package oop.ex6.main.exceptions.methodExceptions;

import oop.ex6.main.exceptions.SjavaException;

/**
 * Created by yuvalavrami on 6/14/15.
 */
public class IllegalArgumentValueException extends SjavaException {
    String errorMessage = "cannot assign argument with this value: value is illegal for this type";
}
