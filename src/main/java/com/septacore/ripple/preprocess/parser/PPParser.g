grammar PPParser;

options{
	output=AST;
	ASTLabelType=CommonTree;
	language=Java;
}

tokens {
	LPAREN = '(';
	RPAREN = ')';
	COMMA = ',';
	SEMICOLON = ';';
	TUPLES;
	IDENT;
	LITSTRING;
	LITFLOAT;
	LITINT;
	APP;
	PPID;
}

@header{
  package preprocess.parser;
  import preprocess.*;
}
@members{
@Override
 public void displayRecognitionError(String[] tokenNames, RecognitionException e)
  {
    if(e instanceof MismatchedTokenException)
      PPLogger.log(e.line, e.charPositionInLine, "Unexpected symbol \"" + e.token.getText() + "\" encountered.");
    else if(e instanceof NoViableAltException)
      PPLogger.log(e.line, e.charPositionInLine, "Invalid statement encountered.");
    else
      PPLogger.log(e.line, e.charPositionInLine);
 }

@Override
public void recover(IntStream input, RecognitionException re) {
    
    // Same state so consume a token
    if ( state.lastErrorIndex==input.index() ) {
      input.consume();
    }

	int[] seps = {SEMICOLON};

    beginResync();
    
    // Skip until next separator or EOF
    int ttype = input.LA(1);
    loop: while (ttype != Token.EOF) {
      for(int sep :seps)
        if(ttype == sep) break loop;
      input.consume();
      ttype = input.LA(1);
    }

    endResync();
  }
}
@lexer::header{
  package preprocess.parser;
  import preprocess.*;
}
@lexer::members{

  @Override
  public void displayRecognitionError(String[] tokenNames, RecognitionException e)
  {
   PPLogger.log(e.line, e.charPositionInLine, "Unexpected character '" + (char) e.getUnexpectedType() + "' encountered.");
  }

}

tuple
	:	(ppapp SEMICOLON)+ EOF -> ^(TUPLES ppapp+)
	;

ppapp
	:	atom
	|  	ID LPAREN ppapp (COMMA ppapp)* RPAREN
		-> ^(APP ^(PPID ID) ppapp+)
	;

atom 
	:	ID 		-> ^(IDENT ID)
	| 	STRING	-> ^(LITSTRING STRING)
	| 	FLOAT 	-> ^(LITFLOAT FLOAT)
	| 	INT 	-> ^(LITINT INT)
	;

ID  :	s=CID {setText(s.getText().toUpperCase());}
    ;

fragment CID
	:	('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_'|'.'|'-')*
	;

INT :	'0'..'9'+
    ;

FLOAT
    :   ('0'..'9')+ '.' ('0'..'9')* EXPONENT?
    |   '.' ('0'..'9')+ EXPONENT?
    |   ('0'..'9')+ EXPONENT
    ;

COMMENT
    :   ('//'|'#') ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
    |   '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;}
    ;

WS  :   ( ' '
        | '\t'
        | '\r'
        | '\n'
        ) {$channel=HIDDEN;}
    ;

STRING      
@init{StringBuilder lBuf = new StringBuilder();}
    :   
           '"' 
           ( escaped=ESC {lBuf.append(getText());} | 
             normal=~('"'|'\\'|'\n'|'\r')     {lBuf.appendCodePoint(normal);} )* 
           '"'     
           {setText(lBuf.toString());}
    ;

fragment
ESC
    :   '\\'
        (   'n'    {setText("\n");}
        |   'r'    {setText("\r");}
        |   't'    {setText("\t");}
        |   'b'    {setText("\b");}
        |   'f'    {setText("\f");}
        |   '"'    {setText("\"");}
        |   '\''   {setText("\'");}
        |   '/'    {setText("/");}
        |   '\\'   {setText("\\");}
        |   ('u')+ i=HEX_DIGIT j=HEX_DIGIT k=HEX_DIGIT l=HEX_DIGIT
                   {setText(PPUtil.hexToChar(i.getText(),j.getText(),
                                                 k.getText(),l.getText()));}

        )
    ;

fragment
EXPONENT : ('e'|'E') ('+'|'-')? ('0'..'9')+ ;

fragment
HEX_DIGIT : ('0'..'9'|'a'..'f'|'A'..'F') ;

fragment
ESC_SEQ
    :   '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
    |   UNICODE_ESC
    |   OCTAL_ESC
    ;

fragment
OCTAL_ESC
    :   '\\' ('0'..'3') ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7')
    ;

fragment
UNICODE_ESC
    :   '\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
    ;
