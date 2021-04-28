package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlaceReqBody;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlaceReqBody addPlace(String name, String language, String address)
	{
	AddPlaceReqBody set = new AddPlaceReqBody();
	set.setAccuracy(10);
	set.setAddress(address);
	set.setLanguage(language);
	Location setLoc = new Location();
	setLoc.setLat(-38.383494);
	setLoc.setLng(33.427362);
	set.setLocation(setLoc);

	set.setName(name);
	set.setPhone_number("544487 148498");

	List<String> typeList = new ArrayList<String>();
	typeList.add("shoe");
	typeList.add("shocks");
	set.setTypes(typeList);

	set.setWebsite("www.google.com");
	return set;
	
	}
	
	public String deletePlaceReqBody(String place_id)
	{
		return "{\"place_id\":\""+place_id+"\"}";
	}
	
}
