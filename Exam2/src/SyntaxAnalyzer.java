import java.io.IOException;
import java.util.ArrayList;

public class SyntaxAnalyzer {
    //All tokens and their numeric values
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
    final static int END_STATEMENT = 28; // ';'
    final static int OPEN_BLOCK = 29; // '{'
    final static int CLOSE_BLOCK = 30; // '}'
    final static int IF = 31; // '^'
    final static int ELSE = 32; // '#'
    final static int ELSE_IF = 33; // '@'
    final static int WHILE = 34; // '$'

    //integer to represent the current/latest token
    static int latestToken;
    //current spot in the array list of tokens
    static int latest;

    //defines tokenList array list
    static ArrayList <Integer> tokensList = new ArrayList<>();

    //function that ensures proper syntax with assignments
    public static void assignment (){
        //checks if current token is an identifier
        if (latestToken == IDENTIFIER){
            getTheNextToken();
            //checks if token is an assignment token
            if (latestToken == ASSIGNMENT){
                getTheNextToken();
                expr();
                //checks if token is the end statement token
                if (latestToken == END_STATEMENT){
                    getTheNextToken();
                }
                else{
                    //returns syntax error if above if statements are false
                    returnSyntaxError();
                }
            }
            else {
                //returns syntax error if above if statements are false
                returnSyntaxError();
            }
        }
        else {
            //returns syntax error if above if statements are false
            returnSyntaxError();
        }
    }

    //function that ensures proper syntax with bex
    public static void bex (){
        bterm();
        //checks latestToken before running bterm() function
        while (latestToken == MULTIPLY || latestToken == DIVIDE || latestToken == MODULUS){
            getTheNextToken();
            bterm();
        }
    }

    //function that ensures proper syntax with bfactor
    public static void bfactor (){
        //checks if latestToken is an identifier or integer literal
        if(latestToken == IDENTIFIER || latestToken == INT_LIT){
            getTheNextToken();
        }
        //checks if latestToken is left parenthesis
        else if(latestToken == LEFT_PAREN){
            getTheNextToken();
            bex();
            //checks if latestToken is right parenthesis
            if(latestToken ==RIGHT_PAREN){
                getTheNextToken();
            }
            else{
                //returns syntax error if above if statements are false
                returnSyntaxError();
            }

        }
        else{
            //returns syntax error if above if statements are false
            returnSyntaxError();
        }
    }

    //function that ensures proper syntax with blocks
    public static void block (){
        //checks for open curly brace
        if (latestToken == OPEN_BLOCK){
            getTheNextToken();
            //checks token values for if, else, elseif, while, identifier
            while (latestToken == IF || latestToken == ELSE || latestToken == ELSE_IF || latestToken == WHILE || latestToken == IDENTIFIER){
                statement();
            }
            //checks for close curly brace
            if(latestToken == CLOSE_BLOCK){
                getTheNextToken();
            }
            else {
                //returns syntax error if above if statements are false
                returnSyntaxError();
            }
        }
    }

    //function that ensures proper syntax with boolean expressions
    public static void bool_expr (){
        //runs rel method first
        rel();
        //runs while loop while current token is NOT (equal) or EQUAL TO
        while(latestToken == NOT || latestToken == EQUAL_TO){
            getTheNextToken();
            rel();
        }
    }

    //function that ensures proper syntax with bterm
    public static void bterm (){
        //runs bfactor method
        bfactor();
        //while loop that runs while the current token is ADD or SUBTRACT
        while(latestToken == ADD || latestToken == SUBTRACT){
            getTheNextToken();
            bfactor();
        }
    }

    //function that ensures proper syntax with else statements
    public static void else_statement (){
        //checks for else token
        if(latestToken == ELSE){
            //gets next token
            getTheNextToken();
            //checks if token is left parenthesis, runs boolean expression method if it is
            if(latestToken == LEFT_PAREN){
                getTheNextToken();
                //runs the boolean expression method
                bool_expr();
                //checks if token is right parenthesis
                if(latestToken == RIGHT_PAREN){
                    getTheNextToken();
                    //checks is current token is a block, runs block() if it is
                    if (latestToken == OPEN_BLOCK){
                        block();
                    }
                    else{
                        //returns syntax error if above if statements are false
                        returnSyntaxError();
                    }
                }

                else{
                    //returns syntax error if above if statements are false
                    returnSyntaxError();
                }

            }
            else {
                //returns syntax error if above if statements are false
                returnSyntaxError();
            }
        }
    }

    //function that ensures proper syntax with else/if statements
    public static void elseif_statement (){
        //checks for else token
        if(latestToken == ELSE_IF){
            getTheNextToken();
            //checks if token is left parenthesis, runs boolean expression method if it is
            if(latestToken == LEFT_PAREN){
                getTheNextToken();
                bool_expr();
                //checks if token is right parenthesis
                if(latestToken == RIGHT_PAREN){
                    getTheNextToken();
                    //checks is current token is a block, runs block() if it is
                    if (latestToken == OPEN_BLOCK){
                        block();
                    }
                    else{
                        //returns syntax error if above if statements are false
                        returnSyntaxError();
                    }
                }

                else{
                    //returns syntax error if above if statements are false
                    returnSyntaxError();
                }

            }
            else {
                //returns syntax error if above if statements are false
                returnSyntaxError();
            }
        }
    }

    //function that ensures proper syntax with expressions
    public static void expr (){
        //runs term method
        term();
        //runs loop while current tokes either MULTIPLY, DIVIDE, or MODULUS
        while(latestToken == MULTIPLY || latestToken == DIVIDE || latestToken == MODULUS){
            //runs term method
            term();
        }
    }

    //function that ensures proper syntax with factors
    public static void factor (){
        //checks if current token is an identifier or integer literal
        if (latestToken == IDENTIFIER || latestToken == INT_LIT){
            getTheNextToken();
        }
        //checks if current token is a left parenthesis and runs expression method
        else if(latestToken == LEFT_PAREN){
            getTheNextToken();
            expr();
            //checks if current token is a right parenthesis and gets the next token
            if(latestToken == RIGHT_PAREN){
                getTheNextToken();
            }
            else{
                //returns syntax error if above if statements are false
                returnSyntaxError();
            }
        }

        else{
            //returns syntax error if above if statements are false
            returnSyntaxError();
        }
    }

    //Method that gets the next token in the ArrayList
    public static void getTheNextToken () {
        //checks if the size of the ArrayList is greater than the current index in the ArrayList
        if (tokensList.size() > latest){
            latest +=1;
        }
        //latestToken is assigned the value at the index of the latest variable in the tokensList ArrayList
        latestToken = tokensList.get(latest);

        //prints current token
        System.out.println("The current token = " + latestToken);
    }

    //function that ensures proper syntax with if statements
    public static void if_statement(){
        //checks of current token is IF
        if(latestToken == IF){
            getTheNextToken();
            //checks if current token is left parenthesis, runs boolean expression function if it is
            if(latestToken == LEFT_PAREN){
                getTheNextToken();
                bool_expr();
                //checks if current token is right parenthesis
                if(latestToken == RIGHT_PAREN){
                    getTheNextToken();
                    //checks if current token is the OPEN BLOCK token '{', runs block() if it is
                    if (latestToken == OPEN_BLOCK){
                        block();
                    }
                    else{
                        //returns syntax error if above if statements are false
                        returnSyntaxError();
                    }
                }

                else{
                    //returns syntax error if above if statements are false
                    returnSyntaxError();
                }

            }
            else {
                //returns syntax error if above if statements are false
                returnSyntaxError();
            }
        }

    }


    //function that ensures proper syntax with rel
    public static void rel (){
        //runs bex function
        bex();
        //gets next token and runs bex() function while current token is LESS THAN, GREATER THAN, LESS THAN/EQUAL TO OR GREATER THAN/EQUAL TO
        while(latestToken == LESS_THAN || latestToken == GREATER_THAN || latestToken == LESS_EQ || latestToken == GREATER_EQ){
            getTheNextToken();
            bex();
        }
    }

    //method that returns syntax error message
    public static void returnSyntaxError(){
        System.out.println("There is a syntax error present");
        System.exit(0);
    }

    //method that holds all of the switch/case blocks for all types of statements
    public static void statement(){
        switch (latestToken){
            //case for else statement
            case ELSE:
                else_statement();
                break;
            //case for else/if statement
            case ELSE_IF:
                elseif_statement();
                break;
            //case for if statement
            case IF:
                if_statement();
                break;
            //case for while loop
            case WHILE:
                while_loop();
                break;
            //case for identifiers
            case IDENTIFIER:
                assignment();
                break;
            //case for open block token '{'
            case OPEN_BLOCK:
                block();
                break;
            //case for start program token '['
            case START_PROGRAM:
                statement();
                break;
            default:
                returnSyntaxError();
        }
    }

    //method used for testing/debugging, helps to know program is working and where the program is while it is running
    public static void statement_list(){
        System.out.println("currently inside statement_list() method");
        statement();
        System.out.println("currently in while loop");
        while(latestToken == IF || latestToken == WHILE || latestToken == ELSE || latestToken == ELSE_IF || latestToken == IDENTIFIER || latestToken == OPEN_BLOCK){
            System.out.println("currently in 2nd while loop");
            statement();
        }
    }

    //function that ensures proper syntax with term
    public static void term (){
        //runs factor method
        factor();
        //gets next token and runs factor method while current token is ADD or SUBTRACT
        while (latestToken == ADD || latestToken == SUBTRACT){
            getTheNextToken();
            factor();
        }
    }

    //function that ensures proper syntax with while loops
    public static void while_loop (){
        //checks if current token is the while token
        if(latestToken == WHILE){
            getTheNextToken();
            //checks if token is left parenthesis, runs boolean expression method if true
            if(latestToken == LEFT_PAREN){
                getTheNextToken();
                bool_expr();
                //checks if token is left parenthesis
                if(latestToken == RIGHT_PAREN){
                    getTheNextToken();
                    //checks if token is OPEN BLOCK token
                    if (latestToken == OPEN_BLOCK){
                        block();
                    }
                    else{
                        //returns syntax error if above if statements are false
                        returnSyntaxError();
                    }
                }

                else{
                    //returns syntax error if above if statements are false
                    returnSyntaxError();
                }

            }
            else {
                //returns syntax error if above if statements are false
                returnSyntaxError();
            }
        }

    }

    public static void main (String [] args) throws IOException {
        //creates object of lexical analyzer
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();
        //runs the lexical analyzer main method to generate token list
        LexicalAnalyzer.main(null);
        //stores token list in tokenList array list
        tokensList = LexicalAnalyzer.tokensList;
        //latestToken is assigned the value in the current index of the token list
        latestToken = tokensList.get(latest);

        //checks if first token is to start the program
        if(latestToken == START_PROGRAM){
            //gets next token the moves forward to statement list function
            getTheNextToken();
            statement_list();
            //checks if current token is the END PROGRAM token
            if (latestToken == END_PROGRAM){
                //print statement that there are no syntax errors
                System.out.println("No syntax errors!");
            }
            else{
                //returns syntax error if above if statements are false
                returnSyntaxError();
            }
        }

        else {
            //returns syntax error if above if statements are false
            returnSyntaxError();
        }
    }
}
