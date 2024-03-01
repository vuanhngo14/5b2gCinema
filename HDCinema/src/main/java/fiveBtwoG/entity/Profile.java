package fiveBtwoG.entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Profile {
	private String profileID;
	private String name;
	private String status;
	
	public Profile() {
		this.profileID = "";
		this.name = "";
		this.status = "";
	}
	
	public Profile(String profileID, String name, String status) {
		this.profileID = profileID;
		this.name = name;
		this.status = status;
	}
	
	public void setID(String profileID) {
		this.profileID = profileID;
	}
	
	public String getID() {
		return this.profileID;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public static String getIDofName(String name) {
		if(name.equalsIgnoreCase("SystemAdmin")){
        	return "P001";
        }else if(name.equalsIgnoreCase("Manager")){
        	return "P002";
        }else if(name.equalsIgnoreCase("Staff")){
        	return "P003";
        }else if(name.equalsIgnoreCase("Customer")){
        	return "P004";
        }
		return "NIL";
	}
	
	public static String getStatusOfProf(String profileID) {
ArrayList<Profile> profDB = new ArrayList<>();
		
		Scanner reader = null;
        String filename = "profiles.txt";
        
        try{
            reader = new Scanner(new File(filename));
            while(reader.hasNext()){
                String oneItem = reader.nextLine();
                String[] itemArr = oneItem.split(", ");
                String profID = itemArr[0];
                String nameIn = itemArr[1];
                String statusIn = itemArr[2];
                Profile newProf = new Profile(profID, nameIn, statusIn);
                profDB.add(newProf);
            }
        }
        catch(FileNotFoundException err){
            System.out.println(err);
        }
        finally{
            if(reader != null){
                reader.close();
            }
        }
        
        for(int i=0;i<profDB.size();i++) {
        	if(profDB.get(i).getID().equals(profileID)) {
        		return profDB.get(i).getStatus();
        	}
        }
        return "NIL";
	}
	
	public static boolean createProfile(String profileID, String name) {
		ArrayList<Profile> profDB = new ArrayList<>();
		
		Scanner reader = null;
        String filename = "profiles.txt";
        
        try{
            reader = new Scanner(new File(filename));
            while(reader.hasNext()){
                String oneItem = reader.nextLine();
                String[] itemArr = oneItem.split(", ");
                String profID = itemArr[0];
                String nameIn = itemArr[1];
                String statusIn = itemArr[2];
                Profile newProf = new Profile(profID, nameIn, statusIn);
                profDB.add(newProf);
            }
        }
        catch(FileNotFoundException err){
            System.out.println(err);
        }
        finally{
            if(reader != null){
                reader.close();
            }
        }
        
        for(int i=0;i<profDB.size();i++) {
        	if(profDB.get(i).getID().equalsIgnoreCase(profileID)) {
        		return false;
        	}
        }
		
        Profile newProf = new Profile(profileID, name, "Available");
        profDB.add(newProf);
		
		String filenameOut = "profiles.txt";
        PrintWriter writer = null;
        try{
            writer = new PrintWriter(filenameOut);
            for(int i=0;i<profDB.size();i++){
                Profile temp = profDB.get(i);
                String oneLine = temp.getID() + ", " + temp.getName() + ", " + temp.getStatus();
                writer.println(oneLine);
            }
            writer.flush();
            return true;
        }
        catch(FileNotFoundException err){
            System.out.println(err);
        }
        finally{
            if(writer != null){
                writer.close();
            }
        }
        return false;
	}
	
	public static Profile viewProfile(String profileID) {
		ArrayList<Profile> profDB = new ArrayList<>();
		
		Scanner reader = null;
        String filename = "profiles.txt";
        
        try{
            reader = new Scanner(new File(filename));
            while(reader.hasNext()){
                String oneItem = reader.nextLine();
                String[] itemArr = oneItem.split(", ");
                String profID = itemArr[0];
                String nameIn = itemArr[1];
                String status = itemArr[2];
                Profile newProf = new Profile(profID, nameIn, status);
                profDB.add(newProf);
            }
        }
        catch(FileNotFoundException err){
            System.out.println(err);
        }
        finally{
            if(reader != null){
                reader.close();
            }
        }
        
        for(int i=0;i<profDB.size();i++) {
        	if(profDB.get(i).getID().equals(profileID)) {
        		return profDB.get(i);
        	}
        }
        
        return null;
	}
	
	public static boolean updateProfile(String profileID, String name, String status) {
		ArrayList<Profile> profDB = new ArrayList<>();
		
		Scanner reader = null;
        String filename = "profiles.txt";
        
        try{
            reader = new Scanner(new File(filename));
            while(reader.hasNext()){
                String oneItem = reader.nextLine();
                String[] itemArr = oneItem.split(", ");
                String profID = itemArr[0];
                String nameIn = itemArr[1];
                String statusIn = itemArr[2];
                Profile newProf = new Profile(profID, nameIn, statusIn);
                profDB.add(newProf);
            }
        }
        catch(FileNotFoundException err){
            System.out.println(err);
        }
        finally{
            if(reader != null){
                reader.close();
            }
        }
		
        for(int i=0;i<profDB.size();i++) {
        	if(profDB.get(i).getID().equalsIgnoreCase(profileID)) {
        		profDB.get(i).setName(name);
        		profDB.get(i).setStatus(status);
        		break;
        	}
        }
		
		String filenameOut = "profiles.txt";
        PrintWriter writer = null;
        try{
            writer = new PrintWriter(filenameOut);
            for(int i=0;i<profDB.size();i++){
                Profile temp = profDB.get(i);
                String oneLine = temp.getID() + ", " + temp.getName()+ ", "+temp.getStatus();
                writer.println(oneLine);
            }
            writer.flush();
            return true;
        }
        catch(FileNotFoundException err){
            System.out.println(err);
        }
        finally{
            if(writer != null){
                writer.close();
            }
        }
        return false;
	}
	
	public static boolean suspendProfile(String profileID) {
		ArrayList<Profile> profDB = new ArrayList<>();
		
		Scanner reader = null;
        String filename = "profiles.txt";
        
        try{
            reader = new Scanner(new File(filename));
            while(reader.hasNext()){
                String oneItem = reader.nextLine();
                String[] itemArr = oneItem.split(", ");
                String profID = itemArr[0];
                String nameIn = itemArr[1];
                String status = itemArr[2];
                Profile newProf = new Profile(profID, nameIn, status);
                profDB.add(newProf);
            }
        }
        catch(FileNotFoundException err){
            System.out.println(err);
        }
        finally{
            if(reader != null){
                reader.close();
            }
        }
		
        for(int i=0;i<profDB.size();i++) {
        	if(profDB.get(i).getID().equals(profileID)) {
        		if(profDB.get(i).getStatus().equalsIgnoreCase("Suspended")) {
        			return false;
        		}else {
        			profDB.get(i).setStatus("Suspended");
        		}
        		break;
        	}
        }
		
		String filenameOut = "profiles.txt";
        PrintWriter writer = null;
        try{
            writer = new PrintWriter(filenameOut);
            for(int i=0;i<profDB.size();i++){
                Profile temp = profDB.get(i);
                String oneLine = temp.getID() + ", " + temp.getName()+ ", "+temp.getStatus();
                writer.println(oneLine);
            }
            writer.flush();
            return true;
            
        }
        catch(FileNotFoundException err){
            System.out.println(err);
        }
        finally{
            if(writer != null){
                writer.close();
            }
        }
        return false;
	}
	
	public static Profile searchProfile(String name) {
		ArrayList<Profile> profDB = new ArrayList<>();
		
		Scanner reader = null;
        String filename = "profiles.txt";
        
        try{
            reader = new Scanner(new File(filename));
            while(reader.hasNext()){
                String oneItem = reader.nextLine();
                String[] itemArr = oneItem.split(", ");
                String profID = itemArr[0];
                String nameIn = itemArr[1];
                String status = itemArr[2];
                Profile newProf = new Profile(profID, nameIn, status);
                profDB.add(newProf);
            }
        }
        catch(FileNotFoundException err){
            System.out.println(err);
        }
        finally{
            if(reader != null){
                reader.close();
            }
        }
        
        for(int i=0;i<profDB.size();i++) {
        	if(profDB.get(i).getName().equals(name)) {
        		return profDB.get(i);
        	}
        }
        
        
        
        return null;
	}
	
	public static ArrayList<Profile> getProfileList(){
		ArrayList<Profile> profDB = new ArrayList<>();
		
		Scanner reader = null;
        String filename = "profiles.txt";
        
        try{
            reader = new Scanner(new File(filename));
            while(reader.hasNext()){
                String oneItem = reader.nextLine();
                String[] itemArr = oneItem.split(", ");
                String profID = itemArr[0];
                String nameIn = itemArr[1];
                String status = itemArr[2];
                Profile newProf = new Profile(profID, nameIn, status);
                profDB.add(newProf);
            }
        }
        catch(FileNotFoundException err){
            System.out.println(err);
        }
        finally{
            if(reader != null){
                reader.close();
            }
        }
        return profDB;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Profile) {
			Profile tempProf = (Profile)obj;
			if(this.name == tempProf.getName() && this.profileID == tempProf.getID()) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		String statement = "ProfileID :" + this.profileID+
						   "\nName      :" + this.name+
						   "\nStatus    :"+this.status;
		return statement;
	}
}
