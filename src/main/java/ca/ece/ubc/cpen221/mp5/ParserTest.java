package ca.ece.ubc.cpen221.mp5;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.Test;

public class ParserTest {

	@Test
	public void test() throws IOException {
		ArrayList<Restaurant> restaurants = RestaurantParser.Parse("data/restaurants.json");
		
		//test fields for first element in restaurants.json
		assertTrue(restaurants.get(0).isOpen());
		assertEquals(9,restaurants.get(0).getReview_count());
		assertTrue(2.0 == restaurants.get(0).getStars());
		assertEquals(1,restaurants.get(0).getPrice());
		
		String[] expectedCat1 = {"Cafes", "Restaurants"};
		for (int i = 0 ; i < expectedCat1.length ; i++)
		{
			assertEquals(restaurants.get(0).getCategories()[i], expectedCat1[i]);
		}
		assertEquals(restaurants.get(0).getCategories().length, expectedCat1.length);
		
		assertTrue(37.867417== restaurants.get(0).getLatitude());
		assertTrue(-122.260408 == restaurants.get(0).getLongitude());
		assertTrue("gclB3ED6uk6viWlolSb_uA".equals(restaurants.get(0).getBusiness_id()));
		assertTrue("Cafe 3".equals(restaurants.get(0).getName()));
		
		//test fields for 125th element in restaurants.json
		assertEquals(136,restaurants.get(124).getReview_count());
		assertTrue(4.0 == restaurants.get(124).getStars());
		assertEquals(1,restaurants.get(124).getPrice());
		
		String[] expectedCat125 = {"Food", "Coffee & Tea", "Sandwiches", "Restaurants"};
		for (int i = 0 ; i < expectedCat125.length ; i++)
		{
			assertEquals(restaurants.get(124).getCategories()[i], expectedCat125[i]);
		}
		assertEquals(restaurants.get(124).getCategories().length, expectedCat125.length);
		
		assertTrue(37.8684351== restaurants.get(124).getLatitude());
		assertTrue(-122.2606426 == restaurants.get(124).getLongitude());
		assertTrue("gYbX_IfkzWme4ItyXb_lgw".equals(restaurants.get(124).getBusiness_id()));
		assertTrue("Cafe Espresso Experience".equals(restaurants.get(124).getName()));
	
		//test fields for last (134th) element in restaurants.json
		assertEquals(6,restaurants.get(134).getReview_count());
		assertTrue(4.0 == restaurants.get(134).getStars());
		assertEquals(1,restaurants.get(134).getPrice());
		
		String[] expectedCat135 = {"Mexican", "Restaurants"};
		for (int i = 0 ; i < expectedCat135.length ; i++)
		{
			assertEquals(restaurants.get(134).getCategories()[i], expectedCat135[i]);
		}
		assertEquals(restaurants.get(134).getCategories().length, expectedCat135.length);
		
		assertTrue(37.865831== restaurants.get(134).getLatitude());
		assertTrue(-122.25802 == restaurants.get(134).getLongitude());
		assertTrue("zqcTeWwRe7HjbwDaWJGjCw".equals(restaurants.get(134).getBusiness_id()));
		assertTrue("La Fiesta Mexican Restaurant".equals(restaurants.get(134).getName()));
	}
	
	@Test
	public void test1() throws IOException {
		ArrayList<Review> reviews = ReviewParser.Parse("data/reviews.json");
		
		//verify fields for first entry
		HashMap<String,Integer> expectedVotes = new HashMap<String,Integer>();
		expectedVotes.put("cool", 0);
		expectedVotes.put("useful", 0);
		expectedVotes.put("funny", 0);
		
		for (String s:reviews.get(0).getVotes().keySet())
		{
			assertEquals(expectedVotes.get(s),reviews.get(0).getVotes().get(s));
		}
		assertEquals("1CBs84C-a-cuA3vncXVSAw",reviews.get(0).getBusiness_id());
		assertTrue(2.0==reviews.get(0).getStars());
		assertEquals("90wm_01FAIqhcgV_mPON9Q",reviews.get(0).getUser_id());
		assertEquals("The pizza is terrible, but if you need a place to watch a game or just down some pitchers, this place works.\n\nOh, and the pasta is even worse than the pizza.",reviews.get(0).getText());
		
		//verify fields for 8395th entry
		HashMap<String,Integer> expectedVotes8395 = new HashMap<String,Integer>();
		expectedVotes8395.put("cool", 6);
		expectedVotes8395.put("useful", 11);
		expectedVotes8395.put("funny", 6);
		
		for (String s:reviews.get(8394).getVotes().keySet())
		{
			assertEquals(expectedVotes8395.get(s),reviews.get(8394).getVotes().get(s));
		}
		assertEquals("NGyFcZHghu1uJ0G-pXJxoQ",reviews.get(8394).getBusiness_id());
		assertTrue(4.0==reviews.get(8394).getStars());
		assertEquals("rGdW50y9qjzvx2Zboyl9Mw",reviews.get(8394).getUser_id());
		assertEquals("4.5 stars \n\nBookended by the temptations of Telegraph Ave. in one direction and the ever popular Caffe Strada in the other, It'd be easy to miss Cafe Milano. But that would be a mistake, because this is exactly what a college cafe should be. \n\nThe coffee, from the same roaster that supplies cafes in the area, is solidly decent and Milano has all the standard cafe characteristics - Free, fast, wifi; substantial and surprisingly tasty food and  classical music. \n\nBut what really sets Milano apart from the rest is it's atmosphere: All high ceilings and exposed brick, this place is so pleasant it's a shame to associate it w/ problems set and papers. There's plenty of natural light, several potted plants, an open air roof, and when it's warm out, the cafe's massive two-story windows are left entirely open. It's a wonder anyone gets any work done.",reviews.get(8394).getText());
		
		
	}
	
	@Test
	public void test2() throws IOException {
		ArrayList<YelpUser> yuser = YelpUserParser.Parse("data/users.json");
		
		
		//verify for last entry:
		//{"url": "http://www.yelp.com/user_details?userid=CmAd1cga_XQKdn1onMHtmQ", "votes": {"funny": 11, "useful": 24, "cool": 7}, "review_count": 44, "type": "user", "user_id": "CmAd1cga_XQKdn1onMHtmQ", "name": "Alex M.", "average_stars": 3.47727272727273}

		assertTrue(3.47727272727273==yuser.get(8555).getAverage_stars());
		assertEquals("CmAd1cga_XQKdn1onMHtmQ",yuser.get(8555).getUser_id());

		//verify for 3450th entry:
		//d=418rK1b_PkXJTWhSRKxjew", "votes": {"funny": 149, "useful": 351, "cool": 159}, "review_count": 189, "type": "user", "user_id": "418rK1b_PkXJTWhSRKxjew", "name": "Kelley L.", "average_stars": 3.73544973544974}
		
		assertTrue(3.73544973544974==yuser.get(3449).getAverage_stars());
		assertEquals("418rK1b_PkXJTWhSRKxjew",yuser.get(3449).getUser_id());
		
	}

}
