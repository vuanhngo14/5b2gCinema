package fiveBtwoG.SystemAdmin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import fiveBtwoG.entity.Account;

public class SearchAccount extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException //must use name service
	{
		String username = req.getParameter("username");
		
		Account returnedAcc = Account.searchAcc(username);
			PrintWriter out = res.getWriter();
			 out.println(new Gson().toJson(returnedAcc));
		
	}
}
