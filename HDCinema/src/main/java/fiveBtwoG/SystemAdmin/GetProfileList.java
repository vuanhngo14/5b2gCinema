package fiveBtwoG.SystemAdmin;

import java.io.IOException;
import java.io.PrintWriter;
import fiveBtwoG.entity.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;

import com.google.gson.Gson;

public class GetProfileList extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//System.out.println("Ajax called");
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        ArrayList<Profile> returnList = new ArrayList<Profile>();

        try {

            returnList = Profile.getProfileList();
            //System.out.println(new Gson().toJson(returnList));
            out.println(new Gson().toJson(returnList));

        } finally {
            out.close();
        }

    }
}
