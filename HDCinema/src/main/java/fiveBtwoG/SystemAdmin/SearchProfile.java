package fiveBtwoG.SystemAdmin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import fiveBtwoG.entity.Profile;

public class SearchProfile extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException //must use name service
	{
		String name = req.getParameter("name");
		
		Profile returnedProf = Profile.searchProfile(name);
		
			PrintWriter out = res.getWriter();
			out.println(new Gson().toJson(returnedProf));
	}
}
