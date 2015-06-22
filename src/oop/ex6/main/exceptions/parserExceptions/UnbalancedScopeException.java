package oop.ex6.main.exceptions.parserExceptions;

import oop.ex6.main.exceptions.SjavaException;

/**
 an exception representing a state where the numbers of opening and closing curly brackets in the code do not match,
 i.e there is an unclosed scope / a closing bracket that foes not correspond to an opening one
 */
public class UnbalancedScopeException extends SjavaException{
    private final static String TYPE_ERROR_MESSAGE =
            "could not parse file - mismatched number of opening and closing brackets";

    /**
     * constructor for exception. holds an error message according to the type of exception.
     */
    public UnbalancedScopeException(int line) {
        ERROR_MESSAGE = TYPE_ERROR_MESSAGE;
        this.addLineNumber(line);
    }
}
