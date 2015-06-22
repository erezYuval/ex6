package oop.ex6.variables;

import oop.ex6.main.exceptions.variable_exceptions.VariableException;

/**
 * a class representing a double variable in java-s language
 */
public class VariableDouble extends Variable{

    private final VariableTypes[] LEGAL_TYPES = new VariableTypes[]{VariableTypes.INTEGER,
            VariableTypes.DOUBLE}; //valid types a double accepts (an integer or another double)
    final static String LEGAL_VAL = "\\-?\\d+(\\.\\d+)?"; //regular expression for valid double

    /**
     * create a new variable, setValue with the value of an old one to setValue.
     *
     * @param variableName the name of the variable
     * @param variable     the value to be used
     */
    public VariableDouble(String variableName, Variable variable)throws VariableException {
        super(variableName, variable);
    }

    /**
     * create a new variable, initialized with a value given as a string.
     *
     * @param variableName the name of the variable
     * @param value        string representation of the new value.
     */
    public VariableDouble(String variableName, String value) throws VariableException{
        super(variableName, value);
    }

    /**
     * create a new variable, not initialized with a value.
     *
     * @param variableName
     */
    public VariableDouble(String variableName)throws VariableException {
        super(variableName);
    }

    /**
     * determines whether a string is legal as the value of the specific variable type.
     *
     * @param value
     */
    @Override
    protected boolean isValueLegal(String value) {
        return value.matches(LEGAL_VAL);
    }

    /**
     * determines whether this variable can get another variable as a value.
     *
     * @param otherVariable the variable to determine whether it can be used as a value.
     * @return
     */
    @Override
    protected boolean canGetVariable(Variable otherVariable) {
        return false;
    }

    /**
     * check which type of variable is a specific instance.
     *
     * @return the type of this Variable instance.
     */
    @Override
    public VariableTypes getVariableType() {
        return VariableTypes.DOUBLE;
    }
}
