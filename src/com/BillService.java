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

import model.Bill;
@Path("/Bill")
public class BillService {
	Bill BillObj = new Bill();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readBill()
	 {
		return BillObj.readBill(); 
	 }

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertBill(@FormParam("cusID") int cusID,
	 @FormParam("noOfWatts") int noOfWatts,
	 @FormParam("amount") double amount,
	 @FormParam("total") double total)
	
	{
	 String output = BillObj.insertBill(cusID, noOfWatts, amount, total);
	return output;
	}



	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateBill(String BillData)
	{
	//Convert the input string to a JSON object
	 JsonObject BillObject = new JsonParser().parse(BillData).getAsJsonObject();
	//Read the values from the JSON object
	 int billID = BillObject.get("billID").getAsInt();
	 int cusID = BillObject.get("cusID").getAsInt();
	 int noOfWatts = BillObject.get("noOfWatts").getAsInt();
	 double amount = BillObject.get("amount").getAsDouble();
	 double total = BillObject.get("total").getAsDouble();
	 String output = BillObj.updateBill(billID, cusID, noOfWatts, amount, total);
	return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteBill(String BillData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(BillData, "", Parser.xmlParser());

	//Read the value from the element 
	 String billID = doc.select("billID").text();
	 String output = BillObj.deleteBill(billID);
	return output;
	}
}
