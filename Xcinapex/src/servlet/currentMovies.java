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

@WebServlet("/currentMovies")
public class currentMovies extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public currentMovies() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(true);
		String jdbc_driver= "com.mysql.jdbc.Driver";  
		String url = "jdbc:mysql://localhost:3306/c305";
   		String user = "root";
   		String pass = "pass";
   		
   		/*
   		String jdbc_driver= "com.mysql.jdbc.Driver";  
   		String url = "jdbc:mysql://localhost/CineApex?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
   		String user = "manager";
   		String pass = "manager";
   		*/
   		java.sql.Connection conn = null;
	   	
   		List<Movie> allMovies=null;
		String errorString = null;
		try{
			Class.forName(jdbc_driver).newInstance();
			conn = DriverManager.getConnection(url, user, pass);
			Customer cus =(Customer) session.getAttribute("loggedInUser");
			
			String id = cus.getCustId();
			
			System.out.println(cus +" "+ id+" "+cus.getCustId());
			allMovies= DBUtils.getCustomersHeldMovies(conn, id);
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		request.setAttribute("errorString", errorString);
		request.setAttribute("MovieList", allMovies);
		RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/view/currentMovies.jsp");
        dispatcher.forward(request, response);
		
   	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}