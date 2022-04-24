package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Feedback;

@Path("/Feedback")
public class FeedbackService {
	
	Feedback itemObj = new Feedback(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readFeedbacks() 
	
	 { 
		return itemObj.readFeedbacks();  
	 }
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN)
	
	public String insertFeedback(@FormParam("cusID") String cusID, 
	 @FormParam("fbTitle") String fbTitle, 
	 @FormParam("fbRating") String fbRating, 
	 @FormParam("fbDescription") String fbDescription) 
	
	{ 
	 String output = itemObj.insertItem(cusID, fbTitle, fbRating, fbDescription); 
	return output; 
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String updateFeedback(String itemData) {
		
		//Convert the input string to a JSON object 
		 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject(); 
		 
		//Read the values from the JSON object
		 String fbID = itemObject.get("fbID").getAsString(); 
		 String cusID = itemObject.get("cusID").getAsString(); 
		 String fbTitle = itemObject.get("fbTitle").getAsString(); 
		 String fbRating = itemObject.get("fbRating").getAsString(); 
		 String fbDescription = itemObject.get("fbDescription").getAsString();
	
	String output = itemObj.updateFeedback(fbID, cusID, fbTitle, fbRating, fbDescription);
	return output;
	
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String deleteFeedback(String feedbackData) {
		
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(feedbackData, "", Parser.xmlParser()); 
		 
		//Read the value from the element <itemID>
		 String fbID = doc.select("fbID").text();
		
		String output = itemObj.deleteFeedback(fbID);
		
		return output;
		
	}


}
