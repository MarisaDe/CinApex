package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

@WebServlet("/OrderRecorded")
public class OrderRecorded extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public OrderRecorded() {
        super();
    }
    
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
			
			int orderId = Integer.parseInt(request.getParameter("MOId"));
			String accountId = request.getParameter("MOAId");
			String custRepId = request.getParameter("MOCId");
			int movieId =  Integer.parseInt(request.getParameter("MOMId"));			
			String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
			String returnDate = request.getParameter("MORD");
			
			System.out.println("::: " +timeStamp);
			
			MovieOrder movieOrder = new MovieOrder();
			movieOrder.setId(orderId);
			movieOrder.setAccountId(accountId);
			movieOrder.setMovieId(movieId);
			movieOrder.setReturnDate(returnDate);
			movieOrder.setDateAndTime(timeStamp);
			
			System.out.println("object GET");
			System.out.println(movieOrder.getId());
			System.out.println(movieOrder.getReturnDate());
			System.out.println(movieOrder.getDateAndTime());
			
			Rental rental = new Rental();
			rental.setAccountId(accountId);
			rental.setCustRepId(custRepId);
			rental.setMovieId(movieId);
			rental.setOrderId(orderId);
			
			
			Class.forName(jdbc_driver).newInstance();
			conn = DriverManager.getConnection(url, user, pass);
			
			// Exec the queries and insert the data into the database
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

		RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/view/OrderRecorded.jsp");
        dispatcher.forward(request, response);
	}

}