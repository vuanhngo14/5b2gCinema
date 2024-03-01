package fiveBtwoG.Customer;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import fiveBtwoG.entity.*;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


public class CustomerRedeemReward extends HttpServlet{

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		// ask for customer accID and points to redeem
	 String accID = request.getParameter("accId");
		String points_str = request.getParameter("points");
		int points = Integer.parseInt(points_str);
		//System.out.println("accId "+accID);
		System.out.println(points);
		String file = "loyaltyPoint.txt";
		
		// get the loyalty point list
		ArrayList<LoyaltyPoint> loyaltyList = LoyaltyPoint.getLoyaltyPointList(file);
		
		boolean result = LoyaltyPoint.redeemReward(accID, points, loyaltyList);
		boolean check = LoyaltyPoint.updateReward(loyaltyList, file);
		for(int i=0; i<loyaltyList.size(); i++) {
			System.out.println(loyaltyList.get(i).toString());
		}
		
		if(check == true) {
			for(int i=0; i<loyaltyList.size(); i++) {
				//out.println(loyaltyList.get(i).toString());
			}
			System.out.println("Redeem successfully");
		}
		else {
			System.out.println("Redeemtion fail, insufficient point");
		}
		
	}
}
