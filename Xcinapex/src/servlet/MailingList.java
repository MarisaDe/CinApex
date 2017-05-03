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

import Beans.Customer;
import utils.DBUtils;
import utils.MyUtils;
import utils.setUpConnection;

@WebServlet("/MailingList")
public class MailingList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public MailingList() {
        super();
    }
    
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		String jdbc_driver= "com.mysql.jdbc.Driver";  

		String url = "jdbc:mysql://localhost:3306/" + setUpConnection.DATABASENAME;
   		String user = setUpConnection.USERNAME;
   		String pass = setUpConnection.PASSWORD;

   		
   		
   		java.sql.Connection conn = null;
   		List<String> email = null;
		List<Customer> allCust=null;
		String errorString = null;
		try{
			Class.forName(jdbc_driver).newInstance();
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);
			allCust = DBUtils.getCustomers(conn);
			conn.commit();
			
   		}catch (Exception e) {
   	        // Any error is grounds for rollback
   	        try { 
   	          conn.rollback();
   	          System.out.println("Rolling back..");
   	          e.printStackTrace();
   	        }
   	        catch (SQLException ignored) { } 
   	      }
		request.setAttribute("errorString", errorString);
		request.setAttribute("MailingList", allCust);
		RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/view/MailingList.jsp");
        dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}