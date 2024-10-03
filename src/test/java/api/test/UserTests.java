package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints_CRUD;
import api.payload.User_POJO;
import io.restassured.response.Response;

public class UserTests {
	Faker f;
	User_POJO up;
	
	public Logger logger;  // for logs
	
	@BeforeClass
	public void Setup_FakerData()
	{
		f = new Faker();
		up = new User_POJO();
		
		up.setId(f.idNumber().hashCode());
		up.setUsername(f.name().username());
		up.setFirstName(f.name().firstName());
		up.setLastName(f.name().lastName());
		up.setEmail(f.internet().safeEmailAddress());
		up.setPassword(f.internet().password(5,10));
		up.setPhone(f.phoneNumber().cellPhone());
		
		
		// logs
		logger = LogManager.getLogger(this.getClass());
		
	}

	
	@Test(priority=1)
	public void CreateUserTest()
	{
		logger.info("********** CreatingUser *********");
		
		Response response = UserEndPoints_CRUD.CreateUser(up);
		
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("********** User Created *********");
	}
	
	
	@Test(priority=2)
	public void GetUserTest_ByName()
	{
		logger.info("********** Get User *********");
		
		Response response = UserEndPoints_CRUD.GetUser(this.up.getUsername());
		
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("********** User Got *********");
	}
	
	
	@Test(priority=3)
	public void UpdateUserTest_ByName()
	{
		logger.info("********** Updating User *********");
		
		// Update user Data by using payload(up)
		up.setFirstName(f.name().firstName());
		up.setLastName(f.name().lastName());
		up.setEmail(f.internet().safeEmailAddress());
		
		Response response = UserEndPoints_CRUD.UpdateteUser(this.up.getUsername() , up);
		
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		
		// Checking User Data After Update 
		Response responseAfterUpdate = UserEndPoints_CRUD.GetUser(this.up.getUsername());
		
		response.then().log().all();
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		
		logger.info("********** User Updated *********");
	}
	
	
	@Test(priority=4)
	public void DeleteUserTest_ByName()
	{
		logger.info("********** Deleting User *********");
		
		Response response = UserEndPoints_CRUD.DeleteUser(this.up.getUsername());
		
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("********** User Deleted *********");
	}
	
	
}













