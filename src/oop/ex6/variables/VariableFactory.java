package oop.ex6.variables;

import oop.ex6.main.exceptions.variableExceptions.VariableException;

/**
 * this class is a factory to Variables.
 */
public class VariableFactory {

    /**
     * produces a variable instance with the given arguments, without setting a value
     * @param type the wanted variable type
     * @param name the variable name
     * @return a variable of the given type with the given name (without a value)
     */
    public static Variable produceVariable(VARIABLE_TYPES type, String name) throws VariableException{
        Variable returnVariable = null;
        switch(type){
            case INTEGER:returnVariable = new VariableInteger(name);
                break;
            case DOUBLE:returnVariable = new VariableDouble(name);
                break;
            case CHAR:returnVariable = new VariableChar(name);
                break;
            case STRING:returnVariable = new VariableString(name);
                break;
            case BOOLEAN:returnVariable = new VariableBoolean(name);
                break;
        }
        return returnVariable;
    }

    /**
     * produces a variable instance with the given arguments, with setting a value
     * @param type the wanted variable type
     * @param name the variable name
     * @param value the wanted value
     * @return a variable of the given type with the given name and value
     */
    public static Variable produceVariable(VARIABLE_TYPES type, String name, String value) throws VariableException {
        Variable returnVariable = produceVariable(type, name);
        if (value != null) {
            returnVariable.setValue(value);
        }
        return returnVariable;
    }

}
