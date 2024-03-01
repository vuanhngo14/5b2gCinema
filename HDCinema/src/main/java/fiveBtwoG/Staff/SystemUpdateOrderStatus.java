package fiveBtwoG.Staff;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fiveBtwoG.entity.Account;
import fiveBtwoG.entity.FoodDrink;
import fiveBtwoG.entity.FoodDrinkOrder; 


@WebServlet("/SystemUpdateOrderStatus")
public class SystemUpdateOrderStatus extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public SystemUpdateOrderStatus() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	  doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
		
		//response.getWriter().print("Hello "+ name + "!!");
		
		ArrayList<FoodDrinkOrder> FDOrderList = new ArrayList<>(); 
		
		readOrderDbs(FDOrderList); 
		
		String newStatus = request.getParameter("orderStatus"); 
		String searchId = request.getParameter("orderId");
		
		updateStatus(FDOrderList, searchId, newStatus); 

		
		
	  
	}
	
	public void readOrderDbs(ArrayList<FoodDrinkOrder> FDOrderList) {
		
		// Read in the order database 
		
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
		                FDOrderList.add(fdOrder); 
		                
		                System.out.println("\n");
		            }
		        } catch (IOException e) {
		            System.err.println("Error reading file: " + e.getMessage());
		        }
		
	}
	
	public void updateStatus(ArrayList<FoodDrinkOrder> FDOrderList, String searchId, String newStatus ) {
		// Variable to store information 
				String status = null; 
				ArrayList<FoodDrink> itemList = new ArrayList<>(); 
				double price = 0; 
				String date = null, time = null; 
				
				// Get the information from the database 
				
				for(FoodDrinkOrder i: FDOrderList) {
					// Get the id we need to find
					if(i.getOrderId().equals(searchId)) {
						itemList = i.getItemList(); 
						price = i.getOrderPrice(); 
						date = i.getOrderDate(); 
						time = i.getOrderTime(); 
					}
					
				}
				
				String filename = "foodDrinkOrder.txt"; 
		        boolean found = false; 
		        
		        try {
		            // Open the input and output streams
		            BufferedReader reader = new BufferedReader(new FileReader(filename));
		           // BufferedWriter writer = new BufferedWriter(new FileWriter(filename + ".tmp"));
		            ArrayList<String> temp = new ArrayList<>();
		            String currentLine;

		            // Read each line and write it to the output file,
		            // unless the ID matches the one to be deleted
		            while ((currentLine = reader.readLine()) != null) {
		                String id = currentLine.split("\t")[0];
		                if (!(id.equals(searchId))) {
		                    temp.add(currentLine);
		                } else {
		                	found = true; 
		                    // Update with the new details		                   
		                    StringBuilder sb = new StringBuilder();
		                    for (FoodDrink item : itemList) {
		                        sb.append(item.getName() + "|" + item.getQuantity() + "\t");
		                    }
		                    temp.add(searchId + "\t" + newStatus + "\t" +date + "\t" + time + "\t" + price + "\t"+sb.toString());
		                    System.out.println("Order written to file successfully.");
		                }
		            }

		            
		            // Close the streams
		            reader.close();
		            
		            String filenameOut = "foodDrinkOrder.txt";
		            PrintWriter writer1 = null;
		                writer1 = new PrintWriter(filenameOut);
		                for(int i=0;i<temp.size();i++) {
		                	String oneline = temp.get(i);
		                	writer1.println(oneline);
		                }
		                writer1.flush();
		                writer1.close();

		            
		        } catch (IOException e) {
		            System.out.println("An error occurred: " + e.getMessage());
		        }
		        
		        if(!found) {
		        	System.out.println("ORDER DOES NOT EXIST. PLEASE TRY AGAIN");
		        }else {
		        	System.out.println("Updated Order Details: "); 
		        }
	}
	
	}