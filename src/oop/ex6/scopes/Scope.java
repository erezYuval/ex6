package oop.ex6.scopes;

import oop.ex6.main.exceptions.scopeExceptions.NonexistingVariableException;
import oop.ex6.main.exceptions.scopeExceptions.ScopeException;
import oop.ex6.main.exceptions.scopeExceptions.VarNameExistsInBlockException;
import oop.ex6.main.exceptions.variableExceptions.VariableException;
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

    private Hashtable<String, Variable> variables;
    private Hashtable<String, Method> methods;


    /**
     * create a new scope object, and set its first line to be the given int.
     */
    public Scope() {
        initializeCollections();
    }

    /**
     * create a new scope object, and set it first line to be the given int, and his parent scope to be the given scope.
     * @param parent
     */
    public Scope(Scope parent) {
        initializeCollections();
        this.parent = parent;
    }

    public Scope(Scope parent, ArrayList<Variable> methodVariables) throws ScopeException {
        initializeCollections();
        this.parent = parent;
        for (Variable variable : methodVariables) {
            addVariable(variable);
        }
    }

    /**
     * add a variable to the variable collection of this scope, if not existing already.
     * @param variable
     */
    public void addVariable(Variable variable) throws ScopeException{
        Variable found = searchVariableLocally(variable.toString());
        if (found != null) {
            throw new VarNameExistsInBlockException(variable.toString());
        } else {
            variables.put(variable.toString(),variable);
        }
    }

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

    /*
    set the collection data members.
     */
    private void initializeCollections(){
        variables = new Hashtable<>();
        methods = new Hashtable<>();
    }

    public Method searchMethod(String methodName) {
        if (this.parent != null) {
            return parent.searchMethod(methodName);
        }
        else {
            return methods.get(methodName);
        }
    }
}