MP5 Design Decisions - Andrea Mah and Robin Reyes    
===  
  
## Datatypes to Implement the Database  
  
### `YelpDB`
Datatype that represents Yelp's restaurant dataset  
Has the following fields:  
* `List<Restaurant>` `Restaurants` - list of `Restaurant`s that exist on Yelp  
* `List<Review>` `Reviews` - list of `Review`s that exist on Yelp  
* `List<YelpUser>` `YelpUsers` - list of `YelpUser`s that exist on Yelp  
  
Has the following methods:  
* `List<Review> usersReviews(YelpUser reviewer)` - given a `YelpUser`, return a list of all `Reviews` submitted by that `YelpUser`  
* `List<Review> bestAndWorst(Restaurant restaurant)` - given a `restaurant`, return a List containing its best `Review` in the first entry of the List and its worst `Review` in the second entry  
* `List<Restaurant> closeRestaurants(double latitude, double longitude, double radius)` - given a `latitude` and `longitude`, returns a list that contains all restaurants contained in that `radius` in order of distance to the centre  
* `List<Restaurant> threeBest()` - returns of a List of the three best `Restaurant`s in `YelpDB`  
* `List<Restaurant> relatedRestaurants(Restaurant restaurant)` - given a `restaurant`, returns a list of other `Restaurant`s with the same category  

  
### `Business`  
Supertype to represent businesses of all types (hospitals, stores, etc.)  
Has the following fields:  
* `boolean` `open` - represents if the business is still running or not  
* `String` `url`- the the link which points to the business's page in the database
* `double` `latitude`, `longitude` - represents the geographic location of the business on the map  
* `String[]` `neighborhoods` - collection of neighborhoods a business is a part of  
* `String` `business_id` - string that represents each business's business ID  
* `String` `name` - string that represents the name of the business  
* `String[]` `categories` - collection of tags that the business is associated to  
* `String` `state` - state the business is located in  
* `String` `city` - the city the business is located in  
* `String` `full_address` - the address of the business  
  
Has the following method(s):  
* `void changeOpenStatus(boolean status)` - changes the `open` field of the business to `status`  
  
Rep Invariant:  
* `url` of one `Business` cannot be the same as the `url` of any other `Business`
* intersection of `latitude` and `longitude` must be located in the `city`
* `city` must be located in the `state` of the `Business`  
* `business_id` of one `Business` cannot be the same as the `business_id` of any other `Business`  
  
### `Restaurant`  
Subtype of `Business`; `extends` `Business` to inherit all its fields and methods  
Has the following additional fields:  
* `String` `url` - the link leading to the Yelp page of this `Restaurant`  
* `double` `stars` - represents the rating of the `Restaurant`'s overall quality  
* `int` `review_count` - the number of Yelp reviews this `Restaurant` has been written  
* `String` `photo_url` - link leading to an image of the `Restaurant`  
* `String[]` `schools` - collection of schools that this restaurant is local to  
  
Has the following method(s):  
* `void plusOneReview()` - increments `review_count` by 1  
  
Rep Invariant:
* `review_count` cannot be negative  
* `photo_url` of one `Restaurant` cannot be the same as the `photo_url` of another `Restaurant`  
* `stars` cannot be negative  
  
### `User`  
Supertype to represent a `User` of any kind of web service (Facebook user, Gmail user, etc.)  
Has the following fields:  
* `String` `url`- the the link which points to the user's personal page on the web service    
* `String` `user_id` - string that represents each user's user ID
* `String` `name` - string that represents the name associated with the owner of the `user` account  
  
Rep Invariant:  
* `url` of one `User` cannot be the same as the `url` of another `User`  
* `user_id` of one `User` cannot be the same as the `user_id` of any other `User`  
  
### `YelpUser`  
Subtype of `User`; `extends` `User` to inherit all its fields and methods  
Has the following additional fields:  
* `HashMap<String, Integer>` `votes` - represents the distribution of the user's votes earned in their reviews over each type of vote (`funny`, `useful`, `cool`)  
* `int` `review_count` - the number of Yelp reviews this `YelpUser` has written  
* `double` `average_stars` - average stars this `YelpUser` has given in their reviews  
  
Rep Invariant:  
* `review_count` cannot be negative  
* `average_stars` cannot be negative  
* for all `String`s in `votes.keySet()`, `votes.get(String)` cannot be negative  
  
### `Review`
Datatype that represents a review of a `Restaurant` on Yelp in the `YelpDB`  
Has the following fields:  
* `String` `business_id` - string that represents the `Restaurant` the `Review` is associated to
* `HashMap<String, Integer>` `votes` - represents the distribution of the user's votes on the review over each type of vote (`funny`, `useful`, `cool`)  
* `String` `review_id` - string that represents each review's review ID  
* `String` `text` - string representing the comments by a `YelpUser` about the `Restaurant`  
* `int` `stars` - represents the rating out of 5 given to the `Restaurant` in the `Review`
* `String` `user_id` - string that represents the `YelpUser` that wrote the `Review`
* `String` `date` - string that represents when the `Review` was written
  
Has the following method(s):  
* `void updateVotes(String vote)` - increments the number of votes of a particular type of `vote` in `votes` by 1  
  
Rep Invariant:  
* `review_id` of one `Review` cannot be the same as the `review_id` of another `Review`  
* `stars` cannot be negative  
* `date` must be in the format `YYYY-MM-DD` where `0 <= YYYY <= 2017` , `1 <= MM <= 12` ,  
if `MM == 01 || 03 || 05 || 07 || 08 || 10 || 12` `1 <= DD <= 31`  
if `MM == 04 || 06 || 09 || 11` `1 <= DD <= 30`  
if `(MM == 02) && (YYYY % 4 == 0)` `1 <= DD <= 29`  
if `(MM == 02) && (YYYY % 4 != 0)` `1 <= DD <= 28`  
  
