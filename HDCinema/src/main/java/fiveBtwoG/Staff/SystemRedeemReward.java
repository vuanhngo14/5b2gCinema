package fiveBtwoG.Staff;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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

import fiveBtwoG.entity.LoyaltyPoint;


@WebServlet("/SystemRedeemReward")
public class SystemRedeemReward extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public SystemRedeemReward() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	  doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
		// Template how to link JSON with Servlet
		String custId = request.getParameter("custId").trim();
		int rwPoint = Integer.parseInt(request.getParameter("rwPoint").trim());
		
		
		ArrayList<LoyaltyPoint> pointList = new ArrayList<>(); 

		readDbs(pointList); 
		
		redeemReward(pointList, custId, rwPoint); 
		
		
		// response.getWriter().print(custId + " " + rwPoint);

	  
	}
	
	public void readDbs(ArrayList<LoyaltyPoint> loyaltyPoints) {
		String fileName = "loyaltyPoint.txt"; // Replace with the actual file path
        

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 2) {
                    String custId = parts[0].trim();
                    int point = Integer.parseInt(parts[1].trim());

                    LoyaltyPoint loyaltyPoint = new LoyaltyPoint(custId, point);
                    loyaltyPoints.add(loyaltyPoint);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void redeemReward(ArrayList<LoyaltyPoint> customerList, String searchId, int point) {
			
			boolean enough = false; 
			boolean found = false; 
			
			for(LoyaltyPoint i: customerList) {
				
				if(i.getID().equalsIgnoreCase(searchId)) {
										
					if(i.getPoints() >= point) {
						enough = true; 
						
						int currPoint = i.getPoints() - point;
						
						//TO DO: Edit in the customer data base 
						updateInDbs(i, currPoint); 
						
					}
				}
			}
		
		}
	
	public void updateInDbs(LoyaltyPoint customer, int currentPoint) {
			
			String filename = "loyaltyPoint.txt"; 
	        boolean found = false; 
	        
	        try {
	            // Open the input and output streams
	            BufferedReader reader = new BufferedReader(new FileReader(filename));
	            ArrayList<String> temp  = new ArrayList<>();
	
	            String currentLine;
	
	            // Read each line and write it to the output file,
	            // unless the ID matches the one to be deleted
	            while ((currentLine = reader.readLine()) != null) {
	                String id = currentLine.split(", ")[0];
	                if (!(id.equalsIgnoreCase(customer.getID()))) {
	                    temp.add(currentLine);
	                } else {
	                	found = true; 
	                    //System.out.println("Row with ID " + customer.getPoints() + " deleted successfully.");
	                    // Update with the new details
	                    temp.add(customer.getID() + ", " + currentPoint);
	                    System.out.println("Order written to file successfully.");
	                }
	            }
	            String filenameOut = "loyaltyPoint.txt";
	            PrintWriter writer1 = null;
	                writer1 = new PrintWriter(filenameOut);
	                for(int i=0;i<temp.size();i++) {
	                	String oneline = temp.get(i);
	                	writer1.println(oneline);
	                }
	                writer1.flush();
	                writer1.close();
	            
	            // Close the streams
	            reader.close();
	            
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