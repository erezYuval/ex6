package oop.ex6.methods;

import oop.ex6.main.exceptions.methodExceptions.IllegalMethodNameException;
import oop.ex6.main.exceptions.methodExceptions.MethodException;
import oop.ex6.main.exceptions.methodExceptions.WrongArgumentsNumberException;
import oop.ex6.main.exceptions.variableExceptions.VariableException;
import oop.ex6.variables.Variable;

import java.util.ArrayList;

/**
 * a class representing a single method in the java-s code file.
 */
public class Method{

    private ArrayList<Variable> variablesInOrder;
    private String name;
    private int firstLine;
    private int lastLine;
    private boolean hasReturnStatement = false;
    private static final String LEGAL_NAME = "[a-zA-Z]\\w*";

    /**
     * construct a new method using the given variable types and names.
     * @param methodName
     * @param variablesInOrder
     */
    public Method(String methodName, ArrayList<Variable> variablesInOrder) throws IllegalMethodNameException {
        this.variablesInOrder = variablesInOrder;
        setName(methodName);
    }

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

    public ArrayList<Variable> getVariables() {
        return variablesInOrder;
    }

    public int getLastLine() {
        return lastLine;
    }

    public void setLastLine(int lastLine) {
        this.lastLine = lastLine;
    }

    public boolean isHasReturnStatement() {
        return hasReturnStatement;
    }

    public int getNumOfArguments() {
        return variablesInOrder.size();
    }

    private void setName(String name) throws IllegalMethodNameException{
        if(!name.matches(LEGAL_NAME)) {
            throw new IllegalMethodNameException(name);
        }
        this.name = name;
    }

    public void setHasReturnStatement(boolean hasReturnStatement) {
        this.hasReturnStatement = hasReturnStatement;
    }
}