package testing;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.annotations.Test;

public class FileUploadDownload2 {

    /**
     * Test to upload a single file to the server.
     */
    @Test(priority = 1)
    public void uploadSingleFile() {
    
        File myfile = new File("C:\\Automation\\automationFiles\\Test1.txt");

         given()
            .multiPart("file", myfile) // Attach the file as a multipart request
            .contentType("multipart/form-data") // Set the content type
        .when()
            .post("https://the-internet.herokuapp.com/upload") 
        .then()
            .statusCode(200) 
            .log().body(); 
    }

  
    /**
     * Test to download a file from the server.
     */
    @Test(priority = 2)
    public void downloadFile() {
       given()
        .when()
            .get("https://the-internet.herokuapp.com/download/test.txt") 
        .then()
            .statusCode(200) 
            .log().body(); 
    }
}
