package fiveBtwoG.entity;

import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;


public class ReviewAndRating {
	private String accID;
	private String movieID;
	private String review;
	private double rating;
	
	public ReviewAndRating() {
		this.accID = "";
		this.movieID = "";
		this.review = "";
		this.rating = 0;
	}
	
	public ReviewAndRating(String accID, String movieID, String review, double rating) {
		this.accID = accID;
		this.movieID = movieID;
		this.review = review;
		this.rating = rating;
	}
	
	public String getAccID() {
		return this.accID;
	}
	
	public void setAccID(String accID) {
		this.accID = accID;
	}
	
	public String getMovieID() {
		return this.movieID;
	}
	
	public void setMovieID(String movieID) {
		this.movieID = movieID;
	}
	
	public String getReview() {
		return this.review;
	}
	
	public void setReview(String review) {
		this.review = review;
	}
	
	public double getRating() {
		return this.rating;
	}
	
	public void setRating(double rating) {
		this.rating = rating;
	}
	
	public static ArrayList<ReviewAndRating> getReviewAndRatingList(){
		ArrayList<ReviewAndRating> rArList = new ArrayList<>();
		Scanner reader = null;
		String filename = "reviewArating.txt";
		
		try {
			reader = new Scanner (new File(filename));
			
			// read the file row by row
			while (reader.hasNext()) {
				// each row of movie's detail
				String oneItem = reader.nextLine();
				String[] itemArr = oneItem.split("\\|");
				String accID = itemArr[0];
				String movieID = itemArr[1];
				String review = itemArr[2];
				
				String str_rating = itemArr[3];
				double rating = Double.parseDouble(str_rating);
				
				// movie obj
				ReviewAndRating rArMovie = new ReviewAndRating(accID, movieID, review, rating);
				rArList.add(rArMovie);
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
		
		return rArList;
	}
	
	
	public static boolean submitRaR(String accID, String movieID, String review, double rating, String file) {
		String filenameOut = file;
		PrintWriter writer = null;
		boolean check = false;
				if(accID.isEmpty() || accID==null || movieID.isEmpty() || movieID==null || review.isEmpty() || review==null || rating== Double.NaN) {
					check = false;
		}
		else {
			try {
				writer = new PrintWriter(new FileWriter(filenameOut, true));
				writer.println(accID + "|" + movieID + "|" + review + "|" + rating);
				// Write the updated content back to the file
		        writer.flush();

		        check = true;
			}
			catch(FileNotFoundException err){
				System.out.println(err);
			}
			catch (IOException e) {
	            System.out.println("An error occurred while writing to the file: " + e.getMessage());
	            e.printStackTrace();
	        }
			finally {
				if(writer != null){
	                writer.close();
	            }
			}
		}

		return check;

	}
	
	
	
	@Override
	public String toString() {
		return "Review: " + getReview()+ "Rating: "+ getRating();
	}
}
