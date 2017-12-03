package ca.ubc.ece.cpen211.mp5.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.function.ToDoubleBiFunction;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.Centroid;
import ca.ece.ubc.cpen221.mp5.MP5Db;
import ca.ece.ubc.cpen221.mp5.PredictorFunction;
import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.Review;
import ca.ece.ubc.cpen221.mp5.YelpDB;
import ca.ece.ubc.cpen221.mp5.YelpUser;

public class YelpDBTest<T> {
	
	@Test
	public void test1() {
		Restaurant r1 = new Restaurant(false, null, Math.random()*100, Math.random()*100,
				null, null, null, null, null, null, null, 0, 0, null, null, 0);
		Restaurant r2 = new Restaurant(false, null, Math.random()*100, Math.random()*100,
				null, null, null, null, null, null, null, 0, 0, null, null, 0);
		Restaurant r3 = new Restaurant(false, null, Math.random()*100, Math.random()*100,
				null, null, null, null, null, null, null, 0, 0, null, null, 0);
		Restaurant r4 = new Restaurant(false, null, Math.random()*100, Math.random()*100,
				null, null, null, null, null, null, null, 0, 0, null, null, 0);
		Restaurant r5 = new Restaurant(false, null, Math.random()*100, Math.random()*100,
				null, null, null, null, null, null, null, 0, 0, null, null, 0);
		Restaurant r6 = new Restaurant(false, null, Math.random()*100, Math.random()*100,
				null, null, null, null, null, null, null, 0, 0, null, null, 0);
		Restaurant r7 = new Restaurant(false, null, Math.random()*100, Math.random()*100,
				null, null, null, null, null, null, null, 0, 0, null, null, 0);
		Restaurant r8 = new Restaurant(false, null, Math.random()*100, Math.random()*100,
				null, null, null, null, null, null, null, 0, 0, null, null, 0);
		Restaurant r9 = new Restaurant(false, null, Math.random()*100, Math.random()*100,
				null, null, null, null, null, null, null, 0, 0, null, null, 0);
		Restaurant r10 = new Restaurant(false, null, Math.random()*100, Math.random()*100,
				null, null, null, null, null, null, null, 0, 0, null, null, 0);
		
		
		ArrayList<Restaurant> Restaurants = new ArrayList<Restaurant>();
		Restaurants.add(r1); Restaurants.add(r2); Restaurants.add(r3);
		Restaurants.add(r4); Restaurants.add(r5); Restaurants.add(r6);
		Restaurants.add(r7); Restaurants.add(r8); Restaurants.add(r9);
		Restaurants.add(r10);
		
		YelpDB<T> ydb = new YelpDB<T>(Restaurants, null, null);
		
		int k = 5;
		
		List<Set<Restaurant>> clusters = ydb.kMeansClusters(k);
		
		assertEquals(clusters.size(), k);
		
		int count = 0;
		
		for(Set<Restaurant> cluster : clusters) {
			count += cluster.size();
		}
		
		assertEquals(Restaurants.size(), count);
		
		ArrayList<Centroid> Centroids = new ArrayList<Centroid>();
		HashMap<Set<Restaurant>, Centroid> SCPair = new HashMap<Set<Restaurant>, Centroid>();
		
		for(Set<Restaurant> cluster : clusters) {
			double totalX = 0, totalY = 0;
			
			for(Restaurant r : cluster) {
				totalX += r.getLongitude();
				totalY += r.getLatitude();	
			}
			
			Centroid c = new Centroid(totalY / cluster.size(), totalX / cluster.size());
			Centroids.add(c);
			SCPair.put(cluster, c);
		}
		
		for(Set<Restaurant> set : clusters) {
			Centroid setCentroid = SCPair.get(set);
			
			for(Restaurant r : set) {
				for(Centroid c : Centroids) {
					assertTrue(r.getDistance(setCentroid.getY(), setCentroid.getX()) <=
							r.getDistance(c.getY(), c.getX()));
				}
			}
		}	
	}
	
	@Test
	public void test2() {
		Restaurant ra = new Restaurant(false, null, 1.0, 2.0, null, null, "ra", null, null,
				null, null, 0, 0, null, null, 0);
		Restaurant rb = new Restaurant(false, null, 3.0, 4.0, null, null, "rb", null, null,
				null, null, 0, 0, null, null, 0);
		Restaurant rc = new Restaurant(false, null, 5.0, 6.0, null, null, "rc", null, null,
				null, null, 0, 0, null, null, 0);
		
		ArrayList<Restaurant> Restaurants = new ArrayList<Restaurant>();
		Restaurants.add(ra); Restaurants.add(rb); Restaurants.add(rc);
		
		YelpDB<T> db = new YelpDB<T>(Restaurants, null, null);
		
		String jsonString = db.kMeansClusters_json(3);
		String expectedString = "[{\"x\":2.0,\"y\":1.0,\"name\":\"ra\",\"cluster\":1,"
				+ "\"weight\":1.0},{\"x\":4.0,\"y\":3.0,\"name\":\"rb\",\"cluster\":2,"
				+ "\"weight\":1.0},{\"x\":6.0,\"y\":5.0,\"name\":\"rc\",\"cluster\":3,"
				+ "\"weight\":1.0}]";
		
		assertEquals(jsonString, expectedString);
	}
	
	@Test
	public void test3() {
		Restaurant r1 = new Restaurant(false, null, 0, 0, null, "r1", null, null, null,
				null, null, 0, 0, null, null, 1);
		Restaurant r2 = new Restaurant(false, null, 0, 0, null, "r2", null, null, null,
				null, null, 0, 0, null, null, 4);
		Restaurant r3 = new Restaurant(false, null, 0, 0, null, "r3", null, null, null,
				null, null, 0, 0, null, null, 10);
		
		ArrayList<Restaurant> Restaurants = new ArrayList<Restaurant>();
		Restaurants.add(r1); Restaurants.add(r2); Restaurants.add(r3);
		
		YelpDB<T> db = new YelpDB<T>(Restaurants, null, null);
		
		PredictorFunction<T> pfunc = new PredictorFunction<T>(0.5, 0.7, 100);
		
		assertEquals(pfunc.applyAsDouble(db, "r1"), 1.0, 0.0);
		assertEquals(pfunc.applyAsDouble(db, "r2"), 3.0, 0.0);
		assertEquals(pfunc.applyAsDouble(db, "r3"), 5.0, 0.0);
	}
	
	@Test
	public void test4() {
		Restaurant r1 = new Restaurant(false, null, 0, 0, null, "r1", null, null, null,
				null, null, 0, 0, null, null, 1);
		Restaurant r2 = new Restaurant(false, null, 0, 0, null, "r2", null, null, null,
				null, null, 0, 0, null, null, 2);
		Restaurant r3 = new Restaurant(false, null, 0, 0, null, "r3", null, null, null,
				null, null, 0, 0, null, null, 3);
		Restaurant r4 = new Restaurant(false, null, 0, 0, null, "r4", null, null, null,
				null, null, 0, 0, null, null, 4);
		Restaurant r5 = new Restaurant(false, null, 0, 0, null, "r5", null, null, null,
				null, null, 0, 0, null, null, 5);
		
		ArrayList<Restaurant> Restaurants = new ArrayList<Restaurant>();
		Restaurants.add(r1); Restaurants.add(r2); Restaurants.add(r3);
		Restaurants.add(r4); Restaurants.add(r5);
		
		Review v1 = new Review("r1", null, "v1", null, 1, "u1", null);
		Review v2 = new Review("r2", null, "v2", null, 2, "u1", null);
		Review v3 = new Review("r3", null, "v3", null, 3, "u1", null);
		Review v4 = new Review("r4", null, "v4", null, 4, "u1", null);
		Review v5 = new Review("r5", null, "v5", null, 5, "u1", null);
		Review v6 = new Review("r5", null, "v6", null, 1, "u2", null);
		
		
		ArrayList<Review> Reviews = new ArrayList<Review>();
		Reviews.add(v1); Reviews.add(v2); Reviews.add(v3);
		Reviews.add(v4); Reviews.add(v5); Reviews.add(v6);
		
		YelpUser u1 = new YelpUser(null, "u1", null, null, 0, 3);
		YelpUser u2 = new YelpUser(null, "u2", null, null, 0, 1);
		
		ArrayList<YelpUser> YelpUsers = new ArrayList<YelpUser>();
		YelpUsers.add(u1); YelpUsers.add(u2);
		
		YelpDB<T> db = new YelpDB<T>(Restaurants, Reviews, YelpUsers);
		
		ToDoubleBiFunction<MP5Db<T>, String> pfunc = db.getPredictorFunction("u1");
		
		assertEquals(pfunc.applyAsDouble(db, "r1"), 1.0, 0.0);
		assertEquals(pfunc.applyAsDouble(db, "r2"), 2.0, 0.0);
		assertEquals(pfunc.applyAsDouble(db, "r3"), 3.0, 0.0);
		assertEquals(pfunc.applyAsDouble(db, "r4"), 4.0, 0.0);
		assertEquals(pfunc.applyAsDouble(db, "r5"), 5.0, 0.0);
	}
	
	@Test
	public void test5() {
		Restaurant r1 = new Restaurant(false, null, 0, 0, null, "r1", null, null, null,
				null, null, 0, 0, null, null, 0);
		Restaurant r2 = new Restaurant(false, null, 0, 0, null, "r2", null, null, null,
				null, null, 0, 0, null, null, 0);
		
		ArrayList<Restaurant> Restaurants = new ArrayList<Restaurant>();
		Restaurants.add(r1); Restaurants.add(r2);
		
		Review v1 = new Review("r1", null, "v1", null, 5, null, null);
		Review v2 = new Review("r1", null, "v2", null, 1, null, null);
		Review v3 = new Review("r1", null, "v3", null, 4, null, null);
		Review v4 = new Review("r1", null, "v4", null, 2, null, null);
		Review v5 = new Review("r2", null, "v5", null, 5, null, null);
		Review v6 = new Review("r2", null, "v6", null, 1, null, null);
		
		ArrayList<Review> Reviews = new ArrayList<Review>();
		Reviews.add(v1); Reviews.add(v2); Reviews.add(v3);
		Reviews.add(v4); Reviews.add(v5); Reviews.add(v6);
		
		YelpDB<T> db = new YelpDB<T>(Restaurants, Reviews, null);
		
		ArrayList<Review> bestAndWorstReviews = db.bestAndWorst(r1);
		
		assertTrue(bestAndWorstReviews.size() == 2);
		
		assertEquals(bestAndWorstReviews.get(0), v1);
		assertEquals(bestAndWorstReviews.get(1), v2);
		
		assertFalse(bestAndWorstReviews.contains(v3));
		assertFalse(bestAndWorstReviews.contains(v4));
		assertFalse(bestAndWorstReviews.contains(v5));
		assertFalse(bestAndWorstReviews.contains(v6));
	}
	
	@Test
	public void test6() {
		Restaurant centre = new Restaurant(false, null, 0, 0, null, null, null, null,
				null, null, null, 0, 0, null, null, 0);
		Restaurant r1 = new Restaurant(false, null, 1, 0, null, null, null, null, null,
				null, null, 0, 0, null, null, 0);
		Restaurant r2 = new Restaurant(false, null, 3, 4, null, null, null, null, null,
				null, null, 0, 0, null, null, 0);
		Restaurant r3 = new Restaurant(false, null, 4, 4, null, null, null, null, null,
				null, null, 0, 0, null, null, 0);
		Restaurant r4 = new Restaurant(false, null, 2, 2, null, null, null, null, null,
				null, null, 0, 0, null, null, 0);
		
		ArrayList<Restaurant> Restaurants = new ArrayList<Restaurant>();
		Restaurants.add(centre);
		Restaurants.add(r1); Restaurants.add(r2); Restaurants.add(r3); Restaurants.add(r4);
		
		YelpDB<T> db = new YelpDB<T>(Restaurants, null, null);
		
		ArrayList<Restaurant> closeRestaurants = db.closeRestaurants(0, 0, 5);
		
		assertTrue(closeRestaurants.contains(centre));
		assertTrue(closeRestaurants.contains(r1));
		assertTrue(closeRestaurants.contains(r2));
		assertTrue(closeRestaurants.contains(r4));
		assertFalse(closeRestaurants.contains(r3));
	}
	
	@Test
	public void test7() {
		Restaurant r1 = new Restaurant(false, null, 0, 0, null, null, null, null, null,
				null, null, 5.0, 0, null, null, 0);
		Restaurant r2 = new Restaurant(false, null, 0, 0, null, null, null, null, null,
				null, null, 4.0, 0, null, null, 0);
		Restaurant r3 = new Restaurant(false, null, 0, 0, null, null, null, null, null,
				null, null, 4.0, 0, null, null, 0);
		Restaurant r4 = new Restaurant(false, null, 0, 0, null, null, null, null, null,
				null, null, 1.0, 0, null, null, 0);
		
		ArrayList<Restaurant> Restaurants = new ArrayList<Restaurant>();
		Restaurants.add(r1); Restaurants.add(r2); Restaurants.add(r3); Restaurants.add(r4);
		
		YelpDB<T> db = new YelpDB<T>(Restaurants, null, null);
		
		ArrayList<Restaurant> top3 = db.threeBest();
		
		assertTrue(top3.contains(r1)); 
		assertTrue(top3.contains(r2));
		assertTrue(top3.contains(r3)); 
		assertFalse(top3.contains(r4));
	}
	
	@Test
	public void test8() {
		String[] c1 = {"Restaurants", "pizza", "fastfood"};
		Restaurant r1 = new Restaurant(false, null, 0, 0, null, null, null, c1, null,
				null, null, 0, 0, null, null, 0);
		
		String[] c2 = {"Restaurants", "burger", "fastfood"};
		Restaurant r2 = new Restaurant(false, null, 0, 0, null, null, null, c2, null,
				null, null, 0, 0, null, null, 0);
		
		String[] c3 = {"Restaurants", "pizza", "finedining"};
		Restaurant r3 = new Restaurant(false, null, 0, 0, null, null, null, c3, null,
				null, null, 0, 0, null, null, 0);
		
		String[] c4 = {"Restaurants"};
		Restaurant r4 = new Restaurant(false, null, 0, 0, null, null, null, c4, null,
				null, null, 0, 0, null, null, 0);
		
		ArrayList<Restaurant> Restaurants = new ArrayList<Restaurant>();
		Restaurants.add(r1); Restaurants.add(r2); Restaurants.add(r3); Restaurants.add(r4);
		
		YelpDB<T> db = new YelpDB<T>(Restaurants, null, null);
		
		ArrayList<Restaurant> RelatedRestaurants = db.relatedRestaurants(r1);
		
		assertEquals(RelatedRestaurants.size(), 2);
		
		assertFalse(RelatedRestaurants.contains(r1));
		assertTrue(RelatedRestaurants.contains(r2));
		assertTrue(RelatedRestaurants.contains(r3));
		assertFalse(RelatedRestaurants.contains(r4));
	}
	
	@Test
	public void test9() throws IOException {
		YelpDB<T> db = new YelpDB<T>("data/restaurants.json","data/users.json", "data/reviews.json");
		
		
		ArrayList<Restaurant> Restaurants = db.getRestaurants();
		
		ArrayList<Review> Reviews = db.getReviews();
		
		//YelpDB<T> db = new YelpDB<T>(Restaurants, Reviews, null);
		
		ArrayList<Review> bestAndWorstReviews = db.bestAndWorst(Restaurants.get(0));
		System.out.println("!!!"); 
		System.out.println(bestAndWorstReviews );
		; 
		for (Review r:bestAndWorstReviews )
		{
			System.out.print(r.getText());
		}
		/*
		assertTrue(bestAndWorstReviews.size() == 2);
		
		assertEquals(bestAndWorstReviews.get(0), v1);
		assertEquals(bestAndWorstReviews.get(1), v2);
		
		assertFalse(bestAndWorstReviews.contains(v3));
		assertFalse(bestAndWorstReviews.contains(v4));
		assertFalse(bestAndWorstReviews.contains(v5));
		assertFalse(bestAndWorstReviews.contains(v6));
		*/
	}
	

}
