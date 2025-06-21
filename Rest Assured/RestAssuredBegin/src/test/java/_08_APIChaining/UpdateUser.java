package _08_APIChaining;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import static io.restassured.RestAssured.*;

public class UpdateUser {
	static final String Bearer_token = "0aa141a018d70a29d72f1e8241ae500d07652d29fc5cd4036cf6caf371198d85";
	static final String baseURL = "https://gorest.co.in/public/v2/users";
	Faker faker = new Faker();
	
	@Test(dependsOnMethods= {"_08_APIChaining.GetUser.getUser"})
	void updateUser(ITestContext context) {
		
		JSONObject requestData = new JSONObject();
		requestData.put("name", faker.name().fullName());
		requestData.put("email", faker.internet().emailAddress());
		requestData.put("gender", "male");
		requestData.put("status", "active");
		
		
		given()
			.headers("Authorization","Bearer "+ Bearer_token)
			.pathParam("id", (Integer)context.getAttribute("userId"))
			.contentType("application/json")
			.body(requestData.toString())
		
		.when()
			.put(baseURL+"/{id}")
		
		.then()
			.statusCode(200)
			.log().body();
	}
	
}
