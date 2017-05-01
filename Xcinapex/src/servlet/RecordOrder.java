package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Beans.*;
import utils.DBUtils;
import utils.MyUtils;

@WebServlet("/RecordOrder")
public class RecordOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public RecordOrder() {
        super();
    }
    
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		String accountId = request.getParameter("accountId");
		int movieId =  Integer.parseInt(request.getParameter("movieId"));
		String date = request.getParameter("date");
		String returnDate = request.getParameter("returnDate");
		
		MovieOrder movieOrder = new MovieOrder();
		movieOrder.setAccountId(accountId);
		movieOrder.setDateAndTime(date);
		movieOrder.setId(orderId);
		movieOrder.setMovieId(movieId);
		movieOrder.setReturnDate(returnDate);
		
		Rental rental = new Rental();
		
		/*
   		String jdbc_driver= "com.mysql.jdbc.Driver";  
		String url = "jdbc:mysql://localhost:3306/c305";
   		String user = "root";
   		String pass = "pass";
   		*/
   		String jdbc_driver= "com.mysql.jdbc.Driver";  
   		String url = "jdbc:mysql://localhost/CineApex?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
   		String user = "manager";
   		String pass = "manager";
   		
   		
   		java.sql.Connection conn = null;

		try{
			Class.forName(jdbc_driver).newInstance();
			conn = DriverManager.getConnection(url, user, pass);
			DBUtils.insertMovieOrder(conn, movieOrder);
			DBUtils.insertRental(conn, rental);
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		//request.setAttribute("errorString", errorString);
		//request.setAttribute("MovieList", allMovies);
		RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/view/FindMovie.jsp");
        dispatcher.forward(request, response);
	}

}
