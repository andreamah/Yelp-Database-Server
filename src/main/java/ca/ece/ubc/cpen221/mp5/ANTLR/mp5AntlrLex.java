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
		PRICE=19, LPAREN=20, RPAREN=21, REVIEWCOUNT=22, WS=23, STRING=24, WSp=25;
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
		"LPAREN", "RPAREN", "REVIEWCOUNT", "WS", "STRING", "WSp"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'||'", "'&&'", "'>'", "'>='", "'<'", "'<='", "'='", null, null, 
		null, null, null, "'open'", "'true'", "'false'", null, null, "'rating'", 
		"'price'", "'('", "')'", "'review_count'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "OR", "AND", "GT", "GTE", "LT", "LTE", "EQ", "IN", "CATEGORY", "SCHOOLS", 
		"NAME", "CITY", "OPEN", "TRUE", "FALSE", "STATE", "NUM", "RATING", "PRICE", 
		"LPAREN", "RPAREN", "REVIEWCOUNT", "WS", "STRING", "WSp"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\33\u00d4\b\1\b\1"+
		"\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t"+
		"\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4"+
		"\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4"+
		"\31\t\31\4\32\t\32\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\6\3\6"+
		"\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3"+
		"\17\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\3\22\6\22\u0096\n\22\r\22\16\22\u0097\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\26\3\26\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\6\30"+
		"\u00b9\n\30\r\30\16\30\u00ba\3\30\3\30\3\31\6\31\u00c0\n\31\r\31\16\31"+
		"\u00c1\3\31\7\31\u00c5\n\31\f\31\16\31\u00c8\13\31\6\31\u00ca\n\31\r\31"+
		"\16\31\u00cb\3\31\3\31\3\32\6\32\u00d1\n\32\r\32\16\32\u00d2\2\2\33\4"+
		"\3\6\4\b\5\n\6\f\7\16\b\20\t\22\n\24\13\26\f\30\r\32\16\34\17\36\20 \21"+
		"\"\22$\23&\24(\25*\26,\27.\30\60\31\62\32\64\33\4\2\3\6\3\2C\\\3\2\62"+
		";\5\2\13\f\17\17\"\"\4\2#),\u0080\2\u00d8\2\4\3\2\2\2\2\6\3\2\2\2\2\b"+
		"\3\2\2\2\2\n\3\2\2\2\2\f\3\2\2\2\2\16\3\2\2\2\2\20\3\2\2\2\2\22\3\2\2"+
		"\2\2\24\3\2\2\2\2\26\3\2\2\2\2\30\3\2\2\2\2\32\3\2\2\2\2\34\3\2\2\2\2"+
		"\36\3\2\2\2\2 \3\2\2\2\2\"\3\2\2\2\2$\3\2\2\2\2&\3\2\2\2\2(\3\2\2\2\2"+
		"*\3\2\2\2\2,\3\2\2\2\2.\3\2\2\2\2\60\3\2\2\2\3\62\3\2\2\2\3\64\3\2\2\2"+
		"\4\66\3\2\2\2\69\3\2\2\2\b<\3\2\2\2\n>\3\2\2\2\fA\3\2\2\2\16C\3\2\2\2"+
		"\20F\3\2\2\2\22H\3\2\2\2\24O\3\2\2\2\26\\\3\2\2\2\30h\3\2\2\2\32q\3\2"+
		"\2\2\34z\3\2\2\2\36\177\3\2\2\2 \u0084\3\2\2\2\"\u008a\3\2\2\2$\u0095"+
		"\3\2\2\2&\u0099\3\2\2\2(\u00a0\3\2\2\2*\u00a6\3\2\2\2,\u00a8\3\2\2\2."+
		"\u00aa\3\2\2\2\60\u00b8\3\2\2\2\62\u00c9\3\2\2\2\64\u00d0\3\2\2\2\66\67"+
		"\7~\2\2\678\7~\2\28\5\3\2\2\29:\7(\2\2:;\7(\2\2;\7\3\2\2\2<=\7@\2\2=\t"+
		"\3\2\2\2>?\7@\2\2?@\7?\2\2@\13\3\2\2\2AB\7>\2\2B\r\3\2\2\2CD\7>\2\2DE"+
		"\7?\2\2E\17\3\2\2\2FG\7?\2\2G\21\3\2\2\2HI\7k\2\2IJ\7p\2\2JK\3\2\2\2K"+
		"L\5*\25\2LM\3\2\2\2MN\b\t\2\2N\23\3\2\2\2OP\7e\2\2PQ\7c\2\2QR\7v\2\2R"+
		"S\7g\2\2ST\7i\2\2TU\7q\2\2UV\7t\2\2VW\7{\2\2WX\3\2\2\2XY\5*\25\2YZ\3\2"+
		"\2\2Z[\b\n\2\2[\25\3\2\2\2\\]\7u\2\2]^\7e\2\2^_\7j\2\2_`\7q\2\2`a\7q\2"+
		"\2ab\7n\2\2bc\7u\2\2cd\3\2\2\2de\5*\25\2ef\3\2\2\2fg\b\13\2\2g\27\3\2"+
		"\2\2hi\7p\2\2ij\7c\2\2jk\7o\2\2kl\7g\2\2lm\3\2\2\2mn\5*\25\2no\3\2\2\2"+
		"op\b\f\2\2p\31\3\2\2\2qr\7e\2\2rs\7k\2\2st\7v\2\2tu\7{\2\2uv\3\2\2\2v"+
		"w\5*\25\2wx\3\2\2\2xy\b\r\2\2y\33\3\2\2\2z{\7q\2\2{|\7r\2\2|}\7g\2\2}"+
		"~\7p\2\2~\35\3\2\2\2\177\u0080\7v\2\2\u0080\u0081\7t\2\2\u0081\u0082\7"+
		"w\2\2\u0082\u0083\7g\2\2\u0083\37\3\2\2\2\u0084\u0085\7h\2\2\u0085\u0086"+
		"\7c\2\2\u0086\u0087\7n\2\2\u0087\u0088\7u\2\2\u0088\u0089\7g\2\2\u0089"+
		"!\3\2\2\2\u008a\u008b\7u\2\2\u008b\u008c\7v\2\2\u008c\u008d\7c\2\2\u008d"+
		"\u008e\7v\2\2\u008e\u008f\7g\2\2\u008f\u0090\3\2\2\2\u0090\u0091\5*\25"+
		"\2\u0091\u0092\t\2\2\2\u0092\u0093\t\2\2\2\u0093#\3\2\2\2\u0094\u0096"+
		"\t\3\2\2\u0095\u0094\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0095\3\2\2\2\u0097"+
		"\u0098\3\2\2\2\u0098%\3\2\2\2\u0099\u009a\7t\2\2\u009a\u009b\7c\2\2\u009b"+
		"\u009c\7v\2\2\u009c\u009d\7k\2\2\u009d\u009e\7p\2\2\u009e\u009f\7i\2\2"+
		"\u009f\'\3\2\2\2\u00a0\u00a1\7r\2\2\u00a1\u00a2\7t\2\2\u00a2\u00a3\7k"+
		"\2\2\u00a3\u00a4\7e\2\2\u00a4\u00a5\7g\2\2\u00a5)\3\2\2\2\u00a6\u00a7"+
		"\7*\2\2\u00a7+\3\2\2\2\u00a8\u00a9\7+\2\2\u00a9-\3\2\2\2\u00aa\u00ab\7"+
		"t\2\2\u00ab\u00ac\7g\2\2\u00ac\u00ad\7x\2\2\u00ad\u00ae\7k\2\2\u00ae\u00af"+
		"\7g\2\2\u00af\u00b0\7y\2\2\u00b0\u00b1\7a\2\2\u00b1\u00b2\7e\2\2\u00b2"+
		"\u00b3\7q\2\2\u00b3\u00b4\7w\2\2\u00b4\u00b5\7p\2\2\u00b5\u00b6\7v\2\2"+
		"\u00b6/\3\2\2\2\u00b7\u00b9\t\4\2\2\u00b8\u00b7\3\2\2\2\u00b9\u00ba\3"+
		"\2\2\2\u00ba\u00b8\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc"+
		"\u00bd\b\30\3\2\u00bd\61\3\2\2\2\u00be\u00c0\t\5\2\2\u00bf\u00be\3\2\2"+
		"\2\u00c0\u00c1\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2\u00c6"+
		"\3\2\2\2\u00c3\u00c5\5\64\32\2\u00c4\u00c3\3\2\2\2\u00c5\u00c8\3\2\2\2"+
		"\u00c6\u00c4\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00ca\3\2\2\2\u00c8\u00c6"+
		"\3\2\2\2\u00c9\u00bf\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cb"+
		"\u00cc\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00ce\b\31\4\2\u00ce\63\3\2\2"+
		"\2\u00cf\u00d1\t\4\2\2\u00d0\u00cf\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d0"+
		"\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\65\3\2\2\2\n\2\3\u0097\u00ba\u00c1"+
		"\u00c6\u00cb\u00d2\5\4\3\2\b\2\2\4\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}