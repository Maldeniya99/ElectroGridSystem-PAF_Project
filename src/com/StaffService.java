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

import model.Staff;
@Path("/Staff")
public class StaffService {
	Staff staffObj = new Staff();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readStaff()
	 {
		return staffObj.readStaff(); 
	 }

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertStaff(@FormParam("sname") String sname,
	 @FormParam("spassword") String spassword,
	 @FormParam("semail") String semail,
	 @FormParam("stel") String stel,
	 @FormParam("salary") String salary,
	 @FormParam("department") String department)
	{
	 String output = staffObj.insertStaff(sname, spassword, semail, stel, salary, department);
	return output;
	}



	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateStaff(String staffData)
	{
	//Convert the input string to a JSON object
	 JsonObject staffObject = new JsonParser().parse(staffData).getAsJsonObject();
	//Read the values from the JSON object
	 String staffID = staffObject.get("staffID").getAsString();
	 String sname = staffObject.get("sname").getAsString();
	 String spassword = staffObject.get("spassword").getAsString();
	 String semail = staffObject.get("semail").getAsString();
	 String stel = staffObject.get("stel").getAsString();
	 String salary = staffObject.get("salary").getAsString();
	 String department = staffObject.get("department").getAsString();
	 String output = staffObj.updateStaff(staffID, sname, spassword, semail, stel, salary, department);
	return output;
	}




	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteStaff(String staffData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(staffData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String staffID = doc.select("staffID").text();
	 String output = staffObj.deleteStaff(staffID);
	return output;
	}


}
