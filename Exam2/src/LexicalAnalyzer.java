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
    final static int EQUAL = 19; // '='
    final static int NOT = 20; // '!'
    final static int IDENTIFIER = 24;
    final static int START = 22; // '['
    final static int END = 23; // ']'
    final static int INT_LIT = 25;
    final static int LEFT_PAREN = 26; // '('
    final static int RIGHT_PAREN = 27; // ')'
    final static int END_LINE = 28; // ';'
    //create more tokens for if, else, while, etc.
    final static int IF = 29; // '^'
    final static int ELSE = 30; // '#'
    final static int ELSE_IF = 31; // "@'
    final static int WHILE = 32; // '$'

    //test token to represent tokens with more than one character.
    final static int UNICORN = 33; // 'UNICORN'




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

        System.out.println("List of tokens: "+tokensList);
    }

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
        lexeme.setLength(0);
        //checks for whitespaces
        getNonBlank();
        //switch ca
        switch (charType){
            case LETTERS:
                addChar();
                getChar();
                while (charType == LETTERS){
                    addChar();
                    getChar();
                }
                switch(lexeme.toString()){
                    //add cases for each keyword.
                    case "UNICORN":
                        nextToken = UNICORN;
                        break;
                    default:
                        nextToken = IDENTIFIER;
                }
                break;

            case DIGITS:
                addChar();
                getChar();
                while (charType == DIGITS){
                    addChar();
                    getChar();
                }
                nextToken = INT_LIT;
                break;

            case UNKNOWN:
                lookup((char)nextChar);
                getChar();
                break;

            case EOF:
                nextToken = EOF;
                lexeme.append("EOF");

                break;

        }
        System.out.println("Token = " + nextToken + ", Lexeme = " + lexeme);
        return nextToken;
    }


    public static void addChar() {
        lexeme.append((char) nextChar);
    }

    public static void getNonBlank() throws IOException {
        while (Character.isWhitespace(nextChar)){
            getChar();
        }
    }

    public static void createTokensList(){
        tokensList.add(nextToken);
    }



    public static int lookup (char c) {
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
                nextToken = START;
                break;
            case ']': //end
                addChar();
                nextToken = END;
                break;
            case '=':
                addChar();
                nextToken = EQUAL;
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

            default: //handles invalid groups of characters.
                throw new AssertionError("invalid expression");
        }

        return 0;
    }


}
