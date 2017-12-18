package ca.ece.ubc.cpen221.mp5.ANTLR;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.ANTLR.mp5AntlrParse.AndexprContext;
import ca.ece.ubc.cpen221.mp5.ANTLR.mp5AntlrParse.AtomContext;
import ca.ece.ubc.cpen221.mp5.ANTLR.mp5AntlrParse.CategorypContext;
import ca.ece.ubc.cpen221.mp5.ANTLR.mp5AntlrParse.CitypContext;
import ca.ece.ubc.cpen221.mp5.ANTLR.mp5AntlrParse.IneqContext;
import ca.ece.ubc.cpen221.mp5.ANTLR.mp5AntlrParse.InpContext;
import ca.ece.ubc.cpen221.mp5.ANTLR.mp5AntlrParse.IsopenContext;
import ca.ece.ubc.cpen221.mp5.ANTLR.mp5AntlrParse.NamepContext;
import ca.ece.ubc.cpen221.mp5.ANTLR.mp5AntlrParse.OpenpContext;
import ca.ece.ubc.cpen221.mp5.ANTLR.mp5AntlrParse.OrexprContext;
import ca.ece.ubc.cpen221.mp5.ANTLR.mp5AntlrParse.PricepContext;
import ca.ece.ubc.cpen221.mp5.ANTLR.mp5AntlrParse.RatingpContext;
import ca.ece.ubc.cpen221.mp5.ANTLR.mp5AntlrParse.ReviewcountpContext;
import ca.ece.ubc.cpen221.mp5.ANTLR.mp5AntlrParse.RootContext;
import ca.ece.ubc.cpen221.mp5.ANTLR.mp5AntlrParse.SchoolspContext;
import ca.ece.ubc.cpen221.mp5.ANTLR.mp5AntlrParse.StatepContext;

public class mp5AntlrListenerCollect implements mp5AntlrParseListener {
	private Stack<ArrayList<Restaurant>> stack = new Stack<ArrayList<Restaurant>>();
	private ArrayList<Restaurant> filteredList;

	private Stack<String> ineq = new Stack<String>();
	private ArrayList<Restaurant> restaurants;
	
	
	public mp5AntlrListenerCollect(ArrayList<Restaurant> rests)
	{
		this.restaurants = rests;
	}
	
	public ArrayList<Restaurant> getFilteredList() {
		return new ArrayList<Restaurant>(filteredList);
	}
	
	public ArrayList<Restaurant> getYelpRestaurants() {
		return new ArrayList<Restaurant>(restaurants); 
	}
	
	@Override
	public void enterEveryRule(ParserRuleContext arg0) {}

	@Override
	public void exitEveryRule(ParserRuleContext arg0) {}

	@Override
	public void visitErrorNode(ErrorNode arg0) {}

	@Override
	public void visitTerminal(TerminalNode arg0) {}

	@Override
	public void enterRoot(RootContext ctx) {}

	@Override
	public void exitRoot(RootContext ctx) {
		filteredList = stack.pop();
	}

	@Override
	public void enterOrexpr(OrexprContext ctx) {}

	@Override
	public void exitOrexpr(OrexprContext ctx) {
		int numChildren = (ctx.getChildCount()/2 )+1;
		if (numChildren>1)
		{
		ArrayList<ArrayList<Restaurant>> restList= new ArrayList<ArrayList<Restaurant>>();
		
		for (int i = 0; i < numChildren ; i++)
		{
			restList.add(stack.pop());
		}
		Set<Restaurant> mainSet = new HashSet<>();
		
		for (int j = 0; j < numChildren;j++)
		{
			mainSet.addAll(new HashSet<Restaurant>(restList.get(j)));
		}
		
		ArrayList<Restaurant> mainList= new ArrayList<Restaurant>(mainSet);
		
		stack.push(mainList);
		}
	}

	@Override
	public void enterAndexpr(AndexprContext ctx) {}

	@Override
	public void exitAndexpr(AndexprContext ctx) {
		int numChildren = (ctx.getChildCount()/2 )+1;
		if (numChildren>1)
		{
		ArrayList<ArrayList<Restaurant>> restList= new ArrayList<ArrayList<Restaurant>>();
		
		for (int i = 0; i < numChildren ; i++)
		{
			restList.add(stack.pop());
		}
		
		for (int j = 0; j < numChildren-1;j++)
		{
			restList.get(j+1).retainAll(restList.get(j));
		}
		
		stack.push(restList.get(numChildren-1));
		}
	}

	@Override
	public void enterAtom(AtomContext ctx) {}

	@Override
	public void exitAtom(AtomContext ctx) {}

	@Override
	public void enterIneq(IneqContext ctx) {}

	@Override
	public void exitIneq(IneqContext ctx) {
		String tokenString = ctx.getChild(0).toString();
		ineq.push(tokenString);
	}

	@Override
	public void enterInp(InpContext ctx) { }

	@Override
	public void exitInp(InpContext ctx) {
		
		String tokenString = ctx.STRING().toString();
		
		ArrayList<Restaurant> rests = getYelpRestaurants();
		rests = (ArrayList<Restaurant>) rests.stream()
				.filter(r -> Arrays.asList(r.getNeighborhoods()).contains(tokenString))
				.collect(Collectors.toList());
		
		stack.push(rests);
	}

	@Override
	public void enterCategoryp(CategorypContext ctx) { }

	@Override
	public void exitCategoryp(CategorypContext ctx) {
		String tokenString = ctx.STRING().toString();
		
		ArrayList<Restaurant> rests = getYelpRestaurants();
		rests = (ArrayList<Restaurant>) rests.stream()
				.filter(r -> Arrays.asList(r.getCategories()).contains(tokenString))
				.collect(Collectors.toList());
		
		stack.push(rests);
		}

	@Override
	public void enterNamep(NamepContext ctx) {}

	@Override
	public void exitNamep(NamepContext ctx) {
		String tokenString = ctx.STRING().toString();
		
		ArrayList<Restaurant> rests = getYelpRestaurants();
		rests = (ArrayList<Restaurant>) rests.stream()
				.filter(r -> (r.getName()).equals(tokenString))
				.collect(Collectors.toList());
		
		stack.push(rests);
	}

	@Override
	public void enterRatingp(RatingpContext ctx) {}

	@Override
	public void exitRatingp(RatingpContext ctx) {
		int comparedNum = Integer.parseInt(ctx.NUM().toString());
		
		String comparator = ineq.pop();
		ArrayList<Restaurant> rests = getYelpRestaurants();
		
		if ("<".equals(comparator)) {
			rests = (ArrayList<Restaurant>) rests.stream()
					.filter(r -> r.getStars() < comparedNum)
					.collect(Collectors.toList());
		} else if ("<=".equals(comparator)) {
			rests = (ArrayList<Restaurant>) rests.stream()
					.filter(r -> r.getStars() <= comparedNum)
					.collect(Collectors.toList());
		} else if ("=".equals(comparator)) {
			rests = (ArrayList<Restaurant>) rests.stream()
					.filter(r -> r.getStars() == comparedNum)
					.collect(Collectors.toList());
		} else if (">=".equals(comparator)) {
			rests = (ArrayList<Restaurant>) rests.stream()
					.filter(r -> r.getStars() >= comparedNum)
					.collect(Collectors.toList());
		} else if (">".equals(comparator)) {
			rests = (ArrayList<Restaurant>) rests.stream()
					.filter(r -> r.getStars() > comparedNum)
					.collect(Collectors.toList());
		} 
		
		stack.push(rests);
	}

	@Override
	public void enterPricep(PricepContext ctx) {}

	@Override
	public void exitPricep(PricepContext ctx) {
		
		int comparedNum = Integer.parseInt(ctx.NUM().toString());
		
		String comparator = ineq.pop();
		ArrayList<Restaurant> rests = getYelpRestaurants();
		
		if ("<".equals(comparator)) {
			rests = (ArrayList<Restaurant>) rests.stream()
					.filter(r -> r.getPrice() < comparedNum)
					.collect(Collectors.toList());
		} else if ("<=".equals(comparator)) {
			rests = (ArrayList<Restaurant>) rests.stream()
					.filter(r -> r.getPrice() <= comparedNum)
					.collect(Collectors.toList());
		} else if ("=".equals(comparator)) {
			rests = (ArrayList<Restaurant>) rests.stream()
					.filter(r -> r.getPrice() == comparedNum)
					.collect(Collectors.toList());
		} else if (">=".equals(comparator)) {
			rests = (ArrayList<Restaurant>) rests.stream()
					.filter(r -> r.getPrice() >= comparedNum)
					.collect(Collectors.toList());
		} else if (">".equals(comparator)) {
			rests = (ArrayList<Restaurant>) rests.stream()
					.filter(r -> r.getPrice() > comparedNum)
					.collect(Collectors.toList());
		} 
		
		stack.push(rests);
		
	}

	@Override
	public void enterOpenp(OpenpContext ctx) {}

	@Override
	public void exitOpenp(OpenpContext ctx) {}

	@Override
	public void enterIsopen(IsopenContext ctx) {}

	@Override
	public void exitIsopen(IsopenContext ctx) {

		String tokenString = ctx.getChild(0).toString();
		ArrayList<Restaurant> rests = getYelpRestaurants();
		
		if ("true".equals(tokenString)) {
			rests = (ArrayList<Restaurant>) rests.stream()
					.filter(r -> r.isOpen())
					.collect(Collectors.toList());
		} else if ("false".equals(tokenString)) {
			rests = (ArrayList<Restaurant>) rests.stream()
					.filter(r -> (!r.isOpen()))
					.collect(Collectors.toList());
		}
		
		stack.push(rests);
	}


	@Override
	public void enterStatep(StatepContext ctx) {}

	@Override
	public void exitStatep(StatepContext ctx) {
		String tokenString = ctx.getChild(0).toString();
		String subTokenString = tokenString.substring(tokenString.length()-2);
		ArrayList<Restaurant> rests = getYelpRestaurants();
		rests = (ArrayList<Restaurant>) rests.stream()
				.filter(r -> (r.getState()).equals(subTokenString))
				.collect(Collectors.toList());
		stack.push(rests);
		
	}

	@Override
	public void enterCityp(CitypContext ctx) {}

	@Override
	public void exitCityp(CitypContext ctx) {
		String tokenString = ctx.STRING().toString();
		
		ArrayList<Restaurant> rests = getYelpRestaurants();
		rests = (ArrayList<Restaurant>) rests.stream()
				.filter(r -> (r.getCity()).equals(tokenString))
				.collect(Collectors.toList());
		
		stack.push(rests);
		
	}

	@Override
	public void enterReviewcountp(ReviewcountpContext ctx) {}

	@Override
	public void exitReviewcountp(ReviewcountpContext ctx){
		int comparedNum = Integer.parseInt(ctx.NUM().toString());
		String comparator = ineq.pop();
		ArrayList<Restaurant> rests = getYelpRestaurants();
		
		
		if ("<".equals(comparator)) {
			rests = (ArrayList<Restaurant>) rests.stream()
					.filter(r -> r.getReview_count() < comparedNum)
					.collect(Collectors.toList());
		} else if ("<=".equals(comparator)) {
			rests = (ArrayList<Restaurant>) rests.stream()
					.filter(r -> r.getReview_count() <= comparedNum)
					.collect(Collectors.toList());
		} else if ("=".equals(comparator)) {
			rests = (ArrayList<Restaurant>) rests.stream()
					.filter(r -> r.getReview_count() == comparedNum)
					.collect(Collectors.toList());
		} else if (">=".equals(comparator)) {
			rests = (ArrayList<Restaurant>) rests.stream()
					.filter(r -> r.getReview_count() >= comparedNum)
					.collect(Collectors.toList());
		} else if (">".equals(comparator)) {
			rests = (ArrayList<Restaurant>) rests.stream()
					.filter(r -> r.getReview_count() > comparedNum)
					.collect(Collectors.toList());
		} 
		
		
		stack.push(rests);
		
	}

	@Override
	public void enterSchoolsp(SchoolspContext ctx) { }

	@Override
	public void exitSchoolsp(SchoolspContext ctx) {

		String tokenString = ctx.STRING().toString();
		
		ArrayList<Restaurant> rests = getYelpRestaurants();
		rests = (ArrayList<Restaurant>) rests.stream()
				.filter(r -> Arrays.asList(r.getSchools()).contains(tokenString))
				.collect(Collectors.toList());
		
		stack.push(rests);
	}
}
