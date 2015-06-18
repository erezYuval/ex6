package oop.ex6.parser;

import oop.ex6.variables.VARIABLE_TYPES;
import oop.ex6.variables.PREDECLERATIONS;

/**
 * Created by Erez Levanon on 15/06/2015.
 */
public class JavaSPatterns {
    static String PREDECLERATION;
    static String DECLARATION_VARIABLES;
    static String VARIABLE_LINE;
    static String METHOD_SIGNATURE;
    static String VALUE = "((-?\\w+)|(\".*\")|(\\'.*\\'))";
    static String VARIABLE_OR_ASSIGNMENT = "((\\w+)\\s*(=\\s*"+ VALUE +")?\\s*)";
    static String METHOD_CALL = "(\\w+)\\s*\\(\\s*"+VALUE+"(\\s*,\\s*"+VALUE+")*\\s*\\)\\s*;\\s*";
//    static String NESTED_SCOPES = "\\{([^\\{\\}]*(\\{[^\\{\\}]*})*[^\\{\\}]*)*\\}";


    public static void compilePatterns(){
        PREDECLERATION = generateOrString(PREDECLERATIONS.values());
        DECLARATION_VARIABLES = generateOrString(VARIABLE_TYPES.values());
        VARIABLE_LINE = "(" + PREDECLERATION + "\\s+)?((" + DECLARATION_VARIABLES + ")\\s+)?" + VARIABLE_OR_ASSIGNMENT + "(,\\s*" + VARIABLE_OR_ASSIGNMENT + ")*\\s*;";
        METHOD_SIGNATURE = "(void)(\\s)+(\\w+)(\\s*)\\(\\s*"+ DECLARATION_VARIABLES +"(\\s+)(\\w+)(\\s*)(,(\\s)*"+ DECLARATION_VARIABLES +"(\\s+)(\\w+)(\\s*))*\\)(\\s*)\\{\\s*";

    }


    private static String generateOrString(Enum[] values) {
        String returnString = "(";
        if (values != null && values.length !=0) {
            for (int i = 0 ; i < values.length ; i++) {
                if( i == values.length - 1) {
                    returnString += values[i].toString();
                } else {
                    returnString += values[i].toString() + "|";
                }
            }
            returnString += ")";
        }
        return returnString;
    }

    public static void main (String[] args) {
        compilePatterns();
        System.out.println("variable line\n\t"+VARIABLE_LINE);
        System.out.println("method signature:\n\t"+METHOD_SIGNATURE);
        System.out.println("method call:\n\t" + METHOD_CALL);
        System.out.println("variable or assignment:\n\t"+VARIABLE_OR_ASSIGNMENT);

    }
}