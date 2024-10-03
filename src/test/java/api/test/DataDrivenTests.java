package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints_CRUD;
import api.payload.User_POJO;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTests {
	
	@Test(priority=1, dataProvider="Data", dataProviderClass=DataProviders.class)
	public void CreateUserTest(String userID, String UserName, String fname, String lname, String email,String password, String ph)
	{
		User_POJO up = new User_POJO();
		
		up.setId(Integer.parseInt(userID));
		up.setUsername(UserName);
		up.setFirstName(fname);
		up.setLastName(lname);
		up.setEmail(email);
		up.setPassword(password);
		up.setPhone(ph);
		
		
		Response response = UserEndPoints_CRUD.CreateUser(up);
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	
	@Test(priority=2, dataProvider="UserNames", dataProviderClass=DataProviders.class)
	public void DeleteUserTestByNames(String UserName)
	{
		
		Response response = UserEndPoints_CRUD.DeleteUser(UserName);
		Assert.assertEquals(response.getStatusCode(), 200);
	
	}
	
	
	
}












