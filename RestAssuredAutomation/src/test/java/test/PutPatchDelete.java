package test;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*; //added static and .*
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;

public class PutPatchDelete {

	@Test
	public void examplePut() {

		JSONObject request = new JSONObject();

		request.put("name", "Stuti");
		request.put("job", "QA");

		System.out.println(request.toJSONString());
		baseURI = "https://reqres.in/api";
		
		given().header("Content-Type","application/json").
		contentType(ContentType.JSON).
		accept(ContentType.JSON).
		body(request.toJSONString()).
		when().
		put("/users/2").
		then().
		statusCode(200).
		log().all();

	}
	@Test
	public void examplePatch() {

		JSONObject request = new JSONObject();

		request.put("name", "Stuti");
		request.put("job", "QA");

		System.out.println(request.toJSONString());
		baseURI = "https://reqres.in";
		
		given().header("Content-Type","application/json").
		contentType(ContentType.JSON).
		accept(ContentType.JSON).
		body(request.toJSONString()).
		when().
		patch("api/users/2").
		then().
		statusCode(200).
		log().all();

	}
	@Test
	public void exampleDelete() {

		JSONObject request = new JSONObject();

		
		baseURI = "https://reqres.in/api";
		
		
		when().
		delete("/users/2").
		then().
		statusCode(204).
		log().all();

	}
}
