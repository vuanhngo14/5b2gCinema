package fiveBtwoG.Manager;

import java.io.*;
import javax.servlet.http.*;
import com.google.gson.Gson;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

import fiveBtwoG.entity.FoodDrink;

@WebServlet("/searchFoodDrink")
public class ManagerSearchFoodDrink extends HttpServlet{
	@Override
	public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String name = request.getParameter("name");
		FoodDrink item = FoodDrink.searchFoodDrink(name);

		final Gson gson = new Gson();
		

			String json = gson.toJson(item);
			response.setContentType("application/json");//set content type to 
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
	}
}
