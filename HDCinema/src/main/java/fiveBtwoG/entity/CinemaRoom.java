package fiveBtwoG.entity;

import java.util.*;
import java.io.*;

public class CinemaRoom {
	private String cinemaRoomNum;
	private String cinemaRoomType;

	public CinemaRoom() {
		this.cinemaRoomNum = "";
		this.cinemaRoomType = "";
	}
	
	public CinemaRoom(String cinemaRoomNum,String cinemaRoomType) {
		this.cinemaRoomNum = cinemaRoomNum;
		this.cinemaRoomType = cinemaRoomType;
	}
	
	public String getCinemaRoomNum() {
		return this.cinemaRoomNum;
	}
	
	public void setCinemaRoomNum(String cinemaRoomNum) {
		this.cinemaRoomNum = cinemaRoomNum;
	}
	
	public String getCinemaRoomType() {
		return this.cinemaRoomType;
	}
	
	public void setCinemaRoomType(String cinemaRoomType) {
		this.cinemaRoomType = cinemaRoomType;
	}
//Manager create cinema room
	public static boolean createCinemaRoom(String cinemaRoomNum, String cinemaRoomType) {
		ArrayList<CinemaRoom> createCR = new ArrayList<>();
		Scanner reader = null;
		String filename = "CinemaRoom.txt";
		try {
			reader = new Scanner(new File(filename));
			while(reader.hasNext()) {
				String eachItem = reader.nextLine();
				String[] itemArr = eachItem.split(", ");
				String readCinemaRoomNum = itemArr[0];
				String readCinemaRoomType = itemArr[1];
				
				CinemaRoom readCR = new CinemaRoom(readCinemaRoomNum, readCinemaRoomType);
				createCR.add(readCR);
			}
		}catch(FileNotFoundException err) {
			System.out.println(err);
		}finally {
			if(reader != null) {
				reader.close();
			}
		}//end of finally
		CinemaRoom newCinema = new CinemaRoom(cinemaRoomNum, cinemaRoomType);
		createCR.add(newCinema);
		
		String filenameOut = "CinemaRoom.txt";
		PrintWriter writer= null;
		try {
			writer = new PrintWriter(filenameOut);
			for(int i=0; i<createCR.size(); i++) {
				CinemaRoom temp = createCR.get(i);
				String oneLine = temp.getCinemaRoomNum()+ ", "+
								 temp.getCinemaRoomType();
				writer.println(oneLine);
			}
			writer.flush();
			return true;
		}catch(FileNotFoundException err) {
			System.out.println(err);
		}finally {
			if(writer != null) {
				writer.close();
			}
		}
		return false;
	}
	
	//Manager view cinema room
	public static ArrayList<CinemaRoom> viewCinemaRoom(){
		Scanner reader = null;
		String filename = "CinemaRoom.txt";
		ArrayList<CinemaRoom> viewCR = new ArrayList<>();
		
		try {
			reader = new Scanner(new File(filename));
			if(reader.hasNext()) {
				String oneLine = reader.nextLine();
				String[] itemArr = oneLine.split(", ");
				String readCinRoomNum = itemArr[0];
				String readCinRoomType = itemArr[1];
				
				CinemaRoom readCR = new CinemaRoom(readCinRoomNum, readCinRoomType);
				viewCR.add(readCR);
			}
		}catch(FileNotFoundException err) {
			System.out.println(err);
		}finally {
			if(reader != null) {
				reader.close();
			}
		}
		return viewCR;
	}
	
	//Manager update cinema room
	public static boolean updateCinemaRoom(String cinemaRoomNum, String cinemaRoomType) {
		ArrayList<CinemaRoom> updateCR = new ArrayList<>();
		
		Scanner reader = null;
		String filename = "CinemaRoom.txt";
		try {
			reader = new Scanner(new File(filename));
			while(reader.hasNext()) {
				String eachItem = reader.nextLine();
				String[] itemArr = eachItem.split(", ");
				String readCinemaRoomNum = itemArr[0];
				String readCinemaRoomType = itemArr[1];
				
				CinemaRoom readCR = new CinemaRoom(readCinemaRoomNum, readCinemaRoomType);
				updateCR.add(readCR);
			}
		}catch(FileNotFoundException err) {
			System.out.println(err);
		}finally {
			if(reader != null) {
				reader.close();
			}
		}//end of finally
		Optional<CinemaRoom> opCR = updateCR.stream()
				.filter(cr -> cr.getCinemaRoomNum().equalsIgnoreCase(cinemaRoomNum))
				.findFirst();
		if(opCR.isPresent()) {
			CinemaRoom crToUpdate = opCR.get();
			crToUpdate.setCinemaRoomNum(cinemaRoomNum);
			crToUpdate.setCinemaRoomType(cinemaRoomType);	
		}
		
		String filenameOut = "CinemaRoom.txt";
		PrintWriter writer= null;
		try {
			writer = new PrintWriter(filenameOut);
			for(int i=0; i<updateCR.size(); i++) {
				CinemaRoom temp = updateCR.get(i);
				String oneLine = temp.getCinemaRoomNum()+ ", "+
								 temp.getCinemaRoomType();
				writer.println(oneLine);
			}
			writer.flush();
			return true;
		}catch(FileNotFoundException err) {
			System.out.println(err);
		}finally {
			if(writer != null) {
				writer.close();
			}
		}
		return false;
	}

	//Manager delete cinema room
	public static boolean deleteCinemaRoom(String cinemaRoomNum) {
		ArrayList<CinemaRoom> deleteCR = new ArrayList<>();
		Scanner reader = null;
		String filename = "CinemaRoom.txt";
		
		try {
			reader = new Scanner(new File(filename));
			while(reader.hasNext()) {
				String eachItem = reader.nextLine();
				String[] itemArr = eachItem.split(", ");
				String readCinemaRoomNum = itemArr[0];
				String readCinemaRoomType = itemArr[1];
				
				CinemaRoom readCR = new CinemaRoom(readCinemaRoomNum, readCinemaRoomType);
				deleteCR.add(readCR);
			}
		}catch(FileNotFoundException err) {
			System.out.println(err);
		}finally {
			if(reader != null) {
				reader.close();
			}
		}//end of finally
		
		//delete according to cinemaRoomNum
		for(int i=0; i<deleteCR.size(); i++) {
			CinemaRoom temp = deleteCR.get(i);
			if(temp.getCinemaRoomNum().equalsIgnoreCase(cinemaRoomNum)) {
				deleteCR.remove(i);
			}
		}
		
		//write back to file
		String filenameOut = "CinemaRoom.txt";
		PrintWriter writer= null;
		try {
			writer = new PrintWriter(filenameOut);
			for(int i=0; i<deleteCR.size(); i++) {
				CinemaRoom temp = deleteCR.get(i);
				String oneLine = temp.getCinemaRoomNum()+ ", "+
								 temp.getCinemaRoomType();
				writer.println(oneLine);
			}
			writer.flush();
			return true;
		}catch(FileNotFoundException err) {
			System.out.println(err);
		}finally {
			if(writer != null) {
				writer.close();
			}
		}
		return false;
	}
	//Manager search cinema room
	public static CinemaRoom searchCinemaRoom (String cinemaRoomNum) {
		ArrayList<CinemaRoom> searchCR = new ArrayList<>();
		Scanner reader = null;
		String filename = "CinemaRoom.txt";
		
		try {
			reader = new Scanner(new File(filename));
			while(reader.hasNext()) {
				String eachItem = reader.nextLine();
				String[] itemArr = eachItem.split(", ");
				String readCinemaRoomNum = itemArr[0];
				String readCinemaRoomType = itemArr[1];
				
				CinemaRoom readCR = new CinemaRoom(readCinemaRoomNum, readCinemaRoomType);
				searchCR.add(readCR);
			}
		}catch(FileNotFoundException err) {
			System.out.println(err);
		}finally {
			if(reader != null) {
				reader.close();
			}
		}//end of finally
		
		for(int i=0; i<searchCR.size(); i++) {
			CinemaRoom temp = searchCR.get(i);
			if(temp.getCinemaRoomNum().equalsIgnoreCase(cinemaRoomNum)) {
				return temp;
			}
		}
		return null;
	}
}


























