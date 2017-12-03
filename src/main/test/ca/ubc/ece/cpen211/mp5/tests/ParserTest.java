package ca.ubc.ece.cpen211.mp5.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.RestaurantParser;
import ca.ece.ubc.cpen221.mp5.Review;
import ca.ece.ubc.cpen221.mp5.ReviewParser;
import ca.ece.ubc.cpen221.mp5.YelpUser;
import ca.ece.ubc.cpen221.mp5.YelpUserParser;

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
		assertEquals("gclB3ED6uk6viWlolSb_uA",restaurants.get(0).getBusiness_id());
		assertEquals("Cafe 3",restaurants.get(0).getName());
		
		assertEquals("http://s3-media1.ak.yelpcdn.com/bphoto/AaHq1UzXiT6zDBUYrJ2NKA/ms.jpg",restaurants.get(0).getPhoto_url());
		
		String[] expectedSc1 = {"University of California at Berkeley"};
		for (int j = 0 ; j < expectedSc1.length ; j++)
		{
			assertEquals(restaurants.get(0).getSchools()[j], expectedSc1[j]);
		}
		assertEquals(restaurants.get(0).getSchools().length, expectedSc1.length);
		
		assertEquals("http://www.yelp.com/biz/cafe-3-berkeley",restaurants.get(0).getUrl());
		
		String[] expectedNe1 = {"Telegraph Ave", "UC Campus Area"};
		for (int k = 0 ; k < expectedNe1.length ; k++)
		{
			assertEquals(restaurants.get(0).getNeighborhoods()[k], expectedNe1[k]);
		}
		assertEquals(restaurants.get(0).getNeighborhoods().length, expectedNe1.length);
		
		assertEquals("CA",restaurants.get(0).getState());
		assertEquals("Berkeley",restaurants.get(0).getCity());
		assertEquals("2400 Durant Ave\nTelegraph Ave\nBerkeley, CA 94701",restaurants.get(0).getFull_address());
		
		
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
		
		//verify fields for 8395th entry:
		
		//{"type": "review", "business_id": "NGyFcZHghu1uJ0G-pXJxoQ", "votes": {"cool": 6, "useful": 11, "funny": 6}, "review_id": "ALesPilbRgXArp_1-zwBQA", "text": "4.5 stars \n\nBookended by the temptations of Telegraph Ave. in one direction and the ever popular Caffe Strada in the other, It'd be easy to miss Cafe Milano. But that would be a mistake, because this is exactly what a college cafe should be. \n\nThe coffee, from the same roaster that supplies cafes in the area, is solidly decent and Milano has all the standard cafe characteristics - Free, fast, wifi; substantial and surprisingly tasty food and  classical music. \n\nBut what really sets Milano apart from the rest is it's atmosphere: All high ceilings and exposed brick, this place is so pleasant it's a shame to associate it w/ problems set and papers. There's plenty of natural light, several potted plants, an open air roof, and when it's warm out, the cafe's massive two-story windows are left entirely open. It's a wonder anyone gets any work done.", "stars": 4, "user_id": "rGdW50y9qjzvx2Zboyl9Mw", "date": "2010-10-05"}
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
		assertEquals("ALesPilbRgXArp_1-zwBQA",reviews.get(8394).getReview_id());
		
		HashMap<String,Integer> expectedDate8395 = new HashMap<String,Integer>();
		expectedDate8395.put("day", 5);
		expectedDate8395.put("month", 10);
		expectedDate8395.put("year", 2010);
		
		for (String s:reviews.get(8394).getDate().keySet())
		{
			assertEquals(expectedDate8395.get(s),reviews.get(8394).getDate().get(s));
		}
		
		
	}
	
	@Test
	public void test2() throws IOException {
		ArrayList<YelpUser> yuser = YelpUserParser.Parse("data/users.json");
		
		
		//verify for last entry:
		assertTrue(3.47727272727273==yuser.get(8555).getAverage_stars());
		assertEquals("CmAd1cga_XQKdn1onMHtmQ",yuser.get(8555).getUser_id());

		//verify for 3450th entry:
		//{"url": "http://www.yelp.com/user_details?userid=418rK1b_PkXJTWhSRKxjew", "votes": {"funny": 149, "useful": 351, "cool": 159}, "review_count": 189, "type": "user", "user_id": "418rK1b_PkXJTWhSRKxjew", "name": "Kelley L.", "average_stars": 3.73544973544974}
		assertTrue(3.73544973544974==yuser.get(3449).getAverage_stars());
		assertEquals("418rK1b_PkXJTWhSRKxjew",yuser.get(3449).getUser_id());
		assertEquals("http://www.yelp.com/user_details?userid=418rK1b_PkXJTWhSRKxjew",yuser.get(3449).getUrl());
		assertEquals("Kelley L.",yuser.get(3449).getName());
		assertEquals(189,yuser.get(3449).getReview_count());
		
		HashMap<String,Integer> expectedVotes2 = new HashMap<String,Integer>();
		expectedVotes2.put("cool", 159);
		expectedVotes2.put("useful", 351);
		expectedVotes2.put("funny", 149);
		
		for (String s:yuser.get(3449).getVotes().keySet())
		{
			assertEquals(expectedVotes2.get(s),yuser.get(3449).getVotes().get(s));
		}
	}

}
