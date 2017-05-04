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

import Beans.Employee;
import Beans.Movie;
import utils.DBUtils;
import utils.MyUtils;
import utils.setUpConnection;

@WebServlet("/AddRating")
public class AddRating extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public AddRating() {
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
		String errorString = null;
		
		try{
			Class.forName(jdbc_driver).newInstance();
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);
			String movId= request.getParameter("movieId");
			String userrate= request.getParameter("movieRating");
			int id= Integer.parseInt(movId);
			int userrating = Integer.parseInt(userrate);
			
			System.out.println(movId + userrating);
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
		RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/view/History.jsp");
        dispatcher.forward(request, response);
   	}
   	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
