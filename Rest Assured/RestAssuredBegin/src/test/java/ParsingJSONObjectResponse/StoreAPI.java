package ParsingJSONObjectResponse;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;

import org.testng.annotations.Test;

public class StoreAPI {

	@Test
	void testJsonResponseBody() {
		
		ResponseBody responseBody = given()
		
		.when()
			.get("http://localhost:3000/store")
		.then()
			.statusCode(200)
			.extract().response().body();
		
		JsonPath jsonPath = new JsonPath(responseBody.asString());
		
		// Get the size of array
		int bookCount = jsonPath.getInt("book.size()");
		
		// validate atleast one book should be in store
		assertThat(bookCount,greaterThan(0));
		
		// Print the titles of books
		for(int i=0;i<bookCount;i++) {
			String bookTitle = jsonPath.getString("book["+i+"].title");
			System.out.println(bookTitle);
		}
		
		// Specific book present in response
		boolean status = false;
		
		for(int i=0;i<bookCount;i++) {
			String bookTitle = jsonPath.getString("book["+i+"].title");
			if(bookTitle.equals("Moby Dick")) {
				status=true;
				break;
			}
		}
		
		assertThat(status,is(true));
		
		// Calculate and validate the total price of books
		double totalPrice = 0;
		
		for(int i=0;i<bookCount;i++) {
			double bookPrice = jsonPath.getDouble("book["+i+"].price");
			totalPrice+= bookPrice;
		}
		
		System.out.println("Total price of books: "+totalPrice);
		assertThat(totalPrice,is(53.92));
}
	
}
