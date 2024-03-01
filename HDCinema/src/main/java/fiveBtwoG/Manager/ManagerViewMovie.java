package fiveBtwoG.Manager;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.gson.Gson;
import fiveBtwoG.entity.Movie;

public class ManagerViewMovie extends HttpServlet  {
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		String name = request.getParameter("name");
		final Gson gson = new Gson();
		String json = gson.toJson(Movie.searchMovie(name));
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}
}
