package fiveBtwoG.entity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Report {
	private String reportID;
	private String date;//is the generated date
	private double totalRevenue;
	private String month;//to select month
	
	public Report() {
		this.reportID = "";
		this.date = "";
		this.totalRevenue = 0.0;
		this.month = "";
	}
	
	public Report(String reportID, String date, double totalRevenue, String month) {
		this.reportID = reportID;
		this.date = date;
		this.totalRevenue = totalRevenue;
		this.month = month;
	}
	
	public String getID() {
		return this.reportID;
	}
	
	public void setID(String reportID) {
		this.reportID = reportID;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public double getRevenue() {
		return this.totalRevenue;
	}
	
	public void setRevenue(double revenue) {
		this.totalRevenue = revenue;
	}
	
	public String getMonth() {
		return this.month;
	}
	
	public void setMonth(String month) {
		this.month = month;
	}
	

	//generate one id for report month/week
	public static String genMonthReportID() {
		int max = 1000;
		
		Random rand = new Random();
		int ranNum = rand.nextInt(max);
		
		String id = "RPT" + ranNum + "\n";
		return id;
	}
	
	public static Report generateReport(String month) {
		ArrayList<Report> reportList = new ArrayList<>();//to put final report
		//declare required file
		String FDDB = "foodDrinkOrder.txt";
		String TicketDB = "Ticket.txt";
		
		//declare date format to filter
				
		Scanner reader = null;
		ArrayList<String> filterFoodDrink = new ArrayList<>();//store filtered date & price
		ArrayList<String> filterTicket = new ArrayList<>();
		String monthNum ="";
		
		if(month.equalsIgnoreCase("january")) {
			monthNum = "01";
		}else if(month.equalsIgnoreCase("february")) {
			monthNum = "02";
		}else if(month.equalsIgnoreCase("march")) {
			monthNum = "03";
		}else if(month.equalsIgnoreCase("april")) {
			monthNum = "04";
		}else if(month.equalsIgnoreCase("may")) {
			monthNum = "05";
		}else if(month.equalsIgnoreCase("june")) {
			monthNum = "06";
		}else if(month.equalsIgnoreCase("july")) {
			monthNum = "07";
		}else if(month.equalsIgnoreCase("august")) {
			monthNum = "08";
		}else if(month.equalsIgnoreCase("september")) {
			monthNum = "09";
		}else if(month.equalsIgnoreCase("october")) {
			monthNum = "10";
		}else if(month.equalsIgnoreCase("november")) {
			monthNum = "11";
		}else if(month.equalsIgnoreCase("december")) {
			monthNum = "12";
		}
		
		//filter food drink month(filter ticket after this)
		try {
			reader = new Scanner(new File(FDDB));
			while(reader.hasNext()) {
				
				String eachItem = reader.nextLine();
				String[] itemArr = eachItem.split("\t");
				String readDate = itemArr[2];
				String str_readPrice = itemArr[4];
				String[] dateArr = readDate.split("-");
				String monthP = dateArr[1];
				if(monthP.equals(monthNum)) {
					filterFoodDrink.add(readDate + "\t" + str_readPrice);
				}
				
			}
		}catch(Exception err) {
			System.out.println(err);
		}finally{
			if(reader != null) {
				reader.close();
			}
		}
		
		//filter ticket month
		try {
			reader = new Scanner(new File(TicketDB));
			while(reader.hasNext()) {

				
				String eachItem = reader.nextLine();
				String[] itemArr = eachItem.split("\t");
				String readDate = itemArr[2];
				String str_readPrice = itemArr[6];
				String[] dateArr = readDate.split("-");
				String monthP = dateArr[1];
				if(monthP.equals(monthNum)) {
					filterTicket.add(readDate + "\t" + str_readPrice);
				}
			}
		}catch(Exception err) {
			System.out.println(err);
		}finally{
			if(reader != null) {
				reader.close();
			}
		}
		
		Double totalFoodDrink = 0.0;
		Double totalTicket = 0.0;
		
		for(int i=0; i<filterFoodDrink.size();i++) {
			String line = filterFoodDrink.get(i);
			String[] eachItem = line.split("\t");
			Double eachFoodDrink = Double.parseDouble(eachItem[1]);
			totalFoodDrink += eachFoodDrink;
		}
		
		for(int i=0; i<filterTicket.size();i++) {
			String line = filterTicket.get(i);
			String[] eachItem = line.split("\t");
			Double eachTicket = Double.parseDouble(eachItem[1]);
			totalTicket += eachTicket;
		}
		
		Double totalRevenue = totalFoodDrink + totalTicket;
		//generate current date
		LocalDate curDate = LocalDate.now();
		
		String id = genMonthReportID();
		String dateString = curDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		Report newReport = new Report(id, dateString, totalRevenue, month);
		reportList.add(newReport);
		
		for(int i=0; i<reportList.size();i++) {
			Report temp = reportList.get(i);
			if(temp.getMonth().equals(month)) {
				return temp;
			}
		}
		
		return null;
	}
	
}
