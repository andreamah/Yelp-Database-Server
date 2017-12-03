package ca.ece.ubc.cpen221.mp5;

import java.util.ArrayList;

import java.util.function.ToDoubleBiFunction;
import java.util.stream.Collectors;

public class PredictorFunction<T> implements ToDoubleBiFunction<MP5Db<T>, String> {
	// abstraction function: linear function that takes the form
	//			y =a + bx
	// with r_squared being a measure of the accuracy of the function's
	// approximation
	
	double a;
	double b;
	double r_squared;
	
	
	public PredictorFunction(double a, double b, double r_squared) {
		this.a = a; this.b = b; this.r_squared = r_squared;
	}


	public double applyAsDouble(MP5Db<T> t, String u) {
		ArrayList<Restaurant> Restaurants = ((YelpDB<T>) t).getRestaurants();
		
		// get the price of the restaurant
		double price = Restaurants.stream()
				.filter(restaurant -> restaurant.business_id.equals(u))
				.map(restaurant -> restaurant.getPrice())
				.collect(Collectors.toList())
				.get(0);
		
		// apply the price as the parameter to the function
		double predictedStars = a + b * price;
		
		// rating has a maximum of 5
		if(predictedStars >= 5.0) {
			return 5.0;
		}
		// rating has a minimum of 1
		else if(predictedStars <= 1) {
			return 1.0;
		}
		// rating must be a natural number
		else {
			return (double) (int) (predictedStars + 0.5);
		}
	}
	



}