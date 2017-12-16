// Generated from mp5AntlrLex.g4 by ANTLR 4.7.1

package ca.ece.ubc.cpen221.mp5.ANTLR;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class mp5AntlrLex extends Lexer {
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
		ISLAND=1;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE", "ISLAND"
	};

	public static final String[] ruleNames = {
		"OR", "AND", "GT", "GTE", "LT", "LTE", "EQ", "IN", "CATEGORY", "SCHOOLS", 
		"NAME", "CITY", "OPEN", "TRUE", "FALSE", "STATE", "NUM", "RATING", "PRICE", 
		"LPAREN", "RPAREN", "REVIEWCOUNT", "BUSINESSID", "STR", "WS", "STRING", 
		"WSp"
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


	public mp5AntlrLex(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "mp5AntlrLex.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\35\u00f9\b\1\b\1"+
		"\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t"+
		"\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4"+
		"\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4"+
		"\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4"+
		"\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3"+
		"\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\6\22\u009a\n\22\r\22\16\22\u009b"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\25"+
		"\3\25\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\31\7\31\u00c9\n\31\f\31\16\31\u00cc\13\31\3\31\6\31\u00cf\n\31\r\31"+
		"\16\31\u00d0\3\31\7\31\u00d4\n\31\f\31\16\31\u00d7\13\31\6\31\u00d9\n"+
		"\31\r\31\16\31\u00da\3\32\6\32\u00de\n\32\r\32\16\32\u00df\3\32\3\32\3"+
		"\33\6\33\u00e5\n\33\r\33\16\33\u00e6\3\33\7\33\u00ea\n\33\f\33\16\33\u00ed"+
		"\13\33\6\33\u00ef\n\33\r\33\16\33\u00f0\3\33\3\33\3\34\6\34\u00f6\n\34"+
		"\r\34\16\34\u00f7\2\2\35\4\3\6\4\b\5\n\6\f\7\16\b\20\t\22\n\24\13\26\f"+
		"\30\r\32\16\34\17\36\20 \21\"\22$\23&\24(\25*\26,\27.\30\60\31\62\32\64"+
		"\33\66\348\35\4\2\3\7\3\2C\\\3\2\62;\4\2C\\c|\5\2\62;C\\c|\5\2\13\f\17"+
		"\17\"\"\2\u0101\2\4\3\2\2\2\2\6\3\2\2\2\2\b\3\2\2\2\2\n\3\2\2\2\2\f\3"+
		"\2\2\2\2\16\3\2\2\2\2\20\3\2\2\2\2\22\3\2\2\2\2\24\3\2\2\2\2\26\3\2\2"+
		"\2\2\30\3\2\2\2\2\32\3\2\2\2\2\34\3\2\2\2\2\36\3\2\2\2\2 \3\2\2\2\2\""+
		"\3\2\2\2\2$\3\2\2\2\2&\3\2\2\2\2(\3\2\2\2\2*\3\2\2\2\2,\3\2\2\2\2.\3\2"+
		"\2\2\2\60\3\2\2\2\2\62\3\2\2\2\2\64\3\2\2\2\3\66\3\2\2\2\38\3\2\2\2\4"+
		":\3\2\2\2\6=\3\2\2\2\b@\3\2\2\2\nB\3\2\2\2\fE\3\2\2\2\16G\3\2\2\2\20J"+
		"\3\2\2\2\22L\3\2\2\2\24S\3\2\2\2\26`\3\2\2\2\30l\3\2\2\2\32u\3\2\2\2\34"+
		"~\3\2\2\2\36\u0083\3\2\2\2 \u0088\3\2\2\2\"\u008e\3\2\2\2$\u0099\3\2\2"+
		"\2&\u009d\3\2\2\2(\u00a4\3\2\2\2*\u00aa\3\2\2\2,\u00ac\3\2\2\2.\u00ae"+
		"\3\2\2\2\60\u00bb\3\2\2\2\62\u00d8\3\2\2\2\64\u00dd\3\2\2\2\66\u00ee\3"+
		"\2\2\28\u00f5\3\2\2\2:;\7~\2\2;<\7~\2\2<\5\3\2\2\2=>\7(\2\2>?\7(\2\2?"+
		"\7\3\2\2\2@A\7@\2\2A\t\3\2\2\2BC\7@\2\2CD\7?\2\2D\13\3\2\2\2EF\7>\2\2"+
		"F\r\3\2\2\2GH\7>\2\2HI\7?\2\2I\17\3\2\2\2JK\7?\2\2K\21\3\2\2\2LM\7k\2"+
		"\2MN\7p\2\2NO\3\2\2\2OP\5*\25\2PQ\3\2\2\2QR\b\t\2\2R\23\3\2\2\2ST\7e\2"+
		"\2TU\7c\2\2UV\7v\2\2VW\7g\2\2WX\7i\2\2XY\7q\2\2YZ\7t\2\2Z[\7{\2\2[\\\3"+
		"\2\2\2\\]\5*\25\2]^\3\2\2\2^_\b\n\2\2_\25\3\2\2\2`a\7u\2\2ab\7e\2\2bc"+
		"\7j\2\2cd\7q\2\2de\7q\2\2ef\7n\2\2fg\7u\2\2gh\3\2\2\2hi\5*\25\2ij\3\2"+
		"\2\2jk\b\13\2\2k\27\3\2\2\2lm\7p\2\2mn\7c\2\2no\7o\2\2op\7g\2\2pq\3\2"+
		"\2\2qr\5*\25\2rs\3\2\2\2st\b\f\2\2t\31\3\2\2\2uv\7e\2\2vw\7k\2\2wx\7v"+
		"\2\2xy\7{\2\2yz\3\2\2\2z{\5*\25\2{|\3\2\2\2|}\b\r\2\2}\33\3\2\2\2~\177"+
		"\7q\2\2\177\u0080\7r\2\2\u0080\u0081\7g\2\2\u0081\u0082\7p\2\2\u0082\35"+
		"\3\2\2\2\u0083\u0084\7v\2\2\u0084\u0085\7t\2\2\u0085\u0086\7w\2\2\u0086"+
		"\u0087\7g\2\2\u0087\37\3\2\2\2\u0088\u0089\7h\2\2\u0089\u008a\7c\2\2\u008a"+
		"\u008b\7n\2\2\u008b\u008c\7u\2\2\u008c\u008d\7g\2\2\u008d!\3\2\2\2\u008e"+
		"\u008f\7u\2\2\u008f\u0090\7v\2\2\u0090\u0091\7c\2\2\u0091\u0092\7v\2\2"+
		"\u0092\u0093\7g\2\2\u0093\u0094\3\2\2\2\u0094\u0095\5*\25\2\u0095\u0096"+
		"\t\2\2\2\u0096\u0097\t\2\2\2\u0097#\3\2\2\2\u0098\u009a\t\3\2\2\u0099"+
		"\u0098\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u0099\3\2\2\2\u009b\u009c\3\2"+
		"\2\2\u009c%\3\2\2\2\u009d\u009e\7t\2\2\u009e\u009f\7c\2\2\u009f\u00a0"+
		"\7v\2\2\u00a0\u00a1\7k\2\2\u00a1\u00a2\7p\2\2\u00a2\u00a3\7i\2\2\u00a3"+
		"\'\3\2\2\2\u00a4\u00a5\7r\2\2\u00a5\u00a6\7t\2\2\u00a6\u00a7\7k\2\2\u00a7"+
		"\u00a8\7e\2\2\u00a8\u00a9\7g\2\2\u00a9)\3\2\2\2\u00aa\u00ab\7*\2\2\u00ab"+
		"+\3\2\2\2\u00ac\u00ad\7+\2\2\u00ad-\3\2\2\2\u00ae\u00af\7t\2\2\u00af\u00b0"+
		"\7g\2\2\u00b0\u00b1\7x\2\2\u00b1\u00b2\7k\2\2\u00b2\u00b3\7g\2\2\u00b3"+
		"\u00b4\7y\2\2\u00b4\u00b5\7a\2\2\u00b5\u00b6\7e\2\2\u00b6\u00b7\7q\2\2"+
		"\u00b7\u00b8\7w\2\2\u00b8\u00b9\7p\2\2\u00b9\u00ba\7v\2\2\u00ba/\3\2\2"+
		"\2\u00bb\u00bc\7d\2\2\u00bc\u00bd\7w\2\2\u00bd\u00be\7u\2\2\u00be\u00bf"+
		"\7k\2\2\u00bf\u00c0\7p\2\2\u00c0\u00c1\7g\2\2\u00c1\u00c2\7u\2\2\u00c2"+
		"\u00c3\7u\2\2\u00c3\u00c4\7a\2\2\u00c4\u00c5\7k\2\2\u00c5\u00c6\7f\2\2"+
		"\u00c6\61\3\2\2\2\u00c7\u00c9\t\4\2\2\u00c8\u00c7\3\2\2\2\u00c9\u00cc"+
		"\3\2\2\2\u00ca\u00c8\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb\u00ce\3\2\2\2\u00cc"+
		"\u00ca\3\2\2\2\u00cd\u00cf\5$\22\2\u00ce\u00cd\3\2\2\2\u00cf\u00d0\3\2"+
		"\2\2\u00d0\u00ce\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00d5\3\2\2\2\u00d2"+
		"\u00d4\t\5\2\2\u00d3\u00d2\3\2\2\2\u00d4\u00d7\3\2\2\2\u00d5\u00d3\3\2"+
		"\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d9\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d8"+
		"\u00ca\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00d8\3\2\2\2\u00da\u00db\3\2"+
		"\2\2\u00db\63\3\2\2\2\u00dc\u00de\t\6\2\2\u00dd\u00dc\3\2\2\2\u00de\u00df"+
		"\3\2\2\2\u00df\u00dd\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1"+
		"\u00e2\b\32\3\2\u00e2\65\3\2\2\2\u00e3\u00e5\t\5\2\2\u00e4\u00e3\3\2\2"+
		"\2\u00e5\u00e6\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00eb"+
		"\3\2\2\2\u00e8\u00ea\58\34\2\u00e9\u00e8\3\2\2\2\u00ea\u00ed\3\2\2\2\u00eb"+
		"\u00e9\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00ef\3\2\2\2\u00ed\u00eb\3\2"+
		"\2\2\u00ee\u00e4\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f0"+
		"\u00f1\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2\u00f3\b\33\4\2\u00f3\67\3\2\2"+
		"\2\u00f4\u00f6\t\6\2\2\u00f5\u00f4\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7\u00f5"+
		"\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f89\3\2\2\2\16\2\3\u009b\u00ca\u00d0\u00d5"+
		"\u00da\u00df\u00e6\u00eb\u00f0\u00f7\5\4\3\2\b\2\2\4\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}