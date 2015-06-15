package oop.ex6.methods;

import oop.ex6.variables.VARIABLE_TYPES;
import oop.ex6.variables.Variable;
import oop.ex6.variables.VariableFactory;

import java.util.ArrayList;

/**
 * a class representing a single method in the java-s code file.
 */
public class Method{

    private ArrayList<Variable> variables;
    private String name;

    /**
     * construct a new method using the given variable types and names.
     * @param methodName
     * @param argumentTypesInOrder
     * @param argumentNamesInOrder
     */
    public Method(String methodName, VARIABLE_TYPES[] argumentTypesInOrder, String[] argumentNamesInOrder ) {
        variables = new ArrayList<>();
        name = methodName;
        if (!(argumentNamesInOrder == null && argumentTypesInOrder == null)) {
            for (int i = 0; i < argumentTypesInOrder.length; i++) {
                Variable variable = VariableFactory.produceVariable(argumentTypesInOrder[i], argumentNamesInOrder[i]);
                variable.setInitialized();
                variables.add(variable);
            }
        }
    }

    /**
     * this is used while calling a method on the java-s file. this function check whether a value
     * is legal in the location it was given in the call.
     * @param index the location in the method call, starting with 0
     * @param value the value to be used.
     */
    public void checkArgumentInIndex(int index, String value) {
        // TODO catch illegal value
        variables.get(index).setValue(value);
    }

    /**
     * this is used while calling a method on the java-s file. this function check whether a variable
     * is legal in the location it was given in the call.
     * @param index the location in the method call, starting with 0
     * @param variable the variable to be used.
     */
    public void checkArgumentInIndex(int index, Variable variable) {
        // TODO catch illegal value
        variables.get(index).setValue(variable);
    }

    /**
     * get the method name
     * @return the name of this method.
     */
    public String getName() {
        return name;
    }
}