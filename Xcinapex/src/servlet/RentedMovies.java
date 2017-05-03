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

import Beans.Movie;
import utils.DBUtils;
import utils.MyUtils;
import utils.setUpConnection;

@WebServlet("/RentedMovies")
public class RentedMovies extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public RentedMovies() {
        super();
    }
    
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
   		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		System.out.println(session.getAttribute("loggedInUser").getClass());
		/* 
		if(!"admin".equals(usertype))
		 {
		   response.sendRedirect("unauthorized.jsp");
		   return; //necessary to make the redirect happen right now
		 }
		*/
		String keyword = request.getParameter("search");
		String selector=request.getParameter("selector");
		System.out.println(keyword+" "+selector);
		

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
			allMovies= DBUtils.findMovie(conn,keyword,selector);
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
                .getRequestDispatcher("/WEB-INF/view/FindMovie.jsp");
        dispatcher.forward(request, response);
	}

}
