package oop.ex6.variables;

import oop.ex6.main.exceptions.variableExceptions.*;

/**
 * classes implementing this interface will represent variables of different types in
 * java-s.
 */
public abstract class Variable {

    private boolean initialized;
    private String varName;

    /**
     * constructor for a variable that doesn't receive a value nor a name - is unsupported (all vars must have a name)
     */
    public Variable() {
    }

    /**
     * create a new variable, setValue with the value of an old one to setValue.
     * @param variable the value to be used
     * @param variableName the name of the variable
     */
    public Variable(String variableName, Variable variable) throws VariableException{
        setVarName(variableName);
        setValue(variable);
    }

    /**
     * create a new variable, initialized with a value given as a string.
     * @param value string representation of the new value.
     * @param variableName the name of the variable
     */
    public Variable(String variableName, String value) throws VariableException{
        setVarName(variableName);
        setValue(value);
    }

    /**
     * create a new variable, not initialized with a value.
     * @param variableName
     */
    public Variable(String variableName) throws IllegalNameException{
        setVarName(variableName);
        initialized = false;
    }

    /**
     * check whether the variable has been initialized with a value yet (in the constructor or
     * by setValue method)
     * @return true if the variable was initialized, false otherwise.
     */
    public boolean isInitialized() {
        return initialized;
    };

    /**
     * sets a variable's initialized state to true
     */
    public void setInitialized(){
        initialized = true;
    }

    /**
     * initalize the variable with a value.
     * @param value the new value as a string.
     */
    public void setValue(String value) throws VariableException{
        if (isValueLegal(value)) {
            initialized = true;
        } else {
            throw new TypeMismatchException(this, value);
        }
    }

    /**
     * initalize the variable with another variable
     * @param variable
     * @throws VariableException
     */
    public void setValue(Variable variable) throws VariableException{
        if (!variable.isInitialized()) {
            throw new AssignUnintializedVariableException(variable);
        }else if (canGetVariable(variable)) {
            initialized = true;
        } else {
            throw new TypeMismatchException(this, variable);
        }
    }

    /**
     * @return the variable's name
     */
    @Override
    public String toString() {
        return varName;
    }

    /**
     * determines whether a string is legal as the value of the specific variable type.
     * @param value
     */
    abstract protected boolean isValueLegal(String value);

    /**
     * determines whether this variable can get another variable as a value.
     * @param otherVariable the variable to determine whether it can be used as a value.
     * @return
     */
    abstract protected boolean canGetVariable(Variable otherVariable);

    /**
     * check which type of variable is a specific instance.
     * @return the type of this Variable instance.
     */
    abstract public VARIABLE_TYPES getVariableType();

    /**
     * set a variable's name to given name
     * @param name
     * @throws IllegalNameException
     */
    private void setVarName(String name) throws IllegalNameException {
        if (!VariableUtils.isNameLegal(name)) {
            throw new IllegalNameException(name);
        }
        varName = name;
    }

    /**
     * @return flase isFinal state for all variables that are not final
     */
    protected boolean isFinal() {
        return false;
    }

}