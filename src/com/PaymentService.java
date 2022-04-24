package com;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON
import com.google.gson.*; 

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

import model.Payment;

@Path("/Payment") 
public class PaymentService {
	Payment itemObj = new Payment(); 
	@GET
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML) 
	
	public String readpayment(/*String AccNo*/) { 
		return itemObj.readpayment(); 
	 }

	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	
	public String insertpayment(
	 @FormParam("AccNo") String AccNo, 
	 @FormParam("Name") String Name, 
	 @FormParam("Address") String Address, 
	 @FormParam("Phone") String Phone,
	 @FormParam("Amount") String Amount,
	 @FormParam("cardNo") String cardNo,
	 @FormParam("Expire") String Expire) {
		
	 String output = itemObj.insertpayment(AccNo, Name, Address, Phone, Amount, cardNo, Expire); 
	 
	return output; 
	}
	 
}
