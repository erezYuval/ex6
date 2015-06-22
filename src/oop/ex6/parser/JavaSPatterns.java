package oop.ex6.parser;

import oop.ex6.variables.VARIABLE_TYPES;
import oop.ex6.variables.PREDECLARATIONS;

/**
 * a class of regex patterns for various uses by the Parser class
 */
public class JavaSPatterns {

    // these are automatically generated by the generate patterns method - these are still treated as constants
    static String PREDECLARATION;
    static String DECLARATION_VARIABLES;
    static String VARIABLE_LINE;
    static String METHOD_SIGNATURE;
    static String VARIABLE_TYPE_NAME;

    // these are constant
    final static String EMPTY_LINE = "\\s*";
    final static String COMMENT_LINE = "\\//.*";
    final static String VALUE = "((-?\\w+(\\.\\d+)?)|(\"[^\"]*\")|(\\'[^']*\\'))";
    final static String VARIABLE_OR_ASSIGNMENT = "((\\w+)\\s*(=\\s*"+ VALUE +")?\\s*)";
    final static String METHOD_CALL = "\\s*(\\w+)\\s*(\\(\\s*("+VALUE+"(\\s*,\\s*"+VALUE+")*)*\\s*\\))\\s*;\\s*";
    final static String LOGICAL_OPERATORS = "((\\|\\|)|(&&))";
    final static String INSIDE_PARENTHESIS = "[^\\)\\(]*\\(([^\\)\\(]*)\\)[^\\)\\(]*";
    final static String CONDITION_BLOCK_STARTERS = "((if)|(while))";
    final static String CONDITION_AND_BOOLEAN_IN_PARENTHESIS = "\\s*"+CONDITION_BLOCK_STARTERS + "(\\s*)(\\()(\\s*)"+VALUE+"(\\s*)("+LOGICAL_OPERATORS+"(\\s*)"+VALUE+"(\\s*))*(\\s*)(\\))(\\s*)(\\{)(\\s*)";
    final static String START_BLOCK = "[^\\{\\}]*\\{\\s*";
    final static String END_BLOCK = "\\s*\\}\\s*";
    final static String RETURN = "\\s*(return)\\s*;\\s*";


    /**
     * generates the patterns that vary according to language rules:
     * pre-declaration reserved words ( such as final )
     * variable type reserved words ( such as int, String)
     * and other lines that depend on these.
     */
    public static void generatePatterns(){
        PREDECLARATION = generateOrString(PREDECLARATIONS.values());
        DECLARATION_VARIABLES = generateOrString(VARIABLE_TYPES.values());
        VARIABLE_TYPE_NAME = "(\\s*" + DECLARATION_VARIABLES + "\\s+)(\\w+)";
        VARIABLE_LINE = "\\s*((" + PREDECLARATION + "\\s+)?((" + DECLARATION_VARIABLES + ")\\s+))?(" + VARIABLE_OR_ASSIGNMENT + "(,\\s*" + VARIABLE_OR_ASSIGNMENT + ")*)\\s*;\\s*";
        METHOD_SIGNATURE = "(\\s*void)(\\s)+(\\w+)(\\s*)\\(\\s*("+ DECLARATION_VARIABLES +"(\\s+)(\\w+)(\\s*)(,(\\s)*"+ DECLARATION_VARIABLES +"(\\s+)(\\w+)(\\s*))*)?\\)(\\s*)\\{\\s*";

    }


    /*
     * generate a regex or pattern of given enums
     * @param values an array of enums - must have meaningful toString methods
     * @return a string representing a regex expression of or relationship between all given enums.
     */
    private static String generateOrString(Enum[] values) {
        final String REGEX_START_GROUP = "(", REGEX_END_GROUP = ")", REGEX_OR_OPERATOR = "|";
        String returnString = REGEX_START_GROUP;
        if (values != null && values.length !=0) {
            for (int i = 0 ; i < values.length ; i++) {
                if( i == values.length - 1) {
                    returnString += values[i].toString();
                } else {
                    returnString += values[i].toString() + REGEX_OR_OPERATOR;
                }
            }
            returnString += REGEX_END_GROUP;
        }
        return returnString;
    }
}