package _10_DataDrivenTesting;
import org.json.JSONObject;
import org.testng.annotations.Test;


import com.github.javafaker.Faker;

import static io.restassured.RestAssured.*;


public class DataDrivenTestingJson {
	
	static String Bearer_Token;
	static String base_uri = "https://simple-books-api.glitch.me";
	
	static void createToken() {
		Faker faker = new Faker();
		JSONObject requestBody = new JSONObject();
		requestBody.put("clientName", faker.name().firstName());
		requestBody.put("clientEmail", faker.internet().emailAddress());
		
		Bearer_Token = given()
			.body(requestBody.toString())
		.when()
			.post(base_uri+"/api-clients")
		.then()
			.extract().response().jsonPath().getString("accessToken");
		System.out.println(Bearer_Token);
	}
	
	

}
