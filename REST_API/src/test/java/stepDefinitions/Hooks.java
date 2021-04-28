package stepDefinitions;

import io.cucumber.java.Before;

public class Hooks {
	
	
	@Before("@DeletePlace")
	public void beforeScenario() throws Throwable
	{
		
		StepDefinition steps = new StepDefinition();
		if(StepDefinition.place_id==null)
		{
		steps.add_place_payload_with_name_language_and_address("Ganeshkumar", "kannada", "Bengaluru");
		steps.user_calls_add_place_api_with_post_http_request("AddPlaceAPI", "POST");
		steps.verify_placeid_created_maps_to_something_using_something("Ganeshkumar", "getPlaceAPI");
		}
		
	}

}
