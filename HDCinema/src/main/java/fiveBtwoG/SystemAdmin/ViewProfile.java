package fiveBtwoG.SystemAdmin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import fiveBtwoG.entity.Profile;

public class ViewProfile extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException //must use name service
	{
		String profID = req.getParameter("profID");
		
		Profile returnedProf = Profile.viewProfile(profID);
			PrintWriter out = res.getWriter();
			out.println(new Gson().toJson(returnedProf));
	}
}
