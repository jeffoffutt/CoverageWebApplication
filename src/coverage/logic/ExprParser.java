// $ANTLR 2.7.6 (2005-12-22): "expr.g" -> "ExprParser.java"$

package coverage.logic;

import antlr.TokenBuffer;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.ANTLRException;
import antlr.LLkParser;
import antlr.Token;
import antlr.TokenStream;
import antlr.RecognitionException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.ParserSharedInputState;
import antlr.collections.impl.BitSet;
import antlr.collections.AST;
import java.util.Hashtable;
import antlr.ASTFactory;
import antlr.ASTPair;
import antlr.collections.impl.ASTArray;

import java.io.*;

public class ExprParser extends antlr.LLkParser       implements ExprParserTokenTypes
 {

protected ExprParser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public ExprParser(TokenBuffer tokenBuf) {
  this(tokenBuf,1);
}

protected ExprParser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public ExprParser(TokenStream lexer) {
  this(lexer,1);
}

public ExprParser(ParserSharedInputState state) {
  super(state,1);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

	public final void expr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		antlr.CommonAST expr_AST = null;
		
		try {      // for error handling
			equalExpr();
			astFactory.addASTChild(currentAST, returnAST);
			match(SEM);
			expr_AST = (antlr.CommonAST)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
		returnAST = expr_AST;
	}
	
	public final void equalExpr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		antlr.CommonAST equalExpr_AST = null;
		
		try {      // for error handling
			eorExpr();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop4:
			do {
				if ((LA(1)==EQUAL)) {
					antlr.CommonAST tmp2_AST = null;
					tmp2_AST = (antlr.CommonAST)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp2_AST);
					match(EQUAL);
					eorExpr();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop4;
				}
				
			} while (true);
			}
			equalExpr_AST = (antlr.CommonAST)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		returnAST = equalExpr_AST;
	}
	
	public final void eorExpr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		antlr.CommonAST eorExpr_AST = null;
		
		try {      // for error handling
			implExpr();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop7:
			do {
				if ((LA(1)==EOR)) {
					antlr.CommonAST tmp3_AST = null;
					tmp3_AST = (antlr.CommonAST)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp3_AST);
					match(EOR);
					implExpr();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop7;
				}
				
			} while (true);
			}
			eorExpr_AST = (antlr.CommonAST)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_2);
		}
		returnAST = eorExpr_AST;
	}
	
	public final void implExpr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		antlr.CommonAST implExpr_AST = null;
		
		try {      // for error handling
			orExpr();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop10:
			do {
				if ((LA(1)==IMPL)) {
					antlr.CommonAST tmp4_AST = null;
					tmp4_AST = (antlr.CommonAST)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp4_AST);
					match(IMPL);
					orExpr();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop10;
				}
				
			} while (true);
			}
			implExpr_AST = (antlr.CommonAST)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_3);
		}
		returnAST = implExpr_AST;
	}
	
	public final void orExpr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		antlr.CommonAST orExpr_AST = null;
		
		try {      // for error handling
			andExpr();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop13:
			do {
				if ((LA(1)==OR)) {
					antlr.CommonAST tmp5_AST = null;
					tmp5_AST = (antlr.CommonAST)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp5_AST);
					match(OR);
					andExpr();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop13;
				}
				
			} while (true);
			}
			orExpr_AST = (antlr.CommonAST)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_4);
		}
		returnAST = orExpr_AST;
	}
	
	public final void andExpr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		antlr.CommonAST andExpr_AST = null;
		
		try {      // for error handling
			postfixExpr();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop16:
			do {
				if ((LA(1)==AND)) {
					antlr.CommonAST tmp6_AST = null;
					tmp6_AST = (antlr.CommonAST)astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp6_AST);
					match(AND);
					postfixExpr();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop16;
				}
				
			} while (true);
			}
			andExpr_AST = (antlr.CommonAST)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_5);
		}
		returnAST = andExpr_AST;
	}
	
	public final void postfixExpr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		antlr.CommonAST postfixExpr_AST = null;
		
		try {      // for error handling
			if ((LA(1)==NOT)) {
				notExpr();
				astFactory.addASTChild(currentAST, returnAST);
				postfixExpr_AST = (antlr.CommonAST)currentAST.root;
			}
			else if ((LA(1)==ID||LA(1)==LPAREN)) {
				atom();
				astFactory.addASTChild(currentAST, returnAST);
				postfixExpr_AST = (antlr.CommonAST)currentAST.root;
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_6);
		}
		returnAST = postfixExpr_AST;
	}
	
	public final void notExpr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		antlr.CommonAST notExpr_AST = null;
		
		try {      // for error handling
			antlr.CommonAST tmp7_AST = null;
			tmp7_AST = (antlr.CommonAST)astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp7_AST);
			match(NOT);
			atom();
			astFactory.addASTChild(currentAST, returnAST);
			notExpr_AST = (antlr.CommonAST)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_6);
		}
		returnAST = notExpr_AST;
	}
	
	public final void atom() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		antlr.CommonAST atom_AST = null;
		
		try {      // for error handling
			if ((LA(1)==ID)) {
				antlr.CommonAST tmp8_AST = null;
				tmp8_AST = (antlr.CommonAST)astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp8_AST);
				match(ID);
				atom_AST = (antlr.CommonAST)currentAST.root;
			}
			else if ((LA(1)==LPAREN)) {
				match(LPAREN);
				equalExpr();
				astFactory.addASTChild(currentAST, returnAST);
				match(RPAREN);
				atom_AST = (antlr.CommonAST)currentAST.root;
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_6);
		}
		returnAST = atom_AST;
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"SEM",
		"EQUAL",
		"EOR",
		"IMPL",
		"OR",
		"AND",
		"NOT",
		"ID",
		"LPAREN",
		"RPAREN",
		"WS"
	};
	
	protected void buildTokenTypeASTClassMap() {
		tokenTypeToASTClassMap=null;
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 2L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 8208L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 8240L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 8304L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 8432L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 8688L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = { 9200L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	
	}
