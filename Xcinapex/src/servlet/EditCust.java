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
import utils.setUpConnection;

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
		String jdbc_driver= "com.mysql.jdbc.Driver";  
		String url = "jdbc:mysql://localhost:3306/" + setUpConnection.DATABASENAME;
   		String user = setUpConnection.USERNAME;
   		String pass = setUpConnection.PASSWORD;
   		java.sql.Connection conn = null;
	   	
		String errorString = null;
		
		try{
			
			String first = request.getParameter("custFirstName");
			String last = request.getParameter("custLastName");
			String id = request.getParameter("custId");
			String address = request.getParameter("custAddress");
			//String city= request.getParameter("CusCity");
			String szip = request.getParameter("custZipcode");
			//String state = request.getParameter("CusState");
			String phone = request.getParameter("custTelephone");
			String email = request.getParameter("custEmail");
			String cCard = request.getParameter("custCCard");
			String srating = request.getParameter("custRating");
	      
			int zip = Integer.parseInt(szip);
			int rating =  Integer.parseInt(srating);
			
			System.out.println(first);
			Class.forName(jdbc_driver).newInstance();
			conn = DriverManager.getConnection(url, user, pass);
<<<<<<< HEAD
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
=======
			DBUtils.updateCustomer(conn,id,first,last,address,zip,phone,email,cCard,rating);
>>>>>>> efee68c4f132649c4beecbada809d60b219aa1c9

			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = request.getServletContext()
	                .getRequestDispatcher("/WEB-INF/view/EditDelCus.jsp");
	        dispatcher.forward(request, response);
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}


}