package oop.ex6.variables;

/**
 * Created by Erez Levanon on 14/06/2015.
 */
public class FinalVariable extends Variable {


    private Variable insideVar;

    public FinalVariable(Variable variable) {
        if (!variable.isInitialized()) {
            //TODO throw final exception
        }
    }

    public FinalVariable() {
        // TODO throw
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
    public void initialize(String value){
        //TODO throw final variable exception
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
}