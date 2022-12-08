package videoGameAPI;

import org.junit.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class TestCases1 {
	@Test(priority=1)
	public void test_getAllVideoGames() {

		given().
		
		when().
		get("http://localhost:8080/app/videogames").
		
		then().statusCode(200);
		
		
	}
	@Test(priority=2  )
	public void test_addNewVideoGameS() {
		
		HashMap data= new HashMap();
		data.put("id", "100");
		data.put("name", "Atharv");
		data.put("releaseDate", "2019-09-20T00:00:00+05:30");
		data.put("reviewScore", "2");
		data.put("category", "Travel");
		data.put("rating", "Global");
		
		Response res=
		given().
		contentType("application/json"). //for PUT & POST we give contentType to know which type of data we are passing like jason.
		body(data).
		when().
		post("http://localhost:8080/app/videogames").
		
		then().statusCode(200).
		log().body(). 
		extract().response();
		
	String jsonString= res.asString();
	
	Assert.assertEquals(jsonString.contains("Record Added Successfully"), true);
	}
@Test(priority=3)
	public void test_getVideoGameonBaseOfID() {
	
	given().
	when().
	get("http://localhost:8080/app/videogames/100").
	then().statusCode(200).
	log().all().
	body("videoGame.id", equalTo("100"))
	.body("videoGame.name",equalTo("Atharv"))
	.body("videoGame.releaseDate",equalTo("2019-09-20T00:00:00+05:30"));
	}
	
	@Test(priority=4)
	public void test_updateVideoGame() {
		
		HashMap data= new HashMap();
		data.put("id","100");
		data.put("name","SpiderMan");
		data.put("releaseDate", "2019-09-20T00:00:00+05:30");
		data.put("reviewScore", "1");
		data.put("category", "Adventure");
		data.put("rating", "Global");
		
		given().
		contentType("application/json").
		body(data).
		
		when().
		put("http://localhost:8080/app/videogames/100").
		then().
		statusCode(200).
		log().body().
		body("videoGame.id", equalTo("100")).
		body("videoGame.name", equalTo("SpiderMan"));
		
	}
	@Test(priority=5)
	public void test_deleteVideoGame() {
		Response res=
		given().
		when().
		delete("http://localhost:8080/app/videogames/100").
		then().
		statusCode(200).
		log().body().
		extract().response();
		
		String jsonString= res.asString();
		Assert.assertEquals(jsonString.contains("Record Deleted Successfully"),true);
	
	}
	
}

 