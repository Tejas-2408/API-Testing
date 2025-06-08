package _04_FileUpload;

import io.restassured.http.Cookie;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.Map;

import org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

public class ResponseCookies {
	
	
	// Response cookies
	// For cookies an Active internet session is required, won't be created for local app
	
	@Test
	void responseCookies() {
		
		Response response = given()
		
		.when()
			.get("https://www.google.com/")
		
		.then()
			.statusCode(200)
			.log().cookies()
//			.cookie("AEC", notNullValue());
			.extract().response();
		
		
		// Get a single cookie
		String cookie = response.getCookie("AEC");
	    System.out.println(cookie);
	    
	    // Extract all cookies
	    Map<String,String> cookies = response.getCookies();
        System.out.println(cookies);
        
        // Print each cookie
        for(String key:cookies.keySet()) {
        	System.out.println(key + " : " + cookies.get(key));
        }
        
        // Capture details of cookies
        Cookie cookie_info = response.getDetailedCookie("AEC");
        
        System.out.println(cookie_info.hasExpiryDate());
        System.out.println(cookie_info.getExpiryDate());
        System.out.println(cookie_info.hasValue());
        System.out.println(cookie_info.getValue());
        System.out.println(cookie_info.isSecured());
	}

}
