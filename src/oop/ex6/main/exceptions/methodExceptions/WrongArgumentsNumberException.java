package oop.ex6.main.exceptions.methodExceptions;

import oop.ex6.main.exceptions.SjavaException;
import oop.ex6.methods.Method;

/**
 * Created by yuvalavrami on 6/14/15.
 */
public class WrongArgumentsNumberException extends MethodException {
    private final static String TYPE_ERROR_MESSAGE =
            "number of arguments received does not match number of arguments required by this method";
    public WrongArgumentsNumberException(Method method, int numOfArgs) {
        ERROR_MESSAGE = TYPE_ERROR_MESSAGE;
        uniqueMessage = "the method -" + method.getName() + "- should get " + method.getNumOfArguments() + " arguments"
                + " but got " + numOfArgs + ".";
    }
}
