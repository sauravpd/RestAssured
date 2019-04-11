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
 * Delete user test
 * @author Saurav
 *
 */
public class deleteUserTest 
{
	public static Logger LOG = Logger.getLogger(updateUserTest.class);

	@BeforeMethod
	public void setup()
	{
		RestAssured.baseURI = "http://localhost:3000";
	}

	/**
	 * DELETE request
	 */
	@Test
	public void deleteUser() {
		LOG.info("deleteUser");

		Response rs = given().contentType(ContentType.JSON).pathParam("id",5)
				.when()
				.delete("/users/{id}");

		if (rs.getStatusCode() == HttpStatus.SC_OK) {
			LOG.info("Test Pass");
			LOG.info(rs.getStatusLine() + " " + rs.getStatusCode());
		} else {
			LOG.error("Test Fail");
			Assert.fail(rs.getStatusLine() + " " + rs.getStatusCode());
		}
	}
}
