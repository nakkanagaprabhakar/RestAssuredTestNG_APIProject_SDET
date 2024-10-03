package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.util.ResourceBundle;

import api.payload.User_POJO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

// Here we will work on CRUD operations >> Create, Retrive(Get), Update, Delete 

public class UserEndPoints_CRUD2_FromProperties {
	
	// This method is for To get URL's from properties file
	public static ResourceBundle getURLsFromProperties() 
	{
		ResourceBundle routes = ResourceBundle.getBundle("GlobalRoutes");   // for load properties file
		
		return routes;
	}
	
	
	
	
	
	public static Response CreateUser(User_POJO payload) throws IOException
	{
		String post_url = getURLsFromProperties().getString("post_url");
		
		Response response = given().contentType(ContentType.JSON)
									.accept(ContentType.JSON)
									.body(payload)
							.when().post(post_url);
		
		return response;
	}
	
	
	public static Response GetUser(String userName) throws IOException
	{
		String get_url = getURLsFromProperties().getString("get_url");
		
		Response response = given().pathParam("username", userName)
							.when().get(get_url);
		
		return response;
	}
	
	
	public static Response UpdateteUser(String userName, User_POJO payload) throws IOException
	{
		String update_url = getURLsFromProperties().getString("update_url");
		
		Response response = given().contentType(ContentType.JSON)
									.accept(ContentType.JSON)
									.pathParam("username", userName)
									.body(payload)
							.when().put(update_url);
		
		return response;
	}
	
	
	public static Response DeleteUser(String userName) throws IOException
	{
		String delete_url = getURLsFromProperties().getString("delete_url");
		
		Response response = given().pathParam("username", userName)
							.when().delete(delete_url);
		
		return response;
	}
	
	
	
	

}






