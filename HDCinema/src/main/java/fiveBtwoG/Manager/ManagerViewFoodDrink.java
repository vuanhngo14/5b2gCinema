package fiveBtwoG.Manager;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.google.gson.Gson;
import fiveBtwoG.entity.FoodDrink;

@WebServlet("/viewFoodDrink")
public class ManagerViewFoodDrink extends HttpServlet{
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		String name = request.getParameter("name");
		System.out.println(name);
		final Gson gson = new Gson();
		String json = gson.toJson(FoodDrink.searchFoodDrink(name));
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}
}
