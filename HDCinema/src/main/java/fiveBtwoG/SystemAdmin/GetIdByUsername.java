package fiveBtwoG.SystemAdmin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import fiveBtwoG.entity.Account;

@WebServlet("/getIDByUSer")
public class GetIdByUsername extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//System.out.println("Ajax called");
		String username = request.getParameter("username");
        
       String returnAccId = Account.getIDofUsername(username);
       response.setContentType("text/plain");
       
       // Write the string response to the output stream
       PrintWriter out = response.getWriter();
       out.print(returnAccId);
       out.flush();

    }
}
