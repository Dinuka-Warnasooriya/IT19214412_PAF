package com;

import model.Invoice;

//for REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//for JSON
import com.google.gson.*;

//for XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Invoices")
public class InvoiceService {
	
	Invoice invoiceObj = new Invoice();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readInvoices() {
		
		return invoiceObj.readInvoices();
		
		
		
		
	}
	
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String insertInvoices(
							@FormParam("customerName") String customerName,
							@FormParam("email") String email,
							@FormParam("units") String units,
							@FormParam("amount") String amount,
							@FormParam("month") String month)
							
	{
		
		String output = invoiceObj.insertInvoice(customerName, email, units, amount, month);
		return output;
		
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateInvoices(String invoiceData)
	{
		//Convert input string to a JSON object
		JsonObject invoiceObj = new JsonParser().parse(invoiceData).getAsJsonObject();
		
		//Read values from JSON object
		String invoiceID = invoiceObj.get("invoiceID").getAsString();
		String customerName = invoiceObj.get("customerName").getAsString();
		String email = invoiceObj.get("email").getAsString();
		String units = invoiceObj.get("units").getAsString();
		String amount = invoiceObj.get("amount").getAsString();
		String month = invoiceObj.get("month").getAsString();
		
		String output = invoiceObj.updateInvoice(invoiceID, customerName, email, units, amount, month );
		return output;
		
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteInvoices(String invoiceData)
	{
		//Convert input string to a JSON object
		Document doc = Jsoup.parse(invoiceData, "", Parser.xmlParser());
		
		//Read values from JSON object
		String invoiceID = doc.select("invoiceID").text();
		
		String output = invoiceObj.deleteInvoice(invoiceID);
		return output;
		
	}

}
