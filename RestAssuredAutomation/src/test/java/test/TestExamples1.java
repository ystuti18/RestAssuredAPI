package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*; //added static and .*
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class TestExamples1 {

	@Test
	public void test1() {
		Response response= get("https://reqres.in/api/users?page=2"); //removed RestAssured, as because of import statement we don't need it
		System.out.println(response.getStatusCode());
		System.out.println(response.getTime());
		System.out.println(response.getBody().asString());
		System.out.println(response.asString());
		System.out.println(response.getStatusLine());
		System.out.println(response.getHeader("Content-type"));
		
		int StatusCode= response.getStatusCode();
		Assert.assertEquals(StatusCode, 200);
		
	}
	
	@Test
	public void test2() {
		baseURI="https://reqres.in/api";
		
		given().get("/users?page=2").then().statusCode(200).body("data[1].id",equalTo(8))
		.log().all();
		
	}
	
	@Test
	public void test3() {
		baseURI="https://reqres.in/api";
		
		given().get("/users/2").then().statusCode(200).body("data.id", equalTo(2)).log().all();
	}
	
	
	
}
