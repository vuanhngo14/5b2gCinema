package fiveBtwoG.entity;

import java.util.*;
import java.util.stream.Stream;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FoodDrink {
	private String itemID;
	private String name;
	private String size;
	private String description;
	private double price;
	private FoodDrinkType type;
	
 
	private int quantity; 
	private String img; 

	
	public FoodDrink() {
		this.itemID = "";
		this.name = "";
		this.size = "";
		this.description = "";
		this.price = 0.0;
		this.type = null;
	}
	
	public FoodDrink(String itemID, String name, String size, String description, double price, FoodDrinkType type) {
		this.itemID = itemID;
		this.name = name;
		this.size = size;
		this.description = description;
		this.price = price;
		this.type = type;
	}
	
	public String getID() {
		return this.itemID;
	}
	
	public void setID(String itemID) {
		this.itemID = itemID;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSize() {
		return this.size;
	}
	
	public void setSize(String size) {
		this.size = size;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return this.price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public FoodDrinkType getType() {
		return this.type;
	}
	
	public void setType(FoodDrinkType type) {
		this.type = type;
	}
  
    //Manager create FD
	public static boolean createFoodDrink(String itemID, String name, String size, String description, double price, FoodDrinkType type) {
	    File filename = new File("FoodDrink.txt");
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))){
	    	FoodDrink newFD = new FoodDrink(itemID, name, size, description, price, type);
	    	String oneLine = newFD.getID() + "|" +
                    newFD.getName() + "|" +
                    newFD.getSize() + "|" +
                    newFD.getDescription() + "|" +
                    newFD.getPrice() + "|" +
                    newFD.getType();
	        writer.write(oneLine);
	        writer.newLine();
	        return true;
	    }catch(IOException e) {
	    	e.printStackTrace();
	    	return false;
	    } 
	   
	    
	    
	}
	
	//Manager update FD
	public static boolean updateFoodDrink(String itemID, String name, String size, String description,
			  							  double price, FoodDrinkType type) {
		String filename = "FoodDrink.txt";
		ArrayList<FoodDrink> searchFDDB = new ArrayList<>();
		
		try (Scanner reader = new Scanner(new File(filename))) {
	        while (reader.hasNext()) {
	            String eachItem = reader.nextLine();
	            String[] itemArr = eachItem.split("\\|");
	            String readItemID = itemArr[0];
	            String readName = itemArr[1];
	            String readSize = itemArr[2];
	            String readDescription = itemArr[3];
	            String str_price = itemArr[4];
	            double readPrice = Double.parseDouble(str_price);
	            String str_fd = itemArr[5];
	            FoodDrinkType readType = FoodDrinkType.valueOf(str_fd);

	            FoodDrink readFD = new FoodDrink(readItemID, readName, readSize, readDescription,
	                    readPrice, readType);
	            searchFDDB.add(readFD);
	        }
	    } catch (IOException err) {
	        System.out.println(err);
	    }
		
		for(int i=0; i<searchFDDB.size();i++) {
		    	if(searchFDDB.get(i).getID().equals(itemID)) {
		    		searchFDDB.get(i).setName(name);
		    		searchFDDB.get(i).setSize(size);
		    		searchFDDB.get(i).setDescription(description);
		    		searchFDDB.get(i).setPrice(price);
		    		searchFDDB.get(i).setType(type);
		    	}
		    }
		 
		 try (PrintWriter writer = new PrintWriter("FoodDrink.txt")) {
			 searchFDDB.forEach(item -> {
		            String oneLine = item.getID() + "|" +
		                    item.getName() + "|" +
		                    item.getSize() + "|" +
		                    item.getDescription() + "|" +
		                    item.getPrice() + "|" +
		                    item.getType();
		            writer.println(oneLine);
		        });
		        return true;
		    } catch (FileNotFoundException e) {
		        System.out.println(e.getMessage());
		        return false;
		    }
	}
	
	//Manager delete FD
	public static boolean deleteFoodDrink(String itemID) {
	    List<FoodDrink> foodDrinkList = new ArrayList<>();

	    // Read file to list
	    try (Stream<String> stream = Files.lines(Paths.get("FoodDrink.txt"))) {
	        stream.forEach(line -> {
	            String[] itemArr = line.split("\\|");
	            String readItemID = itemArr[0];
	            String readName = itemArr[1];
	            String readSize = itemArr[2];
	            String readDescription = itemArr[3];
	            double readPrice = Double.parseDouble(itemArr[4]);
	            
	            FoodDrinkType readType = FoodDrinkType.valueOf(itemArr[5]);

	            FoodDrink readFD = new FoodDrink(readItemID, readName, readSize, readDescription,
	                     readPrice, readType);
	            foodDrinkList.add(readFD);
	        });
	    } catch (IOException e) {
	        System.out.println(e.getMessage());
	        return false;
	    }

	    // Remove item with given ID
	    boolean itemRemoved = foodDrinkList.removeIf(item -> item.getID().equals(itemID));

	    if (!itemRemoved) {
	        System.out.println("Item not found in list.");
	        return false;
	    }

	    // Write updated list to file
	    try (PrintWriter writer = new PrintWriter("FoodDrink.txt")) {
	        foodDrinkList.forEach(item -> {
	            String oneLine = item.getID() + "|" +
	                    item.getName() + "|" +
	                    item.getSize() + "|" +
	                    item.getDescription() + "|" +
	                    item.getPrice() + "|" +
	                    item.getType();
	            writer.println(oneLine);
	        });
	        return true;
	    } catch (FileNotFoundException e) {
	        System.out.println(e.getMessage());
	        return false;
	    }
	}


	//Manager searchFD & viewFD by name 
	public static FoodDrink searchFoodDrink(String name) {
		ArrayList<FoodDrink> searchFDDB = new ArrayList<>();
	    String filename = "FoodDrink.txt";

	    try (Scanner reader = new Scanner(new File(filename))) {
	        while (reader.hasNext()) {
	        
	            String eachItem = reader.nextLine();
	            String[] itemArr = eachItem.split("\\|");
	            String readItemID = itemArr[0];
	            String readName = itemArr[1];
	            String readSize = itemArr[2];
	            String readDescription = itemArr[3];
	            String str_price = itemArr[4];
	            double readPrice = Double.parseDouble(str_price);
	            String str_fd = itemArr[5];
	            FoodDrinkType readType = FoodDrinkType.valueOf(str_fd);

	            FoodDrink readFD = new FoodDrink(readItemID, readName, readSize, readDescription,
	                    readPrice, readType);
	            searchFDDB.add(readFD);
	        }
	    } catch (IOException err) {
	        System.out.println(err);
	    }
	    
	    for(int i=0; i<searchFDDB.size();i++) {
	    	if(searchFDDB.get(i).getName().equalsIgnoreCase(name)) {
	    		return searchFDDB.get(i);
	    	}
	    }
	    
	    return null;
	}
	
	

	public FoodDrink(String name, double price) {
		this.name = name;
		this.price = price; 
	}
	
	// Constructor overloading -> create Food Drink Object without image link
	public FoodDrink(String name, int quantity) {
		this.name = name; 
		this.quantity = quantity; 
	}
	
	public FoodDrink(String name, double price, String img) {
		this.name = name; 
		this.price = price; 
		this.img = img;
	}
	
	
	public String getImg() {
		return img; 
	}


	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "FoodAndDrink [name=" + name + ", quantity=" + quantity + "]";
	}
	
}//end of all 