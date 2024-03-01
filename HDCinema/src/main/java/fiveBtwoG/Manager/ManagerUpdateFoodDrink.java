package fiveBtwoG.Manager;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import fiveBtwoG.entity.FoodDrink;
import fiveBtwoG.entity.FoodDrinkType;

@WebServlet("/updateFoodDrink")
public class ManagerUpdateFoodDrink extends HttpServlet  {
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		
		//do searching first...
		//request for new item para
		String itemID = request.getParameter("itemID");//get item id the argument refer html
		String name = request.getParameter("name");//get name
		String size = request.getParameter("size");//get size
		String description = request.getParameter("description");//get item description
		String str_price = request.getParameter("price");
		double price = Double.parseDouble(str_price);//get price from html
		
		String input = request.getParameter("type");
		FoodDrinkType type = null;
		if(input.equalsIgnoreCase("food")) {
			type = FoodDrinkType.Food;
		}else if(input.equalsIgnoreCase("drink")){
			type = FoodDrinkType.Drink;
		}
		
		System.out.println(type);
		
		
		if(FoodDrink.updateFoodDrink(itemID, name,size,description,price,type ) == true) {
			System.out.println("Success");
		}else {
			System.out.println("Failed");
		}
		
	}
}
