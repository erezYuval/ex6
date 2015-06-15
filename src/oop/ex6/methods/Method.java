package oop.ex6.methods;

import oop.ex6.variables.VARIABLE_TYPES;
import oop.ex6.variables.Variable;
import oop.ex6.variables.VariableFactory;

import java.util.ArrayList;

/**
 * Created by Erez Levanon on 13/06/2015.
 */
public class Method{

    private ArrayList<Variable> variables;
    private String name;

    public Method(String methodName, VARIABLE_TYPES[] argumentTypesInOrder, String[] argumentNamesInOrder ) {
        variables = new ArrayList<>();
        name = methodName;
        for (int i = 0; i < argumentTypesInOrder.length ; i++) {
            Variable variable = VariableFactory.produceVariable(argumentTypesInOrder[i], argumentNamesInOrder[i]);
            variable.setInitialized();
            variables.add(variable);
        }
    }

    public void checkArgumentInIndex(int index, String value) {
        // TODO catch illegal value
        variables.get(index).setValue(value);
    }

    public void checkArgumentInIndex(int index, Variable variable) {
        // TODO catch illegal value
        variables.get(index).setValue(variable);
    }

    public String getName() {
        return name;
    }

}
