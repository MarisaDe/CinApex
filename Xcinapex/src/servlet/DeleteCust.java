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

import Beans.Customer;
import Beans.Movie;
import utils.DBUtils;
import utils.MyUtils;
import utils.setUpConnection;

@WebServlet("/DeleteCust")
public class DeleteCust extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public DeleteCust() {
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
		List<Customer> allCusts=null;
		String errorString = null;
		
		try{
			Class.forName(jdbc_driver).newInstance();
			conn = DriverManager.getConnection(url, user, pass);
			allCusts= DBUtils.getCustomers(conn);
			String id= request.getParameter("custId");
			String ssn= request.getParameter("PersonSSN");
			DBUtils.deleteCustomer(conn, ssn, id);
			
			System.out.println(ssn);
			
			Class.forName(jdbc_driver).newInstance();
			conn = DriverManager.getConnection(url, user, pass);
			allCusts = DBUtils.getCustomers(conn);
			
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
                .getRequestDispatcher("/WEB-INF/view/EditDelCus.jsp");
        dispatcher.forward(request, response);
   	}
   	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}