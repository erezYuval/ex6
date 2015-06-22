package oop.ex6.main.exceptions.scope_exceptions;

/**
 an exception representing a state where a variable name already exists in the current block.
 */
public class VarNameExistsInBlockException extends ScopeException {
    private final static String TYPE_ERROR_MESSAGE =
            "cannot assign this name to variable - name already exists in block";

    /**
     * constructor for exception. holds an error message according to the type of exception,
     * and a unique message updated according to the existing variable that was called for which it was thrown.
     * @param variableName relevant variable's name
     */
    public VarNameExistsInBlockException (String variableName) {
        ERROR_MESSAGE = TYPE_ERROR_MESSAGE;
        uniqueMessage = "the variable -" + variableName + "- already exists in this scope.";
    }
}
