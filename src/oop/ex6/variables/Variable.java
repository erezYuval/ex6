package oop.ex6.variables;

/**
 * classes implementig this interface will represent variables of different types in
 * java-s.
 */
public abstract class Variable {

    protected VARIABLE_TYPES type;
    protected boolean isInitialized;

    /**
     * create a new variable, initialize with the value of an old one to initialize.
     * @param variable the value to be used
     * @param variableName the name of the variable
     */
    public Variable(String variableName, Variable variable) {
        // TODO write this constructor
    }

    /**
     * create a new variable, initialized with a value given as a string.
     * @param value string representaion of the new value.
     * @param variableName the name of the variable
     */
    public Variable(String variableName, String value){
        // TODO write this constructor
    }

    /**
     * create a new variable, not initialized with a value.
     * @param variableName
     */
    public Variable(String variableName){
        // TODO write this constructor
    }

    /**
     * check whether the variable has been initialized with a value yet (in the constructor or
     * by initialize method)
     * @return true if the variable was initialized, false otherwise.
     */
    abstract public boolean isInitialized();

    /**
     * initalize the variable with a value.
     * @param value the new value as a string.
     */
    abstract public void initialize(String value);

    /**
     * determines whether a string is legal as the value of the specific variable type.
     * @param value
     */
    abstract protected boolean isValueLegel(String value);

    /**
     * determines rather this variable can get another variable as a value.
     * @param otherVariable the variable to determine rather it can be used as a value.
     * @return
     */
    abstract protected boolean canGetVariable(Variable otherVariable);

    /**
     * check which type of variable is a specific instance.
     * @return the type of this Variable instance.
     */
    abstract protected VARIABLE_TYPES getVariableType();
}