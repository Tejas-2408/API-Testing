package _08_APIChaining;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import static io.restassured.RestAssured.*;

public class CreateUser {
	static final String Bearer_token = "0aa141a018d70a29d72f1e8241ae500d07652d29fc5cd4036cf6caf371198d85";
	static final String baseURL = "https://gorest.co.in/public/v2/users";
	int userId;
	Faker faker = new Faker();
	
	@Test
	void createUser(ITestContext context) {
		
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
		
		context.setAttribute("userId", userId);
	}
}
