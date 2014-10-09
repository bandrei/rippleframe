// $ANTLR 3.4 /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g 2012-11-17 02:14:59

  package com.septacore.ripple.preprocess.parser;
  import com.septacore.ripple.preprocess.*;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.tree.*;


@SuppressWarnings({"all", "warnings", "unchecked"})
public class PPParserParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "APP", "CID", "COMMA", "COMMENT", "ESC", "ESC_SEQ", "EXPONENT", "FLOAT", "HEX_DIGIT", "ID", "IDENT", "INT", "LITFLOAT", "LITINT", "LITSTRING", "LPAREN", "OCTAL_ESC", "PPID", "RPAREN", "SEMICOLON", "STRING", "TUPLES", "UNICODE_ESC", "WS"
    };

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

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public PPParserParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public PPParserParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

protected TreeAdaptor adaptor = new CommonTreeAdaptor();

public void setTreeAdaptor(TreeAdaptor adaptor) {
    this.adaptor = adaptor;
}
public TreeAdaptor getTreeAdaptor() {
    return adaptor;
}
    public String[] getTokenNames() { return PPParserParser.tokenNames; }
    public String getGrammarFileName() { return "/home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g"; }


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


    public static class tuple_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "tuple"
    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:77:1: tuple : ( ppapp SEMICOLON )+ EOF -> ^( TUPLES ( ppapp )+ ) ;
    public final PPParserParser.tuple_return tuple() throws RecognitionException {
        PPParserParser.tuple_return retval = new PPParserParser.tuple_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token SEMICOLON2=null;
        Token EOF3=null;
        PPParserParser.ppapp_return ppapp1 =null;


        CommonTree SEMICOLON2_tree=null;
        CommonTree EOF3_tree=null;
        RewriteRuleTokenStream stream_SEMICOLON=new RewriteRuleTokenStream(adaptor,"token SEMICOLON");
        RewriteRuleTokenStream stream_EOF=new RewriteRuleTokenStream(adaptor,"token EOF");
        RewriteRuleSubtreeStream stream_ppapp=new RewriteRuleSubtreeStream(adaptor,"rule ppapp");
        try {
            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:78:2: ( ( ppapp SEMICOLON )+ EOF -> ^( TUPLES ( ppapp )+ ) )
            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:78:4: ( ppapp SEMICOLON )+ EOF
            {
            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:78:4: ( ppapp SEMICOLON )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==FLOAT||LA1_0==ID||LA1_0==INT||LA1_0==STRING) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:78:5: ppapp SEMICOLON
            	    {
            	    pushFollow(FOLLOW_ppapp_in_tuple123);
            	    ppapp1=ppapp();

            	    state._fsp--;

            	    stream_ppapp.add(ppapp1.getTree());

            	    SEMICOLON2=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_tuple125);  
            	    stream_SEMICOLON.add(SEMICOLON2);


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            EOF3=(Token)match(input,EOF,FOLLOW_EOF_in_tuple129);  
            stream_EOF.add(EOF3);


            // AST REWRITE
            // elements: ppapp
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 78:27: -> ^( TUPLES ( ppapp )+ )
            {
                // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:78:30: ^( TUPLES ( ppapp )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(TUPLES, "TUPLES")
                , root_1);

                if ( !(stream_ppapp.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_ppapp.hasNext() ) {
                    adaptor.addChild(root_1, stream_ppapp.nextTree());

                }
                stream_ppapp.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "tuple"


    public static class ppapp_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "ppapp"
    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:81:1: ppapp : ( atom | ID LPAREN ppapp ( COMMA ppapp )* RPAREN -> ^( APP ^( PPID ID ) ( ppapp )+ ) );
    public final PPParserParser.ppapp_return ppapp() throws RecognitionException {
        PPParserParser.ppapp_return retval = new PPParserParser.ppapp_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token ID5=null;
        Token LPAREN6=null;
        Token COMMA8=null;
        Token RPAREN10=null;
        PPParserParser.atom_return atom4 =null;

        PPParserParser.ppapp_return ppapp7 =null;

        PPParserParser.ppapp_return ppapp9 =null;


        CommonTree ID5_tree=null;
        CommonTree LPAREN6_tree=null;
        CommonTree COMMA8_tree=null;
        CommonTree RPAREN10_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_ppapp=new RewriteRuleSubtreeStream(adaptor,"rule ppapp");
        try {
            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:82:2: ( atom | ID LPAREN ppapp ( COMMA ppapp )* RPAREN -> ^( APP ^( PPID ID ) ( ppapp )+ ) )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==ID) ) {
                int LA3_1 = input.LA(2);

                if ( (LA3_1==LPAREN) ) {
                    alt3=2;
                }
                else if ( (LA3_1==COMMA||(LA3_1 >= RPAREN && LA3_1 <= SEMICOLON)) ) {
                    alt3=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA3_0==FLOAT||LA3_0==INT||LA3_0==STRING) ) {
                alt3=1;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;

            }
            switch (alt3) {
                case 1 :
                    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:82:4: atom
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_atom_in_ppapp149);
                    atom4=atom();

                    state._fsp--;

                    adaptor.addChild(root_0, atom4.getTree());

                    }
                    break;
                case 2 :
                    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:83:6: ID LPAREN ppapp ( COMMA ppapp )* RPAREN
                    {
                    ID5=(Token)match(input,ID,FOLLOW_ID_in_ppapp156);  
                    stream_ID.add(ID5);


                    LPAREN6=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_ppapp158);  
                    stream_LPAREN.add(LPAREN6);


                    pushFollow(FOLLOW_ppapp_in_ppapp160);
                    ppapp7=ppapp();

                    state._fsp--;

                    stream_ppapp.add(ppapp7.getTree());

                    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:83:22: ( COMMA ppapp )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( (LA2_0==COMMA) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:83:23: COMMA ppapp
                    	    {
                    	    COMMA8=(Token)match(input,COMMA,FOLLOW_COMMA_in_ppapp163);  
                    	    stream_COMMA.add(COMMA8);


                    	    pushFollow(FOLLOW_ppapp_in_ppapp165);
                    	    ppapp9=ppapp();

                    	    state._fsp--;

                    	    stream_ppapp.add(ppapp9.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop2;
                        }
                    } while (true);


                    RPAREN10=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_ppapp169);  
                    stream_RPAREN.add(RPAREN10);


                    // AST REWRITE
                    // elements: ID, ppapp
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 84:3: -> ^( APP ^( PPID ID ) ( ppapp )+ )
                    {
                        // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:84:6: ^( APP ^( PPID ID ) ( ppapp )+ )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(APP, "APP")
                        , root_1);

                        // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:84:12: ^( PPID ID )
                        {
                        CommonTree root_2 = (CommonTree)adaptor.nil();
                        root_2 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(PPID, "PPID")
                        , root_2);

                        adaptor.addChild(root_2, 
                        stream_ID.nextNode()
                        );

                        adaptor.addChild(root_1, root_2);
                        }

                        if ( !(stream_ppapp.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_ppapp.hasNext() ) {
                            adaptor.addChild(root_1, stream_ppapp.nextTree());

                        }
                        stream_ppapp.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "ppapp"


    public static class atom_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "atom"
    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:87:1: atom : ( ID -> ^( IDENT ID ) | STRING -> ^( LITSTRING STRING ) | FLOAT -> ^( LITFLOAT FLOAT ) | INT -> ^( LITINT INT ) );
    public final PPParserParser.atom_return atom() throws RecognitionException {
        PPParserParser.atom_return retval = new PPParserParser.atom_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token ID11=null;
        Token STRING12=null;
        Token FLOAT13=null;
        Token INT14=null;

        CommonTree ID11_tree=null;
        CommonTree STRING12_tree=null;
        CommonTree FLOAT13_tree=null;
        CommonTree INT14_tree=null;
        RewriteRuleTokenStream stream_INT=new RewriteRuleTokenStream(adaptor,"token INT");
        RewriteRuleTokenStream stream_FLOAT=new RewriteRuleTokenStream(adaptor,"token FLOAT");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_STRING=new RewriteRuleTokenStream(adaptor,"token STRING");

        try {
            // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:88:2: ( ID -> ^( IDENT ID ) | STRING -> ^( LITSTRING STRING ) | FLOAT -> ^( LITFLOAT FLOAT ) | INT -> ^( LITINT INT ) )
            int alt4=4;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt4=1;
                }
                break;
            case STRING:
                {
                alt4=2;
                }
                break;
            case FLOAT:
                {
                alt4=3;
                }
                break;
            case INT:
                {
                alt4=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;

            }

            switch (alt4) {
                case 1 :
                    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:88:4: ID
                    {
                    ID11=(Token)match(input,ID,FOLLOW_ID_in_atom198);  
                    stream_ID.add(ID11);


                    // AST REWRITE
                    // elements: ID
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 88:9: -> ^( IDENT ID )
                    {
                        // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:88:12: ^( IDENT ID )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(IDENT, "IDENT")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_ID.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 2 :
                    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:89:5: STRING
                    {
                    STRING12=(Token)match(input,STRING,FOLLOW_STRING_in_atom214);  
                    stream_STRING.add(STRING12);


                    // AST REWRITE
                    // elements: STRING
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 89:12: -> ^( LITSTRING STRING )
                    {
                        // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:89:15: ^( LITSTRING STRING )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(LITSTRING, "LITSTRING")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_STRING.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 3 :
                    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:90:5: FLOAT
                    {
                    FLOAT13=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_atom228);  
                    stream_FLOAT.add(FLOAT13);


                    // AST REWRITE
                    // elements: FLOAT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 90:12: -> ^( LITFLOAT FLOAT )
                    {
                        // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:90:15: ^( LITFLOAT FLOAT )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(LITFLOAT, "LITFLOAT")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_FLOAT.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 4 :
                    // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:91:5: INT
                    {
                    INT14=(Token)match(input,INT,FOLLOW_INT_in_atom243);  
                    stream_INT.add(INT14);


                    // AST REWRITE
                    // elements: INT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 91:10: -> ^( LITINT INT )
                    {
                        // /home/rory/Documents/cybersec/potatobox/src/preprocess/PPParser.g:91:13: ^( LITINT INT )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(LITINT, "LITINT")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_INT.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "atom"

    // Delegated rules


 

    public static final BitSet FOLLOW_ppapp_in_tuple123 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_SEMICOLON_in_tuple125 = new BitSet(new long[]{0x000000000100A800L});
    public static final BitSet FOLLOW_EOF_in_tuple129 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atom_in_ppapp149 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_ppapp156 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_LPAREN_in_ppapp158 = new BitSet(new long[]{0x000000000100A800L});
    public static final BitSet FOLLOW_ppapp_in_ppapp160 = new BitSet(new long[]{0x0000000000400040L});
    public static final BitSet FOLLOW_COMMA_in_ppapp163 = new BitSet(new long[]{0x000000000100A800L});
    public static final BitSet FOLLOW_ppapp_in_ppapp165 = new BitSet(new long[]{0x0000000000400040L});
    public static final BitSet FOLLOW_RPAREN_in_ppapp169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_atom198 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_atom214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_atom228 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_atom243 = new BitSet(new long[]{0x0000000000000002L});

}