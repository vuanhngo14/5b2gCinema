package fiveBtwoG.Manager;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import fiveBtwoG.entity.Movie;
//import entity.MovieSession;

@WebServlet("/createMovie")
public class ManagerCreateMovie extends HttpServlet{
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		//request parameter
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
		double avgRating = Double.parseDouble(request.getParameter("avgRating"));
		//System.out.println(movieID+ movieName+ PGRating+ movieGenre+releaseDate+ language+ cast+ director+synopsis+ duration+avgRating);
		//for testing purpose 
		
		boolean result = Movie.createMovie(movieID, movieName, PGRating, movieGenre,releaseDate, language, cast, director, synopsis, duration, avgRating);
		response.getWriter().println(result);
	}
}
