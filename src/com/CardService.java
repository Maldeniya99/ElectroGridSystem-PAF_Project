package com; 
import model.Card; 

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON
import com.google.gson.*; 

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

@Path("/Card") //set path
public class CardService { 

    Card cardObj = new Card(); //declare card object

    @GET
    @Path("/") 
    @Produces(MediaType.TEXT_HTML) 

    public String readCard() { 
        return cardObj.readCard(); 
    } 
 
    @POST
    @Path("/") 
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
    @Produces(MediaType.TEXT_PLAIN) 
    
    public String insertCard(
        @FormParam("accNo") String accNo, 
        @FormParam("cardNo") String cardNo, 
        @FormParam("expire") String expire, 
        @FormParam("cvc") String cvc) { 

            String output = cardObj.insertCard(accNo, cardNo, expire, cvc); 
            return output; 
        }

        @PUT
        @Path("/") 
        @Consumes(MediaType.APPLICATION_JSON) 
        @Produces(MediaType.TEXT_PLAIN) 

        public String updateCard(String cardData) { 

            //Convert the input string to a JSON object 
            JsonObject cardObject = new JsonParser().parse(cardData).getAsJsonObject(); 

            //Read the values from the JSON object
            String accNo = cardObject.get("accNo").getAsString(); 
            String cardNo = cardObject.get("cardNo").getAsString(); 
            String expire = cardObject.get("expire").getAsString(); 
            String cvc = cardObject.get("cvc").getAsString();  

            String output = cardObj.updateCard(accNo, cardNo, expire, cvc); 

            return output; 
        }

        @DELETE
        @Path("/") 
        @Consumes(MediaType.APPLICATION_XML)
        @Produces(MediaType.TEXT_PLAIN) 

        public String deleteCard(String cardData) { 

            //Convert the input string to an XML document
        	Document doc = Jsoup.parse(cardData, "", Parser.xmlParser());
        
            //Read the value from the element <itemID>
            String cardID =doc.select("cardID").text();

 
            String output = cardObj.deleteCard(cardID); 

            return output; 
        }

}