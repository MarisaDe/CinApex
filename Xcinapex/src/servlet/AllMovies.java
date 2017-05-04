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

import Beans.Manager;
import Beans.Movie;
import utils.DBUtils;
import utils.MyUtils;
import utils.setUpConnection;

@WebServlet("/AllMovies")
public class AllMovies extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public AllMovies() {
        super();
    }
    
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
   		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		HttpSession session = request.getSession(true);
		//System.out.println(session.getAttribute("loggedInUser").getClass());
		if (session.getAttribute("loggedInUser")!=null){//make sure someone is logged in to check
			Object usertype = session.getAttribute("loggedInUser");
			if(!(usertype instanceof Manager)) 
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
   		
		List<Movie> allMovies=null;
		String errorString = null;
		try{
			Class.forName(jdbc_driver).newInstance();
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);
			allMovies= DBUtils.listAllMovies(conn);
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
		request.setAttribute("MovieList", allMovies);
		RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/view/AllMovies.jsp");
        dispatcher.forward(request, response);
	}

}
