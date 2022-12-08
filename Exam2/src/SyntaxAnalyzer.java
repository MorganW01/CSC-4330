import java.lang.reflect.Array;
import java.util.ArrayList;

public class SyntaxAnalyzer {

    static int latestToken;
    static int latest;
    static ArrayList <Integer> tokensList = new ArrayList<>();


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

    public static void syntaxError(){
        System.out.println("There is a syntax error present");
        System.exit(0);
    }

    /*public static void statements(){
        switch (latestToken){
            case WHILE:



        }



    }*/

    public static void getTheNextToken () {
        if (tokensList.size() > latest){
            latest +=1;
        }
        latestToken = tokensList.get(latest);

        System.out.println("The current token = " + latestToken);
    }

    public static void assignment (){
        if (latestToken == IDENTIFIER){
            getTheNextToken();
            if (latestToken == ASSIGNMENT){
                getTheNextToken();
                expr();
                if (latestToken == END_STATEMENT){
                    getTheNextToken();
                }
                else{
                    syntaxError();
                }
            }
            else {
                syntaxError();
            }
        }
        else {
            syntaxError();
        }
    }

    public static void bex (){
        bterm();
        while (latestToken == MULTIPLY || latestToken == DIVIDE || latestToken == MODULUS){
            getTheNextToken();
            bterm();
        }
    }

    public static void bfactor (){




    }


    public static void block (){
        if (latestToken == OPEN_BLOCK){
            getTheNextToken();
            while (latestToken == IF || latestToken == ELSE || latestToken == ELSE_IF || latestToken == WHILE || latestToken == IDENTIFIER){
                stmt();
            }
            if(latestToken == CLOSE_BLOCK){
                getTheNextToken();
            }
            else {
                syntaxError();
            }
        }
    }


    public static void bool_expr (){
        rel();
        while(latestToken == NOT || latestToken == EQUAL_TO){
            getTheNextToken();
            rel();
        }
    }

    public static void bterm (){
        bfactor();
        while(latestToken == ADD || latestToken == SUBTRACT){
            getTheNextToken();
            bfactor();
        }
    }

    public static void else_stmt (){




    }

    public static void elseif_stmt (){




    }

    public static void expr (){
        term();
        while(latestToken == MULTIPLY || latestToken == DIVIDE || latestToken == MODULUS){
            term();
        }
    }

    public static void factor (){
        if (latestToken == IDENTIFIER || latestToken == INT_LIT){
            getTheNextToken();
        }
        else if(latestToken == LEFT_PAREN){
            getTheNextToken();
            expr();
            if(latestToken == RIGHT_PAREN){
                getTheNextToken();
            }
            else{
                syntaxError();
            }
        }

        else{
            syntaxError();
        }
    }


    public static void if_stmt (){




    }


    public static void rel (){
        bex();
        while(latestToken == LESS_THAN || latestToken == GREATER_THAN || latestToken == LESS_EQ || latestToken == GREATER_EQ){
            getTheNextToken();
            bex();
        }
    }

    public static void stmt (){




    }



    public static void term (){
        factor();
        while (latestToken == ADD || latestToken == SUBTRACT){
            getTheNextToken();
            factor();
        }
    }


    public static void while_loop (){




    }

    public static void main (String [] args) {





    }





}
