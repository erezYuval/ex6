package oop.ex6.variables;

/**
 * Created by yuvalavrami on 6/14/15.
 */
public class VariableString extends Variable {

    type = VARIABLE_TYPES.


    /**
     * determines whether a string is legal as the value of the specific variable type.
     *
     * @param value
     */
    @Override
    protected boolean isValueLegal(String value) {
        return false;
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
    protected VARIABLE_TYPES getVariableType() {
        return null;
    }
}
