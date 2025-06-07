package _02_RequestPayload;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import groovy.util.logging.Log;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;


/*
 	Demonstrates different ways to create Post request bodies:
 	1. Using HashMap (if data is less)
 	2. Using org.json library
 	3. Using Java POJO class
 	4. Using External JSON File
 */

public class RequestPayload {
	
	String userId;
	
	// 1. Create post request body using HashMap
	
//	@Test
	void createStudentHashMap() {
		
		HashMap<String,Object> requestBody = new HashMap<>();
		
		requestBody.put("name", "tejas");
		requestBody.put("location", "india");
		requestBody.put("phone", "1234567");
		
		String courses[] = {"Java","C++"};
		requestBody.put("courses", courses);
		
		userId = given()
			.contentType("application/json")
			.body(requestBody)
		
		.when()
			.post("http://localhost:3000/students")
			
		.then()
			.statusCode(201)
			.body("name", equalTo("tejas"))
			.body("location", equalTo("india"))
			.body("phone", equalTo("1234567"))
			.body("courses[0]", equalTo("Java"))
			.body("courses[1]",equalTo("C++"))
			.header("Content-Type",equalTo("application/json"))
			.log().body()
			.extract().jsonPath().getString("id");
	}
	
	
	// 2. Create post request body using JSON Library
//	@Test
void createStudentJSONLib() {
		
		JSONObject requestBody = new JSONObject();
		requestBody.put("name", "tejas");
		requestBody.put("location", "india");
		requestBody.put("phone", "1234567");
		
		String courses[] = {"Java","C++"};
		requestBody.put("courses", courses);
		
		userId = given()
			.contentType("application/json")
			.body(requestBody.toString())
		
		.when()
			.post("http://localhost:3000/students")
			
		.then()
			.statusCode(201)
			.body("name", equalTo("tejas"))
			.body("location", equalTo("india"))
			.body("phone", equalTo("1234567"))
			.body("courses[0]", equalTo("Java"))
			.body("courses[1]",equalTo("C++"))
			.header("Content-Type",equalTo("application/json"))
			.log().body()
			.extract().jsonPath().getString("id");
	}



//3. Create post request body using POJO class
//	@Test
void createStudentPOJO() {
		
		StudentPojo requestBody = new StudentPojo();
		requestBody.setName("tejas");
		requestBody.setLocation("india");
		requestBody.setPhone("1234566");
		
		String courses[] = {"Java","C++"};
		requestBody.setCourses(courses);
		
		
		userId = given()
			.contentType("application/json")
			.body(requestBody)
		
		.when()
			.post("http://localhost:3000/students")
			
		.then()
			.statusCode(201)
			.body("name", equalTo(requestBody.getName()))
			.body("location", equalTo(requestBody.getLocation()))
			.body("phone", equalTo(requestBody.getPhone()))
			.body("courses[0]", equalTo(requestBody.getCourses()[0]))
			.body("courses[1]",equalTo(requestBody.getCourses()[1]))
			.header("Content-Type",equalTo("application/json"))
			.log().body()
			.extract().jsonPath().getString("id");
	}



// 4. Create post request body using External File

@Test
void createStudentExternalFile() throws FileNotFoundException {
	
	File myFile = new File(".\\src\\test\\resources\\body.json");
	FileReader fileReader = new FileReader(myFile);
	JSONTokener jsonTokener = new JSONTokener(fileReader);
	
	JSONObject requestBody = new JSONObject(jsonTokener);
	
	
	userId = given()
		.contentType("application/json")
		.body(requestBody.toString())
	
	.when()
		.post("http://localhost:3000/students")
		
	.then()
		.statusCode(201)
		.body("name", equalTo("tejas"))
		.body("location", equalTo("india"))
		.body("phone", equalTo("1234567"))
		.body("courses[0]", equalTo("Java"))
		.body("courses[1]",equalTo("C++"))
		.header("Content-Type",equalTo("application/json"))
		.log().body()
		.extract().jsonPath().getString("id");
}
	
	@AfterMethod
	void deleteStudentRecord() {
		
		given()
		
		.when()
			.delete("http://localhost:3000/students/"+userId)
			
		.then()
			.statusCode(200);
	}

}
