package oop.ex6.variables;

import oop.ex6.main.exceptions.variableExceptions.AttemptedChangeFinalVarException;
import oop.ex6.main.exceptions.variableExceptions.DeclaredFinalIntWithoutInitializationException;
import oop.ex6.main.exceptions.variableExceptions.VariableException;

/**
 * Created by Erez Levanon on 14/06/2015.
 */
public class FinalVariable extends Variable {


    private Variable insideVar;

    /**
     * constructor for a final variable that receives a variable
     *
     * @param variable
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
     * @param variableName
     * @param variable
     */
    public FinalVariable(String variableName, Variable variable){
        throw new UnsupportedOperationException();
    }

    /**
     * constructor for a final variable that only receives a variable name - is not supported
     * @param variableName
     * @throws DeclaredFinalIntWithoutInitializationException
     */
    public FinalVariable(String variableName) throws DeclaredFinalIntWithoutInitializationException{
        throw new DeclaredFinalIntWithoutInitializationException(variableName);
    }

    /**
     * all final variables must be initialized - this method always returns true
     * @return
     */
    public boolean isInitialized() {
        return true;
    }

    /**
     * returns String representation of variable
     * @return
     */
    @Override
    public String toString() {
        return insideVar.toString();
    }

    /**
     * determines whether a string is legal as the value of the specific variable type.
     * @param value
     */
    @Override
    protected boolean isValueLegal(String value) {
        return insideVar.isValueLegal(value);
    }

    /**
     * determines whether this variable can get another variable as a value.
     *
     * @param otherVariable the variable to determine whether it can be used as a value.
     * @return
     */
    @Override
    protected boolean canGetVariable(Variable otherVariable) {
        return insideVar.canGetVariable(otherVariable);
    }

    /**
     * attempting to change a final variable's value after initialization is illegal
     * @param value the new value as a string.
     * @throws VariableException
     */
    @Override
    public void setValue(String value) throws VariableException{
        throw new AttemptedChangeFinalVarException(this.toString());
    }

    /**
     * attempting to change a final variable's value after initialization is illegal
     * @param variable the new variable
     * @throws VariableException
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
    public VARIABLE_TYPES getVariableType() {
        return insideVar.getVariableType();

    }

    /**
     * return true isFinal state for all final variables
     * @return
     */
    @Override
    protected boolean isFinal() {
        return true;
    }
}