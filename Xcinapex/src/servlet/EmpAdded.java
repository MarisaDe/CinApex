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
import Beans.Person;
import utils.DBUtils;

@WebServlet("/EmpAdded")
public class EmpAdded extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    public EmpAdded() {
        super();
    }
    
	
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		
   		
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
   		
		String errorString = null;
		
		
		try {
			
			String firstName = request.getParameter("EmpFname");
			String lastName = request.getParameter("EmpLName");
			String ssn = request.getParameter("EmpSSN");
			String address = request.getParameter("EmpAddress");
			//int zip =  request.getParameter("EmpZip");
			String state = request.getParameter("EmpState");
			String phone = request.getParameter("EmpPhone");
			String startDate = request.getParameter("EmpDate");
			//int hourlyRate = request.getParameter("EmpWage");
			
			Person person = new Person();
			person.setTelephone(phone);
			person.setSSN(ssn);
			//person.setZipcode(Integer.parseInt(zip));
			person.setFirstName(firstName);
			person.setLastName(lastName);
			person.setAddress(address);
			
			Employee employee = new Employee();
			
			employee.setFirstName(firstName);
			employee.setLastName(lastName);
			employee.setSsn(ssn);
			employee.setAddress(address);
			//employee.setZipcode(Integer.parseInt(zip));
			employee.setStartDate(startDate);
			employee.setTelephone(phone);
			employee.setStartDate(startDate);
			//employee.setHourlyRate(Integer.parseInt(hourlyRate));
			
			
			Class.forName(jdbc_driver).newInstance();
			conn = DriverManager.getConnection(url, user, pass);
			
			DBUtils.insertPerson(conn, person);
			DBUtils.insertEmployee(conn, employee);
			
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = request.getServletContext()
	                .getRequestDispatcher("/WEB-INF/view/EmpAdded.jsp");
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
