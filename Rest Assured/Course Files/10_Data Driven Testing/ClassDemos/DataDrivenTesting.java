package day10;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.json.JSONObject;
import org.testng.annotations.Test;

public class DataDrivenTesting {

	 private static final String AUTH_TOKEN = "Bearer 24615c7dd917c0c504514080843047e63c162015d34c10c92dd822f786813143";
	 private static final String BASE_URL = "https://simple-books-api.glitch.me/orders";
	 
	 @Test(dataProvider="excelDataProvider", dataProviderClass=DataProviders.class)
	 public void testWithExcelData(String bookId, String customerName)
	 {
		 testSubmitAndDeleteOrder(bookId,customerName);
	 }
	  
	 	 
	 @Test(dataProvider="jsonDataProvider", dataProviderClass=DataProviders.class)
	 public void testWithJsonData(Map<String,String> data)
	 {
		 testSubmitAndDeleteOrder(data.get("BookID"), data.get("CustomerName"));
	 }
	  
	 
	 @Test(dataProvider="csvDataProvider", dataProviderClass=DataProviders.class)
	 public void testWithCSVData(String bookId, String customerName)
	 {
		 testSubmitAndDeleteOrder(bookId,customerName);
	 }
	  
	 
	 
	 void testSubmitAndDeleteOrder(String bookId, String customerName)
	 {
		//Submitting order
		 JSONObject requestBody=new JSONObject();
		 requestBody.put("bookId", Integer.parseInt(bookId));
		 requestBody.put("customerName", customerName);
		 
		  String orderId=given()
		  	.contentType("application/json")
		  	.header("Authorization",AUTH_TOKEN)
		  	.body(requestBody.toString())
		  	
		 .when()
		 	.post(BASE_URL)
		 .then()
		 	.statusCode(201)
		 	.log().body()
		 	.extract().jsonPath().getString("orderId");
		  
		  //Deleting order
		  given()
		  	.header("Authorization",AUTH_TOKEN)
		  	.pathParam("orderId", orderId)
		  	
		  .when()
		  		.delete(BASE_URL+"/{orderId}")
		  .then()
		  	.statusCode(204);
	 }
	
		 
}
