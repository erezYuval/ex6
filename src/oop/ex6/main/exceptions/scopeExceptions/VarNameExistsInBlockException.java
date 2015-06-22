package oop.ex6.main.exceptions.scopeExceptions;

/**
 * Created by yuvalavrami on 6/14/15.
 */
public class VarNameExistsInBlockException extends ScopeException {
    private final static String TYPE_ERROR_MESSAGE = "cannot assign this name to variable - name already exists in block";

    /**
     * constructor for exception. holds an error message according to the type of exception,
     * and a unique message updated according to the existing variable that was called for which it was thrown.
     */
    public VarNameExistsInBlockException (String variableName) {
        ERROR_MESSAGE = TYPE_ERROR_MESSAGE;
        uniqueMessage = "the variable -" + variableName + "- already exists in this scope.";
    }
}
