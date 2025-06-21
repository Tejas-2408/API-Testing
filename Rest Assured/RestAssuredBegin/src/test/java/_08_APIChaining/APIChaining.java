package _08_APIChaining;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import static io.restassured.RestAssured.*;


public class APIChaining {
	
	static final String Bearer_token = "0aa141a018d70a29d72f1e8241ae500d07652d29fc5cd4036cf6caf371198d85";
	static final String baseURL = "https://gorest.co.in/public/v2/users";
	int userId;
	Faker faker = new Faker();
	
	@Test
	void createUser() {
		
		JSONObject requestData = new JSONObject();
		requestData.put("name", faker.name().fullName());
		requestData.put("email", faker.internet().emailAddress());
		requestData.put("gender", "male");
		requestData.put("status", "inactive");
		
		
		userId = given()
			.headers("Authorization","Bearer "+ Bearer_token)
			.contentType("application/json")
			.body(requestData.toString())
		
		.when()
			.post(baseURL)
		
		.then()
			.statusCode(201)
			.log().body()
			.extract().response().jsonPath().getInt("id");
	}
	
	@Test(dependsOnMethods= {"createUser"})
	void getUser() {
		given()
			.headers("Authorization","Bearer "+ Bearer_token)
			.pathParam("id", userId)
		
		.when()
			.get(baseURL+"/{id}")
		
		.then()
			.statusCode(200)
			.log().body();
	}
	
	
	@Test(dependsOnMethods= {"getUser"})
	void updateUser() {
		
		JSONObject requestData = new JSONObject();
		requestData.put("name", faker.name().fullName());
		requestData.put("email", faker.internet().emailAddress());
		requestData.put("gender", "male");
		requestData.put("status", "active");
		
		
		given()
			.headers("Authorization","Bearer "+ Bearer_token)
			.pathParam("id", userId)
			.contentType("application/json")
			.body(requestData.toString())
		
		.when()
			.put(baseURL+"/{id}")
		
		.then()
			.statusCode(200)
			.log().body();
	}
	
	
	@Test(dependsOnMethods= {"updateUser"})
	void deleteUser() {
		given()
		.headers("Authorization","Bearer "+ Bearer_token)
		.pathParam("id", userId)
		
		.when()
			.delete(baseURL+"/{id}")
		
		.then()
			.statusCode(204);
	}

}
