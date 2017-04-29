package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Beans.Movie;

public class DBUtils {

	public static List<Movie> queryMovies(Connection conn) throws SQLException{
		String sql = "Select * From movie;"; 
		 PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		
		List<Movie> allMovies = new ArrayList<Movie>();
		while(rs.next()){
 			int distrFee = rs.getInt("DistrFee");
 			int id = rs.getInt("Id");
 			String name = rs.getString("Name");
 			String type = rs.getString("Type");
 			int rating = rs.getInt("Rating");
 			int numcopies= rs.getInt("NumCopies");
 			
			Movie movie = new Movie();
			movie.setDistrFee(distrFee);
			movie.setId(id);
			movie.setName(name);
			movie.setNumCopies(numcopies);
			movie.setRating(rating);
			movie.setType(type);
			allMovies.add(movie);
		}
 		System.out.println(allMovies.size());
 		return allMovies;
	}
	
	public static Movie findMovie(Connection conn, int id) throws SQLException {
	      String sql = "Select * from Movie where id=?";
	 
	      PreparedStatement pstm = conn.prepareStatement(sql);
	      pstm.setInt(1, id);
	 
	      ResultSet rs = pstm.executeQuery();
	 
	      while (rs.next()) {
	    	  	int distrFee = rs.getInt("DistrFee");
	 			String name = rs.getString("Name");
	 			String type = rs.getString("Type");
	 			int rating = rs.getInt("Rating");
	 			int numcopies= rs.getInt("NumCopies");
	 			
				Movie movie = new Movie();
				movie.setDistrFee(distrFee);
				movie.setId(id);
				movie.setName(name);
				movie.setNumCopies(numcopies);
				movie.setRating(rating);
				movie.setType(type);
	      }
	      return null;
	  }
}
