package oop.ex6.main.exceptions.scopeExceptions;

import oop.ex6.main.exceptions.SjavaException;
import oop.ex6.scopes.Scope;

/**
 * Created by yuvalavrami on 6/14/15.
 */
public class VarNameExistsInBlockException extends ScopeException {
    private final static String TYPE_ERROR_MESSAGE = "cannot assign this name to variable - name already exists in block";

    public VarNameExistsInBlockException (String variableName) {
        ERROR_MESSAGE = TYPE_ERROR_MESSAGE;
        uniqueMessage = "the variable -" + variableName + "- already exists in this scope.";
    }
}
