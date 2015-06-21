package oop.ex6.variables;

import oop.ex6.main.exceptions.variableExceptions.VariableException;

/**
 * Created by yuvalavrami on 6/14/15.
 */
public class VariableString extends Variable {

    final String LEGAL_VAL = "\".*\""; //regular expression for valid string
    private final VARIABLE_TYPES[] LEGAL_TYPES = new VARIABLE_TYPES[]{VARIABLE_TYPES.STRING}; //valid types that a
     //String can receive (only another String)

    /**
     * create a new variable, setValue with the value of an old one to setValue.
     *
     * @param variableName the name of the variable
     * @param variable     the value to be used
     */
    public VariableString(String variableName, Variable variable)throws VariableException {
        super(variableName, variable);
    }

    /**
     * create a new variable, initialized with a value given as a string.
     *
     * @param variableName the name of the variable
     * @param value        string representation of the new value.
     */
    public VariableString(String variableName, String value)throws VariableException {
        super(variableName, value);
    }

    /**
     * create a new variable, not initialized with a value.
     *
     * @param variableName
     */
    public VariableString(String variableName)throws VariableException {
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
        for (VARIABLE_TYPES type : LEGAL_TYPES) {
            if (otherVariable.getVariableType() == type) {
                return true;
            }
        }
        return false;
    }

    /**
     * check which type of variable is a specific instance.
     *
     * @return the type of this Variable instance.
     */
    @Override
    public VARIABLE_TYPES getVariableType(){
        return VARIABLE_TYPES.STRING;
    }

}
