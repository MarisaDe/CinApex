package org.o7planning.simplewebapp.utils;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
import org.o7planning.simplewebapp.beans.Movie;
import org.o7planning.simplewebapp.beans.UserAccount;
 
public class DBUtils {
 
  public static UserAccount findUser(Connection conn, String userName, String password) throws SQLException {
 
      String sql = "Select a.User_Name, a.Password, a.Gender from User_Account a "
              + " where a.User_Name = ? and a.password= ?";
 
      PreparedStatement pstm = conn.prepareStatement(sql);
      pstm.setString(1, userName);
      pstm.setString(2, password);
      ResultSet rs = pstm.executeQuery();
 
      if (rs.next()) {
          String gender = rs.getString("Gender");
          UserAccount user = new UserAccount();
          user.setUserName(userName);
          user.setPassword(password);
          user.setGender(gender);
          return user;
      }
      return null;
  }
 
  public static UserAccount findUser(Connection conn, String userName) throws SQLException {
 
      String sql = "Select a.User_Name, a.Password, a.Gender from User_Account a " + " where a.User_Name = ? ";
 
      PreparedStatement pstm = conn.prepareStatement(sql);
      pstm.setString(1, userName);
 
      ResultSet rs = pstm.executeQuery();
 
      if (rs.next()) {
          String password = rs.getString("Password");
          String gender = rs.getString("Gender");
          UserAccount user = new UserAccount();
          user.setUserName(userName);
          user.setPassword(password);
          user.setGender(gender);
          return user;
      }
      return null;
  }
 
  public static Movie buildMovie(ResultSet rs)  throws SQLException{
      
	  int Id = rs.getInt("Id");
      String Name = rs.getString("Name");
      String Type = rs.getString("Type");
      int Rating  = rs.getInt("Rating");
      int DistrFee = rs.getInt("DistrFee");
      int NumCopies = rs.getInt("NumCopies");
      Movie movie = new Movie();
      movie.setId(Id);
      movie.setName(Name);
      movie.setType(Type);
      movie.setRating(Rating);
      movie.setDistrFee(DistrFee);
      movie.setNumCopies(NumCopies);
      
      return movie;
      
  }
  
  public static List<Movie> movieList(Connection conn) throws SQLException {
      String sql = "Select a.Id, a.Name, a.Type, a.Rating, a.DistrFee, a.NumCopies from movie a ";
      
      PreparedStatement pstm = conn.prepareStatement(sql);
 
      ResultSet rs = pstm.executeQuery();
      List<Movie> list = new ArrayList<Movie>();
      while (rs.next()) {
    	  Movie movie = buildMovie(rs);
          list.add(movie);
      }
      return list;
  }
  
  public static List<Movie> movieSuggestions(Connection conn, String Id) throws SQLException{
	String sql = "SELECT M.Id, M.Name, M.Type FROM Movie M WHERE M.Type IN (SELECT O.Type FROM PastOrder O WHERE O.CustId = '?')  AND M.Id NOT IN (SELECT O.MovieId FROM PastOrder O WHERE O.CustId = '?'";
	
	PreparedStatement pstm = conn.prepareStatement(sql);
	pstm.setString(1, Id);
	
	ResultSet rs = pstm.executeQuery();
    List<Movie> list = new ArrayList<Movie>();
    while (rs.next()) {
    	Movie movie = buildMovie(rs);
        list.add(movie);
    }
	
	return list;
  }
  
  public static Movie findMovie(Connection conn, String Name) throws SQLException {
      String sql = "Select a.Id, a.Name, a.DistrFee from Product a where a.Name=?";
 
      PreparedStatement pstm = conn.prepareStatement(sql);
      pstm.setString(1, Name);
 
      ResultSet rs = pstm.executeQuery();
 
      while (rs.next()) {
    	  Movie movie = buildMovie(rs);
          return movie;
      }
      return null;
  }
 
  /*
  public static void updateProduct(Connection conn, Product product) throws SQLException {
      String sql = "Update Product set Name =?, Price=? where Code=? ";
 
      PreparedStatement pstm = conn.prepareStatement(sql);
 
      pstm.setString(1, product.getName());
      pstm.setFloat(2, product.getPrice());
      pstm.setString(3, product.getCode());
      pstm.executeUpdate();
  }
 */
  
  public static void insertMovie(Connection conn, Movie movie) throws SQLException {
      String sql = "INSERT INTO Movie VALUES(?, ?, ?, ?, ?, ?);";
 
      PreparedStatement pstm = conn.prepareStatement(sql);
 
      pstm.setInt(1, movie.getId());
      pstm.setString(2, movie.getName());
      pstm.setString(3, movie.getType());
      pstm.setInt(4, movie.getRating());
      pstm.setInt(5, movie.getDistrFee());
      pstm.setInt(6, movie.getNumCopies());
 
      pstm.executeUpdate();
  }
 
  public static void deleteMovie(Connection conn, int id) throws SQLException {
      String sql = "DELETE FROM Movie WHERE Id = ?";
 
      PreparedStatement pstm = conn.prepareStatement(sql);
 
      pstm.setInt(1, id);
 
      pstm.executeUpdate();
  }
 
}