package oop.ex6.variables.exceptions;

/**
 * Created by Erez Levanon on 15/06/2015.
 */
public enum PREDECLERATIONS {
    FINAL("final");

    String asString;

    PREDECLERATIONS(String stringRep) {
        this.asString = stringRep;
    }

    @Override
    public String toString() {
        return asString;
    }
}
