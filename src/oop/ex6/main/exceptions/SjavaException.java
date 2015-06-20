package oop.ex6.main.exceptions;

/**
 * Created by yuvalavrami on 6/18/15.
 */
public abstract class SjavaException extends Exception {

    protected String ERROR_MESSAGE;
    protected String uniqueMessage;
    private int lineNumber;

    public String getErrorMessage(){
        return ERROR_MESSAGE;
    }

    public String getMessage() {
        return "Error in line " + lineNumber + "\n\t" + ERROR_MESSAGE + "\n" + uniqueMessage;
    }

    public void addLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }
}
