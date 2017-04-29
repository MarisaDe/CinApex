package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Beans.*;

public class DBUtils {

	
	public static Movie buildMovie(ResultSet rs) throws SQLException {
		int distrFee = rs.getInt("DistrFee");
		int id = rs.getInt("Id");
		String name = rs.getString("Name");
		String type = rs.getString("Type");
		int rating = rs.getInt("Rating");
		int numcopies = rs.getInt("NumCopies");

		Movie movie = new Movie();
		movie.setDistrFee(distrFee);
		movie.setId(id);
		movie.setName(name);
		movie.setNumCopies(numcopies);
		movie.setRating(rating);
		movie.setType(type);

		return movie;
	}
	

	public static List<Movie> queryMovies(Connection conn) throws SQLException {
		String sql = "Select * From movie;";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();

		List<Movie> allMovies = new ArrayList<Movie>();
		while (rs.next()) {
			Movie movie = buildMovie(rs);
			allMovies.add(movie);
		}
		
		return allMovies;
	}
	
	public static List<Movie> getBestSeller(Connection conn) throws SQLException {
		String sql = "SELECT M.Id, M.Name, M.Type, M.Rating, N.NumOrders FROM MovieOrder N, Movie M WHERE N.MovieId = M.Id ORDER BY N.NumOrders DESC";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();

		List<Movie> bestSellers = new ArrayList<Movie>();
		while (rs.next()) {
			Movie movie = buildMovie(rs);
			bestSellers.add(movie);
		}
		System.out.println(bestSellers.size());
		return bestSellers;
	}

	public static List<Movie> movieSuggestions(Connection conn, String Id) throws SQLException {
		String sql = "SELECT M.Id, M.Name, M.Type FROM Movie M WHERE M.Type IN (SELECT O.Type FROM PastOrder O WHERE O.CustId = '?')  AND M.Id NOT IN (SELECT O.MovieId FROM PastOrder O WHERE O.CustId = '?'";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, Id);

		ResultSet rs = pstm.executeQuery();
		List<Movie> list = new ArrayList<Movie>();
		while (rs.next()) {
			Movie movie = buildMovie(rs);
			list.add(movie);
		}

		return list;
	}


	public static List<Movie> findMovie(Connection conn, String keyword, String selector) throws SQLException{
		if(selector.equals("Title")){
			String percent="%";
			String name=percent+keyword+percent;
			return findMovieByName(conn,name);
		}else if (selector.equals("Genre")){
			return findMovieByType(conn,keyword);
		}else{
			return null; //not yet impl
		}
	}


	public static List<Movie> findMovieByType(Connection conn, String type) throws SQLException {
		String sql = "Select * from Movie where Type=?";


		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, type);

		ResultSet rs = pstm.executeQuery();

		List<Movie> list = new ArrayList<Movie>();
		while (rs.next()) {
			Movie movie = buildMovie(rs);
			list.add(movie);
		}
		System.out.println(list.size());
		return list;
	}

	public static List<Movie> findMovieByName(Connection conn, String name) throws SQLException {
		String sql = "SELECT *  FROM Movie WHERE Name LIKE ?;";


		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, name);

		ResultSet rs = pstm.executeQuery();

		List<Movie> list = new ArrayList<Movie>();
		while (rs.next()) {
			Movie movie = buildMovie(rs);
			list.add(movie);
		}
		System.out.println(list.size());
		return list;
	}

	/**
	 * Manager Level Transaction ONLY
	 * 
	 * @param conn
	 * @param movie
	 * @throws SQLException
	 */
	public static void insertMovie(Connection conn, Movie movie) throws SQLException {
		String sql = "INSERT INTO Movie VALUES(?, ?, ?, ?, ?, ?);";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setInt(1, movie.getId());
		pstm.setString(2, movie.getName());
		pstm.setString(3, movie.getType());
		pstm.setInt(4, movie.getRating());
		pstm.setInt(5, movie.getDistrFee());
		pstm.setInt(6, movie.getNumCopies());

		pstm.executeUpdate();
	}

	/**
	 * Manager Level Transaction ONLY
	 */
	public static void deleteMovie(Connection conn, int id) throws SQLException {
		String sql = "DELETE FROM Movie WHERE Id = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setInt(1, id);

		pstm.executeUpdate();
	}

	public static void updateMovieType(Connection conn, String type, int id) throws SQLException {
		String sql = "UPDATE Movie SET Rating = ? WHERE  Type = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, type);
		pstm.setInt(2, id);

		pstm.executeUpdate();

	}

	public static void updateMovieRating(Connection conn, int rating, int id) throws SQLException {
		String sql = "UPDATE Movie SET Rating = ? WHERE  Rating = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setInt(1, rating);
		pstm.setInt(2, id);

		pstm.executeUpdate();
	}

	public static void updateMovieDistrFee(Connection conn, int fee, int id) throws SQLException {
		String sql = "UPDATE Movie SET Rating = ? WHERE  DistrFee = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setInt(1, fee);
		pstm.setInt(2, id);

		pstm.executeUpdate();
	}

	public static void updateMovieNumCopies(Connection conn, int NumCopies, int id) throws SQLException {
		String sql = "UPDATE Movie SET Rating = ? WHERE  NumCopies = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setInt(1, NumCopies);
		pstm.setInt(2, id);

		pstm.executeUpdate();
	}

	// updates employee's hourly rate, really the only thing you could change in
	// an employee
	public static void updateEmployee(Connection conn, int rate, int id) throws SQLException {
		String sql = "UPDATE Employee SET HourlyRate = ? WHERE Id = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setInt(1, rate);
		pstm.setInt(2, id);

		pstm.executeUpdate();
	}

	// Obtain a Sales Report
	// Note that the query string does NOT have semicolons. I don't know if they
	// need them when there are multiple queries in a statment
	public static int obtainSalesReport(Connection conn, String Date) throws SQLException {
		String sql = 
				"CREATE TABLE Cost (" +
				"AcctType     CHAR(50)," +
				"MonthlyFee   INT," +
				"PRIMARY KEY(AcctType))" +

				"INSERT INTO Cost" + "VALUES('Limited', 10)" +

				"INSERT INTO Cost" + "VALUES('Unlimited-1', 15)" +

				"INSERT INTO Cost" + "VALUES('Unlimited-2', 20)" +

				"INSERT INTO Cost" + "VALUES('Unlimited-3', 25)" +

				"SELECT SUM(C.MonthlyFee)" + "FROM Account A, Cost C"
				+ "WHERE A.DateOpened >'?' AND A.AcctType = C.AcctType";

		PreparedStatement pstm = conn.prepareStatement(sql);
		// DATE FORMAT 2004-11-1
		pstm.setString(1, Date);

		ResultSet rs = pstm.executeQuery();
		int sum = rs.getInt(1);

		return sum;
	}

	
		// Return an employee to build a list
		public static Employee buildEmployee(ResultSet rs) throws SQLException {
			String FName = rs.getString("FName");
			String LName = rs.getString("LName");
			String address = rs.getString("address");
			int zip = rs.getInt("zip");
			String phone = rs.getString("phone");
			String city = rs.getString("city");
			String state = rs.getString("state");
			String ssn = rs.getString("ssn");

			Employee emp = new Employee(FName, LName, address, zip, phone,city,state);
			emp.setSsn(ssn);

			return emp;
		}
		
		
		//Get all Employees
		public static List<Employee> getEmployees(Connection conn) throws SQLException {
			String sql = "Select * From Employees;";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			List<Employee> allEmp = new ArrayList<Employee>();
			while (rs.next()) {
				Employee emp = buildEmployee(rs);
				allEmp.add(emp);
			}
			System.out.println(allEmp.size());
			return allEmp;
		}
		
		//Get Employee by id
		public static List<Employee> getEmployees(Connection conn, String id) throws SQLException {
			String sql = "Select * From Employee Where id = ;";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			List<Employee> allEmp = new ArrayList<Employee>();
			while (rs.next()) {
				Employee emp = buildEmployee(rs);
				allEmp.add(emp);
			}
			System.out.println(allEmp.size());
			return allEmp;
		}
		
	public static void insertEmployee(Connection conn, Employee employee) throws SQLException {
		String sql = "INSERT INTO Employee VALUES (?, ?, ?)";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, employee.getSsn());
		pstm.setString(2, employee.getStartDate());
		pstm.setDouble(3, employee.getHourlyRate());

		pstm.executeUpdate();

	}

	public static void deleteEmployee(Connection conn, String ssn) throws SQLException {
		String sql = "DELETE FROM Employee WHERE SSN=?";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, ssn);

		pstm.executeUpdate();

	}

	public static void customerRepOversawMostTrans(Connection conn, String ssn) throws SQLException {
		String sql = "FROM     Rental R, Employee E, Person P" + 
					 "WHERE    E.Id  = R.CustRepId AND" + "P.SSN = E.SSN" +
					 "GROUP BY CustRepId " +
					 "HAVING   COUNT(CustRepId) =" + 
					 		"( SELECT   COUNT(CustRepId) totalCount"+ 
					 		"FROM     Rental R, Employee E " +
					 		"WHERE    E.Id = R.CustRepId" +
					 		"GROUP BY CustRepId " +
					 		"ORDER BY totalCount DESC" + 
					 		"LIMIT 1)";

		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		
		// NOT FINSIHED

	}
	
	public static List<String> mostActivelyRentedMovies(Connection conn) throws SQLException{
		String sql = "SELECT   M.Name, COUNT(M.Id) totalCount" +
					 "FROM     Rental R, Movie M"+
					 "WHERE    M.Id = R.MovieId"+
					 "GROUP BY MovieId"+
					 "HAVING   totalCount >= 2;";
		
		List<String> activelyRentedMovies = new ArrayList<String>();
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		
		while(rs.next()){
			String movieName = rs.getString("Name");
			activelyRentedMovies.add(movieName);
		}
		
		return activelyRentedMovies;
	}
	
	/**
	 * END OF Manager Level Transaction
	 */

	public static List<Movie> getCustomersHeldMovies(Connection conn, String id) throws SQLException {
		String sql = "SELECT MovieId" + "FROM Rental" + "WHERE AccountId = ? AND EXISTS(" + "SELECT ReturnDate"
				+ "FROM MovieOrder" + "WHERE OrderId = Id AND ReturnDate > NOW());";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, id);

		ResultSet rs = pstm.executeQuery();

		List<Movie> list = new ArrayList<Movie>();

		while (rs.next()) {
			Movie movie = buildMovie(rs);
			list.add(movie);
		}

		return list;

	}
	// PERSON QUERIES /////////

	public static void insertPerson(Connection conn, Person person) throws SQLException {
		String sql = "INSERT INTO Person VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		pstm.setString(2, person.getFName());
		pstm.setString(3, person.getLName());
		pstm.setString(4, person.getAddress());
		pstm.setInt(5, person.getZip());
		pstm.setString(6, person.getPhone());

		pstm.executeUpdate();
	}

	public static void deletePerson(Connection conn, String SSN) throws SQLException {
		String sql = "DELETE FROM Employee WHERE SSN = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, SSN);

		pstm.executeUpdate();
	}

}
