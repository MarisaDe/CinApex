package servlet;

import java.io.IOException;
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

import Beans.Movie;
import Beans.Customer;
import Beans.Employee;
import utils.DBUtils;
import utils.setUpConnection;

@WebServlet("/EditDelCust")
public class EditDelCust extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditDelCust() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
   
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		HttpSession session = request.getSession(true);
		//System.out.println(session.getAttribute("loggedInUser").getClass());
		if (session.getAttribute("loggedInUser")!=null){//make sure someone is logged in to check
			Object usertype = session.getAttribute("loggedInUser");
			if(!(usertype instanceof Employee)) 
			 {
				RequestDispatcher dispatcher = request.getServletContext()
		                .getRequestDispatcher("/WEB-INF/view/404EmpOnly.jsp");
		        dispatcher.forward(request, response);
			   return; //necessary to make the redirect happen right now
			 }
		}else if(session.getAttribute("loggedInUser")==null){
			RequestDispatcher dispatcher = request.getServletContext()
	                .getRequestDispatcher("/WEB-INF/view/404.jsp");
	        dispatcher.forward(request, response);
		   return;
		}

   		String jdbc_driver= "com.mysql.jdbc.Driver";  
		String url = "jdbc:mysql://localhost:3306/" + setUpConnection.DATABASENAME;
   		String user = setUpConnection.USERNAME;
   		String pass = setUpConnection.PASSWORD;
   		

   		java.sql.Connection conn = null;
	   	
   		
		List<Customer> allCusts=null;
		String errorString = null;
		
		try{
			Class.forName(jdbc_driver).newInstance();
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);
			allCusts= DBUtils.getCustomers(conn);
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
		request.setAttribute("CustList", allCusts);
		RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/view/EditDelCus.jsp");
        dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}


}
