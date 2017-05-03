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

import Beans.Employee;
import Beans.Location;
import Beans.Person;
import utils.DBUtils;
import utils.setUpConnection;

@WebServlet("/EmpAdded")
public class EmpAdded extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    public EmpAdded() {
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
			
			String firstName = request.getParameter("EmpFname");
			String lastName = request.getParameter("EmpLName");
			String id = request.getParameter("EmpId");
			String ssn = request.getParameter("EmpSSN");
			String city = request.getParameter("EmpCity");
			String address = request.getParameter("EmpAddress");
			String zip =  request.getParameter("EmpZip");
			String state = request.getParameter("EmpState");
			String phone = request.getParameter("EmpPhone");
			String startDate = request.getParameter("EmpDate");
			String hourlyRate = request.getParameter("EmpWage");
			
			
			Location location = new Location();
			location.setCity(city);
			location.setZip(Integer.parseInt(zip));
			location.setState(state);
			
			Person person = new Person();
			person.setTelephone(phone);
			person.setSSN(ssn);
			person.setZipcode(Integer.parseInt(zip));
			person.setFirstName(firstName);
			person.setLastName(lastName);
			person.setAddress(address);
			
			Employee employee = new Employee();
			
			employee.setFirstName(firstName);
			employee.setLastName(lastName);
			employee.setId(Integer.parseInt(id));
			employee.setSsn(ssn);
			employee.setAddress(address);
			employee.setZipcode(Integer.parseInt(zip));
			employee.setStartDate(startDate);
			employee.setTelephone(phone);
			employee.setStartDate(startDate);
			employee.setHourlyRate(Integer.parseInt(hourlyRate));
			
			
			Class.forName(jdbc_driver).newInstance();
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);
			
			DBUtils.insertLocation( conn, location);
			DBUtils.insertPerson(conn, person);
			DBUtils.insertEmployee(conn, employee);
			
			conn.commit();
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = request.getServletContext()
	                .getRequestDispatcher("/WEB-INF/view/EmpAdded.jsp");
	        dispatcher.forward(request, response);
				
		}catch (Exception e) {
	        // Any error is grounds for rollback
	        try { 
	          conn.rollback();
	          System.out.println("Rolling back..");
	          e.printStackTrace();
	        }
	        catch (SQLException ignored) { } 
	      }
		
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

	
}
