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
import utils.DBUtils;
import utils.setUpConnection;

@WebServlet("/EditMovie")
public class EditMovie extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditMovie() {
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
			
			
			String movieId = request.getParameter("MovieId");
			String name = request.getParameter("MovieName");
			String rating = request.getParameter("MovieRating");
			String type = request.getParameter("MovieType");
			String distrFee = request.getParameter("MovieDistFee");
			String numCopies = request.getParameter("MovieNumCopies");
			
			System.out.println(movieId);
			System.out.println(name);
			System.out.println(rating);
			System.out.println(type);
			System.out.println(distrFee);
			System.out.println(numCopies);
			
			//Movie movie = DBUtils.findMovieById(conn, Integer.parseInt(movieId));
			
			Class.forName(jdbc_driver).newInstance();
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);
			allMovie= DBUtils.queryMovies(conn);
			
			int id = Integer.parseInt(movieId);
			
			
			if(name != null && name.length() != 0){
				DBUtils.updateMovieName(conn, name, id);
			}
			
			if(type != null && type.length() != 0)
				DBUtils.updateMovieType(conn, type, id);
			
			if(rating != null && rating.length() != 0){
				int r =Integer.parseInt(rating);
				DBUtils.updateMovieRating(conn, r, id);
			}
			
			if(distrFee != null&& distrFee.length() != 0){
				int df = Integer.parseInt(distrFee);
				DBUtils.updateMovieDistrFee(conn, df, id);
			}
			
			if(numCopies != null && numCopies.length() != 0){
				int nC = Integer.parseInt(numCopies);
				DBUtils.updateMovieNumCopies(conn, nC, id);
			}
				
			conn.commit();
			Class.forName(jdbc_driver).newInstance();
			conn = DriverManager.getConnection(url, user, pass);
			allMovie = DBUtils.queryMovies(conn);

			
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
