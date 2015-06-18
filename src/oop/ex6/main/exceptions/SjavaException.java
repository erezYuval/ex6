package oop.ex6.main.exceptions;

/**
 * Created by yuvalavrami on 6/18/15.
 */
public abstract class SjavaException extends Exception {

    protected String ERROR_MESSAGE;

    public String getErrorMessage(){
        return ERROR_MESSAGE;
    }
}
