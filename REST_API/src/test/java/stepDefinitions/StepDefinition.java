package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlaceReqBody;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils {
	RequestSpecification response;
	ResponseSpecification resSpec;
	Response res;
	TestDataBuild data = new TestDataBuild();
	static String place_id;
	
	@Given("^Add place payload with \"([^\"]*)\" \"([^\"]*)\" and \"([^\"]*)\"$")
	public void add_place_payload_with_name_language_and_address(String name, String language, String address) throws Throwable {
	
	    // Write code here that turns the phrase above into concrete action
			
			response = given().spec(requestSpec())
			.body(data.addPlace(name, language, address));
	}
	@When("^User calls \"([^\"]*)\" with \"([^\"]*)\" http request$")
	public void user_calls_add_place_api_with_post_http_request(String resource, String method) {
	    // Write code here that turns the phrase above into concrete actions
		
		APIResources resourceAPI =APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		
		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("POST")) {
		res = response.when()
				.post(resourceAPI.getResource())
				.then().spec(resSpec).extract().response();
		}
		else if(method.equalsIgnoreCase("GET"))
		{
			res = response.when()
					.get(resourceAPI.getResource())
					.then().spec(resSpec).extract().response();
		}
	}
	@Then("^the api call is success with status code \"([^\"]*)\"$")
	public void the_api_call_is_success_with_status_code(String statuscode) {
	    assertEquals(res.getStatusCode(),200);
	}

	 @And("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
	    public void something_in_response_body_is_something(String key, String value) throws Throwable {
			
			assertEquals(getJsonPath(res,key).toString(),value);
			System.out.println("success");
	    }
	 
	 @And("^Verify place_id created maps to \"([^\"]*)\" using \"([^\"]*)\"$")
	    public void verify_placeid_created_maps_to_something_using_something(String name, String resource) throws Throwable {
	        
		 place_id = getJsonPath(res,"place_id").toString();
		 
		 response = given().spec(requestSpec()).queryParam("place_id",place_id);
		 user_calls_add_place_api_with_post_http_request(resource, "GET");
		 
		 String nameRes = getJsonPath(res,"name").toString();
		
		 
		 assertEquals(name,nameRes);
	 }
		 
		 @Given("^Add deletePlace payload$")
		    public void add_deleteplace_payload() throws Throwable {
		        
			 response = given().spec(requestSpec()).body(data.deletePlaceReqBody(place_id));
		    }
		 
}

