package fiveBtwoG.Customer;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import fiveBtwoG.entity.*;
import java.util.*;


public class CustomerSubmitReviews extends HttpServlet{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		//response.setContentType("application/json; charset=UTF-8");
		String accId = request.getParameter("accId");
		String movieId = request.getParameter("movieId");
		String mReview = request.getParameter("opinion");
		String mRating_str = request.getParameter("rating");
		double mRating = Double.parseDouble(mRating_str);
		
//		System.out.println(accId);
//		System.out.println(movieId);
//		System.out.println(mReview);
//		System.out.println(mRating_str);



		boolean success = ReviewAndRating.submitRaR(accId, movieId, mReview, mRating, "reviewArating.txt");
		
		if(success) {
			out.println("Submit successfully!");
			System.out.println("Submit successfully!");
		}
		else{
			out.println("ERROR!");
			System.out.println("ERROR");
		}
		
	}
}
