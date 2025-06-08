package day4;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;



public class FileUploadAndDownload {

	//1) single file upload
	
	//@Test
	void uploadSingleFile()
	{
		File myfile=new File("C:\\Automation\\automationFiles\\Test1.txt");
		
		given()
			.multiPart("file",myfile)
			.contentType("multipart/form-data")
			
		.when()
			.post("http://localhost:8080/uploadFile")
		.then()
			.statusCode(200)
			.body("fileName", equalTo("Test1.txt"))
			.log().body();
		
	}
	
	
	//2) Multiple files upload
	
	//@Test
	void uploadMultipleFiles()
	{
		File myfile1=new File("C:\\Automation\\automationFiles\\Test1.txt");
		File myfile2=new File("C:\\Automation\\automationFiles\\Test2.txt");
		
		given()
			.multiPart("files",myfile1)
			.multiPart("files",myfile2)
			.contentType("multipart/form-data")
			
		.when()
			.post("http://localhost:8080/uploadMultipleFiles")
		.then()
			.statusCode(200)
			.body("[0].fileName", equalTo("Test1.txt"))
			.body("[1].fileName", equalTo("Test2.txt"))
			.log().body();
		
	}
	
	@Test
	void downloadFile()
	{
		given()
			.pathParam("filename","Test1.txt")
		.when()
			.get("http://localhost:8080/downloadFile/{filename}")
		.then()
			.statusCode(200)
			.log().body();
		
	}
	
	
	
	
	
	
	
	
}
