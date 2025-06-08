package _06_ParsingJSONArray;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;

import org.testng.annotations.Test;

public class EmployeesAPI {
	
	@Test
	void testJsonResponseBody() {
		
		ResponseBody responseBody = given()
		
		.when()
			.get("http://localhost:3000/employees")
		.then()
			.statusCode(200)
			.extract().response().body();
		
		JsonPath jsonPath = new JsonPath(responseBody.asString());
		
		// Get the size of JSON Array
		int employeeCount = jsonPath.getInt("size()");
		
		// Print all the details of employees
		for(int i =0;i<employeeCount;i++) {
			String firstName = jsonPath.getString("["+i+"].first_name");
			String lastName = jsonPath.getString("["+i+"].last_name");
			String email = jsonPath.getString("["+i+"].email");
			String gender = jsonPath.getString("["+i+"].gender");
			
			System.out.println(firstName+" "+ lastName+" "+email+" "+gender);
		}
		
		// Check if specific employee is present in response or not
		
		boolean status = false;
		
		for(int i=0;i<employeeCount;i++) {
			String firstName = jsonPath.getString("["+i+"].first_name");
			
			if(firstName.equals("virat")) {
				status = true;
				break;
			}
		}
		
		assertThat(status, is(true)); // virat exists in the list or not
	}
	

}
