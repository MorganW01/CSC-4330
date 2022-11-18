public class LexicalAnalyzer {

    //
    static int charClass;
    static char [] lexeme;
    static char nextChar;
    static int lexLen;
    static int token;
    static int nextToken;

    //CHARACTER CLASSES
    final static int LETTERS = 0;
    final static int DIGITS = 1;
    final static int UNKNOWN = 99;


    //TOKENS
    final static int ADD = 1;
    final static int SUBTRACT = 2;
    final static int MULTIPLY = 3;
    final static int DIVIDE = 4;
    final static int MODULE = 5;
    final static int LESS_THAN = 6;
    final static int GREATER_THAN = 7;
    final static int LESS_EQ = 8;
    final static int GREATER_EQ = 9;
    final static int EQUAL = 10;
    final static int NOT_EQ = 11;
    final static int ASSIGNMENT = 12;
    final static int START = 13; //[
    final static int END = 14; //]


    public static void main(String[] args) {


    }
}

