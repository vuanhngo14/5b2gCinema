package fiveBtwoG.Manager;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.google.gson.Gson;
import fiveBtwoG.entity.Movie;

@WebServlet("/searchMovie")
public class ManagerSearchMovie extends HttpServlet  {
	@Override
	public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String movieName = request.getParameter("movieName");
		Movie item = Movie.searchMovie(movieName);

		final Gson gson = new Gson();
		String json = gson.toJson(item);
		response.setContentType("application/json");//set content type to 
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
		
		
	}
}
