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

import Beans.Customer;
import Beans.Movie;
import utils.DBUtils;
import utils.MyUtils;
import utils.setUpConnection;

@WebServlet("/DeleteCust")
public class DeleteCust extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public DeleteCust() {
        super();
    }
    
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		HttpSession session=request.getSession(true);
   		//Don't forget to change this
		
   		String jdbc_driver= "com.mysql.jdbc.Driver";  
		String url = "jdbc:mysql://localhost:3306/" + setUpConnection.DATABASENAME;
   		String user = setUpConnection.USERNAME;
   		String pass = setUpConnection.PASSWORD;
   		
   		java.sql.Connection conn = null;
		List<Customer> changethisCust=null;
		String errorString = null;
		
		try{
			Class.forName(jdbc_driver).newInstance();
			conn = DriverManager.getConnection(url, user, pass);
			
			conn.setAutoCommit(false);
			changethisCust= DBUtils.getCustomers(conn);
			String custid= request.getParameter("custId");
			int AcctId = DBUtils.getAccountIdFromCustomerId(conn, custid);
			
			DBUtils.deleteMovieQByAcctId(conn, AcctId);
			System.out.println("Deleted MovieQ");
			DBUtils.deleteRental(conn, AcctId);
			System.out.println("Deleted Rental");
			DBUtils.deleteMovieOrderByAccount(conn, AcctId);
			System.out.println("Deleted MovieOrder");
			DBUtils.deleteAccount(conn, custid);
			System.out.println("Deleted Account");
			DBUtils.deleteCustomer(conn, custid);
			System.out.println("Deleted Customer");
			DBUtils.deletePerson(conn, custid);
			System.out.println("Deleted Person");
			int zip = DBUtils.getZipBySSN(conn, custid);
			DBUtils.deleteLocation(conn, zip);
			
			changethisCust= DBUtils.getCustomers(conn);
			conn.commit();
			
			Class.forName(jdbc_driver).newInstance();
			conn = DriverManager.getConnection(url, user, pass);
			changethisCust = DBUtils.getCustomers(conn);
			
		}catch (Exception e) {
	        // Any error is grounds for rollback
	        try { 
	          conn.rollback();
	          System.out.println("Rolling back..");
	  		RequestDispatcher dispatcher = request.getServletContext()
	                .getRequestDispatcher("/WEB-INF/view/EditDelCus.jsp");
	        dispatcher.forward(request, response);
	          e.printStackTrace();
	        }
	        catch (SQLException ignored) { } 
	      }
		RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/view/EditDelCus.jsp");
        dispatcher.forward(request, response);
		request.setAttribute("errorString", errorString);
		
   	}
   	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}