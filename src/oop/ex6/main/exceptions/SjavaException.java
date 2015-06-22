package oop.ex6.main.exceptions;

/**
 * Created by yuvalavrami on 6/18/15.
 */
public abstract class SjavaException extends Exception {

    // data members all SJavaExceptions must have //
    protected String ERROR_MESSAGE; // an error message according to the type of exception
    protected String uniqueMessage; // a unique, more informative message, updated according to the illegal value that
        //caused it
    private int lineNumber; //lineNumber in which the exception was thrown, for error message purposes

    /**
     * @return an exception's error messages
     */
    public String getMessage() {
        return "Error in line " + lineNumber + "\n\t" + ERROR_MESSAGE + "\n" + uniqueMessage;
    }

    /**
     * adds a line number to the exception's error message
     * @param lineNumber
     */
    public void addLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }
}
