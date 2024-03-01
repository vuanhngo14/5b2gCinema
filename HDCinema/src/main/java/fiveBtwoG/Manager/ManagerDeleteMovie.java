package fiveBtwoG.Manager;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import fiveBtwoG.entity.Movie;

@WebServlet("/deleteMovie")
public class ManagerDeleteMovie extends HttpServlet  {
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String movieID = request.getParameter("movieID");

		boolean result = Movie.deleteMovie(movieID);
		response.getWriter().println(result);
	}
}
