package _09_Serialization_Deserialization;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;
import io.restassured.response.Response;

public class SerializationDeserialization {
	String stuId;

	@Test
	public void serialization() {
		
		String courses[] = {"selenium","java","python"};
		Student stu = new Student("Tejas","Delhi","1234567",courses);
		
		stuId = given()
			.contentType("application/json")
			.body(stu)  // Serialization occurred by defining contentType
		
		.when()
			.post("http://localhost:3000/students")
		
		.then()
			.statusCode(201)
			.log().body()
			.extract().response().jsonPath().getString("id");
		
	}
	
	
	@Test(dependsOnMethods= {"serialization"})
	public void deserialization() {
		
		Response response = given()
			.pathParam("id", stuId)
		.when()
			.get("http://localhost:3000/students/{id}")
		
		.then()
			.statusCode(200)
			.extract().response();
		
		// Extract ID separately from JSON response
		String extractedId = response.jsonPath().getString("id");
		
		// De-serialization convert JSON to student object
		Student stu = response.as(Student.class);
		System.out.println("Student Details: "+stu+ extractedId);
		
	}
	
	
	
	@Test(dependsOnMethods= {"deserialization"})
	public void deleteStudent() {
		System.out.println("Deleting student with ID ==> "+stuId);
		
		given()
			.pathParam("id", stuId)
		
		.when()
			.delete("http://localhost:3000/students/{id}")
			
		.then()
			.statusCode(200)
			.log().all();
	}
}
