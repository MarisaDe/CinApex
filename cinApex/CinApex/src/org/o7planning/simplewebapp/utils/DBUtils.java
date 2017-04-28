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
 
  public static List<Movie> movieList(Connection conn) throws SQLException {
      String sql = "Select a.Id, a.Name, a.Type, a.Rating, a.DistrFee, a.NumCopies from movie a ";
 
      PreparedStatement pstm = conn.prepareStatement(sql);
 
      ResultSet rs = pstm.executeQuery();
      List<Movie> list = new ArrayList<Movie>();
      while (rs.next()) {
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
          
          list.add(movie);
      }
      return list;
  }
 
  public static Movie findProduct(Connection conn, String code) throws SQLException {
      String sql = "Select a.Code, a.Name, a.Price from Product a where a.Code=?";
 
      PreparedStatement pstm = conn.prepareStatement(sql);
      pstm.setString(1, code);
 
      ResultSet rs = pstm.executeQuery();
 
      while (rs.next()) {
          String name = rs.getString("Name");
          float price = rs.getFloat("Price");
          Product product = new Product(code, name, price);
          return product;
      }
      return null;
  }
 
  public static void updateProduct(Connection conn, Product product) throws SQLException {
      String sql = "Update Product set Name =?, Price=? where Code=? ";
 
      PreparedStatement pstm = conn.prepareStatement(sql);
 
      pstm.setString(1, product.getName());
      pstm.setFloat(2, product.getPrice());
      pstm.setString(3, product.getCode());
      pstm.executeUpdate();
  }
 
  public static void insertProduct(Connection conn, Product product) throws SQLException {
      String sql = "Insert into Product(Code, Name,Price) values (?,?,?)";
 
      PreparedStatement pstm = conn.prepareStatement(sql);
 
      pstm.setString(1, product.getCode());
      pstm.setString(2, product.getName());
      pstm.setFloat(3, product.getPrice());
 
      pstm.executeUpdate();
  }
 
  public static void deleteProduct(Connection conn, String code) throws SQLException {
      String sql = "Delete Product where Code= ?";
 
      PreparedStatement pstm = conn.prepareStatement(sql);
 
      pstm.setString(1, code);
 
      pstm.executeUpdate();
  }
 
}