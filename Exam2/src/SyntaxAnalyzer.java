import java.lang.reflect.Array;
import java.util.ArrayList;

public class SyntaxAnalyzer {

    /*
    * array of tokens
    * identifier should be followed by an equals
    * check list array of tokens to ensure syntax is correct
    * PEMDAS is implemented with Stacks
    *
    * make sure it can output tokens, syntax is all about tokens
    *
    * what tokens are allowed to come before and after particular tokens
    *
    * */


    public static void main (String [] args) {
        LexicalAnalyzer.createTokensList();

        ArrayList arrayList = LexicalAnalyzer.tokensList;

        System.out.println(arrayList);



    }



}
