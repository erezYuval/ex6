package oop.ex6.main.exceptions.scopeExceptions;

/**
 * Created by yuvalavrami on 6/14/15.
 */
public class NonexistingVariableException extends ScopeException {
   private final static String TYPE_ERROR_MESSAGE = "variable does not exist";

   /**
    * constructor for exception. holds an error message according to the type of exception,
    * and a unique message updated according to the non-existing variable that was called for which it was thrown.
    */
   public NonexistingVariableException(String variableName) {
      ERROR_MESSAGE = TYPE_ERROR_MESSAGE;
      uniqueMessage = "the variable -" + variableName + "- doesn't exist.";
   }
}
