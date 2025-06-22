package _10_DataDrivenTesting;
import static io.restassured.RestAssured.*;

import java.util.Map;

import org.json.JSONObject;
import org.testng.annotations.Test;

public class DataDrivenTesting {
	
	private static String Bearer_Token = "Bearer b23ba476747bcab6e1820f9e23018320f0a59df41b571f50f4f668ded9a2ac94";
	private static String Base_URL = "https://simple-books-api.glitch.me/orders";
	

//	@Test(dataProvider = "excelDataProvider",dataProviderClass = DataProviders.class)
	public void testWithExcel(String bookId, String customerName) {
		testSubmitAndDeleteOrder(bookId, customerName);
	}
	
//	@Test(dataProvider = "jsonDataProvider",dataProviderClass = DataProviders.class)
	public void testWithJson(Map<String,String> data) {
		testSubmitAndDeleteOrder(data.get("BookID"), data.get("CutomerName"));
	}
	
	@Test(dataProvider = "csvDataProvider",dataProviderClass = DataProviders.class)
	public void testWithCSV(String bookId, String customerName) {
		testSubmitAndDeleteOrder(bookId, customerName);
	}
	
	public void testSubmitAndDeleteOrder(String bookId, String customerName) {
		JSONObject requestBody = new JSONObject();
		requestBody.put("bookId", Integer.parseInt(bookId));
		requestBody.put("customerName", customerName);
		
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
