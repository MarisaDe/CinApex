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

@WebServlet("/DatabaseBacked")
public class DatabaseBacked extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public DatabaseBacked() {
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
		List<Customer> changethisCust=null;
		String errorString = null;
		
		try{
			Class.forName(jdbc_driver).newInstance();
			conn = DriverManager.getConnection(url, user, pass);
			
			conn.setAutoCommit(false);
			String path= request.getParameter("path");
			System.out.println(path);			

	        //String cmd = "/usr/local/bin/mysqldump -u " + user + " -p" + pass + " --add-drop-database -B " + setUpConnection.DATABASENAME + " -r " + path + "/backup.sql";
	        String cmd = "/usr/local/bin/mysqldump -u " + user + " -p" + pass + " --add-drop-database -B " + "CineApex" + " -r " + path + "//backup.sql";
	        //String cmd = "mysqldump -u " + user + " -p" + pass + " --add-drop-database -B " + setUpConnection.DATABASENAME + " -r " + path + "/backup.sql";

			System.out.println(cmd);
	        
	        Process runtimeProcess;
	        try {
	 
	            runtimeProcess = Runtime.getRuntime().exec(cmd);
	            int processComplete = runtimeProcess.waitFor();
	 
	            if (processComplete == 0) {
	                System.out.println("Backup created successfully");
	            } else {
	                System.out.println("Could not create the backup");
	            }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        
	        
			conn.commit();
			
			Class.forName(jdbc_driver).newInstance();
			conn = DriverManager.getConnection(url, user, pass);
			changethisCust = DBUtils.getCustomers(conn);
			
		}catch (Exception e) {
	        // Any error is grounds for rollback
	        try { 
	          conn.rollback();
	          System.out.println("Rolling back..");
	  		RequestDispatcher dispatcher = request.getServletContext()
	                .getRequestDispatcher("/WEB-INF/view/EditDelCus.jsp");
	        dispatcher.forward(request, response);
	          e.printStackTrace();
	        }
	        catch (SQLException ignored) { } 
	      }
		RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/view/DatabaseBacked.jsp");
        dispatcher.forward(request, response);
		request.setAttribute("errorString", errorString);
		
   	}
   	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}