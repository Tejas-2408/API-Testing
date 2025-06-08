package _04_FileUpload;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;



public class FileUploadDownload {
	
	// 1. Single File Upload
//	@Test
	void uploadSingleFile() {
		
		File myFile = new File("D:\\API testing\\Postman Manual Testing\\File Upload and Download API and Oauths\\File+Upload.pdf");
		
		
		given()
			.multiPart("file",myFile)
			.contentType("multipart/form-data")
		.when()
			.post("http://localhost:8080/uploadFile")
		
		.then()
			.statusCode(200)
			.body("fileName", equalTo("File+Upload.pdf"))
			.log().body();
	}
	
	
	// 2. Multiple Files Upload
		@Test
		void uploadMultipleFiles() {
			
			File myFile1 = new File("D:\\API testing\\Postman Manual Testing\\File Upload and Download API and Oauths\\File+Upload.pdf");
			File myFile2 = new File("D:\\API testing\\Postman Manual Testing\\File Upload and Download API and Oauths\\Authorization+Types+in+Postman.pdf");
			
			
			given()
				.multiPart("files",myFile1)
				.multiPart("files",myFile2)
				.contentType("multipart/form-data")
			.when()
				.post("http://localhost:8080/uploadMultipleFiles")
			
			.then()
				.statusCode(200)
				.log().body();
		}

}
