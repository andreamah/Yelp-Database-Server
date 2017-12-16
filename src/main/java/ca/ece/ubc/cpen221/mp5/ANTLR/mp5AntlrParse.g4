parser grammar mp5AntlrParse;

options { tokenVocab=mp5AntlrLex; } // use tokens from mp5AntlrLex.g4

root: orexpr EOF;
orexpr : andexpr (OR andexpr)*;
andexpr : atom (AND atom)*;
atom :  inp | categoryp | ratingp | pricep | namep | openp | statep | cityp |reviewcountp | schoolsp | businessidp | LPAREN orexpr RPAREN ;
ineq :  GT | GTE | LT | LTE | EQ ;
inp : IN (STRING) RPAREN;
categoryp : CATEGORY (STRING) RPAREN;
namep : NAME (STRING) RPAREN;
ratingp : RATING ineq NUM;
pricep : PRICE ineq NUM;

openp : OPEN LPAREN isopen RPAREN;
isopen: TRUE | FALSE;
statep : STATE RPAREN;
cityp : CITY (STRING) RPAREN;
reviewcountp : REVIEWCOUNT ineq NUM;
schoolsp : SCHOOLS (STRING) RPAREN;
businessidp : BUSINESSID LPAREN STR RPAREN;
