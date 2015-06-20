package oop.ex6.parser;

import oop.ex6.variables.VARIABLE_TYPES;
import oop.ex6.variables.PREDECLERATIONS;

/**
 * Created by Erez Levanon on 15/06/2015.
 */
public class JavaSPatterns {
    final static String EMPTY_LINE = "\\s*";
    final static String COMMENT_LINE = "\\//.*";
    static String PREDECLARATION;
    static String DECLARATION_VARIABLES;
    static String VARIABLE_LINE;
    static String METHOD_SIGNATURE;
    static String VALUE = "((-?\\w+(.\\d+)?)|(\"[^\"]*\")|(\\'[^']*\\'))";
    static String VARIABLE_OR_ASSIGNMENT = "((\\w+)\\s*(=\\s*"+ VALUE +")?\\s*)";
    static String METHOD_CALL = "(\\w+)\\s*(\\(\\s*("+VALUE+"(\\s*,\\s*"+VALUE+")*)*\\s*\\))\\s*;\\s*";
    static String LOGICAL_OPERATORS = "((\\|\\|)|(&&))";
    static String CONDITION_BLOCK_STARTERS = "((if)|(while))";
    static String BOOLEAN_IN_PARENTHESIS = CONDITION_BLOCK_STARTERS + "(\\s*)(\\()(\\s*)(\\w+)(\\s*)("+LOGICAL_OPERATORS+"(\\s*)(\\w+)(\\s*))*(\\s*)(\\))(\\s*)(\\{)(\\s*)";
    static String END_BLOCK = "\\s*\\}\\s*";
    static String RETURN = "\\s*(return)\\s*;\\s*";
    static String VARIABLE_TYPE_NAME;

    public static void compilePatterns(){
        PREDECLARATION = generateOrString(PREDECLERATIONS.values());
        DECLARATION_VARIABLES = generateOrString(VARIABLE_TYPES.values());
        VARIABLE_TYPE_NAME = "(\\s*" + DECLARATION_VARIABLES + "\\s+)(\\w+)";
        VARIABLE_LINE = "((" + PREDECLARATION + "\\s+)?((" + DECLARATION_VARIABLES + ")\\s+))?(" + VARIABLE_OR_ASSIGNMENT + "(,\\s*" + VARIABLE_OR_ASSIGNMENT + ")*)\\s*;\\s*";
        METHOD_SIGNATURE = "(void)(\\s)+(\\w+)(\\s*)\\(\\s*("+ DECLARATION_VARIABLES +"(\\s+)(\\w+)(\\s*)(,(\\s)*"+ DECLARATION_VARIABLES +"(\\s+)(\\w+)(\\s*))*)?\\)(\\s*)\\{\\s*";

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
        System.out.println(VARIABLE_OR_ASSIGNMENT);
    }
}