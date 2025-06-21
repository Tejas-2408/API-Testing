package _08_APIChaining;
import org.testng.ITestContext;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;

public class GetUser {
	static final String Bearer_token = "0aa141a018d70a29d72f1e8241ae500d07652d29fc5cd4036cf6caf371198d85";
	static final String baseURL = "https://gorest.co.in/public/v2/users";
	
	@Test(dependsOnMethods= {"_08_APIChaining.CreateUser.createUser"})
	void getUser(ITestContext context) {
		given()
			.headers("Authorization","Bearer "+ Bearer_token)
			.pathParam("id", (Integer)context.getAttribute("userId"))
		
		.when()
			.get(baseURL+"/{id}")
		
		.then()
			.statusCode(200)
			.log().body();
	}
	
}
