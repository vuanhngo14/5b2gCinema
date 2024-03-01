package fiveBtwoG.SystemAdmin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import fiveBtwoG.entity.Account;

public class ViewAccount  extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException //must use name service
	{
		String accID = req.getParameter("accID");
		
		Account returnedAcc = Account.viewAcc(accID);
			PrintWriter out = res.getWriter();
			out.println(new Gson().toJson(returnedAcc));

	}
}
