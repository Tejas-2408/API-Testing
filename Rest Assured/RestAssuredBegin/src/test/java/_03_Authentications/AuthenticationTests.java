package _03_Authentications;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;



public class AuthenticationTests {
	
	// 1. Basic Authentication
	
//	@Test
	void verfiyBasicAuth() {
		
		given()
			.auth().basic("postman", "password")
		
		.when()
			.get("https://postman-echo.com/basic-auth")
		
		.then()
			.body("authenticated", equalTo(true))
			.log().body();
	}

	
	// 2. Basic Preemptive Auth
	
//	@Test
	void verfiyPreemptiveAuth() {
		
		given()
			.auth().preemptive().basic("postman","password")
		
		.when()
			.get("https://postman-echo.com/basic-auth")
		
		.then()
			.body("authenticated", equalTo(true))
			.log().body();
	}

	
	// 3. Digest Auth
	
//		@Test
		void verfiyDigestAuth() {
			
			given()
				.auth().digest("postman","password")
			
			.when()
				.get("https://postman-echo.com/basic-auth")
			
			.then()
				.body("authenticated", equalTo(true))
				.log().body();
		}
		
		
		// 4. Bearer Token Authentication
//		@Test
//		void verfiyBearerTokenAuth() {
//			
//			String bearerToken = "***************";
//			
//			given()
//				.header("Authorization","Bearer "+ bearerToken)
//			
//			.when()
//				.get("https://api.github.com/user/repos")
//			
//			.then()
//				.statusCode(200)
//				.log().body();
//		}
		
		
		// 5 API Key authentication
//		@Test
		void verifyAPIKey() {
			
			given()
				.header("x-api-key","reqres-free-v1")
				.queryParam("page", 2)
			
			.when()
				.get("https://reqres.in/api/users")
			
			.then()
				.statusCode(200)
				.log().body();
		}
		
		
		// 6. auth2 Authentication
		

}
