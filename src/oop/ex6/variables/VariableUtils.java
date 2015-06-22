package oop.ex6.variables;

import oop.ex6.main.exceptions.variable_exceptions.VariableException;

/**
 * a collection of static utility methods to Variable class.
 */
public class VariableUtils {

    private final static String LEGAL_NAME = "((_\\w+)|([a-zA-Z]\\w*))"; //regular expression for a legal variable name

    /**
     * determines whether a string is legal as a variable name.
     * @param name the string to be determined
     * @return true if the string is a legal variable name, false otherwise.
     */
    public static boolean isNameLegal(String name) {
        return name != null && name.matches(LEGAL_NAME);
    }

    /**
     * creates a copy for a variable, identical to the given variable
     * @param variable the variable to be copied
     * @return variableCopy a variable identical to the given one
     * @throws VariableException if anything is wrong in the copy process
     */
    public static Variable deepCopyVariable(Variable variable)throws VariableException {
        Variable variableCopy = VariableFactory.produceVariable(variable.getVariableType(),variable.toString());
        if(variable.isInitialized()){
            variableCopy.setInitialized();
        }
        if(variable.isFinal()) {
            return new FinalVariable(variable);
        }
        return variableCopy;
    }

    /**
     * converts a string representation of a type as appears in a s-java code file to its respective type
     * @param type the wanted variable type as a string
     * @return the variable type the goes with this string
     */
    public static VariableTypes stringToType(String type) {
        for (VariableTypes variableTypes : VariableTypes.values()) {
            if (type.equals(variableTypes.toString())) {
                return variableTypes;
            }
        }
        return null;
    }

}
