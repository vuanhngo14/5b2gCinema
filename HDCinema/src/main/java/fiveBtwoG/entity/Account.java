package fiveBtwoG.entity;

import java.util.ArrayList;
import java.text.SimpleDateFormat;  
import java.util.Date;   
import java.util.Scanner;
import java.io.*;

public class Account {
	private String accountID;
	private String name;
	private Profile profile;
	private String username;
	private String password;
	private String status;
	
	public Account() {
		this.accountID = "";
		this.name = "";
		this.profile = null;
		this.username = "";
		this.password = "";
		this.status = "";
	}
	
	public Account(String accountID, String name, Profile profile, String username, String password, String status) {
		this.accountID = accountID;
		this.name = name;
		this.profile = profile;
		this.username = username;
		this.password = password;
		this.status = status;
	}

	public String getID() {
		return this.accountID;
		
	}
	
	public void setID (String accountID){
		this.accountID = accountID;
	}

	public String getName() {
		return this.name;
	}
	
	public void setName (String name){
		this.name = name;
	}

	public Profile getProfile() {
		return this.profile;
	}
	
	public void setProfile (Profile profile){
		this.profile = profile;
	}

	public String getUsername() {
		return this.username;
	}
	
	public void setUsername (String username){
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}
	
	public void setPassword (String password){
		this.password = password;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public static boolean login( String username, String password, String role) {
		ArrayList<Account> accDB = new ArrayList<>();
		
		Scanner reader = null;
        String filename = "accounts.txt";
        
        try{
            reader = new Scanner(new File(filename));
            while(reader.hasNext()){
                String oneItem = reader.nextLine();
                String[] itemArr = oneItem.split(", ");
                String accID = itemArr[0];
                String name = itemArr[1];
                String profID = Profile.getIDofName(itemArr[2]);
                Profile prof = new Profile(profID, itemArr[2], Profile.getStatusOfProf(profID));
                String usernameDB = itemArr[3];
                String passwordDB = itemArr[4];
                String status = itemArr[5];
                Account newAcc = new Account(accID, name, prof, usernameDB, passwordDB, status);
                accDB.add(newAcc);
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
		
		for(int i=0;i<accDB.size();i++) {
			if(accDB.get(i).getUsername().equalsIgnoreCase(username)) {
				if(accDB.get(i).getPassword().equalsIgnoreCase(password)) {
					if(accDB.get(i).getProfile().getName().equalsIgnoreCase(role)) {
						if(accDB.get(i).getStatus().equalsIgnoreCase("available")){
							return true;
						}
					}
					
				}
			}
		}
		return false;
	}
	
	public static Account viewAcc( String accountID) {
		ArrayList<Account> accDB = new ArrayList<>();
		
		Scanner reader = null;
        String filename = "accounts.txt";
        
        try{
            reader = new Scanner(new File(filename));
            while(reader.hasNext()){
                String oneItem = reader.nextLine();
                String[] itemArr = oneItem.split(", ");
                String accID = itemArr[0];
                String name = itemArr[1];
                String profID = Profile.getIDofName(itemArr[2]);
                Profile prof = new Profile(profID, itemArr[2], Profile.getStatusOfProf(profID));
                String usernameDB = itemArr[3];
                String passwordDB = itemArr[4];
                String status = itemArr[5];
                Account newAcc = new Account(accID, name, prof, usernameDB, passwordDB, status);
                accDB.add(newAcc);
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
		
		for(int i=0;i<accDB.size();i++) {
			if(accDB.get(i).getID().equalsIgnoreCase(accountID)) {
				return accDB.get(i);
			}
		}
		return null;
	}
	
	
	public static boolean createAccount(String accountID, String name, String profileType, String username, String password) {
		ArrayList<Account> accDB = new ArrayList<>();
		
		Scanner reader = null;
        String filename = "accounts.txt";
        
        try{
            reader = new Scanner(new File(filename));
            while(reader.hasNext()){
                String oneItem = reader.nextLine();
                String[] itemArr = oneItem.split(", ");
                String accID = itemArr[0];
                String nameIn = itemArr[1];
                String profID = Profile.getIDofName(itemArr[2]);
                Profile prof = new Profile(profID, itemArr[2], Profile.getStatusOfProf(profID));
                String usernameDB = itemArr[3];
                String passwordDB = itemArr[4];
                String status = itemArr[5];
                Account newAcc = new Account(accID, nameIn, prof, usernameDB, passwordDB, status);
                accDB.add(newAcc);
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
        
        String profID = Profile.getIDofName(profileType);
        Profile profile = new Profile(profID, profileType, Profile.getStatusOfProf(profID));
		
        
        for(int i=0;i<accDB.size();i++) {
        	if(accDB.get(i).getID().equalsIgnoreCase(accountID) || accDB.get(i).getUsername().equalsIgnoreCase(username)) {
        		return false;
        	}
        }
        Account newAcc = new Account(accountID, name, profile, username, password, "Available");
        accDB.add(newAcc);
		
		String filenameOut = "accounts.txt";
        PrintWriter writer = null;
        try{
            writer = new PrintWriter(filenameOut);
            for(int i=0;i<accDB.size();i++){
                Account temp = accDB.get(i);
                String oneLine = temp.getID() + ", " + temp.getName() + ", " + temp.getProfile().getName() + ", " + temp.getUsername() + ", " + temp.getPassword() + ", "+temp.getStatus();
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
	
	public static boolean updateAccount(String accountID, String name, String profileType, String username, String password, String status) {
		ArrayList<Account> accDB = new ArrayList<>();
		
		Scanner reader = null;
        String filename = "accounts.txt";
        
        try{
            reader = new Scanner(new File(filename));
            while(reader.hasNext()){
                String oneItem = reader.nextLine();
                String[] itemArr = oneItem.split(", ");
                String accID = itemArr[0];
                String nameIn = itemArr[1];
                String profID = Profile.getIDofName(itemArr[2]);
                Profile prof = new Profile(profID, itemArr[2], Profile.getStatusOfProf(profID));
                String usernameDB = itemArr[3];
                String passwordDB = itemArr[4];
                String statusIn = itemArr[5];
                Account newAcc = new Account(accID, nameIn, prof, usernameDB, passwordDB, statusIn);
                accDB.add(newAcc);
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
        
        String profID = Profile.getIDofName(profileType);
        Profile profile = new Profile(profID, profileType, Profile.getStatusOfProf(profID));
        
		
        Account acc = new Account(accountID, name, profile, username, password, status);
        boolean exist = false;
        for(int i=0;i<accDB.size();i++) {
        	if(acc.getID().equals(accDB.get(i).getID())) {
        		exist =true;
        		break;
        	}
        }
        
        if(!exist) {
        	return false;
        }
        
        for(int i=0;i<accDB.size();i++) {
        	if(acc.getID().equals(accDB.get(i).getID())) {
        		accDB.get(i).setName(name);
        		String tempProfID = Profile.getIDofName(profileType);
                Profile tempProfile = new Profile(tempProfID, profileType, Profile.getStatusOfProf(profID));
        		accDB.get(i).setProfile(tempProfile);
        		for(int a=0;a<accDB.size();a++) {
        			if(accDB.get(a).getName().equalsIgnoreCase(username)&&(a!=i)) {
        				return false;
        			}
        		}
        		accDB.get(i).setUsername(username);
        		accDB.get(i).setPassword(password);
        		accDB.get(i).setStatus(status);
        	}
        }
		
		String filenameOut = "accounts.txt";
        PrintWriter writer = null;
        try{
            writer = new PrintWriter(filenameOut);
            for(int i=0;i<accDB.size();i++){
                Account temp = accDB.get(i);
                String oneLine = temp.getID() + ", " + temp.getName() + ", " + temp.getProfile().getName() + ", " + temp.getUsername() + ", " + temp.getPassword() + ", "+temp.getStatus();
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
	
	public static boolean suspendAccount(String accountID) {
		ArrayList<Account> accDB = new ArrayList<>();
		
		Scanner reader = null;
        String filename = "accounts.txt";
        
        try{
            reader = new Scanner(new File(filename));
            while(reader.hasNext()){
                String oneItem = reader.nextLine();
                String[] itemArr = oneItem.split(", ");
                String accID = itemArr[0];
                String nameIn = itemArr[1];
                String profID = Profile.getIDofName(itemArr[2]);
                Profile prof = new Profile(profID, itemArr[2], Profile.getStatusOfProf(profID));
                String usernameDB = itemArr[3];
                String passwordDB = itemArr[4];
                String status = itemArr[5];
                Account newAcc = new Account(accID, nameIn, prof, usernameDB, passwordDB, status);
                accDB.add(newAcc);
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
        

        for(int i=0;i<accDB.size();i++) {
        	if(accDB.get(i).getID().equals(accountID)) {
        		if(accDB.get(i).getStatus().equals("Suspended")) {
        			return false;
        		}
        		accDB.get(i).setStatus("Suspended");
        		break;
        	}
        }
		
		String filenameOut = "accounts.txt";
        PrintWriter writer = null;
        try{
            writer = new PrintWriter(filenameOut);
            for(int i=0;i<accDB.size();i++){
                Account temp = accDB.get(i);
                String oneLine = temp.getID() + ", " + temp.getName() + ", " + temp.getProfile().getName() + ", " + temp.getUsername() + ", " + temp.getPassword() + ", "+temp.getStatus();
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
	
	public static Account searchAcc( String username) {
		ArrayList<Account> accDB = new ArrayList<>();
		
		Scanner reader = null;
        String filename = "accounts.txt";
        
        try{
            reader = new Scanner(new File(filename));
            while(reader.hasNext()){
                String oneItem = reader.nextLine();
                String[] itemArr = oneItem.split(", ");
                String accID = itemArr[0];
                String nameIn = itemArr[1];
                String profID = Profile.getIDofName(itemArr[2]);
                Profile prof = new Profile(profID, itemArr[2], Profile.getStatusOfProf(profID));
                String usernameDB = itemArr[3];
                String passwordDB = itemArr[4];
                String status = itemArr[5];
                Account newAcc = new Account(accID, nameIn, prof, usernameDB, passwordDB, status);
                accDB.add(newAcc);
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
		
		for(int i=0;i<accDB.size();i++) {
			if(accDB.get(i).getUsername().equalsIgnoreCase(username)) {
				return accDB.get(i);
				
			}
		}
		return null;
	}
	
	public static boolean logout(String accountID) {
		ArrayList<String> accIDs = new ArrayList<>();
		ArrayList<String> logoutTimes = new ArrayList<>();
		
		Scanner reader = null;
        String filename = "logouts.txt";
        
        try{
            reader = new Scanner(new File(filename));
            while(reader.hasNext()){
                String oneItem = reader.nextLine();
                String[] itemArr = oneItem.split(", ");
                String accID = itemArr[0];
                String logoutTime = itemArr[1];
                accIDs.add(accID);
                logoutTimes.add(logoutTime);
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
        
        accIDs.add(accountID);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
        Date date = new Date();  
        String dateTime = formatter.format(date); 
        logoutTimes.add(dateTime);
		
		String filenameOut = "logouts.txt";
        PrintWriter writer = null;
        try{
            writer = new PrintWriter(filenameOut);
            for(int i=0;i<accIDs.size();i++){
                String oneLine = accIDs.get(i) + ", " + logoutTimes.get(i);
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
	
	public static ArrayList<Account> getAccountList() {
		ArrayList<Account> accDB = new ArrayList<>();
		
		Scanner reader = null;
        String filename = "accounts.txt";
        
        try{
            reader = new Scanner(new File(filename));
            while(reader.hasNext()){
                String oneItem = reader.nextLine();
                String[] itemArr = oneItem.split(", ");
                String accID = itemArr[0];
                String nameIn = itemArr[1];
                String profID = Profile.getIDofName(itemArr[2]);
                Profile prof = new Profile(profID, itemArr[2], Profile.getStatusOfProf(profID));
                String usernameDB = itemArr[3];
                String passwordDB = itemArr[4];
                String status = itemArr[5];
                Account newAcc = new Account(accID, nameIn, prof, usernameDB, passwordDB, status);
                accDB.add(newAcc);
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
        return accDB;
	}
	
	public static String getIDofUsername(String username) {
		ArrayList<Account> accDB = new ArrayList<>();
		
		Scanner reader = null;
        String filename = "accounts.txt";
        
        try{
            reader = new Scanner(new File(filename));
            while(reader.hasNext()){
                String oneItem = reader.nextLine();
                String[] itemArr = oneItem.split(", ");
                String accID = itemArr[0];
                String nameIn = itemArr[1];
                String profID = Profile.getIDofName(itemArr[2]);
                Profile prof = new Profile(profID, itemArr[2], Profile.getStatusOfProf(profID));
                String usernameDB = itemArr[3];
                String passwordDB = itemArr[4];
                String status = itemArr[5];
                Account newAcc = new Account(accID, nameIn, prof, usernameDB, passwordDB, status);
                accDB.add(newAcc);
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
        for(int i=0;i<accDB.size();i++) {
        	if(accDB.get(i).getUsername().equalsIgnoreCase(username)) {
        		return accDB.get(i).getID();
        	}
        }
        return null;
	}

	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Account) {
			Account tempAcc = (Account)obj;
			if(this.accountID == tempAcc.getID() && this.name == tempAcc.getName() && this.username == tempAcc.getUsername() && this.password == tempAcc.getPassword() && this.profile.equals(tempAcc.getProfile())) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		String returnStatement ="AccountID :" + this.accountID+
								"\nName      :" + this.name+
								"\nType      :" + this.profile.getName()+
								"\nUsername  :" + this.username+
								"\nStatus    :" + this.status;
		return returnStatement;
	}
}
