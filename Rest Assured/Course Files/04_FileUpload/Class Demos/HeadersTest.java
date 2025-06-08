package day4;

import org.testng.annotations.Test;

import io.restassured.http.Cookie;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;


public class HeadersTest {

	@Test
	void testHeadersInResponse()
	{
		
		Response response=given()
		
		.when()
			.get("https://www.google.com/")
		.then()
			.log().headers()
			.statusCode(200)
			.header("Content-Type", containsString("text/html"))
			.header("Content-Encoding", notNullValue()) // Verify Content-Encoding is present
			.header("Content-Encoding", equalTo("gzip")) // Verify Content-Encoding is "gzip"
			.header("Content-Encoding", "gzip") // Verify Content-Encoding is "gzip"
			
			.header("X-Frame-Options", equalTo("SAMEORIGIN")) // Verify X-Frame-Options header value
			.header("Server",equalTo("gws"))
			.extract().response();
		
		
		//Extract specific header
		String headerValue=response.getHeader("Content-Type");	
		System.out.println("Value of header (Content-Type) : "+ headerValue);
		
		
		//Extract all the headers and print then
		Headers headers=response.getHeaders();
		
		for(Header h:headers)
		{
			System.out.println(h.getName() + " ==> "+ h.getValue());
		}
		
		
	}
		
}


