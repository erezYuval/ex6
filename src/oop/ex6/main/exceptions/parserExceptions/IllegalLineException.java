package oop.ex6.main.exceptions.parserExceptions;
import oop.ex6.main.exceptions.*;

/**
 * Created by yuvalavrami on 6/14/15.
 */
public class IllegalLineException extends SjavaException{
    String errorMessage = "could not parse file - found an illegal line";
}
