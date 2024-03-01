package fiveBtwoG.Customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import fiveBtwoG.entity.Movie;

@WebServlet("/getMovieName")
public class GetMovieName extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//System.out.println("Ajax called");
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();

        ArrayList<Movie>  returned = Movie.getList();
        

        try {

            out.println(new Gson().toJson(returned));

        } finally {
            out.close();
        }

    }
}
