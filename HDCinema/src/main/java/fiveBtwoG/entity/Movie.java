package fiveBtwoG.entity;

import java.util.*;
import java.io.*;

public class Movie {
	private String movieID;
	private String movieName;
	private String PGRating;//double
	private String movieGenre;
	private String releaseDate;
	private String language;
	private String cast;
	private String director;
	private String synopsis;
	private String duration;
	private double avgRating;
	//need to access review n rating
	
	private String id;
    private String title;
    private String category; 
    private double rating; 
    private double price;
    private String img; 
    private ArrayList<String> date; 
    private ArrayList<String> time; 
	
	public Movie() {
		this.movieID = "";
		this.movieName = "";
		this.PGRating = "";
		this.movieGenre = "";
		this.releaseDate = "";
		this.language = "";
		this.cast = "";
		this.director = "";
		this.synopsis = "";
		this.duration = "";
		this.avgRating = 0;
	}
	
	public Movie(String movieID, String movieName, String PGRating, String movieGenre, 
				 String releaseDate, String language, String cast, String director,
				 String synopsis, String duration, double avgRating) {
		this.movieID = movieID;
		this.movieName = movieName;
		this.PGRating = PGRating;
		this.movieGenre = movieGenre;
		this.releaseDate = releaseDate;
		this.language = language;
		this.cast = cast;
		this.director = director;
		this.synopsis = synopsis;
		this.duration = duration;
		this.avgRating = avgRating;
	}
	
	public String getID() {
		return this.movieID;
	}
	
	public void setID(String movieID) {
		this.movieID = movieID;
	}
	
	public String getName() {
		return this.movieName;
	}
	
	public void setName(String movieName) {
		this.movieName = movieName;
	}
	
	public String getPGRating() {
		return this.PGRating;
	}
	
	public void setPGRating(String PGRating) {
		this.PGRating = PGRating;
	}
	
	public String getGenre() {
		return this.movieGenre;
	}
	
	public void setGenre(String movieGenre) {
		this.movieGenre = movieGenre;
	}
	
	public String getReleaseDate() {
		return this.releaseDate;
	}
	
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	public String getLanguage() {
		return this.language;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public String getCast() {
		return this.cast;
	}
	
	public void setCast(String cast) {
		this.cast = cast;
	}
	
	public String getDirector() {
		return this.director;
	}
	
	public void setDirector(String director) {
		this.director = director;
	}
	
	public String getSynopsis() {
		return this.synopsis;
	}
	
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	
	public String getDuration() {
		return this.duration;
	}
	
	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	public double getAvgRating() {
		return this.avgRating;
	}
	
	public void setAvgRating(double avgRating) {
		this.avgRating = avgRating;
	}
	
	//Manager create movie
	public static boolean createMovie(String movieID, String movieName, String PGRating, String movieGenre, 
									 String releaseDate, String language, String cast, String director,
									 String synopsis, String duration, double avgRating) {
		ArrayList<Movie> movieDB = new ArrayList<>(); 
		Scanner reader = null;
		String filename = "Movie.txt";
		
		try {
			reader = new Scanner(new File(filename));
            while(reader.hasNext()){
                String eachItem = reader.nextLine();
                String[] itemArr = eachItem.split("\\|");
                String readMovieID = itemArr[0];
                String readName = itemArr[1];
                String readPGRating = itemArr[2];
                String readMovieGenre = itemArr[3];
                String readReleaseDate = itemArr[4];
                String readLanguage = itemArr[5];
                String readCast = itemArr[6];
                String readDirector = itemArr[7];
                String readSynopsis = itemArr[8];
                String readDuration = itemArr[9];
                String str_avgRating = itemArr[10];
                double readAvgRating = Double.parseDouble(str_avgRating);
                
                Movie readMovie = new Movie(readMovieID, readName, readPGRating,readMovieGenre,
                							readReleaseDate ,readLanguage,readCast,readDirector,
                							readSynopsis,readDuration, readAvgRating);
                movieDB.add(readMovie);
            }
        }catch(IOException err){
            System.out.println(err);
        }finally{
	        if(reader != null){
	            reader.close();
	        }
        }//end of finally
		
		
		//create new one
		Movie newMovie = new Movie(movieID, movieName, PGRating, movieGenre, releaseDate,
								   language, cast, director, synopsis, duration, avgRating);
		movieDB.add(newMovie);
		
		String filenameOut = "Movie.txt";
        PrintWriter writer = null;
	        try{
	            writer = new PrintWriter(filenameOut);
	            for(int i=0;i<movieDB.size();i++){
	                Movie temp = movieDB.get(i);
	                String oneLine = temp.getID() + "|" + 
	                				 temp.getName()+ "|" + 
	                				 temp.getPGRating()+ "|" + 
	                				 temp.getGenre()+ "|" + 
	                				 temp.getReleaseDate()+ "|" + 
	                				 temp.getLanguage() + "|" + 
	                				 temp.getCast()+ "|" + 
	                				 temp.getDirector()+ "|" + 
	                				 temp.getSynopsis()+ "|" +
	                				 temp.getDuration()+ "|" + 
	                				 temp.getAvgRating();
	                writer.println(oneLine);
	                //System.out.println(oneLine);
	            }
	            writer.flush();
	            //return true;
	        }catch(FileNotFoundException err){
	            System.out.println(err);
	        }finally{
	            if(writer != null){
	                writer.close();
	            }
	        }
	        
	        String file_movieTicket = "movieBasic.txt"; 
            ArrayList<Movie> movieList = new ArrayList<>();

	        try {
	            BufferedReader reader1 = new BufferedReader(new FileReader(file_movieTicket));
	            String line;
	            

	            while ((line = reader1.readLine()) != null) {
	                String[] tokens = line.split("\t");
	                String id = tokens[0];
	                String movie_title = tokens[1];
	                String category = tokens[2]; 
	                double rating = Double.parseDouble(tokens[3]); 
	                double price = Double.parseDouble(tokens[4]); 
	                String img = tokens[5];
	                
	                // Have movie id, from id get session 
	                
	                Movie movie = new Movie(id, movie_title, category, rating, price, img, null, null); 
 
	                movieList.add(movie); // Generate the list of movie
	      
	                
	            }
	           
	            reader1.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	        Movie newMovie1 = new Movie(movieID, movieName, movieGenre, avgRating, 15,  "NIL", null, null);
	        movieList.add(newMovie1);
	        
	        String filenameOut1 = "movieBasic.txt";
	        PrintWriter writer1 = null;
		        try{
		            writer1 = new PrintWriter(filenameOut1);
		            for(int i=0;i<movieList.size();i++){
		                Movie temp = movieList.get(i);
		                String oneLine = temp.getId() + "\t" + 
		                				 temp.getTitle()+ "\t" + 
		                				 temp.getCategory()+ "\t" + 
		                				 temp.getRating()+ "\t" + 
		                				 temp.getPrice()+ "\t" + 
		                				 temp.getImg();
		                writer1.println(oneLine);
		                //System.out.println(oneLine);
		            }
		            writer1.flush();
		            return true;
		        }catch(FileNotFoundException err){
		            System.out.println(err);
		        }finally{
		            if(writer1 != null){
		                writer1.close();
		            }
		        }
			
	       return false;
	}
	
	//Manager update movie
	public static boolean updateMovie(String movieID, String movieName, String PGRating, String movieGenre, 
									  String releaseDate, String language, String cast, String director,
								      String synopsis, String duration, double avgRating) {
		
		
		ArrayList<Movie> updateMovie = new ArrayList<>();
		if(movieID.equalsIgnoreCase("A001")||movieID.equalsIgnoreCase("A002")||movieID.equalsIgnoreCase("A003")||movieID.equalsIgnoreCase("A004")||movieID.equalsIgnoreCase("A005")||movieID.equalsIgnoreCase("A006")||movieID.equalsIgnoreCase("A007")||movieID.equalsIgnoreCase("A008")) {
			return true;
		}
		
		Scanner reader = null;
		String filename = "Movie.txt";
		try{
            reader = new Scanner(new File(filename));
            while(reader.hasNext()){
                String eachItem = reader.nextLine();
                String[] itemArr = eachItem.split("\\|");
                String readMovieID = itemArr[0];
                String readName = itemArr[1];
                String readPGRating = itemArr[2];
                String readMovieGenre = itemArr[3];
                String readReleaseData = itemArr[4];
                String readLanguage = itemArr[5];
                String readCast = itemArr[6];
                String readDirector = itemArr[7];
                String readSynopsis = itemArr[8];
                String readDuration = itemArr[9];
                String str_avgRating = itemArr[10];
                double readAvgRating = Double.parseDouble(str_avgRating);
                
                Movie readMovie = new Movie(readMovieID, readName, readPGRating,readMovieGenre,
                							readReleaseData ,readLanguage,readCast,readDirector,
                							readSynopsis,readDuration, readAvgRating);
                updateMovie.add(readMovie);
            }
        }catch(IOException err){
            System.out.println(err);
        }finally{
	        if(reader != null){
	            reader.close();
	        }
        }//end of finally

		Optional<Movie> opMov = updateMovie.stream()
								.filter(item -> item.getID().equalsIgnoreCase(movieID))
								.findFirst();
		if(opMov.isPresent()) {
			Movie movToUpdate = opMov.get();
			movToUpdate.setName(movieName);
			movToUpdate.setPGRating(PGRating);
			movToUpdate.setGenre(movieGenre);
			movToUpdate.setReleaseDate(releaseDate);
			movToUpdate.setLanguage(language);
			movToUpdate.setCast(cast);
			movToUpdate.setDirector(director);
			movToUpdate.setDuration(duration);
			movToUpdate.setAvgRating(avgRating);
			movToUpdate.setSynopsis(synopsis);
		}
		
		String filenameOut = "Movie.txt";
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(filenameOut);
			for(int i=0; i<updateMovie.size(); i++) {
				Movie temp = updateMovie.get(i);
                String oneLine = temp.getID() + "|" + 
			       				 temp.getName()+ "|" + 
			       				 temp.getPGRating()+ "|" + 
			       				 temp.getGenre()+ "|" + 
			       				 temp.getReleaseDate()+ "|" + 
			       				 temp.getLanguage() + "|" + 
			       				 temp.getCast()+ "|" + 
			       				 temp.getDirector()+ "|" + 
			       				 temp.getSynopsis()+ "|" +
			       				 temp.getDuration()+ "|" + 
			       				 temp.getAvgRating();
				writer.println(oneLine);
			}
			writer.flush();
			//return true;
		}catch(FileNotFoundException err) {
			System.out.println(err);
		}finally {
			if(writer != null) {
				writer.close();
			}
		}
		
		if(movieID.equalsIgnoreCase("MV001")||movieID.equalsIgnoreCase("MV002")||movieID.equalsIgnoreCase("MV003")||movieID.equalsIgnoreCase("MV004")||movieID.equalsIgnoreCase("MV005")||movieID.equalsIgnoreCase("MV006")||movieID.equalsIgnoreCase("MV007")||movieID.equalsIgnoreCase("MV008")||movieID.equalsIgnoreCase("MV009")||movieID.equalsIgnoreCase("MV0010")) {
			return true;
		}
		

		String file_movieTicket = "movieBasic.txt"; 
        ArrayList<Movie> movieList = new ArrayList<>();

        try {
            BufferedReader reader1 = new BufferedReader(new FileReader(file_movieTicket));
            String line;
            

            while ((line = reader1.readLine()) != null) {
                String[] tokens = line.split("\t");
                String id = tokens[0];
                String movie_title = tokens[1];
                String category = tokens[2]; 
                double rating = Double.parseDouble(tokens[3]); 
                double price = Double.parseDouble(tokens[4]); 
                String img = tokens[5];
                
                // Have movie id, from id get session 
                
                Movie movie = new Movie(id, movie_title, category, rating, price, img, null, null); 

                movieList.add(movie); // Generate the list of movie
      
                
            }
           
            reader1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Optional<Movie> opMov1 = movieList.stream()
				.filter(item -> item.getId().equalsIgnoreCase(movieID))
				.findFirst();
        
        System.out.println(opMov1.isPresent());
        if(opMov1.isPresent()) {
			Movie movToUpdate = opMov1.get();
			movToUpdate.setTitle(movieName);
			movToUpdate.setCategory(movieGenre);
			movToUpdate.setRating(avgRating);
		}
        
        String filenameOut1 = "movieBasic.txt";
        PrintWriter writer1 = null;
	        try{
	            writer1 = new PrintWriter(filenameOut1);
	            for(int i=0;i<movieList.size();i++){
	                Movie temp = movieList.get(i);
	                String oneLine = temp.getId() + "\t" + 
	                				 temp.getTitle()+ "\t" + 
	                				 temp.getCategory()+ "\t" + 
	                				 temp.getRating()+ "\t" + 
	                				 temp.getPrice()+ "\t" + 
	                				 temp.getImg();
	                writer1.println(oneLine);
	                //System.out.println(oneLine);
	            }
	            writer1.flush();
	            return true;
	        }catch(FileNotFoundException err){
	            System.out.println(err);
	        }finally{
	            if(writer1 != null){
	                writer1.close();
	            }
	        }
		
		return false;
	}

	//Manager delete movie
	public static boolean deleteMovie(String movieID) {
		
		if(movieID.equalsIgnoreCase("A001")||movieID.equalsIgnoreCase("A002")||movieID.equalsIgnoreCase("A003")||movieID.equalsIgnoreCase("A004")||movieID.equalsIgnoreCase("A005")||movieID.equalsIgnoreCase("A006")||movieID.equalsIgnoreCase("A007")||movieID.equalsIgnoreCase("A008")) {
			return true;
		}
		ArrayList<Movie> deleteMovieDB = new ArrayList<>();
		Scanner reader = null;
		String filename = "Movie.txt";
		
		//read file to arraylist
		try {
			reader = new Scanner(new File(filename));
			while(reader.hasNext()){
                String eachItem = reader.nextLine();
                String[] itemArr = eachItem.split("\\|");
                String readMovieID = itemArr[0];
                String readName = itemArr[1];
                String readPGRating = itemArr[2];
                String readMovieGenre = itemArr[3];
                String readReleaseData = itemArr[4];
                String readLanguage = itemArr[5];
                String readCast = itemArr[6];
                String readDirector = itemArr[7];
                String readSynopsis = itemArr[8];
                String readDuration = itemArr[9];
                String str_avgRating = itemArr[10];
                double readAvgRating = Double.parseDouble(str_avgRating);
                
                Movie readMovie = new Movie(readMovieID, readName, readPGRating,readMovieGenre,
                							readReleaseData ,readLanguage,readCast,readDirector,
                							readSynopsis,readDuration, readAvgRating);
                deleteMovieDB.add(readMovie);
            }
		}catch(IOException err){
            System.out.println(err);
        }finally{
	        if(reader != null){
	            reader.close();
	        }
        }//end of finally
		
		//delete itemID
		for(int i=0; i<deleteMovieDB.size(); i++) {
        	Movie temp1 = deleteMovieDB.get(i);
			if(temp1.getID().equals(movieID)) {
				deleteMovieDB.remove(i);
			}
		}
		
		//write arraylist to textfile
		String filenameOut = "Movie.txt";
        PrintWriter writer = null;
	        try{
	            writer = new PrintWriter(filenameOut);
	            for(int i=0;i<deleteMovieDB.size();i++){
	                Movie temp = deleteMovieDB.get(i);
	                String oneLine = temp.getID() + "|" + 
			           				 temp.getName()+ "|" + 
			           				 temp.getPGRating()+ "|" + 
			           				 temp.getGenre()+ "|" + 
			           				 temp.getReleaseDate()+ "|" + 
			           				 temp.getLanguage() + "|" + 
			           				 temp.getCast()+ "|" + 
			           				 temp.getDirector()+ "|" + 
			           				 temp.getSynopsis()+ "|" + 
			           				 temp.getDuration()+ "|" + 
			           				 temp.getAvgRating();
	   				writer.println(oneLine);
	                //System.out.println(oneLine);
	            }
	            writer.flush();
	            //return true;
	        }catch(FileNotFoundException err){
	            System.out.println(err);
	        }finally{
	            if(writer != null){
	                writer.close();
	            }
	        }
	        
	        
	        

			String file_movieTicket = "movieBasic.txt"; 
	        ArrayList<Movie> movieList = new ArrayList<>();

	        try {
	            BufferedReader reader1 = new BufferedReader(new FileReader(file_movieTicket));
	            String line;
	            

	            while ((line = reader1.readLine()) != null) {
	                String[] tokens = line.split("\t");
	                String id = tokens[0];
	                String movie_title = tokens[1];
	                String category = tokens[2]; 
	                double rating = Double.parseDouble(tokens[3]); 
	                double price = Double.parseDouble(tokens[4]); 
	                String img = tokens[5];
	                
	                // Have movie id, from id get session 
	                
	                Movie movie = new Movie(id, movie_title, category, rating, price, img, null, null); 

	                movieList.add(movie); // Generate the list of movie
	      
	                
	            }
	           
	            reader1.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	        for(int i=0; i<movieList.size(); i++) {
	        	Movie temp1 = movieList.get(i);
				if(temp1.getId().equals(movieID)) {
					movieList.remove(i);
				}
			}

	        String filenameOut1 = "movieBasic.txt";
	        PrintWriter writer1 = null;
		        try{
		            writer1 = new PrintWriter(filenameOut1);
		            for(int i=0;i<movieList.size();i++){
		                Movie temp = movieList.get(i);
		                String oneLine = temp.getId() + "\t" + 
		                				 temp.getTitle()+ "\t" + 
		                				 temp.getCategory()+ "\t" + 
		                				 temp.getRating()+ "\t" + 
		                				 temp.getPrice()+ "\t" + 
		                				 temp.getImg();
		                writer1.println(oneLine);
		                //System.out.println(oneLine);
		            }
		            writer1.flush();
		            return true;
		        }catch(FileNotFoundException err){
		            System.out.println(err);
		        }finally{
		            if(writer1 != null){
		                writer1.close();
		            }
		        }
	       return false;
	}
	
	//manager search movie & view movie
	public static Movie searchMovie(String movieName) {
		ArrayList<Movie> searchMovieDB = new ArrayList <>();
		Scanner reader = null;
		String filename = "Movie.txt";

		//read text file
		try {
			reader = new Scanner(new File(filename));
			while(reader.hasNext()){
                String eachItem = reader.nextLine();
                String[] itemArr = eachItem.split("\\|");
                String readMovieID = itemArr[0];
                String readName = itemArr[1];
                String readPGRating = itemArr[2];
                String readMovieGenre = itemArr[3];
                String readReleaseData = itemArr[4];
                String readLanguage = itemArr[5];
                String readCast = itemArr[6];
                String readDirector = itemArr[7];
                String readSynopsis = itemArr[8];
                String readDuration = itemArr[9];
                String str_avgRating = itemArr[10];
                
                double readAvgRating = Double.parseDouble(str_avgRating);
                
                Movie readMovie = new Movie(readMovieID, readName, readPGRating,readMovieGenre,
                							readReleaseData ,readLanguage,readCast,readDirector,
                							readSynopsis,readDuration, readAvgRating);
                searchMovieDB.add(readMovie);
            }
		}catch(IOException err){
            System.out.println(err);
        }finally{
	        if(reader != null){
	            reader.close();
	        }
        }//end of finally
		
		for(int i=0; i<searchMovieDB.size(); i++) {
			Movie temp = searchMovieDB.get(i);
			if(temp.getName().equalsIgnoreCase(movieName)) {
				return temp;
			}
		}
		return null;
	}
	
	 public Movie(String id, String title, String category, double rating, double price, String img, ArrayList<String> date, ArrayList<String> time) {
	        this.id = id;
	        this.title = title;
	        this.category = category; 
	        this.rating = rating; 
	        this.price = price; 
	        this.img = img; 
	        this.date = date;
	        this.time = time; 
	    }
	    
	    public String getCategory() {
	    	return category; 
	    }
	    
	    public double getRating() {
	    	return rating; 
	    }
	    
	    public double getPrice() {
	    	return price; 
	    }
	    
	    public String getImg() {
	    	return img; 
	    }


	    public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id = id;
	    }

	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }


	    public ArrayList<String> getDate() {
	        return date;
	    }

	    public void setDate(ArrayList<String> date) {
	        this.date = date;
	    }
	    
	    public ArrayList<String> getTime() {
	    	return time;
	    }
	    
	    public void setTime(ArrayList<String> time) {
	    	this.time = time; 
	    }
	    
	    public static ArrayList<Movie> getList() {
	    	ArrayList<Movie> movieDB = new ArrayList<>(); 
			Scanner reader = null;
			String filename = "movieBasic.txt";
			
			try {
				reader = new Scanner(new File(filename));
	            while(reader.hasNext()){
	            	String line = reader.nextLine();
	            	String[] tokens = line.split("\t");
	                String id = tokens[0];
	                String movie_title = tokens[1];
	                String category = tokens[2]; 
	                double rating = Double.parseDouble(tokens[3]); 
	                double price = Double.parseDouble(tokens[4]); 
	                String img = tokens[5];
	                
	             // Have movie id, from id get session 
	                ArrayList<String> dateList = new ArrayList<>(); 
	                ArrayList<String> timeList = new ArrayList<>(); 

	                
	                Movie movie = new Movie(id, movie_title, category, rating, price, img, dateList, timeList); 
	                
	                movieDB.add(movie); // Generate the list of movie;
	            }
	            return movieDB;
	        }catch(IOException err){
	            System.out.println(err);
	        }finally{
		        if(reader != null){
		            reader.close();
		        }
	        }//end of finally
			
			
			return null;
	    }
	    
		public void setCategory(String movieGenre2) {
			this.category = movieGenre2;
		}
		
		public void setRating(double avgRating2) {
			this.rating = avgRating2;
			
		}
	    
	    @Override
	    public String toString() {
	        StringBuilder sb = new StringBuilder();
	        sb.append("ID: ").append(id).append("\n");
	        sb.append("Title: ").append(title).append("\n");
	        sb.append("Date: ");
	        for (String d : date) {
	            sb.append(d).append(", ");
	        }
	        sb.delete(sb.length() - 2, sb.length()); // remove trailing comma and space
	        sb.append("\nTime: ");
	        for (String t : time) {
	            sb.append(t).append(", ");
	        }
	        sb.delete(sb.length() - 2, sb.length()); // remove trailing comma and space
	        return sb.toString();
	    }
	    
	    
	    
	
}
