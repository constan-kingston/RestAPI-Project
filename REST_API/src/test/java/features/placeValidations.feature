Feature: Validating Place API's 

@AddPlace
Scenario Outline: Verify if place is being added successfully using AddPlaceAPI 
	Given Add place payload with "<name>" "<language>" and "<address>" 
	When User calls "AddPlaceAPI" with "POST" http request 
	Then the api call is success with status code "200" 
	And "status" in response body is "OK" 
	And "scope" in response body is "APP" 
	And Verify place_id created maps to "<name>" using "getPlaceAPI"
	
	Examples: 
		|name|language|address|
		|constan|telugu|56 sandaipettai street|
		|kingston|tamil|No 5 puthuthottam|

@DeletePlace
Scenario: Verify if place is deleted by using deletePlaceAPI

	Given Add deletePlace payload
	When User calls "deletePlaceAPI" with "POST" http request
	Then the api call is success with status code "200" 
	And "status" in response body is "OK" 
	