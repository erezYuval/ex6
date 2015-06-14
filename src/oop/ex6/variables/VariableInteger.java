package oop.ex6.variables;


/**
 * Created by Erez Levanon on 14/06/2015.
 */
public class VariableInteger extends Variable {

    private final static VARIABLE_TYPES[] canGetVars = new VARIABLE_TYPES[]{VARIABLE_TYPES.INTEGER};
    private final static String LEGAL_VAL = "\\d\\d*";

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
        VARIABLE_TYPES otherType = otherVariable.getVariableType();
        for (VARIABLE_TYPES type : canGetVars) {
            if (type == otherType) {
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
    public VARIABLE_TYPES getVariableType() {
        return VARIABLE_TYPES.INTEGER;
    }
}
