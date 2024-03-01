package fiveBtwoG.Customer;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import fiveBtwoG.entity.*;


@WebServlet("/viewOrderC")
public class SystemListReceipt extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public SystemListReceipt() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	  doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
		// Template how to link JSON with Servlet
		String searchId = request.getParameter("result");
		
		ArrayList<FoodDrinkOrder> FDOrder = new ArrayList<>();
		Gson gson = new Gson(); 
		
		loadFromDbs(FDOrder, searchId); 
		System.out.println("hello world");
		for(FoodDrinkOrder i:FDOrder) {
			if(i.getOrderId().equalsIgnoreCase(searchId)) {
				System.out.println(i.toString()); 
				
				String jsonOrder = gson.toJson(i); 
				response.getWriter().print(jsonOrder);

			}
		}
		
		

	}
	
	// Load all order from DBS 
	public void loadFromDbs(ArrayList<FoodDrinkOrder> FDOrder, String searchId) {
		String filePath = "foodDrinkOrder.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            
            while ((line = br.readLine()) != null) {
                
            	// Item list for that line 
            	ArrayList<FoodDrink> FDList = new ArrayList<>(); 
            	
            	String[] parts = line.split("\t");
                	
                // Check the number of item list 
                int itemLength = parts.length; 
                
                
                String id = parts[0];
                String status = parts[1];
                String date = parts[2];
                String time = parts[3];
                Double price = Double.parseDouble(parts[4]);
                
                for(int i = 5; i<itemLength; i++) {
                	String rawItem = parts[i]; 
                	String[] parts2 = rawItem.split("\\|"); 
                	
                	String iName = parts2[0]; 
                	int iQuantity = Integer.parseInt(parts2[1]); 
                	
                	FoodDrink fd = new FoodDrink(iName, iQuantity); 
                	FDList.add(fd); 
                	
                }
                
                // Generate the order from each line collected 
                FoodDrinkOrder fdOrder = new FoodDrinkOrder(id, status, FDList, price, date, time); 
                FDOrder.add(fdOrder); 
                
                System.out.println("\n");
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
	}
	
}
