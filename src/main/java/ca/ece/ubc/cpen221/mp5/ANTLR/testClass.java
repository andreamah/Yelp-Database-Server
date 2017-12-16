package ca.ece.ubc.cpen221.mp5.ANTLR;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

//import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.YelpDB;

public class testClass {
	private YelpDB yelpDB;
	public static void main(String[] args) {
		/*
		CharStream stream = CharStreams.fromString("in(Telegraph Ave Ave Ave) && (category(Chinese Food) || category(Italian)) && price <= 2 && rating > 2 && (review_count > 100 || (review_count <= 20 && review_count >= 80)) && ((city(Regina) && city(Vancouver)) && city(Berkley)) && business_id(ipgnAjJ5TUBWGmGxxzoiGQ) && (in(West Van) || in(uwu)) && schools(UBC) && name(ROOB) && state(CA) && open(true)");
			*/	
		testClass r = new testClass();
		try {
			System.out.println(r.QUERY("business_id(1)"));
		} catch (Exception e) {
			System.out.println("ERROR");
		}
		//Trees.inspect( tree, parser );
		
		
			
		
	}
	private String QUERY(String query_id) {
		YelpDB yelpDB = null;
		try {
			yelpDB = new YelpDB("data/restaurants.json","data/users.json", "data/reviews.json");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		query_id.trim();
		
		CharStream stream = CharStreams.fromString(query_id);
		mp5AntlrLex lexer = new mp5AntlrLex(stream);
		TokenStream tokens = new CommonTokenStream(lexer);
		mp5AntlrParse parser = new mp5AntlrParse(tokens);
		
		ParseTree tree = parser.root();
		ParseTreeWalker walker = new ParseTreeWalker();
		mp5AntlrParseListener listener = new mp5AntlrListenerCollect(yelpDB);
		walker.walk(listener, tree);
		
		ArrayList<Restaurant> filtered = ((mp5AntlrListenerCollect)listener).getFilteredList();

		String returnResult = "";
		for (Restaurant r : filtered)
		{
			returnResult+=printRestaurant(r)+"\r\n";
		}
		returnResult.trim();
		return returnResult;
	}
	
	public String printRestaurant(Restaurant rest) {
		
		JsonObject value = Json.createObjectBuilder()
			    	.add("open", rest.isOpen())
			        .add("url", rest.getUrl())
					.add("longitude", BigDecimal.valueOf(rest.getLongitude()))
					.add("neighborhoods", arrayToJson(rest.getNeighborhoods()))
					
					
					.add("business_id",rest.getBusiness_id())
					.add("name", rest.getName())
					.add("categories", arrayToJson(rest.getCategories()))
					.add("state", rest.getState())
					.add("type", "business")
					.add("stars", rest.getStars())
					.add("city", rest.getCity())
					
					.add("full_address", rest.getFull_address())
					.add("review_count", rest.getReview_count())

					.add("photo_url", rest.getPhoto_url())
					.add("schools",  arrayToJson(rest.getSchools()))
					
					.add("latitude", BigDecimal.valueOf(rest.getLatitude()))
					.add("price", rest.getPrice())
					.build();
		String jsonString = value.toString();
		return jsonString;
	}
	
	private JsonArray arrayToJson(String[] stringArr)
	{
		JsonArrayBuilder buildArr = Json.createArrayBuilder();
		
		for (String str : stringArr)
		{
			buildArr.add(str);
		}
		JsonArray resultArr = buildArr.build();
		
		return resultArr;
	}
}
