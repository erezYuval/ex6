package oop.ex6.variables;

import oop.ex6.main.exceptions.variableExceptions.VariableException;

/**
 * a collection of static utility methods to Variable class.
 */
public class VariableUtils {

    private final static String LEGAL_NAME = "((_\\w+)|([a-zA-Z]\\w*))";

    /**
     * determines whether a string is legal as a variable name.
     * @param name the string to be determined
     * @return true if the string is a legal variable name, false otherwise.
     */
    public static boolean isNameLegal(String name) {
        return name != null && name.matches(LEGAL_NAME);
    }

    public static Variable deepCopyVariable(Variable variable)throws VariableException {
        Variable variableCopy = VariableFactory.produceVariable(variable.getVariableType(),variable.toString());
        if(variable.isInitialized()){
            variableCopy.setInitialized();
        }
        return variableCopy;
    }

    public static VARIABLE_TYPES stringToType(String type) {
        for (VARIABLE_TYPES variableTypes : VARIABLE_TYPES.values()) {
            if (type.equals(variableTypes.toString())) {
                return variableTypes;
            }
        }
        return null;
    }

}
