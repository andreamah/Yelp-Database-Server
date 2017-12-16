// Generated from mp5AntlrParse.g4 by ANTLR 4.7.1
package ca.ece.ubc.cpen221.mp5.ANTLR;
import java.util.ArrayList;

import org.antlr.v4.runtime.tree.ParseTreeListener;

import ca.ece.ubc.cpen221.mp5.Restaurant;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link mp5AntlrParse}.
 */
public interface mp5AntlrParseListener extends ParseTreeListener {
	
	/**
	 * Enter a parse tree produced by {@link mp5AntlrParse#root}.
	 * @param ctx the parse tree
	 */
	void enterRoot(mp5AntlrParse.RootContext ctx);
	/**
	 * Exit a parse tree produced by {@link mp5AntlrParse#root}.
	 * @param ctx the parse tree
	 */
	void exitRoot(mp5AntlrParse.RootContext ctx);
	/**
	 * Enter a parse tree produced by {@link mp5AntlrParse#orexpr}.
	 * @param ctx the parse tree
	 */
	void enterOrexpr(mp5AntlrParse.OrexprContext ctx);
	/**
	 * Exit a parse tree produced by {@link mp5AntlrParse#orexpr}.
	 * @param ctx the parse tree
	 */
	void exitOrexpr(mp5AntlrParse.OrexprContext ctx);
	/**
	 * Enter a parse tree produced by {@link mp5AntlrParse#andexpr}.
	 * @param ctx the parse tree
	 */
	void enterAndexpr(mp5AntlrParse.AndexprContext ctx);
	/**
	 * Exit a parse tree produced by {@link mp5AntlrParse#andexpr}.
	 * @param ctx the parse tree
	 */
	void exitAndexpr(mp5AntlrParse.AndexprContext ctx);
	/**
	 * Enter a parse tree produced by {@link mp5AntlrParse#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(mp5AntlrParse.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link mp5AntlrParse#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(mp5AntlrParse.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link mp5AntlrParse#ineq}.
	 * @param ctx the parse tree
	 */
	void enterIneq(mp5AntlrParse.IneqContext ctx);
	/**
	 * Exit a parse tree produced by {@link mp5AntlrParse#ineq}.
	 * @param ctx the parse tree
	 */
	void exitIneq(mp5AntlrParse.IneqContext ctx);
	/**
	 * Enter a parse tree produced by {@link mp5AntlrParse#inp}.
	 * @param ctx the parse tree
	 */
	void enterInp(mp5AntlrParse.InpContext ctx);
	/**
	 * Exit a parse tree produced by {@link mp5AntlrParse#inp}.
	 * @param ctx the parse tree
	 */
	void exitInp(mp5AntlrParse.InpContext ctx);
	/**
	 * Enter a parse tree produced by {@link mp5AntlrParse#categoryp}.
	 * @param ctx the parse tree
	 */
	void enterCategoryp(mp5AntlrParse.CategorypContext ctx);
	/**
	 * Exit a parse tree produced by {@link mp5AntlrParse#categoryp}.
	 * @param ctx the parse tree
	 */
	void exitCategoryp(mp5AntlrParse.CategorypContext ctx);
	/**
	 * Enter a parse tree produced by {@link mp5AntlrParse#namep}.
	 * @param ctx the parse tree
	 */
	void enterNamep(mp5AntlrParse.NamepContext ctx);
	/**
	 * Exit a parse tree produced by {@link mp5AntlrParse#namep}.
	 * @param ctx the parse tree
	 */
	void exitNamep(mp5AntlrParse.NamepContext ctx);
	/**
	 * Enter a parse tree produced by {@link mp5AntlrParse#ratingp}.
	 * @param ctx the parse tree
	 */
	void enterRatingp(mp5AntlrParse.RatingpContext ctx);
	/**
	 * Exit a parse tree produced by {@link mp5AntlrParse#ratingp}.
	 * @param ctx the parse tree
	 */
	void exitRatingp(mp5AntlrParse.RatingpContext ctx);
	/**
	 * Enter a parse tree produced by {@link mp5AntlrParse#pricep}.
	 * @param ctx the parse tree
	 */
	void enterPricep(mp5AntlrParse.PricepContext ctx);
	/**
	 * Exit a parse tree produced by {@link mp5AntlrParse#pricep}.
	 * @param ctx the parse tree
	 */
	void exitPricep(mp5AntlrParse.PricepContext ctx);
	/**
	 * Enter a parse tree produced by {@link mp5AntlrParse#openp}.
	 * @param ctx the parse tree
	 */
	void enterOpenp(mp5AntlrParse.OpenpContext ctx);
	/**
	 * Exit a parse tree produced by {@link mp5AntlrParse#openp}.
	 * @param ctx the parse tree
	 */
	void exitOpenp(mp5AntlrParse.OpenpContext ctx);
	/**
	 * Enter a parse tree produced by {@link mp5AntlrParse#isopen}.
	 * @param ctx the parse tree
	 */
	void enterIsopen(mp5AntlrParse.IsopenContext ctx);
	/**
	 * Exit a parse tree produced by {@link mp5AntlrParse#isopen}.
	 * @param ctx the parse tree
	 */
	void exitIsopen(mp5AntlrParse.IsopenContext ctx);
	/**
	 * Enter a parse tree produced by {@link mp5AntlrParse#statep}.
	 * @param ctx the parse tree
	 */
	void enterStatep(mp5AntlrParse.StatepContext ctx);
	/**
	 * Exit a parse tree produced by {@link mp5AntlrParse#statep}.
	 * @param ctx the parse tree
	 */
	void exitStatep(mp5AntlrParse.StatepContext ctx);
	/**
	 * Enter a parse tree produced by {@link mp5AntlrParse#cityp}.
	 * @param ctx the parse tree
	 */
	void enterCityp(mp5AntlrParse.CitypContext ctx);
	/**
	 * Exit a parse tree produced by {@link mp5AntlrParse#cityp}.
	 * @param ctx the parse tree
	 */
	void exitCityp(mp5AntlrParse.CitypContext ctx);
	/**
	 * Enter a parse tree produced by {@link mp5AntlrParse#reviewcountp}.
	 * @param ctx the parse tree
	 */
	void enterReviewcountp(mp5AntlrParse.ReviewcountpContext ctx);
	/**
	 * Exit a parse tree produced by {@link mp5AntlrParse#reviewcountp}.
	 * @param ctx the parse tree
	 */
	void exitReviewcountp(mp5AntlrParse.ReviewcountpContext ctx);
	/**
	 * Enter a parse tree produced by {@link mp5AntlrParse#schoolsp}.
	 * @param ctx the parse tree
	 */
	void enterSchoolsp(mp5AntlrParse.SchoolspContext ctx);
	/**
	 * Exit a parse tree produced by {@link mp5AntlrParse#schoolsp}.
	 * @param ctx the parse tree
	 */
	void exitSchoolsp(mp5AntlrParse.SchoolspContext ctx);
	/**
	 * Enter a parse tree produced by {@link mp5AntlrParse#businessidp}.
	 * @param ctx the parse tree
	 */
	void enterBusinessidp(mp5AntlrParse.BusinessidpContext ctx);
	/**
	 * Exit a parse tree produced by {@link mp5AntlrParse#businessidp}.
	 * @param ctx the parse tree
	 */
	void exitBusinessidp(mp5AntlrParse.BusinessidpContext ctx);

}