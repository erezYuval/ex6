package oop.ex6.scopes;

import oop.ex6.main.exceptions.scope_exceptions.NonexistingVariableException;
import oop.ex6.main.exceptions.scope_exceptions.ScopeException;
import oop.ex6.main.exceptions.scope_exceptions.VarNameExistsInBlockException;
import oop.ex6.main.exceptions.variable_exceptions.VariableException;
import oop.ex6.methods.Method;
import oop.ex6.variables.Variable;
import oop.ex6.variables.VariableUtils;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * a class representing a logic scope in the java-s examined program
 */
public class Scope {

    //this will be the parent scope of this scope, (we need it for outer scope variables.)
    private Scope parent;

    // collections that hold this scope's available variables and methods.
    private Hashtable<String, Variable> variables;
    private Hashtable<String, Method> methods;


    /**
     * create a new scope object to be the given int.
     */
    public Scope() {
        initializeCollections();
    }

    /**
     * create a new scope object, and his parent scope to be the given scope.
     * @param parent this scope's parent scope.
     */
    public Scope(Scope parent) {
        initializeCollections();
        this.parent = parent;
    }

    /**
     * create a new scope and supply a list of variables that he will recognize as is own (usually the parent
     * method's arguments.
     * @param parent the parent scope
     * @param methodVariables the arguments of a method signature
     * @throws ScopeException id the variables are double
     */
    public Scope(Scope parent, ArrayList<Variable> methodVariables) throws ScopeException {
        initializeCollections();
        this.parent = parent;
        for (Variable variable : methodVariables) {
            addVariable(variable);
        }
    }

    /**
     * add a variable to the variable collection of this scope, if not existing already.
     * @param variable the new variable to add
     * @throws ScopeException if a variable iwth the same name already exists in this scope
     */
    public void addVariable(Variable variable) throws ScopeException{
        Variable found = searchVariableLocally(variable.toString());
        if (found != null) {
            throw new VarNameExistsInBlockException(variable.toString());
        } else {
            variables.put(variable.toString(),variable);
        }
    }


    /**
     * update an existing variable value
     * if the existing variable is in this scope - update
     * if it in a parent variable - deep copy and update
     * if it doesn't exist in any scope up until the global scope - throw exception
     * @param variableName the name of the variable to be updated
     * @param value the value to be given to the variable
     * @throws VariableException if the value doesn't match the variable type
     * @throws ScopeException if a variable by this name is not reachable by this scope
     */
    public void updateVariable(String variableName, String value) throws VariableException, ScopeException{
        Variable found = searchVariableLocally(variableName);
        if (found != null) {
            found.setValue(value);
        } else if (parent != null) {
            found = parent.searchVariableUpwards(variableName);
            if (found != null) {
                addVariable(VariableUtils.deepCopyVariable(found));
                updateVariable(variableName, value);
            }
        } else {
            throw new NonexistingVariableException(variableName);
        }
    }

    /**
     * add a method to the collection of accessible methods of this scope.
     * @param method the new method to be added
     */
    public void addMethod(Method method){
        methods.put(method.getName(), method);
    }


    /**
     * searches for a variable with a specific name in this scope's variables and in his parents variable lists,
     * recursively until finding the variable or getting to a scope without a parent scope.
     * @param variableName the variable name to find.
     * @return the wanted variable if found, null otherwise.
     */
    public Variable searchVariableUpwards(String variableName) {
        Variable found = variables.get(variableName);
        if (found!=null) {
            return found;
        } else if (parent!=null) {
            return parent.searchVariableUpwards(variableName);
        } else {
            return null;
        }
    }

    /**
     * searches for a variable with a specific name in this scope's variables.
     * @param variableName the variable name to find.
     * @return the wanted variable if found, null otherwise.
     */
    public Variable searchVariableLocally(String variableName) {
        Variable found = variables.get(variableName);
        if (found != null) {
            return found;
        } else {
            return null;
        }
    }



    /**
     * search for a method by name in this scope and all the parent scopes. and return it.
     * @param methodName the name of the  method to be searched
     * @return the found method if found, null otherwise.
     */
    public Method searchMethod(String methodName) {
        if (this.parent != null) {
            return parent.searchMethod(methodName);
        }
        else {
            return methods.get(methodName);
        }
    }

    /*
    set the collection data members.
     */
    private void initializeCollections(){
        variables = new Hashtable<>();
        methods = new Hashtable<>();
    }
}