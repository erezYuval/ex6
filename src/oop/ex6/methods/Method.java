package oop.ex6.methods;

import oop.ex6.main.exceptions.method_exceptions.IllegalMethodNameException;
import oop.ex6.main.exceptions.method_exceptions.MethodException;
import oop.ex6.main.exceptions.method_exceptions.WrongArgumentsNumberException;
import oop.ex6.main.exceptions.variable_exceptions.VariableException;
import oop.ex6.variables.Variable;

import java.util.ArrayList;

/**
 * a class representing a single method in the java-s code file.
 */
public class Method{

    private ArrayList<Variable> variablesInOrder;
    private String name;
    private static final String LEGAL_NAME = "[a-zA-Z]\\w*"; //regular expression for a legal method name

    /**
     * construct a new method using the given variable types and names.
     * @param methodName
     * @param variablesInOrder
     */
    public Method(String methodName, ArrayList<Variable> variablesInOrder) throws IllegalMethodNameException {
        this.variablesInOrder = variablesInOrder;
        setName(methodName);
    }

    /**
     * construct a new method (that receives no arguments)
     * @param methodName
     */
    public Method(String methodName) throws MethodException{
        variablesInOrder = new ArrayList<>();
        setName(methodName);
    }

    /**
     * this is used while calling a method on the java-s file. this function check whether a value
     * is legal in the location it was given in the call.
     * @param index the location in the method call, starting with 0
     * @param value the value to be used.
     */
    public void checkArgumentInIndex(int index, String value) throws VariableException, MethodException{
        try {
            variablesInOrder.get(index).setValue(value);
        } catch (IndexOutOfBoundsException e) {
            throw new WrongArgumentsNumberException(this, index+1);
        }
    }

    /**
     * adds variable to a method's arguments list and initializes it
     * @param variable
     */
    public void addVariable(Variable variable) {
        variable.setInitialized();
        variablesInOrder.add(variable);
    }

    /**
     * this is used while calling a method on the java-s file. this function check whether a variable
     * is legal in the location it was given in the call.
     * @param index the location in the method call, starting with 0
     * @param variable the variable to be used.
     */
    public void checkArgumentInIndex(int index, Variable variable) throws VariableException, MethodException {
        try {
            variablesInOrder.get(index).setValue(variable);
        } catch (IndexOutOfBoundsException e) {
            throw new WrongArgumentsNumberException(this, index+1);
        }
    }

    /**
     * get the method name
     * @return the name of this method.
     */
    public String getName() {
        return name;
    }

    /**
     * get the method's arguments
     * @return a list of the method's arguments as variables(type and name)
     */
    public ArrayList<Variable> getVariables() {
        return variablesInOrder;
    }

    /**
     * get the number of the method's arguments
     * @return number of arguments the method receives
     */
    public int getNumOfArguments() {
        return variablesInOrder.size();
    }

    /**
     * set a method's name to the given name
     * @param name
     * @throws IllegalMethodNameException
     */
    private void setName(String name) throws IllegalMethodNameException{
        if(!name.matches(LEGAL_NAME)) {
            throw new IllegalMethodNameException(name);
        }
        this.name = name;
    }
}