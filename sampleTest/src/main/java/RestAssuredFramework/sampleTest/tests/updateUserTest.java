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
 * 
 * update the user details test
 * 
 * @author Saurav
 *
 */
public class updateUserTest {
	public static Logger LOG = Logger.getLogger(updateUserTest.class);

	@BeforeMethod
	public void setup() {
		RestAssured.baseURI = "http://localhost:3000";
	}

	/**
	 * PUT request
	 */
	@Test
	public void updateUserDetails() {
		LOG.info("updateUserDetails");

		Response rs = given().contentType(ContentType.JSON).pathParam("id", 10)
				.body(new File(System.getProperty("user.dir") + File.separator + "updateUser.json")).when()
				.put("/users/{id}");

		if (rs.getStatusCode() == HttpStatus.SC_OK) {
			LOG.info("Test Pass");
			LOG.info(rs.getStatusLine());
			LOG.info("******************response body*******************");
			System.out.println(rs.getBody().asString());
			String userName = rs.getBody().jsonPath().get("name");
			Assert.assertEquals(userName, "Steve DuBuque");
		} else {
			LOG.error("Test Fail");
			Assert.fail(rs.getStatusLine() + " " + rs.getStatusCode());
		}
	}
}
