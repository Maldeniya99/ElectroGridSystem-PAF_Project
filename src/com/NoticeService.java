package com;

import model.Notice;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/Notices")

public class NoticeService {
	Notice noticeObj = new Notice(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readNotices() 
	 { 
	 return  noticeObj.readNotices();
	 }
	
	
	//insert
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertNotice(@FormParam("nCategory") String nCategory, 
	 @FormParam("nTitle") String nTitle, 
	 @FormParam("nDesc") String nDesc, 
	 @FormParam("nDate") String nDate,
	 @FormParam("nAuthor") String nAuthor) 
	{ 
	 String output = noticeObj.insertNotice(nCategory, nTitle, nDesc, nDate, nAuthor); 
	return output; 
	}
	
	//update
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateNotice(String noticeData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject noticeObject = new JsonParser().parse(noticeData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String noticeID = noticeObject.get("noticeID").getAsString(); 
	 String nCategory = noticeObject.get("nCategory").getAsString(); 
	 String nTitle = noticeObject.get("nTitle").getAsString(); 
	 String nDesc = noticeObject.get("nDesc").getAsString(); 
	 String nDate = noticeObject.get("nDate").getAsString(); 
	 String nAuthor = noticeObject.get("nAuthor").getAsString(); 
	 String output = noticeObj.updateNotice(noticeID, nCategory, nTitle, nDesc, nDate, nAuthor); 
	return output; 
	}
	
	//delete
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteNotice(String noticeData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(noticeData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String noticeID = doc.select("noticeID").text(); 
	 String output = noticeObj.deleteNotice(noticeID) ;
	return output; 
	}

}

