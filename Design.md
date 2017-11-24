Design Decisions for Machine Problem 5  
===  
  
## Datatypes to Implement the Database  
  
### `Business`  
Supertype to represent businesses of all types (hospitals, stores, etc)  
Has the following fields:  
* `boolean` `open` - represents if the business is still running or not  
* `double` `latitude`, `longitude - represents the geographic location of the business on the map  
* `String[]` `neighborhoods` - collection of neighborhoods a business is a part of  
* `String` `business_id` - string that represents each business's business ID  
* `String` `name` - string that represents the name of the business  
* `String[]` `categories` - collection of tags that the business is associated to  
* `String` `state` - state the business is located in  
* `String` `type` - represents the type of establishment this object represents  
* `String` `city` - the city the business is located in  
* `String` `full_address` - the address of the business  
  
Has the following method(s):  
* `void changeOpenStatus(boolean status)` - changes the `open` field of the business to `status`  
  
Rep Invariant:  
* intersection of `latitude` and `longitude` must be located in the `city`
* `city` must be located in the `state` of the `Business`  
* `business_id` of one business cannot be the same as the `business_id` of any other business  
  
### `Restaurant`  
Subtype of `Business`; `extends` `Business` to inherit all its fields  
Has the following additional fields:  
* `String` `url` - the link leading to the Yelp page of this `Restaurant`  
* `double` `stars` - represents the rating of the `Restaurant`'s overall quality  
* `int` `review_count` - the number of Yelp reviews this `Restaurant` has been written  
* `String` `photo_url` - link leading to an image of the `Restaurant`  

