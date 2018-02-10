package RestAssuredFramework.sampleTest.tests;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

/**
 * get all user details test
 * 
 * @author Saurav
 */
public class GetAllUsersTest {
	public static Logger LOG = Logger.getLogger(GetAllUsersTest.class);

	@BeforeMethod
	public void setup() {
		RestAssured.baseURI = "http://localhost:3000";
	}

	/**
	 * GET request
	 */
	@Test
	public void getAllUsers() {
		LOG.info("getAllUsers");

		Response rs = given().when().get("/users");

		if (rs.getStatusCode() == HttpStatus.SC_OK) {
			LOG.info("Test Pass");
			LOG.info(rs.getStatusLine());
			LOG.info("******************response body*******************");
			System.out.println(rs.getBody().asString());
			List<String> userNameList = rs.getBody().jsonPath().get("name");
			Assert.assertEquals(userNameList.get(0), "Leanne Graham");
		} else {
			LOG.error("Test Fail");
			Assert.fail(rs.getStatusLine() + " " + rs.getStatusCode());
		}
	}
}
