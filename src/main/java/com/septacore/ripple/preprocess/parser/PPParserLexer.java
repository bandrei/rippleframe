// $ANTLR 3.4 /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g 2012-11-17 02:14:59

  package com.septacore.ripple.preprocess.parser;
  import com.septacore.ripple.preprocess.*;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class PPParserLexer extends Lexer {
    public static final int EOF=-1;
    public static final int APP=4;
    public static final int CID=5;
    public static final int COMMA=6;
    public static final int COMMENT=7;
    public static final int ESC=8;
    public static final int ESC_SEQ=9;
    public static final int EXPONENT=10;
    public static final int FLOAT=11;
    public static final int HEX_DIGIT=12;
    public static final int ID=13;
    public static final int IDENT=14;
    public static final int INT=15;
    public static final int LITFLOAT=16;
    public static final int LITINT=17;
    public static final int LITSTRING=18;
    public static final int LPAREN=19;
    public static final int OCTAL_ESC=20;
    public static final int PPID=21;
    public static final int RPAREN=22;
    public static final int SEMICOLON=23;
    public static final int STRING=24;
    public static final int TUPLES=25;
    public static final int UNICODE_ESC=26;
    public static final int WS=27;


      @Override
      public void displayRecognitionError(String[] tokenNames, RecognitionException e)
      {
       PPLogger.log(e.line, e.charPositionInLine, "Unexpected character '" + (char) e.getUnexpectedType() + "' encountered.");
      }



    // delegates
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public PPParserLexer() {} 
    public PPParserLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public PPParserLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "/home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g"; }

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:21:7: ( ',' )
            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:21:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COMMA"

    // $ANTLR start "LPAREN"
    public final void mLPAREN() throws RecognitionException {
        try {
            int _type = LPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:22:8: ( '(' )
            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:22:10: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LPAREN"

    // $ANTLR start "RPAREN"
    public final void mRPAREN() throws RecognitionException {
        try {
            int _type = RPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:23:8: ( ')' )
            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:23:10: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RPAREN"

    // $ANTLR start "SEMICOLON"
    public final void mSEMICOLON() throws RecognitionException {
        try {
            int _type = SEMICOLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:24:11: ( ';' )
            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:24:13: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SEMICOLON"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken s=null;

            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:94:5: (s= CID )
            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:94:7: s= CID
            {
            int sStart72 = getCharIndex();
            int sStartLine72 = getLine();
            int sStartCharPos72 = getCharPositionInLine();
            mCID(); 
            s = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, sStart72, getCharIndex()-1);
            s.setLine(sStartLine72);
            s.setCharPositionInLine(sStartCharPos72);


            setText(s.getText().toUpperCase());

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ID"

    // $ANTLR start "CID"
    public final void mCID() throws RecognitionException {
        try {
            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:98:2: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' | '.' | '-' )* )
            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:98:4: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' | '.' | '-' )*
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:98:28: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' | '.' | '-' )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0 >= '-' && LA1_0 <= '.')||(LA1_0 >= '0' && LA1_0 <= '9')||(LA1_0 >= 'A' && LA1_0 <= 'Z')||LA1_0=='_'||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:
            	    {
            	    if ( (input.LA(1) >= '-' && input.LA(1) <= '.')||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CID"

    // $ANTLR start "INT"
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:101:5: ( ( '0' .. '9' )+ )
            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:101:7: ( '0' .. '9' )+
            {
            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:101:7: ( '0' .. '9' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0 >= '0' && LA2_0 <= '9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "INT"

    // $ANTLR start "FLOAT"
    public final void mFLOAT() throws RecognitionException {
        try {
            int _type = FLOAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:105:5: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( EXPONENT )? | '.' ( '0' .. '9' )+ ( EXPONENT )? | ( '0' .. '9' )+ EXPONENT )
            int alt9=3;
            alt9 = dfa9.predict(input);
            switch (alt9) {
                case 1 :
                    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:105:9: ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( EXPONENT )?
                    {
                    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:105:9: ( '0' .. '9' )+
                    int cnt3=0;
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( ((LA3_0 >= '0' && LA3_0 <= '9')) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt3 >= 1 ) break loop3;
                                EarlyExitException eee =
                                    new EarlyExitException(3, input);
                                throw eee;
                        }
                        cnt3++;
                    } while (true);


                    match('.'); 

                    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:105:25: ( '0' .. '9' )*
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( ((LA4_0 >= '0' && LA4_0 <= '9')) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);


                    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:105:37: ( EXPONENT )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0=='E'||LA5_0=='e') ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:105:37: EXPONENT
                            {
                            mEXPONENT(); 


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:106:9: '.' ( '0' .. '9' )+ ( EXPONENT )?
                    {
                    match('.'); 

                    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:106:13: ( '0' .. '9' )+
                    int cnt6=0;
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( ((LA6_0 >= '0' && LA6_0 <= '9')) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt6 >= 1 ) break loop6;
                                EarlyExitException eee =
                                    new EarlyExitException(6, input);
                                throw eee;
                        }
                        cnt6++;
                    } while (true);


                    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:106:25: ( EXPONENT )?
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( (LA7_0=='E'||LA7_0=='e') ) {
                        alt7=1;
                    }
                    switch (alt7) {
                        case 1 :
                            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:106:25: EXPONENT
                            {
                            mEXPONENT(); 


                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:107:9: ( '0' .. '9' )+ EXPONENT
                    {
                    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:107:9: ( '0' .. '9' )+
                    int cnt8=0;
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( ((LA8_0 >= '0' && LA8_0 <= '9')) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt8 >= 1 ) break loop8;
                                EarlyExitException eee =
                                    new EarlyExitException(8, input);
                                throw eee;
                        }
                        cnt8++;
                    } while (true);


                    mEXPONENT(); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FLOAT"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:111:5: ( ( '//' | '#' ) (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' | '/*' ( options {greedy=false; } : . )* '*/' )
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0=='/') ) {
                int LA14_1 = input.LA(2);

                if ( (LA14_1=='/') ) {
                    alt14=1;
                }
                else if ( (LA14_1=='*') ) {
                    alt14=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 14, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA14_0=='#') ) {
                alt14=1;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;

            }
            switch (alt14) {
                case 1 :
                    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:111:9: ( '//' | '#' ) (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
                    {
                    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:111:9: ( '//' | '#' )
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0=='/') ) {
                        alt10=1;
                    }
                    else if ( (LA10_0=='#') ) {
                        alt10=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 10, 0, input);

                        throw nvae;

                    }
                    switch (alt10) {
                        case 1 :
                            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:111:10: '//'
                            {
                            match("//"); 



                            }
                            break;
                        case 2 :
                            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:111:15: '#'
                            {
                            match('#'); 

                            }
                            break;

                    }


                    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:111:20: (~ ( '\\n' | '\\r' ) )*
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( ((LA11_0 >= '\u0000' && LA11_0 <= '\t')||(LA11_0 >= '\u000B' && LA11_0 <= '\f')||(LA11_0 >= '\u000E' && LA11_0 <= '\uFFFF')) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:
                    	    {
                    	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop11;
                        }
                    } while (true);


                    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:111:34: ( '\\r' )?
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( (LA12_0=='\r') ) {
                        alt12=1;
                    }
                    switch (alt12) {
                        case 1 :
                            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:111:34: '\\r'
                            {
                            match('\r'); 

                            }
                            break;

                    }


                    match('\n'); 

                    _channel=HIDDEN;

                    }
                    break;
                case 2 :
                    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:112:9: '/*' ( options {greedy=false; } : . )* '*/'
                    {
                    match("/*"); 



                    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:112:14: ( options {greedy=false; } : . )*
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( (LA13_0=='*') ) {
                            int LA13_1 = input.LA(2);

                            if ( (LA13_1=='/') ) {
                                alt13=2;
                            }
                            else if ( ((LA13_1 >= '\u0000' && LA13_1 <= '.')||(LA13_1 >= '0' && LA13_1 <= '\uFFFF')) ) {
                                alt13=1;
                            }


                        }
                        else if ( ((LA13_0 >= '\u0000' && LA13_0 <= ')')||(LA13_0 >= '+' && LA13_0 <= '\uFFFF')) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:112:42: .
                    	    {
                    	    matchAny(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop13;
                        }
                    } while (true);


                    match("*/"); 



                    _channel=HIDDEN;

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COMMENT"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:115:5: ( ( ' ' | '\\t' | '\\r' | '\\n' ) )
            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:115:9: ( ' ' | '\\t' | '\\r' | '\\n' )
            {
            if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "STRING"
    public final void mSTRING() throws RecognitionException {
        try {
            int _type = STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken escaped=null;
            int normal;

            StringBuilder lBuf = new StringBuilder();
            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:124:5: ( '\"' (escaped= ESC |normal=~ ( '\"' | '\\\\' | '\\n' | '\\r' ) )* '\"' )
            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:125:12: '\"' (escaped= ESC |normal=~ ( '\"' | '\\\\' | '\\n' | '\\r' ) )* '\"'
            {
            match('\"'); 

            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:126:12: (escaped= ESC |normal=~ ( '\"' | '\\\\' | '\\n' | '\\r' ) )*
            loop15:
            do {
                int alt15=3;
                int LA15_0 = input.LA(1);

                if ( (LA15_0=='\\') ) {
                    alt15=1;
                }
                else if ( ((LA15_0 >= '\u0000' && LA15_0 <= '\t')||(LA15_0 >= '\u000B' && LA15_0 <= '\f')||(LA15_0 >= '\u000E' && LA15_0 <= '!')||(LA15_0 >= '#' && LA15_0 <= '[')||(LA15_0 >= ']' && LA15_0 <= '\uFFFF')) ) {
                    alt15=2;
                }


                switch (alt15) {
            	case 1 :
            	    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:126:14: escaped= ESC
            	    {
            	    int escapedStart394 = getCharIndex();
            	    int escapedStartLine394 = getLine();
            	    int escapedStartCharPos394 = getCharPositionInLine();
            	    mESC(); 
            	    escaped = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, escapedStart394, getCharIndex()-1);
            	    escaped.setLine(escapedStartLine394);
            	    escaped.setCharPositionInLine(escapedStartCharPos394);


            	    lBuf.append(getText());

            	    }
            	    break;
            	case 2 :
            	    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:127:14: normal=~ ( '\"' | '\\\\' | '\\n' | '\\r' )
            	    {
            	    normal= input.LA(1);

            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    lBuf.appendCodePoint(normal);

            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);


            match('\"'); 

            setText(lBuf.toString());

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "STRING"

    // $ANTLR start "ESC"
    public final void mESC() throws RecognitionException {
        try {
            CommonToken i=null;
            CommonToken j=null;
            CommonToken k=null;
            CommonToken l=null;

            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:135:5: ( '\\\\' ( 'n' | 'r' | 't' | 'b' | 'f' | '\"' | '\\'' | '/' | '\\\\' | ( 'u' )+ i= HEX_DIGIT j= HEX_DIGIT k= HEX_DIGIT l= HEX_DIGIT ) )
            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:135:9: '\\\\' ( 'n' | 'r' | 't' | 'b' | 'f' | '\"' | '\\'' | '/' | '\\\\' | ( 'u' )+ i= HEX_DIGIT j= HEX_DIGIT k= HEX_DIGIT l= HEX_DIGIT )
            {
            match('\\'); 

            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:136:9: ( 'n' | 'r' | 't' | 'b' | 'f' | '\"' | '\\'' | '/' | '\\\\' | ( 'u' )+ i= HEX_DIGIT j= HEX_DIGIT k= HEX_DIGIT l= HEX_DIGIT )
            int alt17=10;
            switch ( input.LA(1) ) {
            case 'n':
                {
                alt17=1;
                }
                break;
            case 'r':
                {
                alt17=2;
                }
                break;
            case 't':
                {
                alt17=3;
                }
                break;
            case 'b':
                {
                alt17=4;
                }
                break;
            case 'f':
                {
                alt17=5;
                }
                break;
            case '\"':
                {
                alt17=6;
                }
                break;
            case '\'':
                {
                alt17=7;
                }
                break;
            case '/':
                {
                alt17=8;
                }
                break;
            case '\\':
                {
                alt17=9;
                }
                break;
            case 'u':
                {
                alt17=10;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;

            }

            switch (alt17) {
                case 1 :
                    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:136:13: 'n'
                    {
                    match('n'); 

                    setText("\n");

                    }
                    break;
                case 2 :
                    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:137:13: 'r'
                    {
                    match('r'); 

                    setText("\r");

                    }
                    break;
                case 3 :
                    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:138:13: 't'
                    {
                    match('t'); 

                    setText("\t");

                    }
                    break;
                case 4 :
                    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:139:13: 'b'
                    {
                    match('b'); 

                    setText("\b");

                    }
                    break;
                case 5 :
                    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:140:13: 'f'
                    {
                    match('f'); 

                    setText("\f");

                    }
                    break;
                case 6 :
                    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:141:13: '\"'
                    {
                    match('\"'); 

                    setText("\"");

                    }
                    break;
                case 7 :
                    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:142:13: '\\''
                    {
                    match('\''); 

                    setText("\'");

                    }
                    break;
                case 8 :
                    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:143:13: '/'
                    {
                    match('/'); 

                    setText("/");

                    }
                    break;
                case 9 :
                    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:144:13: '\\\\'
                    {
                    match('\\'); 

                    setText("\\");

                    }
                    break;
                case 10 :
                    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:145:13: ( 'u' )+ i= HEX_DIGIT j= HEX_DIGIT k= HEX_DIGIT l= HEX_DIGIT
                    {
                    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:145:13: ( 'u' )+
                    int cnt16=0;
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( (LA16_0=='u') ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:145:14: 'u'
                    	    {
                    	    match('u'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt16 >= 1 ) break loop16;
                                EarlyExitException eee =
                                    new EarlyExitException(16, input);
                                throw eee;
                        }
                        cnt16++;
                    } while (true);


                    int iStart676 = getCharIndex();
                    int iStartLine676 = getLine();
                    int iStartCharPos676 = getCharPositionInLine();
                    mHEX_DIGIT(); 
                    i = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, iStart676, getCharIndex()-1);
                    i.setLine(iStartLine676);
                    i.setCharPositionInLine(iStartCharPos676);


                    int jStart680 = getCharIndex();
                    int jStartLine680 = getLine();
                    int jStartCharPos680 = getCharPositionInLine();
                    mHEX_DIGIT(); 
                    j = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, jStart680, getCharIndex()-1);
                    j.setLine(jStartLine680);
                    j.setCharPositionInLine(jStartCharPos680);


                    int kStart684 = getCharIndex();
                    int kStartLine684 = getLine();
                    int kStartCharPos684 = getCharPositionInLine();
                    mHEX_DIGIT(); 
                    k = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, kStart684, getCharIndex()-1);
                    k.setLine(kStartLine684);
                    k.setCharPositionInLine(kStartCharPos684);


                    int lStart688 = getCharIndex();
                    int lStartLine688 = getLine();
                    int lStartCharPos688 = getCharPositionInLine();
                    mHEX_DIGIT(); 
                    l = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, lStart688, getCharIndex()-1);
                    l.setLine(lStartLine688);
                    l.setCharPositionInLine(lStartCharPos688);


                    setText(PPUtil.hexToChar(i.getText(),j.getText(),
                                                                     k.getText(),l.getText()));

                    }
                    break;

            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ESC"

    // $ANTLR start "EXPONENT"
    public final void mEXPONENT() throws RecognitionException {
        try {
            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:153:10: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:153:12: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:153:22: ( '+' | '-' )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0=='+'||LA18_0=='-') ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:153:33: ( '0' .. '9' )+
            int cnt19=0;
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( ((LA19_0 >= '0' && LA19_0 <= '9')) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt19 >= 1 ) break loop19;
                        EarlyExitException eee =
                            new EarlyExitException(19, input);
                        throw eee;
                }
                cnt19++;
            } while (true);


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EXPONENT"

    // $ANTLR start "HEX_DIGIT"
    public final void mHEX_DIGIT() throws RecognitionException {
        try {
            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:156:11: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )
            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:
            {
            if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'F')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "HEX_DIGIT"

    // $ANTLR start "ESC_SEQ"
    public final void mESC_SEQ() throws RecognitionException {
        try {
            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:160:5: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | UNICODE_ESC | OCTAL_ESC )
            int alt20=3;
            int LA20_0 = input.LA(1);

            if ( (LA20_0=='\\') ) {
                switch ( input.LA(2) ) {
                case '\"':
                case '\'':
                case '\\':
                case 'b':
                case 'f':
                case 'n':
                case 'r':
                case 't':
                    {
                    alt20=1;
                    }
                    break;
                case 'u':
                    {
                    alt20=2;
                    }
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                    {
                    alt20=3;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 20, 1, input);

                    throw nvae;

                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;

            }
            switch (alt20) {
                case 1 :
                    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:160:9: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' )
                    {
                    match('\\'); 

                    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;
                case 2 :
                    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:161:9: UNICODE_ESC
                    {
                    mUNICODE_ESC(); 


                    }
                    break;
                case 3 :
                    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:162:9: OCTAL_ESC
                    {
                    mOCTAL_ESC(); 


                    }
                    break;

            }

        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ESC_SEQ"

    // $ANTLR start "OCTAL_ESC"
    public final void mOCTAL_ESC() throws RecognitionException {
        try {
            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:167:5: ( '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) )
            int alt21=3;
            int LA21_0 = input.LA(1);

            if ( (LA21_0=='\\') ) {
                int LA21_1 = input.LA(2);

                if ( ((LA21_1 >= '0' && LA21_1 <= '3')) ) {
                    int LA21_2 = input.LA(3);

                    if ( ((LA21_2 >= '0' && LA21_2 <= '7')) ) {
                        int LA21_4 = input.LA(4);

                        if ( ((LA21_4 >= '0' && LA21_4 <= '7')) ) {
                            alt21=1;
                        }
                        else {
                            alt21=2;
                        }
                    }
                    else {
                        alt21=3;
                    }
                }
                else if ( ((LA21_1 >= '4' && LA21_1 <= '7')) ) {
                    int LA21_3 = input.LA(3);

                    if ( ((LA21_3 >= '0' && LA21_3 <= '7')) ) {
                        alt21=2;
                    }
                    else {
                        alt21=3;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 21, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;

            }
            switch (alt21) {
                case 1 :
                    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:167:9: '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    match('\\'); 

                    if ( (input.LA(1) >= '0' && input.LA(1) <= '3') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;
                case 2 :
                    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:168:9: '\\\\' ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    match('\\'); 

                    if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;
                case 3 :
                    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:169:9: '\\\\' ( '0' .. '7' )
                    {
                    match('\\'); 

                    if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }

        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OCTAL_ESC"

    // $ANTLR start "UNICODE_ESC"
    public final void mUNICODE_ESC() throws RecognitionException {
        try {
            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:174:5: ( '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT )
            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:174:9: '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
            {
            match('\\'); 

            match('u'); 

            mHEX_DIGIT(); 


            mHEX_DIGIT(); 


            mHEX_DIGIT(); 


            mHEX_DIGIT(); 


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "UNICODE_ESC"

    public void mTokens() throws RecognitionException {
        // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:1:8: ( COMMA | LPAREN | RPAREN | SEMICOLON | ID | INT | FLOAT | COMMENT | WS | STRING )
        int alt22=10;
        alt22 = dfa22.predict(input);
        switch (alt22) {
            case 1 :
                // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:1:10: COMMA
                {
                mCOMMA(); 


                }
                break;
            case 2 :
                // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:1:16: LPAREN
                {
                mLPAREN(); 


                }
                break;
            case 3 :
                // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:1:23: RPAREN
                {
                mRPAREN(); 


                }
                break;
            case 4 :
                // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:1:30: SEMICOLON
                {
                mSEMICOLON(); 


                }
                break;
            case 5 :
                // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:1:40: ID
                {
                mID(); 


                }
                break;
            case 6 :
                // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:1:43: INT
                {
                mINT(); 


                }
                break;
            case 7 :
                // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:1:47: FLOAT
                {
                mFLOAT(); 


                }
                break;
            case 8 :
                // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:1:53: COMMENT
                {
                mCOMMENT(); 


                }
                break;
            case 9 :
                // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:1:61: WS
                {
                mWS(); 


                }
                break;
            case 10 :
                // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:1:64: STRING
                {
                mSTRING(); 


                }
                break;

        }

    }


    protected DFA9 dfa9 = new DFA9(this);
    protected DFA22 dfa22 = new DFA22(this);
    static final String DFA9_eotS =
        "\5\uffff";
    static final String DFA9_eofS =
        "\5\uffff";
    static final String DFA9_minS =
        "\2\56\3\uffff";
    static final String DFA9_maxS =
        "\1\71\1\145\3\uffff";
    static final String DFA9_acceptS =
        "\2\uffff\1\2\1\1\1\3";
    static final String DFA9_specialS =
        "\5\uffff}>";
    static final String[] DFA9_transitionS = {
            "\1\2\1\uffff\12\1",
            "\1\3\1\uffff\12\1\13\uffff\1\4\37\uffff\1\4",
            "",
            "",
            ""
    };

    static final short[] DFA9_eot = DFA.unpackEncodedString(DFA9_eotS);
    static final short[] DFA9_eof = DFA.unpackEncodedString(DFA9_eofS);
    static final char[] DFA9_min = DFA.unpackEncodedStringToUnsignedChars(DFA9_minS);
    static final char[] DFA9_max = DFA.unpackEncodedStringToUnsignedChars(DFA9_maxS);
    static final short[] DFA9_accept = DFA.unpackEncodedString(DFA9_acceptS);
    static final short[] DFA9_special = DFA.unpackEncodedString(DFA9_specialS);
    static final short[][] DFA9_transition;

    static {
        int numStates = DFA9_transitionS.length;
        DFA9_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA9_transition[i] = DFA.unpackEncodedString(DFA9_transitionS[i]);
        }
    }

    class DFA9 extends DFA {

        public DFA9(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 9;
            this.eot = DFA9_eot;
            this.eof = DFA9_eof;
            this.min = DFA9_min;
            this.max = DFA9_max;
            this.accept = DFA9_accept;
            this.special = DFA9_special;
            this.transition = DFA9_transition;
        }
        public String getDescription() {
            return "104:1: FLOAT : ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( EXPONENT )? | '.' ( '0' .. '9' )+ ( EXPONENT )? | ( '0' .. '9' )+ EXPONENT );";
        }
    }
    static final String DFA22_eotS =
        "\6\uffff\1\13\5\uffff";
    static final String DFA22_eofS =
        "\14\uffff";
    static final String DFA22_minS =
        "\1\11\5\uffff\1\56\5\uffff";
    static final String DFA22_maxS =
        "\1\172\5\uffff\1\145\5\uffff";
    static final String DFA22_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\uffff\1\7\1\10\1\11\1\12\1\6";
    static final String DFA22_specialS =
        "\14\uffff}>";
    static final String[] DFA22_transitionS = {
            "\2\11\2\uffff\1\11\22\uffff\1\11\1\uffff\1\12\1\10\4\uffff\1"+
            "\2\1\3\2\uffff\1\1\1\uffff\1\7\1\10\12\6\1\uffff\1\4\5\uffff"+
            "\32\5\4\uffff\1\5\1\uffff\32\5",
            "",
            "",
            "",
            "",
            "",
            "\1\7\1\uffff\12\6\13\uffff\1\7\37\uffff\1\7",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA22_eot = DFA.unpackEncodedString(DFA22_eotS);
    static final short[] DFA22_eof = DFA.unpackEncodedString(DFA22_eofS);
    static final char[] DFA22_min = DFA.unpackEncodedStringToUnsignedChars(DFA22_minS);
    static final char[] DFA22_max = DFA.unpackEncodedStringToUnsignedChars(DFA22_maxS);
    static final short[] DFA22_accept = DFA.unpackEncodedString(DFA22_acceptS);
    static final short[] DFA22_special = DFA.unpackEncodedString(DFA22_specialS);
    static final short[][] DFA22_transition;

    static {
        int numStates = DFA22_transitionS.length;
        DFA22_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA22_transition[i] = DFA.unpackEncodedString(DFA22_transitionS[i]);
        }
    }

    class DFA22 extends DFA {

        public DFA22(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 22;
            this.eot = DFA22_eot;
            this.eof = DFA22_eof;
            this.min = DFA22_min;
            this.max = DFA22_max;
            this.accept = DFA22_accept;
            this.special = DFA22_special;
            this.transition = DFA22_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( COMMA | LPAREN | RPAREN | SEMICOLON | ID | INT | FLOAT | COMMENT | WS | STRING );";
        }
    }
 

}