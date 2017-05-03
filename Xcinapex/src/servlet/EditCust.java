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


import Beans.Customer;
import utils.DBUtils;

@WebServlet("/EditCust")
public class EditCust extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCust() {
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
		String url = "jdbc:mysql://localhost:3306/cinapex";
   		String user = "root";
   		String pass = "serverplz!";
   		
   		
		/*
   		String jdbc_driver= "com.mysql.jdbc.Driver";  
   		String url = "jdbc:mysql://localhost/CineApex?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
   		String user = "manager";
   		String pass = "manager";
   		*/

   		java.sql.Connection conn = null;
	   	
   		
		List<Customer> allCusts = null;
		String errorString = null;
		
		try{
			
			
			String first = request.getParameter("CustFName");
			String last = request.getParameter("CusLName");
			String id = request.getParameter("CusId");
			String address = request.getParameter("CusAddress");
			String city= request.getParameter("CusCity");
			String zip = request.getParameter("CusZip");
			String state = request.getParameter("CusState");
			String phone = request.getParameter("CusPhone");
			String email = request.getParameter("Email");
			String cCard = request.getParameter("cCard");
			String rating = request.getParameter("rating");
	      
			
			//Movie movie = DBUtils.findMovieById(conn, Integer.parseInt(movieId));
			
			Class.forName(jdbc_driver).newInstance();
			conn = DriverManager.getConnection(url, user, pass);
			allMovie= DBUtils.queryMovies(conn);
			
			int id = Integer.parseInt(movieId);
			
			
			if(first != null && first.length() != 0){
				DBUtils.updateMovieName(conn, name, id);
			}
			
			if(last != null && last.length() != 0)
				DBUtils.updateMovieType(conn, type, id);
			
			if(rating != null && rating.length() != 0){
				int r =Integer.parseInt(rating);
				DBUtils.updateMovieRating(conn, r, id);
			}
			
			if(address != null&& address.length() != 0){
				int df = Integer.parseInt(distrFee);
				DBUtils.updateMovieDistrFee(conn, df, id);
			}
			
			if(city != null && city.length() != 0){
				int nC = Integer.parseInt(numCopies);
				DBUtils.updateMovieNumCopies(conn, nC, id);
			}
			
			if(zip != null && zip.length() != 0){
				int nC = Integer.parseInt(numCopies);
				DBUtils.updateMovieNumCopies(conn, nC, id);
			}
			if(state != null && zip.length() != 0){
				int nC = Integer.parseInt(numCopies);
				DBUtils.updateMovieNumCopies(conn, nC, id);
			}
				
			
			Class.forName(jdbc_driver).newInstance();
			conn = DriverManager.getConnection(url, user, pass);
			allCusts = DBUtils.queryMovies(conn);

			
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
		request.setAttribute("CustList", allCusts);
		RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/view/EditDelCust.jsp");
        dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}


}