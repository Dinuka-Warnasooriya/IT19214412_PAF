package model;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Servlet implementation class InvoicesAPI
 */
@WebServlet("/InvoicesAPI")
public class InvoicesAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Invoice invoiceObj = null;
    
    public InvoicesAPI() {
        super();
        
    }
    
    

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("come to post");
		Invoice Invoice = new Invoice();
		String output = Invoice.insertInvoice(request.getParameter("customerName"),
				 request.getParameter("email"),
				request.getParameter("units"),
				request.getParameter("amount"),
				request.getParameter("month"));
				response.getWriter().write(output);
				
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("came here");
		Invoice Invoice = new Invoice();
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		 String output = Invoice.updateInvoice(paras.get("hidInvoiceIDSave").toString(),
		 paras.get("customerName").toString(),
		paras.get("email").toString(),
		paras.get("units").toString(),
		paras.get("amount").toString(),
		paras.get("month").toString()
		);
		response.getWriter().write(output);
		
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Invoice Invoice = new Invoice();
		Map paras = getParasMap(request);
		 String output = Invoice.deleteInvoice(paras.get("invoiceID").toString());
		response.getWriter().write(output);
	}
	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request)
		{
			Map<String, String> map = new HashMap<String, String>();
	try
	 	{
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ?
			scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			for (String param : params)
				{ 
					String[] p = param.split("=");
					map.put(p[0], p[1]);
				 }
	 	}
		catch (Exception e)
			 {
			 	}
		return map;
				}

}
