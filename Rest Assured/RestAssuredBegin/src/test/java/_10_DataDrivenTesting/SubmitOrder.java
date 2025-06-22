package _10_DataDrivenTesting;

import org.json.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class SubmitOrder {
	
	private static String Bearer_Token = "Bearer b23ba476747bcab6e1820f9e23018320f0a59df41b571f50f4f668ded9a2ac94";
	private static String Base_URL = "https://simple-books-api.glitch.me/orders";
	
	@Test
	void testSubmitAndDeleteOrder() {
		JSONObject requestBody = new JSONObject();
		requestBody.put("bookId", 1);
		requestBody.put("customerName", "John");
		
		String orderId = given()
			.headers("Authorization",Bearer_Token)
			.contentType("application/json")
			.body(requestBody.toString())
		
		.when()
			.post(Base_URL)
		
		.then()
			.statusCode(201)
			.log().body()
			.extract().response().jsonPath().getString("orderId");
		
		
		given()
			.pathParam("orderId", orderId)
			.headers("Authorization",Bearer_Token)
			
		.when()
			.delete(Base_URL + "/{orderId}")
		.then()
			.statusCode(204);
	}

}
