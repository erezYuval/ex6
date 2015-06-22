package oop.ex6.variables;

/**
 * an enum of all reserved words that are allowed before a variable declaration.
 */
public enum PreDeclaration {
    FINAL("final");

    String asString;

    PreDeclaration(String stringRep) {
        this.asString = stringRep;
    }

    /**
     * @return variable's string representation
     */
    @Override
    public String toString() {return asString;}
}
