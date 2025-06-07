package _01_Methods;

import org.testng.annotations.Test;

import groovy.util.logging.Log;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

/*
 
  Pre-condition --- given() ---> content-type, set cookies, add auth, add param, set headers info etc
  
  Action/steps --- when() ---> get, post, put, delete
  
  Validation ---- then() ---> validate status code, extract response, extract headers cookies, response body
  
 */

/*

 Basic Validations:
 Status code
 Response body
 Response time
 Content-type
 Response String
 
*/

public class HTTPMethods {

	int userId;

	// Test to retrieve users and validate the response
	@Test(priority = 1, enabled = true)
	void getUsers() {

		given()

				.when().get("https://reqres.in/api/users?page=2")

				.then().statusCode(200).body("page", equalTo(2)).body(containsString("email"))
				.header("Content-Type", equalTo("application/json; charset=utf-8")).time(lessThan(2000L)).log().all();
	}

	// Test to retrieve users and validate the response
	@Test(priority = 2)
	void createUser() {

		HashMap<String, String> data = new HashMap<String, String>();

		data.put("name", "tejas");
		data.put("job", "tester");

		userId = given().header("x-api-key", "reqres-free-v1").contentType("application/json").body(data)

				.when().post("https://reqres.in/api/users")

				.then().statusCode(201).header("Content-Type", equalTo("application/json; charset=utf-8"))
				.time(lessThan(2000L)).body("name", equalTo("tejas")).body("job", equalTo("tester"))
				.body(containsString("id")).log().all().extract().jsonPath().getInt("id");
	}

	// Test to update existing users and validate the response
	@Test(priority = 3, dependsOnMethods = { "createUser" })
	void updateUser() {

		HashMap<String, String> data = new HashMap<String, String>();

		data.put("name", "tejas");
		data.put("job", "coder");

		given().header("x-api-key", "reqres-free-v1").contentType("application/json").body(data)

				.when().put("https://reqres.in/api/users/" + userId)

				.then().statusCode(200).header("Content-Type", equalTo("application/json; charset=utf-8"))
				.time(lessThan(2000L)).body("name", equalTo("tejas")).body("job", equalTo("coder")).log().all();
	}

	// Test to update existing users and validate the response
	@Test(priority = 4, dependsOnMethods = { "createUser" ,"updateUser"})
	void deleteUser() {


		given()
			.header("x-api-key", "reqres-free-v1")
			
		.when()
		.delete("https://reqres.in/api/users/" + userId)

		.then()
		.statusCode(204)
		.time(lessThan(2000L))
		.body(emptyOrNullString())
		.log().all();
		
	}

}
