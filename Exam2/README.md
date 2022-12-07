This question is assignment is 8-fold:
a. (15 Points) Define the rules for recognizing all lexemes as their proper token, and clearly define integer token codes for each token required for this language 
• Should have Regular Grammar, Regular Expression, or Finite Automata defined.

Regex Representation


Addition '+'

Subtraction '-'

Division '/'

Multiplication '*'

Modulus '%'

Less than '<'

Less than or equal to '~' 

Greater than '>'

Greater than or equal to '?'

Equal '='

Integer Literal '[0-9]+'

Not (Equal to) '!'

Start Program '['

End Program ']'

Left Parenthesis '('

Right Parenthesis ')'

End Line ';'

IF '^'

ELSE '#'

ELSE IF '@'

WHILE '$'


Token Token Value (Numeric)

Addition 10

Subtraction 11

Division 13

Multiplication 12

Modulus 14

Less than 15

Less than or equal to 17

Greater than 16

Greater than or equal to 18

Equal 19

Not (Equal to) 20

Start Program 22

End Program 23

Integer Literal 25

Left Parenthesis 26

Right Parenthesis 27

End Line 28

IF '^' 29

ELSE '#' 30

ELSE IF '@' 31

WHILE '$' 32

Variable name rules: 
[a-zA-Z_][a-zA-Z_][a-zA-Z_][a-zA-Z_][a-zA-Z_][a-zA-Z_][a-zA-Z_]?[a-zA-Z_]?


b. (15 Points) Define production rules for implementing the mathematical syntax of operators and operands, loops, variable declaration, selection statements					
Enforce a non PEMDAS (BODMAS) order of operation, must have at least 6 levels of precedence				
Keywords cannot use the words while, for, do, if, int, short, long
 i. Keywords should be unique, if others share your same words, you may lose more points than this problem is worth
You must clearly state the structure of your language with production rules
<program> -> ‘[’<stmt_list>’]’ 
<stmt_list> -> <stmt> ; 
<stmt_list> -> <stmt> ; <stmt_list>
<stmt> -> <if_stmt> 
<stmt> -> <while_loop>
<stmt> -> <assignment> 
<stmt> -> <block> 
<stmt> -> <declare> 
<declare> -> vari id ; 
<block> -> `{` <stmt_list> `}` 
<if_stmt> -> $ ( <bool_expr> ) <stmt> # <stmt> 
<if_stmt> -> $ ( <bool_expr> ) <stmt> 
<while_loop> -> ROF ( <bool_expr> ) <stmt> 
<assignment> -> id = <expr> ; 
<expr> -> <expr> - <term> 
<expr> -> <expr> + <term> 
<expr> -> <term> 
<term> -> <factor> {(|/|%)} <factor> 
<factor> -> id
factor> -> int_lit
factor> -> <expr> 
<bool_expr> -> <rel> {(!=|==)} <rel> 
<rel> -> <bex> {(<|>|~|^)} <bex> 
<bex> -> <bterm> {(|/|%)} <bterm> 
<bterm> -> <bfactor> {(+|-)} <bfactor> 
<bfactor> -> id|int_lit|bool_lit|(<bex>)
c. (10 points) Show whether every rule set in your language conforms to the standard of an LL Grammar.	

Pairwise disjoint grammar is the grammar for my language. This is my language’s grammar because for all my rules, there isn’t a nonterminal that has multiple rules for one terminal. Also because there are no rules that result in a left hand recursion, where a nonterminal calls itself as the first char.

d. (5 points) Make sure it is not ambiguous grammar

My language’s grammar is not ambiguous because there is no sequence of tokens that can be derived by more than one different leftmost/rightmost derivations					

e. (15 points) Write a program that process all lexemes in a file by recognizing all tokens in a file, and produces a list of those tokens in order
 • If a group of characters is not defined in your language your program should print an error message stating what went wrong and terminate (stop running)
 • This program should be written in an Object-Oriented fashion
 • This program should have comments to describe each method that is defined

Lexical Analyzer.java
 						
f. (10 points) Write a program or an extension to the above program that determines if the tokens conform to the correct syntax. 

SyntaxAnalyzer.java
 						
g. (10 points) Create 4 test files that have different names where each should have 30 or more lexemes that can be converted into tokens

1 with a at least 5 lexical errors based on the rules you defined
Detail each error and say why it doesn’t work

1 with at least 5 syntax errors based on the rules you defined.
Detail each error and say why it doesn’t work

2 with no errors at all based on the language you created
	
	test1.txt and test2.txt




h. (20 points) Create a LR (1) parse table for your language. And show the trace of 4 code samples. Each must have 6 or more tokens.				
Table must be provided, and the rules must be listed
2 code samples must have errors
Show were these samples fail and pass the test 



