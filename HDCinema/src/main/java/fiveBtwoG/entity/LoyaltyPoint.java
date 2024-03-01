package fiveBtwoG.entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class LoyaltyPoint {
	private String accID;
	private int points;
	
	public LoyaltyPoint() {
		this.accID = "";
		this.points = 0;
	}
	
	public LoyaltyPoint(String accID, int points) {
		this.accID = accID;
		this.points = points;
	}
	
	public String getID() {
		return this.accID;
	}
	
	public void setID(String accID) {
		this.accID = accID;
	}
	
	public int getPoints() {
		return this.points;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	// read the loyalty point list
	public static ArrayList<LoyaltyPoint> getLoyaltyPointList(String file){
		ArrayList<LoyaltyPoint> loyaltyList = new ArrayList<>();
		Scanner reader = null;
		String filename = file;
		
		try {
			reader = new Scanner (new File(filename));
			
			// read the file row by row
			while (reader.hasNext()) {
				// each row of movie's detail
				String oneItem = reader.nextLine();
				String[] itemArr = oneItem.split(", ");
				String accID = itemArr[0];
				String points_str = itemArr[1];
				int points = Integer.parseInt(points_str);
				
				//loyalty point obj
				LoyaltyPoint loyaltyPoint = new LoyaltyPoint(accID, points);
				loyaltyList.add(loyaltyPoint);
				
			}
		}
		catch(FileNotFoundException err) {
			System.out.println(err);
		}
		finally {
			if(reader != null) {
				reader.close();
			}
		}
		
		return loyaltyList;
	}
	
	// check is the loyalty point sufficient 
	public static boolean redeemReward(String acc, int val, ArrayList<LoyaltyPoint> loyaltyList) {
		
		//ArrayList<LoyaltyPoint> latest_loyaltyList;
		int latest_point =0;
		boolean result = false;
		
		for (int i=0; i<loyaltyList.size(); i++) {
			if(acc.equals(loyaltyList.get(i).getID())) {
				if(loyaltyList.get(i).getPoints()>= val) {
					latest_point = loyaltyList.get(i).getPoints() - val;
					loyaltyList.get(i).setPoints(latest_point);
					result = true;
					//System.out.println("succuss");
				}
			}
			
		}
		
		return result;
	}
	
	
	public static boolean updateReward(ArrayList<LoyaltyPoint> loyaltyList, String file) {
	    String filenameOut = "loyaltyPoint.txt";
	    PrintWriter writer = null;
	    boolean check = false;
	    
	    
	        try {
	          writer = new PrintWriter(filenameOut);
	          for(int i=0; i<loyaltyList.size(); i++) {
	            writer.println(loyaltyList.get(i).accID + ", "+ loyaltyList.get(i).points);
	            writer.flush();
	          }
	          
	          check = true;
	        }
	        catch(FileNotFoundException err){
	        	System.out.println("hi");
	          System.out.println(err);
	        }
	        finally {
	          if(writer != null){
	                    writer.close();
	                }
	        }
	      

	    return check;

	  }
	
	@Override
	public String toString() {
		return "AccID: " + getID()+ "Points: "+ getPoints();
	}
}
