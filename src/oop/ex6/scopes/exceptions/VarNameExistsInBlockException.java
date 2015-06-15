package oop.ex6.scopes.exceptions;

/**
 * Created by yuvalavrami on 6/14/15.
 */
public class VarNameExistsInBlockException extends Exception{
    String errorMessage = "cannot assign this name to variable - name already exists in block";
}
