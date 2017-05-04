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
import Beans.Employee;
import Beans.Manager;
import utils.DBUtils;
import utils.setUpConnection;

@WebServlet("/EditEmp")
public class EditEmp extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditEmp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
   
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	   	
   		List<Employee> allEmps=null;
   		
		String errorString = null;
		
		try{
			
			String first = request.getParameter("empFirstName");
			String last = request.getParameter("empLastName");
			String id = request.getParameter("empId");
			String address = request.getParameter("empAddress");
			//String city= request.getParameter("CusCity");
			String szip = request.getParameter("empZipcode");
			//String state = request.getParameter("CusState");
			String phone = request.getParameter("empTelephone");
			String startDate = request.getParameter("empStartDate");
			String hourRate = request.getParameter("empHourly");
			String ssn = request.getParameter("ssnOfEmp");
	      
			int zip = Integer.parseInt(szip);
			int hourR =  Integer.parseInt(hourRate);
			System.out.println(first);
			Class.forName(jdbc_driver).newInstance();
			conn = DriverManager.getConnection(url, user, pass);
			DBUtils.updateEmployeeAll(conn,ssn,first,last,address,zip,phone,startDate,hourR);
			allEmps= DBUtils.getEmployees(conn);
			
			request.setAttribute("errorString", errorString);
			request.setAttribute("EmpList", allEmps);
			RequestDispatcher dispatcher = request.getServletContext()
	                .getRequestDispatcher("/WEB-INF/view/EditDelEmp.jsp");
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