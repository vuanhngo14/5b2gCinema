package fiveBtwoG.Manager;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import fiveBtwoG.entity.Movie;

@WebServlet("/updateMovie")
public class ManagerUpdateMovie extends HttpServlet  {
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String movieID = request.getParameter("movieID");
		String movieName = request.getParameter("movieName");
		String PGRating = request.getParameter("PGRating");
		String movieGenre = request.getParameter("movieGenre");
		String releaseDate = request.getParameter("releaseDate");
		String language = request.getParameter("language");
		String cast = request.getParameter("cast");
		String director = request.getParameter("director");
		String synopsis = request.getParameter("synopsis");
		String duration = request.getParameter("duration");
		Double avgRating = Double.parseDouble(request.getParameter("avgRating"));
		
		
		boolean result = Movie.updateMovie(movieID, movieName, PGRating, movieGenre,releaseDate, language, cast, director, synopsis, duration, avgRating);
		response.getWriter().println(result);


	}
}
