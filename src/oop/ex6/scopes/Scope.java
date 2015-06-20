package oop.ex6.scopes;

import oop.ex6.methods.Method;
import oop.ex6.variables.Variable;
import oop.ex6.variables.VariableUtils;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * a class representing a logic scope in the java-s examined program
 */
public class Scope {

    //this will be the line in the code where this scope starts
    private int firstLine;
    //this will be the parent scope of this scope, (we need it for outer scope variables.)
    private Scope parent;

    private Method parentMethod;

    private Hashtable<String, Variable> variables;
    protected static Hashtable<String, Method> methods;


    /**
     * create a new scope object, and set its first line to be the given int.
     * @param firstLine the line in the java-s file where this scope starts.
     */
    public Scope(int firstLine) {
        initializeCollections();
    }

    /**
     * create a new scope object, and set it first line to be the given int, and his parent scope to be the given scope.
     * @param firstLine
     * @param parent
     */
    public Scope(int firstLine, Scope parent) {
        initializeCollections();
        this.firstLine = firstLine;
        this.parent = parent;
    }

    /**
     * add a variable to the variable collection of this scope, if not existing already.
     * @param variable
     */
    public void addVariable(Variable variable){
        Variable found = searchVariableLocally(variable.toString());
        if (found != null) {
            // TODO throw same block exception
        } else {
            variables.put(variable.toString(),variable);
        }
    }

    public void updateVariable(String variableName, String value) {
        Variable found = searchVariableLocally(variableName);
        if (found != null) {
            found.setValue(value);
        } else if (parent != null) {
            found = parent.searchVariableUpwards(variableName);
            if (found != null) {
                addVariable(VariableUtils.deepCopyVariable(found));
                updateVariable(variableName,value);
            }
        }
        // TODO throw non existing variable exception.
    }

    public void addMethod(Method method){
        methods.put(method.getName(), method);
    }

    /**
     * get the line where this scope starts in the java-s file.
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
    setValue the collection data members.
     */
    private void initializeCollections(){
        variables = new Hashtable<>();
        methods = new Hashtable<>();
    }

    public Method getParentMethod() {
        return parentMethod;
    }

    public void setParentMethod(Method parentMethod) {
        this.parentMethod = parentMethod;
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