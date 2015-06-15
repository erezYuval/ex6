package oop.ex6.parser;

import oop.ex6.variables.VARIABLE_TYPES;
import oop.ex6.variables.exceptions.PREDECLERATIONS;

import java.util.regex.Pattern;

/**
 * Created by Erez Levanon on 15/06/2015.
 */
public class JavaSPatterns {
    static String PREDECLERATION;
    static String DECLERATION_VARIABLES;
    static String VARIABLE_LINE;
    static String VARIABLE_OR_ASSIGNMENT = "\\w+\\s*(=\\s*((\\w+)|(\".*\")|('.*')))?\\s*";


    public static void compilePatterns(){
        PREDECLERATION = generateOrString(PREDECLERATIONS.values());
        DECLERATION_VARIABLES = generateOrString(VARIABLE_TYPES.values());
        VARIABLE_LINE = "(" + PREDECLERATION + "\\s+)?((" + DECLERATION_VARIABLES + ")\\s+)?" + VARIABLE_OR_ASSIGNMENT + "(,\\s*" + VARIABLE_OR_ASSIGNMENT + ")*\\s*;";
}


    private static String generateOrString(Enum[] values) {
        String returnString = "";
        if (values != null && values.length !=0) {
            for (int i = 0 ; i < values.length ; i++) {
                if( i == values.length - 1) {
                    returnString += values[i].toString();
                } else {
                    returnString += values[i].toString() + "|";
                }
            }
        }
        return returnString;
    }

    public static void main (String[] args) {
        compilePatterns();
        System.out.println(PREDECLERATION);
        System.out.println(DECLERATION_VARIABLES);
        System.out.println(VARIABLE_LINE);
        for (String string : new String[] {
                "int a = \'3 gfdjks gfd jk vbdfs k gre kgfd bmds\' ;",
                "int a = 15, j = 3;",
                "a = 3;",
                "String asvd = 'fdsaf',a ;",
                "int a, b = 5 , c = \"3\", d, e = 5;"
        }) {
            System.out.println(string.matches(VARIABLE_LINE) + " " + string);
        }
    }
}
