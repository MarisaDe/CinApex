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

import Beans.Employee;
import Beans.Movie;
import Beans.Customer;
import utils.DBUtils;
import utils.setUpConnection;

@WebServlet("/EditDelMovie")
public class EditDelMovie extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditDelMovie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
   
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(true);
   		
		//Don't forget to change this
		
   		String jdbc_driver= "com.mysql.jdbc.Driver";  
		String url = "jdbc:mysql://localhost:3306/" + setUpConnection.DATABASENAME;
   		String user = setUpConnection.USERNAME;
   		String pass = setUpConnection.PASSWORD;
   		

   		java.sql.Connection conn = null;
	   	
   		
   		List<Movie> allMovie = null;
		String errorString = null;
		
		try{
			Class.forName(jdbc_driver).newInstance();
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);
			allMovie= DBUtils.queryMovies(conn);
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
		request.setAttribute("MovieList", allMovie);
		RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/view/EditDelMovie.jsp");
        dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}


}
