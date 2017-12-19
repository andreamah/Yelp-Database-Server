## MP5 Work Breakdown - `andreamah_rmcreyes`  
  
### `andreamah` Contributions  
* created the class representations of database elements (`Business`, `User`, etc.)  
* configured the project's dependencies to be able to use `javax.json` and `antlr4-runtime` external libraries  
* created a parser to parse `restaurants.json`, `reviews.json`, and `users.json`
* implemented the `GETRESTAURANT(business_id)`, `ADDUSER(user_information)`, and `ADDRESTAURANT(restaurant_information)` request methods for the `YelpDBServer`  
* used ANTLR's parser generator to parse `queryString`s, implemented the `YelpDB` method `getMatches(queryString)`, and implemented the `YelpDBServer` request method `QUERY(queryString)`  
* wrote test cases for the json parser  
  
### `rmcreyes` Contributions  
* implemented `YelpDB`'s statistical learning methods `kMeansClusters_json(int k)` and `getPredictorFunction(String user)`  
* studied `FibonacciServer` example to implement `YelpDBServer`  
* implemented `ADDREVIEW(review_information)` request method for the `YelpDBServer`  
* wrote tests cases for `YelpDB` and `YelpDBServer`