package oop.ex6.main.exceptions.scopeExceptions;

import oop.ex6.main.exceptions.SjavaException;
import oop.ex6.variables.Variable;

/**
 * Created by yuvalavrami on 6/14/15.
 */
public class NonexistingVariableException extends ScopeException {
   private final static String TYPE_ERROR_MESSAGE = "variable does not exist";

   public NonexistingVariableException(String variableName) {
      ERROR_MESSAGE = TYPE_ERROR_MESSAGE;
      uniqueMessage = "the variable -" + variableName + "- doesn't exist.";
   }
}
