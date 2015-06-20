package oop.ex6.variables;

/**
 * a collection of static utility methods to Variable class.
 */
public class VariableUtils {

    /**
     * determines whether a string is legal as a variable name.
     * @param name the string to be determined
     * @return true if the string is a legal variable name, false otherwise.
     */
    static boolean isNameLegal(String name) {
        //TODO write this method.
        return false;
    }

    public static Variable deepCopyVariable(Variable variable){
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
