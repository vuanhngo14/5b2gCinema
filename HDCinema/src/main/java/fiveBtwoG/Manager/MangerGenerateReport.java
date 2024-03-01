package fiveBtwoG.Manager;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import com.google.gson.*;
import fiveBtwoG.entity.*;

@WebServlet("/genReport")
public class MangerGenerateReport extends HttpServlet{

	@Override
	public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String month = request.getParameter("month");
		
		final Gson gson = new Gson();
	    String json = gson.toJson(Report.generateReport(month));
	    
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json);
	}
}
