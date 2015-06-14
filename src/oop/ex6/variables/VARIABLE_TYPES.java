package oop.ex6.variables;

/**
 * enum representing the different variable types of the java-s language.
 */
public enum VARIABLE_TYPES{
    INTEGER("int"),
    DOUBLE("double"),
    STRING("String"),
    BOOLEAN("boolean"),
    CHAR("char");

    private String asString;

    VARIABLE_TYPES(String stringRepresenation) {
        this.asString = stringRepresenation;
    }

    @Override
    public String toString() {
        return this.asString;
    }
}
