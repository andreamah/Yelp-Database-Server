// Generated from mp5AntlrParse.g4 by ANTLR 4.7.1
package ca.ece.ubc.cpen221.mp5.ANTLR;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class mp5AntlrParse extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		OR=1, AND=2, GT=3, GTE=4, LT=5, LTE=6, EQ=7, IN=8, CATEGORY=9, SCHOOLS=10, 
		NAME=11, CITY=12, OPEN=13, TRUE=14, FALSE=15, STATE=16, NUM=17, RATING=18, 
		PRICE=19, LPAREN=20, RPAREN=21, REVIEWCOUNT=22, BUSINESSID=23, STR=24, 
		WS=25, STRING=26, WSp=27;
	public static final int
		RULE_root = 0, RULE_orexpr = 1, RULE_andexpr = 2, RULE_atom = 3, RULE_ineq = 4, 
		RULE_inp = 5, RULE_categoryp = 6, RULE_namep = 7, RULE_ratingp = 8, RULE_pricep = 9, 
		RULE_openp = 10, RULE_isopen = 11, RULE_statep = 12, RULE_cityp = 13, 
		RULE_reviewcountp = 14, RULE_schoolsp = 15, RULE_businessidp = 16;
	public static final String[] ruleNames = {
		"root", "orexpr", "andexpr", "atom", "ineq", "inp", "categoryp", "namep", 
		"ratingp", "pricep", "openp", "isopen", "statep", "cityp", "reviewcountp", 
		"schoolsp", "businessidp"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'||'", "'&&'", "'>'", "'>='", "'<'", "'<='", "'='", null, null, 
		null, null, null, "'open'", "'true'", "'false'", null, null, "'rating'", 
		"'price'", "'('", "')'", "'review_count'", "'business_id'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "OR", "AND", "GT", "GTE", "LT", "LTE", "EQ", "IN", "CATEGORY", "SCHOOLS", 
		"NAME", "CITY", "OPEN", "TRUE", "FALSE", "STATE", "NUM", "RATING", "PRICE", 
		"LPAREN", "RPAREN", "REVIEWCOUNT", "BUSINESSID", "STR", "WS", "STRING", 
		"WSp"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "mp5AntlrParse.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public mp5AntlrParse(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class RootContext extends ParserRuleContext {
		public OrexprContext orexpr() {
			return getRuleContext(OrexprContext.class,0);
		}
		public TerminalNode EOF() { return getToken(mp5AntlrParse.EOF, 0); }
		public RootContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_root; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mp5AntlrParseListener ) ((mp5AntlrParseListener)listener).enterRoot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mp5AntlrParseListener ) ((mp5AntlrParseListener)listener).exitRoot(this);
		}
	}

	public final RootContext root() throws RecognitionException {
		RootContext _localctx = new RootContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_root);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			orexpr();
			setState(35);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OrexprContext extends ParserRuleContext {
		public List<AndexprContext> andexpr() {
			return getRuleContexts(AndexprContext.class);
		}
		public AndexprContext andexpr(int i) {
			return getRuleContext(AndexprContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(mp5AntlrParse.OR); }
		public TerminalNode OR(int i) {
			return getToken(mp5AntlrParse.OR, i);
		}
		public OrexprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orexpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mp5AntlrParseListener ) ((mp5AntlrParseListener)listener).enterOrexpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mp5AntlrParseListener ) ((mp5AntlrParseListener)listener).exitOrexpr(this);
		}
	}

	public final OrexprContext orexpr() throws RecognitionException {
		OrexprContext _localctx = new OrexprContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_orexpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37);
			andexpr();
			setState(42);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(38);
				match(OR);
				setState(39);
				andexpr();
				}
				}
				setState(44);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AndexprContext extends ParserRuleContext {
		public List<AtomContext> atom() {
			return getRuleContexts(AtomContext.class);
		}
		public AtomContext atom(int i) {
			return getRuleContext(AtomContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(mp5AntlrParse.AND); }
		public TerminalNode AND(int i) {
			return getToken(mp5AntlrParse.AND, i);
		}
		public AndexprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_andexpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mp5AntlrParseListener ) ((mp5AntlrParseListener)listener).enterAndexpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mp5AntlrParseListener ) ((mp5AntlrParseListener)listener).exitAndexpr(this);
		}
	}

	public final AndexprContext andexpr() throws RecognitionException {
		AndexprContext _localctx = new AndexprContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_andexpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			atom();
			setState(50);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(46);
				match(AND);
				setState(47);
				atom();
				}
				}
				setState(52);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AtomContext extends ParserRuleContext {
		public InpContext inp() {
			return getRuleContext(InpContext.class,0);
		}
		public CategorypContext categoryp() {
			return getRuleContext(CategorypContext.class,0);
		}
		public RatingpContext ratingp() {
			return getRuleContext(RatingpContext.class,0);
		}
		public PricepContext pricep() {
			return getRuleContext(PricepContext.class,0);
		}
		public NamepContext namep() {
			return getRuleContext(NamepContext.class,0);
		}
		public OpenpContext openp() {
			return getRuleContext(OpenpContext.class,0);
		}
		public StatepContext statep() {
			return getRuleContext(StatepContext.class,0);
		}
		public CitypContext cityp() {
			return getRuleContext(CitypContext.class,0);
		}
		public ReviewcountpContext reviewcountp() {
			return getRuleContext(ReviewcountpContext.class,0);
		}
		public SchoolspContext schoolsp() {
			return getRuleContext(SchoolspContext.class,0);
		}
		public BusinessidpContext businessidp() {
			return getRuleContext(BusinessidpContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(mp5AntlrParse.LPAREN, 0); }
		public OrexprContext orexpr() {
			return getRuleContext(OrexprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(mp5AntlrParse.RPAREN, 0); }
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mp5AntlrParseListener ) ((mp5AntlrParseListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mp5AntlrParseListener ) ((mp5AntlrParseListener)listener).exitAtom(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_atom);
		try {
			setState(68);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IN:
				enterOuterAlt(_localctx, 1);
				{
				setState(53);
				inp();
				}
				break;
			case CATEGORY:
				enterOuterAlt(_localctx, 2);
				{
				setState(54);
				categoryp();
				}
				break;
			case RATING:
				enterOuterAlt(_localctx, 3);
				{
				setState(55);
				ratingp();
				}
				break;
			case PRICE:
				enterOuterAlt(_localctx, 4);
				{
				setState(56);
				pricep();
				}
				break;
			case NAME:
				enterOuterAlt(_localctx, 5);
				{
				setState(57);
				namep();
				}
				break;
			case OPEN:
				enterOuterAlt(_localctx, 6);
				{
				setState(58);
				openp();
				}
				break;
			case STATE:
				enterOuterAlt(_localctx, 7);
				{
				setState(59);
				statep();
				}
				break;
			case CITY:
				enterOuterAlt(_localctx, 8);
				{
				setState(60);
				cityp();
				}
				break;
			case REVIEWCOUNT:
				enterOuterAlt(_localctx, 9);
				{
				setState(61);
				reviewcountp();
				}
				break;
			case SCHOOLS:
				enterOuterAlt(_localctx, 10);
				{
				setState(62);
				schoolsp();
				}
				break;
			case BUSINESSID:
				enterOuterAlt(_localctx, 11);
				{
				setState(63);
				businessidp();
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 12);
				{
				setState(64);
				match(LPAREN);
				setState(65);
				orexpr();
				setState(66);
				match(RPAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IneqContext extends ParserRuleContext {
		public TerminalNode GT() { return getToken(mp5AntlrParse.GT, 0); }
		public TerminalNode GTE() { return getToken(mp5AntlrParse.GTE, 0); }
		public TerminalNode LT() { return getToken(mp5AntlrParse.LT, 0); }
		public TerminalNode LTE() { return getToken(mp5AntlrParse.LTE, 0); }
		public TerminalNode EQ() { return getToken(mp5AntlrParse.EQ, 0); }
		public IneqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ineq; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mp5AntlrParseListener ) ((mp5AntlrParseListener)listener).enterIneq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mp5AntlrParseListener ) ((mp5AntlrParseListener)listener).exitIneq(this);
		}
	}

	public final IneqContext ineq() throws RecognitionException {
		IneqContext _localctx = new IneqContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_ineq);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GT) | (1L << GTE) | (1L << LT) | (1L << LTE) | (1L << EQ))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InpContext extends ParserRuleContext {
		public TerminalNode IN() { return getToken(mp5AntlrParse.IN, 0); }
		public TerminalNode RPAREN() { return getToken(mp5AntlrParse.RPAREN, 0); }
		public TerminalNode STRING() { return getToken(mp5AntlrParse.STRING, 0); }
		public InpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mp5AntlrParseListener ) ((mp5AntlrParseListener)listener).enterInp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mp5AntlrParseListener ) ((mp5AntlrParseListener)listener).exitInp(this);
		}
	}

	public final InpContext inp() throws RecognitionException {
		InpContext _localctx = new InpContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_inp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(IN);
			{
			setState(73);
			match(STRING);
			}
			setState(74);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CategorypContext extends ParserRuleContext {
		public TerminalNode CATEGORY() { return getToken(mp5AntlrParse.CATEGORY, 0); }
		public TerminalNode RPAREN() { return getToken(mp5AntlrParse.RPAREN, 0); }
		public TerminalNode STRING() { return getToken(mp5AntlrParse.STRING, 0); }
		public CategorypContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_categoryp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mp5AntlrParseListener ) ((mp5AntlrParseListener)listener).enterCategoryp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mp5AntlrParseListener ) ((mp5AntlrParseListener)listener).exitCategoryp(this);
		}
	}

	public final CategorypContext categoryp() throws RecognitionException {
		CategorypContext _localctx = new CategorypContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_categoryp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			match(CATEGORY);
			{
			setState(77);
			match(STRING);
			}
			setState(78);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NamepContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(mp5AntlrParse.NAME, 0); }
		public TerminalNode RPAREN() { return getToken(mp5AntlrParse.RPAREN, 0); }
		public TerminalNode STRING() { return getToken(mp5AntlrParse.STRING, 0); }
		public NamepContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namep; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mp5AntlrParseListener ) ((mp5AntlrParseListener)listener).enterNamep(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mp5AntlrParseListener ) ((mp5AntlrParseListener)listener).exitNamep(this);
		}
	}

	public final NamepContext namep() throws RecognitionException {
		NamepContext _localctx = new NamepContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_namep);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(NAME);
			{
			setState(81);
			match(STRING);
			}
			setState(82);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RatingpContext extends ParserRuleContext {
		public TerminalNode RATING() { return getToken(mp5AntlrParse.RATING, 0); }
		public IneqContext ineq() {
			return getRuleContext(IneqContext.class,0);
		}
		public TerminalNode NUM() { return getToken(mp5AntlrParse.NUM, 0); }
		public RatingpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ratingp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mp5AntlrParseListener ) ((mp5AntlrParseListener)listener).enterRatingp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mp5AntlrParseListener ) ((mp5AntlrParseListener)listener).exitRatingp(this);
		}
	}

	public final RatingpContext ratingp() throws RecognitionException {
		RatingpContext _localctx = new RatingpContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_ratingp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(RATING);
			setState(85);
			ineq();
			setState(86);
			match(NUM);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PricepContext extends ParserRuleContext {
		public TerminalNode PRICE() { return getToken(mp5AntlrParse.PRICE, 0); }
		public IneqContext ineq() {
			return getRuleContext(IneqContext.class,0);
		}
		public TerminalNode NUM() { return getToken(mp5AntlrParse.NUM, 0); }
		public PricepContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pricep; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mp5AntlrParseListener ) ((mp5AntlrParseListener)listener).enterPricep(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mp5AntlrParseListener ) ((mp5AntlrParseListener)listener).exitPricep(this);
		}
	}

	public final PricepContext pricep() throws RecognitionException {
		PricepContext _localctx = new PricepContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_pricep);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			match(PRICE);
			setState(89);
			ineq();
			setState(90);
			match(NUM);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OpenpContext extends ParserRuleContext {
		public TerminalNode OPEN() { return getToken(mp5AntlrParse.OPEN, 0); }
		public TerminalNode LPAREN() { return getToken(mp5AntlrParse.LPAREN, 0); }
		public IsopenContext isopen() {
			return getRuleContext(IsopenContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(mp5AntlrParse.RPAREN, 0); }
		public OpenpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_openp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mp5AntlrParseListener ) ((mp5AntlrParseListener)listener).enterOpenp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mp5AntlrParseListener ) ((mp5AntlrParseListener)listener).exitOpenp(this);
		}
	}

	public final OpenpContext openp() throws RecognitionException {
		OpenpContext _localctx = new OpenpContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_openp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			match(OPEN);
			setState(93);
			match(LPAREN);
			setState(94);
			isopen();
			setState(95);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IsopenContext extends ParserRuleContext {
		public TerminalNode TRUE() { return getToken(mp5AntlrParse.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(mp5AntlrParse.FALSE, 0); }
		public IsopenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_isopen; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mp5AntlrParseListener ) ((mp5AntlrParseListener)listener).enterIsopen(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mp5AntlrParseListener ) ((mp5AntlrParseListener)listener).exitIsopen(this);
		}
	}

	public final IsopenContext isopen() throws RecognitionException {
		IsopenContext _localctx = new IsopenContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_isopen);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			_la = _input.LA(1);
			if ( !(_la==TRUE || _la==FALSE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatepContext extends ParserRuleContext {
		public TerminalNode STATE() { return getToken(mp5AntlrParse.STATE, 0); }
		public TerminalNode RPAREN() { return getToken(mp5AntlrParse.RPAREN, 0); }
		public StatepContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statep; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mp5AntlrParseListener ) ((mp5AntlrParseListener)listener).enterStatep(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mp5AntlrParseListener ) ((mp5AntlrParseListener)listener).exitStatep(this);
		}
	}

	public final StatepContext statep() throws RecognitionException {
		StatepContext _localctx = new StatepContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_statep);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			match(STATE);
			setState(100);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CitypContext extends ParserRuleContext {
		public TerminalNode CITY() { return getToken(mp5AntlrParse.CITY, 0); }
		public TerminalNode RPAREN() { return getToken(mp5AntlrParse.RPAREN, 0); }
		public TerminalNode STRING() { return getToken(mp5AntlrParse.STRING, 0); }
		public CitypContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cityp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mp5AntlrParseListener ) ((mp5AntlrParseListener)listener).enterCityp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mp5AntlrParseListener ) ((mp5AntlrParseListener)listener).exitCityp(this);
		}
	}

	public final CitypContext cityp() throws RecognitionException {
		CitypContext _localctx = new CitypContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_cityp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			match(CITY);
			{
			setState(103);
			match(STRING);
			}
			setState(104);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReviewcountpContext extends ParserRuleContext {
		public TerminalNode REVIEWCOUNT() { return getToken(mp5AntlrParse.REVIEWCOUNT, 0); }
		public IneqContext ineq() {
			return getRuleContext(IneqContext.class,0);
		}
		public TerminalNode NUM() { return getToken(mp5AntlrParse.NUM, 0); }
		public ReviewcountpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reviewcountp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mp5AntlrParseListener ) ((mp5AntlrParseListener)listener).enterReviewcountp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mp5AntlrParseListener ) ((mp5AntlrParseListener)listener).exitReviewcountp(this);
		}
	}

	public final ReviewcountpContext reviewcountp() throws RecognitionException {
		ReviewcountpContext _localctx = new ReviewcountpContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_reviewcountp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			match(REVIEWCOUNT);
			setState(107);
			ineq();
			setState(108);
			match(NUM);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SchoolspContext extends ParserRuleContext {
		public TerminalNode SCHOOLS() { return getToken(mp5AntlrParse.SCHOOLS, 0); }
		public TerminalNode RPAREN() { return getToken(mp5AntlrParse.RPAREN, 0); }
		public TerminalNode STRING() { return getToken(mp5AntlrParse.STRING, 0); }
		public SchoolspContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_schoolsp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mp5AntlrParseListener ) ((mp5AntlrParseListener)listener).enterSchoolsp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mp5AntlrParseListener ) ((mp5AntlrParseListener)listener).exitSchoolsp(this);
		}
	}

	public final SchoolspContext schoolsp() throws RecognitionException {
		SchoolspContext _localctx = new SchoolspContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_schoolsp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			match(SCHOOLS);
			{
			setState(111);
			match(STRING);
			}
			setState(112);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BusinessidpContext extends ParserRuleContext {
		public TerminalNode BUSINESSID() { return getToken(mp5AntlrParse.BUSINESSID, 0); }
		public TerminalNode LPAREN() { return getToken(mp5AntlrParse.LPAREN, 0); }
		public TerminalNode STR() { return getToken(mp5AntlrParse.STR, 0); }
		public TerminalNode RPAREN() { return getToken(mp5AntlrParse.RPAREN, 0); }
		public BusinessidpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_businessidp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mp5AntlrParseListener ) ((mp5AntlrParseListener)listener).enterBusinessidp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mp5AntlrParseListener ) ((mp5AntlrParseListener)listener).exitBusinessidp(this);
		}
	}

	public final BusinessidpContext businessidp() throws RecognitionException {
		BusinessidpContext _localctx = new BusinessidpContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_businessidp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			match(BUSINESSID);
			setState(115);
			match(LPAREN);
			setState(116);
			match(STR);
			setState(117);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\35z\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22\3\2\3"+
		"\2\3\2\3\3\3\3\3\3\7\3+\n\3\f\3\16\3.\13\3\3\4\3\4\3\4\7\4\63\n\4\f\4"+
		"\16\4\66\13\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\5\5G\n\5\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t"+
		"\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\16"+
		"\3\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\2\2\23\2\4\6\b\n\f\16\20\22\24\26\30\32"+
		"\34\36 \"\2\4\3\2\5\t\3\2\20\21\2u\2$\3\2\2\2\4\'\3\2\2\2\6/\3\2\2\2\b"+
		"F\3\2\2\2\nH\3\2\2\2\fJ\3\2\2\2\16N\3\2\2\2\20R\3\2\2\2\22V\3\2\2\2\24"+
		"Z\3\2\2\2\26^\3\2\2\2\30c\3\2\2\2\32e\3\2\2\2\34h\3\2\2\2\36l\3\2\2\2"+
		" p\3\2\2\2\"t\3\2\2\2$%\5\4\3\2%&\7\2\2\3&\3\3\2\2\2\',\5\6\4\2()\7\3"+
		"\2\2)+\5\6\4\2*(\3\2\2\2+.\3\2\2\2,*\3\2\2\2,-\3\2\2\2-\5\3\2\2\2.,\3"+
		"\2\2\2/\64\5\b\5\2\60\61\7\4\2\2\61\63\5\b\5\2\62\60\3\2\2\2\63\66\3\2"+
		"\2\2\64\62\3\2\2\2\64\65\3\2\2\2\65\7\3\2\2\2\66\64\3\2\2\2\67G\5\f\7"+
		"\28G\5\16\b\29G\5\22\n\2:G\5\24\13\2;G\5\20\t\2<G\5\26\f\2=G\5\32\16\2"+
		">G\5\34\17\2?G\5\36\20\2@G\5 \21\2AG\5\"\22\2BC\7\26\2\2CD\5\4\3\2DE\7"+
		"\27\2\2EG\3\2\2\2F\67\3\2\2\2F8\3\2\2\2F9\3\2\2\2F:\3\2\2\2F;\3\2\2\2"+
		"F<\3\2\2\2F=\3\2\2\2F>\3\2\2\2F?\3\2\2\2F@\3\2\2\2FA\3\2\2\2FB\3\2\2\2"+
		"G\t\3\2\2\2HI\t\2\2\2I\13\3\2\2\2JK\7\n\2\2KL\7\34\2\2LM\7\27\2\2M\r\3"+
		"\2\2\2NO\7\13\2\2OP\7\34\2\2PQ\7\27\2\2Q\17\3\2\2\2RS\7\r\2\2ST\7\34\2"+
		"\2TU\7\27\2\2U\21\3\2\2\2VW\7\24\2\2WX\5\n\6\2XY\7\23\2\2Y\23\3\2\2\2"+
		"Z[\7\25\2\2[\\\5\n\6\2\\]\7\23\2\2]\25\3\2\2\2^_\7\17\2\2_`\7\26\2\2`"+
		"a\5\30\r\2ab\7\27\2\2b\27\3\2\2\2cd\t\3\2\2d\31\3\2\2\2ef\7\22\2\2fg\7"+
		"\27\2\2g\33\3\2\2\2hi\7\16\2\2ij\7\34\2\2jk\7\27\2\2k\35\3\2\2\2lm\7\30"+
		"\2\2mn\5\n\6\2no\7\23\2\2o\37\3\2\2\2pq\7\f\2\2qr\7\34\2\2rs\7\27\2\2"+
		"s!\3\2\2\2tu\7\31\2\2uv\7\26\2\2vw\7\32\2\2wx\7\27\2\2x#\3\2\2\2\5,\64"+
		"F";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}