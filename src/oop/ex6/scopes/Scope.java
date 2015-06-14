package oop.ex6.scopes;

import oop.ex6.variables.VARIABLE_TYPES;
import oop.ex6.variables.Variable;

/**
 * a class representing a logic scope in the java-s examined program
 */
public class Scope {

    //this will be the line in the code were this scope starts
    private int firstLine;
    //this will be the parent scope of this scope, (we need it for outer scope variables.
    private Scope parent;

    //TODO some kind of collection that holds Variable instances of this scope.
    //TODO some kind of collection that holds Scope instances of inner scopes.

    /**
     * create a new scope object, defaultley it will have no parent scope and the first line will be 0.
     */
    public Scope(){
        //TODO write this constructor
    }

    /**
     * create a new scope object, and set it first line to be the given int.
     * @param firstLine the line in the java-s file where this scope strats.
     */
    public Scope(int firstLine) {
        //TODO write this constructor
    }

    /**
     * create a new scope object, and set it first line to be the given int, and his parent scope to be the given scope.
     * @param firstLine
     * @param parent
     */
    public Scope(int firstLine, Scope parent) {
        //TODO write this constructor.
    }

    /**
     * add a new variable to this scope's variables, and initialize it with a given value.
     * @param type the variable type of the new variable.
     * @param variableName the name of the new variable as a String.
     * @param value the value to set for the new variable, in string form.
     */
    public void addVariable(VARIABLE_TYPES type, String variableName, String value){
    }

    /**
     * add a new uninitialized variable to this scope's variables.
     * @param type the variable type of the new variable.
     * @param variableName the name of the new variable as a String.
     */
    public void addVariable(VARIABLE_TYPES type, String variableName){
    }

    /**
     * get the line where this scope strats in the java-s file.
     * @return the first line as an int.
     */
    public int getFirstLine() {
        return firstLine;
    }

    /**
     * get the parent scope of this scope.
     * @return the scope object that is the parent of this scope.
     */
    public Scope getParent() {
        return parent;
    }

    /**
     * set the first line of this scope
     * @param firstLine the line number in the java-s file were this scope starts
     */
    public void setFirstLine(int firstLine){
    this.firstLine = firstLine;
    }

    /**
     * set the parent scope of this scope
     * @param parent the parent scope of this scope.
     */
    public void setParent(Scope parent) {
        this.parent = parent;
    }

    /**
     * searches for a variable with a specific name in this scope's variables and in his parents variable lists,
     * recursively until finding the variable or getting to a scope without a parent scope.
     * @param variableName the variable name to find.
     * @return the wanted variable if found, null otherwise.
     */
    public Variable searchVarialbeUpwards(String variableName) {
        return null;
    }

    /**
     * searches for a variable with a specific name in this scope's variables.
     * @param variableName the variable name to find.
     * @return the wanted variable if found, null otherwise.
     */
    public Variable searchVariableLocally(String variableName) {
        return null;
    }

}
