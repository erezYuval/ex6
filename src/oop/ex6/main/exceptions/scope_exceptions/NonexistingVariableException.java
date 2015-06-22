package oop.ex6.main.exceptions.scope_exceptions;

/**
 an exception representing a state where a called variable does not exist
 */
public class NonexistingVariableException extends ScopeException {
   private final static String TYPE_ERROR_MESSAGE = "variable does not exist";

   /**
    * constructor for exception. holds an error message according to the type of exception,
    * and a unique message updated according to the non-existing variable that was called for which it was thrown.
    * @param variableName
    */
   public NonexistingVariableException(String variableName) {
      ERROR_MESSAGE = TYPE_ERROR_MESSAGE;
      uniqueMessage = "the variable -" + variableName + "- doesn't exist.";
   }
}
