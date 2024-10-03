package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.User_POJO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

// Here we will work on CRUD operations >> Create, Retrive(Get), Update, Delete 

public class UserEndPoints_CRUD {
	
	public static Response CreateUser(User_POJO payload)
	{
		Response response = given().contentType(ContentType.JSON)
									.accept(ContentType.JSON)
									.body(payload)
							.when().post(Routes.post_url);
		
		return response;
	}
	
	
	public static Response GetUser(String userName)
	{
		Response response = given().pathParam("username", userName)
							.when().get(Routes.get_url);
		
		return response;
	}
	
	
	public static Response UpdateteUser(String userName, User_POJO payload)
	{
		Response response = given().contentType(ContentType.JSON)
									.accept(ContentType.JSON)
									.pathParam("username", userName)
									.body(payload)
							.when().put(Routes.update_url);
		
		return response;
	}
	
	
	public static Response DeleteUser(String userName)
	{
		Response response = given().pathParam("username", userName)
							.when().delete(Routes.delete_url);
		
		return response;
	}
	
	
	
	

}






