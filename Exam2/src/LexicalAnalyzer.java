//import statements
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LexicalAnalyzer {

    //Array list that contains all of the tokens. This list is generated using the createTokensList function.
    static ArrayList<Integer> tokensList = new ArrayList<Integer>();

    //Variables
    static int charType;
    static StringBuilder lexeme = new StringBuilder();
    static int nextChar;
    static int nextToken;
    //file reader variable
    static FileReader fr;

    //Character types used to distinguish the chars in the language file.
    final static int LETTERS = 0; //represents letters
    final static int DIGITS = 1; //represents digits
    final static int UNKNOWN = 99; //represents unknown characters
    final static int EOF = 100; //represents the end of the file

    //Tokens: each symbol (lexeme) has a numerical value that will be the token.
    final static int ADD = 10; // '+'
    final static int SUBTRACT = 11; // '-'
    final static int MULTIPLY = 12; // '*'
    final static int DIVIDE = 13;// '/'
    final static int MODULUS = 14; // '%'
    final static int LESS_THAN = 15; // '<'
    final static int GREATER_THAN = 16; // '>'
    final static int LESS_EQ = 17; // '~'
    final static int GREATER_EQ = 18; // '?'
    final static int ASSIGNMENT = 19; // '='
    final static int NOT = 20; // '!'
    final static int EQUAL_TO = 21; //&
    final static int IDENTIFIER = 22;
    final static int START_PROGRAM = 23; // '['
    final static int END_PROGRAM = 24; // ']'
    final static int INT_LIT = 25;
    final static int LEFT_PAREN = 26; // '('
    final static int RIGHT_PAREN = 27; // ')'
    final static int END_LINE = 28; // ';'
    final static int OPEN_BLOCK = 29; // '{'
    final static int CLOSE_BLOCK = 30; // '}'

    //created more tokens for if, else, while, etc.
    final static int IF = 31; // '^'
    final static int ELSE = 32; // '#'
    final static int ELSE_IF = 33; // '@'
    final static int WHILE = 34; // '$'

    //test token to represent tokens with more than one character; disregard;
    final static int UNICORN = 35; // 'UNICORN'

    //Main method
    public static void main(String[] args) throws IOException {
        //creates file reader
        fr = new FileReader("/Users/morganwarren/Desktop/GitHub/CSC-4330/Exam2/language.txt");
        getChar();
        /*do while loop that runs the lexical analyzer while the charClass is NOT at the end of the file and
        generates the ArrayList of tokens that will be used in the SyntaxAnalyzer class. */
        do{
            lex();
            createTokensList();

        }
        while (charType != EOF);

        //prints ArrayList of tokens.
        System.out.println("List of tokens: "+tokensList);

    }

    //function that gets each character in the file and detects what type of char it is.
    public static void getChar() throws IOException {
        //reads all of the characters in the file one by one
        nextChar = fr.read();
        //checks if it is at the end of the file
        if (nextChar != -1){
            //checks if the character is alphabetical
            if (Character.isAlphabetic(nextChar)){
                charType = LETTERS;

            }
            //checks if the character is a digit
            else if (Character.isDigit(nextChar)){
                charType = DIGITS;
            }
            //checks if the character is a neither a digit or alphabetical
            else {
                charType = UNKNOWN;
            }

        }
        //checks if location is at the end of the file
        else {
            charType = EOF;
        }
    }

    //lexical method
    public static int lex() throws IOException {
        //sets length of StringBuilder to 0 each run so one character is analyzed at a time.
        lexeme.setLength(0);
        //checks for and removed whitespaces, if there are any.
        removeWhitespace();
        //switch case for letters, digits and unknown characters.
        switch (charType){
            //switch case that handles letters.
            case LETTERS:
                addChar();
                getChar();
                while (charType == LETTERS){
                    addChar();
                    getChar();
                }

                switch(lexeme.toString()){
                    //testing purposes
                    case "UNICORN":
                        nextToken = UNICORN;
                        break;

                    default: //any word that is not a keyword is by default an identifier.
                        nextToken = IDENTIFIER;
                }

                //checks if variable name is too long (longer than 8 characters) then gives an error if it is
                if (lexeme.length() > 8){
                    throw new AssertionError("This variable name is too long");
                }
                //checks if variable name is too short (less than 6 characters) then gives an error if it is
                else if (lexeme.length() < 6){
                    throw new AssertionError("This variable name is too short");
                }
                break;

            //switch case that handles digits
            case DIGITS:
                addChar();
                getChar();
                while (charType == DIGITS){
                    addChar();
                    getChar();
                }
                nextToken = INT_LIT;
                break;

            //switch case that handles unknown characters/lexemes
            case UNKNOWN:
                lookup((char)nextChar);
                getChar();
                break;

            //switch case that handles the end of the file
            case EOF:
                nextToken = EOF;
                lexeme.append("EOF");
                break;
        }

        //prints out statement that lists the token and the lexeme.
        System.out.println("Token = " + nextToken + ", Lexeme = " + lexeme);
        return nextToken;

    }

    //function that adds characters to the lexeme StringBuilder
    public static void addChar() {
        lexeme.append((char) nextChar);
    }

    //function that removes the whitespaces from file before being analyzed.
    public static void removeWhitespace() throws IOException {
        while (Character.isWhitespace(nextChar)){
            getChar();
        }
    }

    //function that generates an ArrayList of all of the tokens
    public static void createTokensList(){
        //adds tokens to the ArrayList
        tokensList.add(nextToken);
        //System.out.println(tokensList);
    }

    //Function that looks up all of the symbols passed from the file and assigns them a token value.
    public static int lookup (char c) {
        //switch cases for all token symbols defined at the top of the class.
        switch (c){
            case '(':
                addChar();
                nextToken = LEFT_PAREN;
                break;
            case ')':
                addChar();
                nextToken = RIGHT_PAREN;
                break;
            case '+':
                addChar();
                nextToken = ADD;
                break;
            case '-':
                addChar();
                nextToken = SUBTRACT;
                break;
            case '*':
                addChar();
                nextToken = MULTIPLY;
                break;
            case '/':
                addChar();
                nextToken = DIVIDE;
                break;
            case '[': //start
                addChar();
                nextToken = START_PROGRAM;
                break;
            case '=':
                addChar();
                nextToken = ASSIGNMENT;
                break;
            case '!':
                addChar();
                nextToken = NOT;
                break;
            case '>':
                addChar();
                nextToken = GREATER_THAN;
                break;
            case '<':
                addChar();
                nextToken = LESS_THAN;
                break;
            case '%':
                addChar();
                nextToken = MODULUS;
                break;
            case ';':
                addChar();
                nextToken = END_LINE;
                break;
            case '~': //less than or equal to
                addChar();
                nextToken = LESS_EQ;
                break;
            case '?': //greater than or equal to
                addChar();
                nextToken = GREATER_EQ;
                break;
            case '^':
                addChar();
                nextToken = IF;
                break;
            case '#':
                addChar();
                nextToken = ELSE;
                break;
            case '@':
                addChar();
                nextToken = ELSE_IF;
                break;
            case '$':
                addChar();
                nextToken = WHILE;
                break;
            case '{':
                addChar();
                nextToken = OPEN_BLOCK;
                break;
            case '}':
                addChar();
                nextToken = CLOSE_BLOCK;
                break;
            case ']': //end
                addChar();
                nextToken = END_PROGRAM;
                break;
            case '&': //equals to, not assignment. Similar to '=='
                addChar();
                nextToken = EQUAL_TO;
                break;

            default: //handles invalid groups of characters. The assertion error terminates the program.
                throw new AssertionError("invalid expression");
        }

        return 0;
    }

}
