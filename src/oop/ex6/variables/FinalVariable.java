package oop.ex6.variables;

import oop.ex6.main.exceptions.variable_exceptions.AttemptedChangeFinalVarException;
import oop.ex6.main.exceptions.variable_exceptions.DeclaredFinalIntWithoutInitializationException;
import oop.ex6.main.exceptions.variable_exceptions.VariableException;

/**
 * a decorator class for an variable - making it a final unchangable variable
 */
public class FinalVariable extends Variable {


    private Variable insideVar;

    /**
     * constructor for a final variable that receives a variable
     *
     * @param variable the variable to be encapsuled
     * @throws DeclaredFinalIntWithoutInitializationException
     */
    public FinalVariable(Variable variable) throws DeclaredFinalIntWithoutInitializationException {
        if (!variable.isInitialized()) {
            throw new DeclaredFinalIntWithoutInitializationException(variable.toString());
        }
        insideVar = variable;
    }

    /**
     * constructor for a final variable that receives a name and a variable - is not supported
     * @param variableName the variable name
     * @param variable the given variable
     */
    public FinalVariable(String variableName, Variable variable){
        throw new UnsupportedOperationException();
    }

    /**
     * constructor for a final variable that only receives a variable name - is not supported
     * @param variableName the given name
     * @throws DeclaredFinalIntWithoutInitializationException trying to set uninitialized final variable
     */
    public FinalVariable(String variableName) throws DeclaredFinalIntWithoutInitializationException{
        throw new DeclaredFinalIntWithoutInitializationException(variableName);
    }

    /**
     * all final variables must be initialized - this method always returns true
     * @return true
     */
    public boolean isInitialized() {
        return true;
    }

    /**
     * returns String representation of variable
     * @return the encapsulated variable name
     */
    @Override
    public String toString() {
        return insideVar.toString();
    }

    /**
     * determines whether a string is legal as the value of the specific variable type.
     * @param value the wanted value
     */
    @Override
    protected boolean isValueLegal(String value) {
        return insideVar.isValueLegal(value);
    }

    /**
     * determines whether this variable can get another variable as a value.
     *
     * @param otherVariable the variable to determine whether it can be used as a value.
     * @return delcated to inside variable
     */
    @Override
    protected boolean canGetVariable(Variable otherVariable) {
        return insideVar.canGetVariable(otherVariable);
    }

    /**
     * attempting to change a final variable's value after initialization is illegal
     * @param value the new value as a string.
     * @throws VariableException trying to change final variable
     */
    @Override
    public void setValue(String value) throws VariableException{
        throw new AttemptedChangeFinalVarException(this.toString());
    }

    /**
     * attempting to change a final variable's value after initialization is illegal
     * @param variable the new variable
     * @throws VariableException trying to change final variable
     */
    @Override
    public void setValue(Variable variable) throws VariableException{
        throw new AttemptedChangeFinalVarException(this.toString());
    }

    /**
     * check which type of variable is a specific instance.
     *
     * @return the type of this Variable instance.
     */
    @Override
    public VariableTypes getVariableType() {
        return insideVar.getVariableType();

    }

    /**
     * @return true isFinal state for all final variables
     */
    @Override
    protected boolean isFinal() {
        return true;
    }
}