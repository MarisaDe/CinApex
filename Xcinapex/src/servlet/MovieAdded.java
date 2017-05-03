package servlet;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Movie;
import utils.DBUtils;
import utils.setUpConnection;

@WebServlet("/MovieAdded")
public class MovieAdded extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    public MovieAdded() {
        super();
    }
    
	
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		
   		String jdbc_driver= "com.mysql.jdbc.Driver";  
		String url = "jdbc:mysql://localhost:3306/" + setUpConnection.DATABASENAME;
   		String user = setUpConnection.USERNAME;
   		String pass = setUpConnection.PASSWORD;
   		
		java.sql.Connection conn = null;
   	
		String errorString = null;
		
		
		try {
			
			String id = request.getParameter("MovieId");
			String name = request.getParameter("MovieName");
			String type = request.getParameter("MovieType");
			String rating = request.getParameter("MovieRating");
			String numCopies = request.getParameter("MovieCopies");
			String distrFee = request.getParameter("MovieDistrFee");

			
			Movie movie = new Movie();
			movie.setDistrFee(Integer.parseInt(distrFee));
			movie.setId(Integer.parseInt(id));
			movie.setName(name);
			movie.setNumCopies(Integer.parseInt(numCopies));
			movie.setRating(Integer.parseInt(rating));
			movie.setType(type);
			
			
			Class.forName(jdbc_driver).newInstance();
			conn = DriverManager.getConnection(url, user, pass);
			
			DBUtils.insertMovie( conn, movie);
			
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = request.getServletContext()
	                .getRequestDispatcher("/WEB-INF/view/MovieAdded.jsp");
	        dispatcher.forward(request, response);
				
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

	
}
