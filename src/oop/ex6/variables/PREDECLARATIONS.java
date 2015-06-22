package oop.ex6.variables;

/**
 * Created by Erez Levanon on 15/06/2015.
 */
public enum PREDECLARATIONS {
    FINAL("final");

    String asString;

    PREDECLARATIONS(String stringRep) {
        this.asString = stringRep;
    }

    /**
     * @return variable's string representation
     */
    @Override
    public String toString() {return asString;}
}
