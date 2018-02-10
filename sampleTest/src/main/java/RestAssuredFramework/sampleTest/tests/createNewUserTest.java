package RestAssuredFramework.sampleTest.tests;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
/**
 * create new user test
 * @author Saurav
 * 
 */
public class createNewUserTest 
{
	public static Logger LOG = Logger.getLogger(createNewUserTest.class);

	@BeforeMethod
	public void setup()
	{
		RestAssured.baseURI="http://localhost:3000";
	}
	
	/**
	 * POST request
	 */
	@Test
	public void createNewUser()
	{
		LOG.info("createNewUser");

		Response rs=given()
				.contentType(ContentType.JSON)
				.body(new File(System.getProperty("user.dir")+File.separator+"createUser.json"))
				.when()
				.post("/users");

		if(rs.getStatusCode()==HttpStatus.SC_CREATED)
		{
			LOG.info("Test Pass");
			LOG.info(rs.getStatusLine());
			LOG.info("******************response body*******************");
			System.out.println(rs.getBody().asString());
			String userName=rs.getBody().jsonPath().get("name");
			Assert.assertEquals(userName, "Clementina DuBuque");
		}
		else
		{
			LOG.error("Test Fail");
			Assert.fail(rs.getStatusLine()+" "+rs.getStatusCode());
		}
	}
}