package oop.ex6.parser;

import oop.ex6.scopes.SCOPE_CLASSES;
import oop.ex6.scopes.Scope;

import java.io.File;
import java.util.Iterator;

/**
 * a parser class that parse a java-s file.
 */
public class Parser{

    private static Scope currentScope;
    //TODO some sort of a collection of the available methods

    /**
     * a method that go through each line of the java-s file, translate the lines to commands and execute
     * the wanted actions on Scopes.
     * @param file
     */
    public static void parseFile(File file){
    }

    private static void createInnerScope(SCOPE_CLASSES type) {
    }

}