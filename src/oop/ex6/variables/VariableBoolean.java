package oop.ex6.variables;

import oop.ex6.main.exceptions.variable_exceptions.VariableException;

/**
 *  a class representing a boolean variable in java-s language.
 */
public class VariableBoolean extends Variable {

    final String LEGAL_VAL = VariableInteger.LEGAL_VAL + "|" + VariableDouble.LEGAL_VAL + "|" + "true|false"; //defines
     //valid boolean value: any valid number (integer or double), or the expressions true or false
    private final VariableTypes[] LEGAL_TYPES = new VariableTypes[]{VariableTypes.BOOLEAN,
            VariableTypes.INTEGER, VariableTypes.DOUBLE}; //types that boolean accepts (integer, double and boolean)

    /**
     * create a new variable, setValue with the value of an old one to setValue.
     *
     * @param variableName the name of the variable
     * @param variable     the value to be used
     * @throws VariableException if name or variable type don't match this variable
     */
    public VariableBoolean(String variableName, Variable variable) throws VariableException{
        super(variableName, variable);
    }

    /**
     * create a new variable, initialized with a value given as a string.
     *
     * @param variableName the name of the variable
     * @param value        string representation of the new value.
     * @throws VariableException if name or value are illegal
     */
    public VariableBoolean(String variableName, String value) throws VariableException {
        super(variableName, value);
    }

    /**
     * create a new variable, not initialized with a value.
     *
     * @param variableName
     * @throws VariableException if name is illegal
     */
    public VariableBoolean(String variableName)throws VariableException {
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
        for(VariableTypes type: LEGAL_TYPES){
            if(otherVariable.getVariableType() == type){
                return true;}
        }
        return false;
    }

    /**
     * check which type of variable is a specific instance.
     *
     * @return the type of this Variable instance.
     */
    @Override
    public VariableTypes getVariableType() {
        return VariableTypes.BOOLEAN;
    }

}
