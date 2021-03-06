package oop.ex6.main.exceptions.parser_exceptions;
import oop.ex6.main.exceptions.*;

/**
 an exception representing a state where a line in the code is illegal.
 */
public class IllegalLineException extends SjavaException{
    protected final static String TYPE_ERROR_MESSAGE = "could not parse file - found an illegal line";

    /**
     * constructor for exception. holds an error message according to the type of exception,
     * and a unique message updated according to the illegal line for which it was thrown.
     * @param line relevant line
     */
    public IllegalLineException (String line) {
        ERROR_MESSAGE = TYPE_ERROR_MESSAGE;
        uniqueMessage = "the line \n\t" + line + "\nis illegal.";
    }

    /**
     * constructor for exception. holds an error message according to the type of exception,
     * and a unique message updated according to the illegal line for which it was thrown.
     * @param line relevant line
     * @param lineNumber relevant line's number in code
     */
    public IllegalLineException (String line, int lineNumber) {
        ERROR_MESSAGE = TYPE_ERROR_MESSAGE;
        uniqueMessage = "the line \n\t" + line + "\nis illegal.";
        this.addLineNumber(lineNumber);
    }
}
