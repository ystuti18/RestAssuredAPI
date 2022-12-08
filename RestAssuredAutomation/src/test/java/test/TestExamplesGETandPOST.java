package test;

import org.testng.annotations.Test;
import org.json.simple.JSONObject;
import org.testng.Assert;

import static io.restassured.RestAssured.*; //added static and .*

import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class TestExamplesGETandPOST {

	@Test
	public void testGet() {
		baseURI = "https://reqres.in/api";
		given().get("/users?page=2").then().statusCode(200).body("data[3].first_name", equalTo("Byron"))
				.body("data.first_name", hasItems("Byron", "Rachel"));
	}

	@Test
	public void testPost() {

		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("name", "Stuti");
//		map.put("job", "QA");
//		System.out.println(map);

		JSONObject request = new JSONObject();
		request.put("name", "Stuti");
		request.put("job", "QA");

		System.out.println(request.toJSONString());

		baseURI = "https://reqres.in/api";
		given().body(request.toJSONString()).when().post("/users").then().statusCode(201).log().all();
	}

	@Test
	public void testPost1() {

		Map<String, Object> map = new HashMap<String, Object>();

		JSONObject request1 = new JSONObject();
		request1.put("email", "ystuti18@gmail.com");
		request1.put("password", "Admin123");

		baseURI = "https://reqres.in/api";
		String postres = given().header("Content-type", "application/json").body(request1.toJSONString()).when()
				.post("/register").then().assertThat().statusCode(200).extract().response().asPrettyString();
		System.out.println(postres);

	}

}
