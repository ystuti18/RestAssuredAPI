package test;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static io.restassured.RestAssured.baseURI;

public class SOAPXMLRequest {

	@Test
	public void validateSoapXML() throws IOException {
		
		File file = new File("./SoapRequest/Add.xml.txt");
		if (file.exists())
		System.out.println(" >> File Exists");
		FileInputStream fileinputstream = new FileInputStream(file);
		String requestBody = IOUtils.toString(fileinputstream, "UTF-8");

		baseURI = "http://www.dneonline.com";

		given().contentType("text/xml").accept(ContentType.XML).body(requestBody).when().post("/calculator.asmx").then()
				.statusCode(200).log().all().and().body("//*:AddResult.text()", equalTo("6"));
		
		System.out.println();
	}
}
