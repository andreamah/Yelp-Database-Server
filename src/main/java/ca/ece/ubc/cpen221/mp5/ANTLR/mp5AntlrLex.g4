lexer grammar mp5AntlrLex;

OR : '||';
AND : '&&';
GT : '>';
GTE : '>=';
LT : '<';
LTE : '<=';
EQ : '=';
IN : 'in' LPAREN -> mode(ISLAND);
CATEGORY : 'category' LPAREN -> mode(ISLAND);
SCHOOLS : 'schools' LPAREN -> mode(ISLAND);
NAME: 'name' LPAREN -> mode(ISLAND);
CITY: 'city' LPAREN -> mode(ISLAND);
OPEN: 'open';
TRUE : 'true';
FALSE : 'false';
STATE: 'state' LPAREN [A-Z] [A-Z];
NUM : [0-9]+;
RATING: 'rating';
PRICE: 'price';
LPAREN : '(';
RPAREN : ')';
REVIEWCOUNT : 'review_count';

WS : [ \t\r\n]+ -> skip; // skip spaces, tabs, newlines
mode ISLAND;
STRING : ([!-'*-~]+(WSp)*)+  -> mode(DEFAULT_MODE);
WSp : [ \t\r\n]+;