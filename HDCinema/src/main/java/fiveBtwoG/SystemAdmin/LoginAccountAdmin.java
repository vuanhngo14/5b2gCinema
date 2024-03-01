package fiveBtwoG.SystemAdmin;

import java.util.ArrayList;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.InputStreamReader;

import java.io.BufferedReader;

import javax.servlet.*;
import com.google.gson.Gson;

import fiveBtwoG.entity.*;

public class LoginAccountAdmin extends HttpServlet {
	public void doPost	(HttpServletRequest req, HttpServletResponse res) throws IOException //must use name service
	{
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String role =  req.getParameter("roleID");
		System.out.println(role);
		
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
