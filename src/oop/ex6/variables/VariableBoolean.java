package oop.ex6.variables;

/**
 * Created by yuvalavrami on 6/14/15.
 */
public class VariableBoolean extends Variable {

    final static String LEGAL_VAL = VariableInteger.LEGAL_VAL + "|" + VariableDouble.LEGAL_VAL + "|" + "true|false";
    private final static VARIABLE_TYPES[] LEGAL_TYPES = new VARIABLE_TYPES[]{VARIABLE_TYPES.BOOLEAN,
            VARIABLE_TYPES.INTEGER, VARIABLE_TYPES.DOUBLE};

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
        for(VARIABLE_TYPES type: LEGAL_TYPES){
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
    public VARIABLE_TYPES getVariableType() {
        return VARIABLE_TYPES.BOOLEAN;
    }

//        public static void main(String[] args) {
//        String[] array;
//        array = new String[]{"\"false\""}; // shouldn't work with this example
//        for (String a: array){
//            System.out.println(a.matches(LEGAL_VAL));
//        }
//    }
}
