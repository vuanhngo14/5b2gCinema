package fiveBtwoG.Manager;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

import fiveBtwoG.entity.FoodDrink;

public class ManagerDeleteFoodDrink extends HttpServlet{
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String itemID = request.getParameter("itemID");

		boolean result = FoodDrink.deleteFoodDrink(itemID);
		response.getWriter().println(result);
	}
}
