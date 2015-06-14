package oop.ex6.scopes;

/**
 * an enum representing the available scope types.
 */
public enum SCOPE_CLASSES {
    WHILE("while"),
    IF("if"),
    VOID_METHOD("void"),
    GLOBAL("");

    private String asString;

    SCOPE_CLASSES(String stringRepresentation) {
        this.asString = stringRepresentation;
    }

    @Override
    public String toString() {
        return this.asString;
    }
}
