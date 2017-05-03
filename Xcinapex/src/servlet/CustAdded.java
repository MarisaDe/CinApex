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

import Beans.Customer;
import Beans.Location;
import Beans.Person;
import utils.DBUtils;
import utils.setUpConnection;

@WebServlet("/CustAdded")
public class CustAdded extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    public CustAdded() {
        super();
    }
    
	
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		
   		java.sql.Connection conn = null;

   		String jdbc_driver= "com.mysql.jdbc.Driver";  
		String url = "jdbc:mysql://localhost:3306/" + setUpConnection.DATABASENAME;
   		String user = setUpConnection.USERNAME;
   		String pass = setUpConnection.PASSWORD;
		
		String errorString = null;
		
		
		try {
			
			String firstName = request.getParameter("CusFname");
			String lastName = request.getParameter("CusLName");
			String id = request.getParameter("CusId");
			String city = request.getParameter("CusCity");
			String address = request.getParameter("CusAddress");
			String zip =  request.getParameter("CusZip");
			String state = request.getParameter("CusState");
			String phone = request.getParameter("CusPhone");
			String email = request.getParameter("Email");
			String cCard = request.getParameter("cCard");
			String rating = request.getParameter("rating");
			
			
			Location location = new Location();
			location.setCity(city);
			location.setZip(Integer.parseInt(zip));
			location.setState(state);
			
			Person person = new Person();
			person.setTelephone(phone);
			person.setSSN(id);
			person.setZipcode(Integer.parseInt(zip));
			person.setFirstName(firstName);
			person.setLastName(lastName);
			person.setAddress(address);
			
			Customer cust = new Customer();
			
			cust.setFirstName(firstName);
			cust.setLastName(lastName);
			cust.setCustId(id);
			cust.setAddress(address);
			cust.setZipcode(Integer.parseInt(zip));
			cust.setTelephone(phone);
			cust.setEmail(email);
			cust.setRating(Integer.parseInt(rating));
			cust.setcCard(cCard);
			
			
			Class.forName(jdbc_driver).newInstance();
			conn = DriverManager.getConnection(url, user, pass);
			
			DBUtils.insertLocation( conn, location);
			DBUtils.insertPerson(conn, person);
			DBUtils.insertCustomer(conn, cust);
			
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = request.getServletContext()
	                .getRequestDispatcher("/WEB-INF/view/CustAdded.jsp");
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
