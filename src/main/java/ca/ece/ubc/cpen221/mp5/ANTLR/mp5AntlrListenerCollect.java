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
		//constructor receives list of restaurants and stores it in its field
		this.restaurants = rests;
	}
	
	public ArrayList<Restaurant> getFilteredList() {
		//ensure defensive copying for getter method for the resultant list
		return new ArrayList<Restaurant>(filteredList);
	}
	
	private ArrayList<Restaurant> getYelpRestaurants() {
		//ensure defensive copying for returning the list of restaurants
		//used within the method to get the original copy of restaurants
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
		//when exiting the root, pop the stack to release the final element (fully filtered list)
		filteredList = stack.pop();
	}
	
	@Override
	public void enterOrexpr(OrexprContext ctx) {}

	/**
	 * when exiting the or expressions, this method pops the relevant lists (not "||") from the stack,
	 * and finds the union on all of the popped lists
	 */
	@Override
	public void exitOrexpr(OrexprContext ctx) {
		
		//calculate the number of valid children from the number of downstream branches
		int numChildren = (ctx.getChildCount()/2 )+1;
		//if there is more than one child, there is an or statement to use to filter
		if (numChildren>1)
		{
			//for all the number of valid children, pop a filtered list from the main stack 
			//and cumulatively add them 
			ArrayList<ArrayList<Restaurant>> restList= new ArrayList<ArrayList<Restaurant>>();
			for (int i = 0; i < numChildren ; i++)
			{
				restList.add(stack.pop());
			}
			//use a set as a main list to avoid repeated objects
			Set<Restaurant> mainSet = new HashSet<>();
			
			for (int j = 0; j < numChildren;j++)
			{
				//cast each popped restaurant list as a new set to add to the main set
				mainSet.addAll(new HashSet<Restaurant>(restList.get(j)));
			}
			
			//create mainList, which contains the main set as an arraylist
			ArrayList<Restaurant> mainList= new ArrayList<Restaurant>(mainSet);
			
			//push this list to the stack
			stack.push(mainList);
		}
	}

	@Override
	public void enterAndexpr(AndexprContext ctx) {}

	/**
	 * when exiting the or expressions, tihs method pops the relevant lists (not "&&") from the stack
	 * and finds the intersection of the lists
	 */
	@Override
	public void exitAndexpr(AndexprContext ctx) {
		//calculate the number of relevant children 
		int numChildren = (ctx.getChildCount()/2 )+1;
		//if an intersection is needed, perform operations
		if (numChildren>1)
		{
			//pop the number of relevant children from the list 
			ArrayList<ArrayList<Restaurant>> restList= new ArrayList<ArrayList<Restaurant>>();
			
			for (int i = 0; i < numChildren ; i++)
			{
				restList.add(stack.pop());
			}
			//cumulatively find the intersection of all of the popped lists
			
			for (int j = 0; j < numChildren-1;j++)
			{
				restList.get(j+1).retainAll(restList.get(j));
			}
			
			//push the resultant list onto the stack
			stack.push(restList.get(numChildren-1));
		}
	}

	@Override
	public void enterAtom(AtomContext ctx) {}

	@Override
	public void exitAtom(AtomContext ctx) {}

	@Override
	public void enterIneq(IneqContext ctx) {}

	/**
	 * when exiting an inequality, this method adds the inequality to the stack of inequalities
	 */
	@Override
	public void exitIneq(IneqContext ctx) {
		String tokenString = ctx.getChild(0).toString();
		ineq.push(tokenString);
	}

	@Override
	public void enterInp(InpContext ctx) { }

	/**
	 * when exiting an "in" branching, this method filters an original list and then pushes the filtered list to the main stack
	 */
	@Override
	public void exitInp(InpContext ctx) {
		//get the string corresponding to the "STRING" lexer category 
		String tokenString = ctx.STRING().toString();
		
		//filter that the given neighborhood is included in the restaurant's list of neighborhood
		ArrayList<Restaurant> rests = getYelpRestaurants();
		rests = (ArrayList<Restaurant>) rests.stream()
				.filter(r -> Arrays.asList(r.getNeighborhoods()).contains(tokenString))
				.collect(Collectors.toList());
		
		stack.push(rests);
	}

	@Override
	public void enterCategoryp(CategorypContext ctx) { }

	/**
	 * when exiting a "category" branching, this method filters an original list and then pushes the filtered list to the main stack
	 */
	@Override
	public void exitCategoryp(CategorypContext ctx) {
		//get the string corresponding to the "STRING" lexer category 
		String tokenString = ctx.STRING().toString();


		//filter that the given category is included in the restaurant's list of categories
		ArrayList<Restaurant> rests = getYelpRestaurants();
		rests = (ArrayList<Restaurant>) rests.stream()
				.filter(r -> Arrays.asList(r.getCategories()).contains(tokenString))
				.collect(Collectors.toList());
		
		stack.push(rests);
		}

	@Override
	public void enterNamep(NamepContext ctx) {}

	/**
	 * when exiting a "name" branching, this method filters an original list and then pushes the filtered list to the main stack
	 */
	@Override
	public void exitNamep(NamepContext ctx) {
		//get the string corresponding to the "STRING" lexer category 
		String tokenString = ctx.STRING().toString();
		
		//filter that the given name equals in the restaurant's name
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
		//find the number being compared to and parse it into an integer
		int comparedNum = Integer.parseInt(ctx.NUM().toString());

		//find the inequality by popping the main stack
		String comparator = ineq.pop();
		ArrayList<Restaurant> rests = getYelpRestaurants();

		//depending on the comparator, filter based on the stars field of the restaurant accordingly
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
		//find the number being compared to and parse it into an integer
		int comparedNum = Integer.parseInt(ctx.NUM().toString());

		//find the inequality by popping the main stack
		String comparator = ineq.pop();
		ArrayList<Restaurant> rests = getYelpRestaurants();

		//depending on the comparator, filter based on the price field of the restaurant accordingly
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
	
	/**
	 * when exiting a "state" branching, this method filters an original list and then pushes the filtered list to the main stack
	 */
	@Override
	public void exitIsopen(IsopenContext ctx) {
		//find the first child, which contains the boolean value
		String tokenString = ctx.getChild(0).toString();
		ArrayList<Restaurant> rests = getYelpRestaurants();
		
		//filter the list according to whether the value to true or false
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

	/**
	 * when exiting a "state" branching, this method filters an original list and then pushes the filtered list to the main stack
	 */
	@Override
	public void enterStatep(StatepContext ctx) {}

	@Override
	public void exitStatep(StatepContext ctx) {
		//find the first child and parse the first two characters to get the state
		String tokenString = ctx.getChild(0).toString();
		String subTokenString = tokenString.substring(tokenString.length()-2);

		//filter that the given state equals in the restaurant's state
		ArrayList<Restaurant> rests = getYelpRestaurants();
		rests = (ArrayList<Restaurant>) rests.stream()
				.filter(r -> (r.getState()).equals(subTokenString))
				.collect(Collectors.toList());
		stack.push(rests);
		
	}

	@Override
	public void enterCityp(CitypContext ctx) {}

	/**
	 * when exiting a "city" branching, this method filters an original list and then pushes the filtered list to the main stack
	 */
	@Override
	public void exitCityp(CitypContext ctx) {
		//get the string corresponding to the "STRING" lexer category 
		String tokenString = ctx.STRING().toString();

		//filter that the given city equals in the restaurant's city
		ArrayList<Restaurant> rests = getYelpRestaurants();
		rests = (ArrayList<Restaurant>) rests.stream()
				.filter(r -> (r.getCity()).equals(tokenString))
				.collect(Collectors.toList());
		
		stack.push(rests);
		
	}

	@Override
	public void enterReviewcountp(ReviewcountpContext ctx) {}

	/**
	 * when exiting a "rating" branching, this method filters an original list and then pushes the filtered list to the main stack
	 */
	@Override
	public void exitReviewcountp(ReviewcountpContext ctx){
		//find the number being compared to and parse it into an integer
		int comparedNum = Integer.parseInt(ctx.NUM().toString());
		//find the inequality by popping the main stack
		String comparator = ineq.pop();
		ArrayList<Restaurant> rests = getYelpRestaurants();
		
		//depending on the comparator, filter based on the review count field of the restaurant accordingly
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

	/**
	 * when exiting a "school" branching, this method filters an original list and then pushes the filtered list to the main stack
	 */
	@Override
	public void exitSchoolsp(SchoolspContext ctx) {
		//get the string corresponding to the "STRING" lexer category 
		String tokenString = ctx.STRING().toString();
		
		//filter that the given school is included in the restaurant's list of schools
		ArrayList<Restaurant> rests = getYelpRestaurants();
		rests = (ArrayList<Restaurant>) rests.stream()
				.filter(r -> Arrays.asList(r.getSchools()).contains(tokenString))
				.collect(Collectors.toList());
		
		stack.push(rests);
	}
}
