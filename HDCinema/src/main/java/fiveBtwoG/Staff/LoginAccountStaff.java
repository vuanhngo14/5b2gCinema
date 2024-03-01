package fiveBtwoG.Staff;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import fiveBtwoG.entity.Account;

public class LoginAccountStaff extends HttpServlet{
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException //must use name service
	{
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String role = req.getParameter("roleID");
		
boolean result = Account.login(username, password, role);
		
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
