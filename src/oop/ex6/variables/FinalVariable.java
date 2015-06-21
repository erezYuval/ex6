package oop.ex6.variables;

import oop.ex6.main.exceptions.variableExceptions.AttemptedChangeFinalVarException;
import oop.ex6.main.exceptions.variableExceptions.DeclaredFinalIntWithoutInitializationException;
import oop.ex6.main.exceptions.variableExceptions.VariableException;

/**
 * Created by Erez Levanon on 14/06/2015.
 */
public class FinalVariable extends Variable {


    private Variable insideVar;

    public FinalVariable(Variable variable) throws DeclaredFinalIntWithoutInitializationException{
        if (!variable.isInitialized()) {
            throw new DeclaredFinalIntWithoutInitializationException(variable.toString());
        }
        insideVar = variable;
    }

    public FinalVariable(String variableName, Variable variable){
        throw new UnsupportedOperationException();
    }

    public FinalVariable(String variableName) throws DeclaredFinalIntWithoutInitializationException{
        throw new DeclaredFinalIntWithoutInitializationException(variableName);
    }

    public boolean isInitialized() {
        return true;
    }

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

    @Override
    public void setValue(String value) throws VariableException{
        throw new AttemptedChangeFinalVarException(this.toString());
    }

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

    @Override
    protected boolean isFinal() {
        return true;
    }
}