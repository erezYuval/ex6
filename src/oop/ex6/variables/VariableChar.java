package oop.ex6.variables;


import oop.ex6.main.exceptions.variable_exceptions.VariableException;

/**
 * a class representing a char variable in java-s language
 */
public class VariableChar extends Variable {

    final String LEGAL_VAL = "'[^']?'"; //regular expression for valid character
    private final VariableTypes[] LEGAL_TYPES = new VariableTypes[]{VariableTypes.CHAR}; //valid types that a char
    // accepts (only another char)

    /**
     * create a new variable, setValue with the value of an old one to setValue.
     *
     * @param variableName the name of the variable
     * @param variable     the value to be used
     * @throws VariableException if name or variable type are not matching this variable
     */
    public VariableChar(String variableName, Variable variable) throws VariableException {
        super(variableName, variable);
    }

    /**
     * create a new variable, initialized with a value given as a string.
     *
     * @param variableName the name of the variable
     * @param value        string representation of the new value.
     * @throws              VariableException if name or value are illegal
     */
    public VariableChar(String variableName, String value) throws VariableException{
        super(variableName, value);
    }

    /**
     * create a new variable, not initialized with a value.
     *
     * @param variableName
     * @throws VariableException if the name is illegal
     */
    public VariableChar(String variableName)throws VariableException {
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
        return VariableTypes.CHAR;
    }

}

