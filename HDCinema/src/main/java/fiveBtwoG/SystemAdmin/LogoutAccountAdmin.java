package fiveBtwoG.SystemAdmin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import fiveBtwoG.entity.Account;

public class LogoutAccountAdmin extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException //must use name service
	{
		String accID = req.getParameter("accID");
		
		boolean result=Account.logout(accID);
			// Create a Gson instance
		    Gson gson = new Gson();
		    
		    // Convert the boolean result to JSON
		    String jsonResponse = gson.toJson(result);
		    
		    // Set the response content type
		    res.setContentType("application/json");
		    
		    // Write the JSON response to the output stream
		    PrintWriter out = res.getWriter();
		    out.print(jsonResponse);
		    out.flush();
	}
}
