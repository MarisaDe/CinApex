package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

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
		}else if(selector.equals("Actor")){
			return findMovieByActor(conn, keyword);
		}
		
		return null;
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

	public static List<Actors> findActorByName(Connection conn, String name) throws SQLException{
		String sql = "SELECT *  FROM Actor WHERE Name LIKE ?;";
		List<Actors> list = new ArrayList<Actors>();
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		name = name + "%";
		pstm.setString(1, name);
		
		ResultSet rs = pstm.executeQuery();
		
		while (rs.next()) {
			Actors actor = buildActor(rs);
			list.add(actor);
		}
		
		System.out.println("Actor by Name List : " + list.size());
		
		return list;
	}
	
	public static List<Movie> findMovieByActor(Connection conn, String name) throws SQLException{
		
		
		List<Movie> list = new ArrayList<Movie>();
		List<Actors> actorList = findActorByName(conn, name);
		List<Integer> movieId = new ArrayList<Integer>();
		
		// for each actor that matched
		for(int i = 0; i < actorList.size(); i++){
			String sql = "SELECT MovieId FROM appearedIn where ActorId = ?";
			
			Actors actor = actorList.get(i);
			int actorId = actor.getId();
			String actorName = actor.getName();
			System.out.println("actor Id : " + actorId + " actor Name : " + actorName);
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, actorId);
			ResultSet rs = pstm.executeQuery();
			
			// get movie id's
			while(rs.next()){
				movieId.add(rs.getInt("MovieId"));
			}
		}
		
		System.out.println("moveId List" + movieId.size());
		
		// use Movie Id to compose a list of movies with that Id;
		for(int i = 0; i < movieId.size(); i++){
			String sql2 = "SELECT * from Movie WHERE Id = ?";
			
			int Id = movieId.get(i);
			PreparedStatement pstm = conn.prepareStatement(sql2);
			pstm.setInt(1, Id);
			
			ResultSet rs2 = pstm.executeQuery();
			
			while(rs2.next()){
				Movie movie = buildMovie(rs2);
				list.add(movie);
			}
		}
		
		System.out.println("move by actor List" + list.size());
		
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
	// need them
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
			Employee emp = new Employee();
			emp.setAddress(rs.getString("Address"));
			emp.setFName(rs.getString("FirstName"));
			emp.setLName(rs.getString("LastName"));
			emp.setPhone(rs.getString("Telephone"));
			emp.setZip(rs.getInt("ZipCode"));
			emp.setHourlyRate(rs.getInt("HourlyRate"));
			emp.setId(rs.getInt("Id"));
			emp.setSsn(rs.getString("Ssn"));
			emp.setStartDate(rs.getString("StartDate"));
			
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
		public static Employee getEmployee(Connection conn, String id) throws SQLException {
			String sql = "Select * From Employee e JOIN Person p Where e.SSN = ? and e.SSN = p.SSN;";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, id);
			ResultSet rs = pstm.executeQuery();
			Employee emp=new Employee();
			if(rs.next()){
				emp = buildEmployee(rs);
			}
			System.out.println(emp.getFName());
			return emp;
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
	
	/**
	 * Finds the customer rep who over saw the most transactions
	 * Returns an object of CustomerRepOverSaw that contains
	 * the id of the customer rep and the FName, LName, and the
	 * total number of transactions that the customerRep has overseen
	 * @param conn
	 * @param ssn
	 * @return
	 * @throws SQLException
	 */
	public static CustomerRepOverSaw customerRepOversawMostTrans(Connection conn, String ssn) throws SQLException {
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
		
		CustomerRepOverSaw CROS = new CustomerRepOverSaw();
		
		while(rs.next()){
			int id = rs.getInt("CustRepId");
			String FName = rs.getString("FirstName");
			String LName = rs.getString("LastName");
			int count = rs.getInt("totalCount");
			
			CROS.setId(id);
			CROS.setFName(FName);
			CROS.setLName(LName);
			CROS.setCount(count);
		}
		
		return CROS;

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
	
	/**
	 *  Customer Rep Transactions
	 */
	
	public static MovieOrder buildMovieOrder(ResultSet rs) throws SQLException{
		
		MovieOrder movieOrder  = new MovieOrder();
		
		int id = rs.getInt("Id");
		String accountId = rs.getString("AccountId");
		int movieId = rs.getInt("MovieId");
		String dNT = rs.getString("DateAndTime");
		String rd = rs.getString("ReturnDate");

		movieOrder.setId(id);
		movieOrder.setAccountId(accountId);
		movieOrder.setMovieId(movieId);
		movieOrder.setDateAndTime(dNT);
		movieOrder.setReturnDate(rd);

		
		return movieOrder;
		
	}
	
	public static Rental buildRental(ResultSet rs) throws SQLException{
		
		Rental rental  = new Rental();
		
		String custRepId = rs.getString("AccountId");
		String accountId = rs.getString("CustRepId");
		int orderId = rs.getInt("OrderId");
		int movieId = rs.getInt("MovieId");

		rental.setCustRepId(custRepId);
		rental.setAccountId(accountId);
		rental.setOrderId(orderId);
		rental.setMovieId(movieId);

		
		return rental;
		
	}
	
	public static Actors buildActor(ResultSet rs) throws SQLException{
		
		Actors actor  = new Actors();
		
		int id = rs.getInt("Id");
		String name = rs.getString("Name");
		int age = rs.getInt("Age");
		char sex = rs.getString("Sex").charAt(0);
		int rating = rs.getInt("Rating");

		actor.setId(id);
		actor.setName(name);
		actor.setAge(age);
		actor.setSex(sex);
		actor.setRating(rating);

		
		return actor;
		
	}
	
	// Adding an Order should call bother insertMovieOrder and insertRental
	
	public static void insertMovieOrder(Connection conn, MovieOrder movieOrder) throws SQLException{
		String sql = "INSERT INTO MovieOrder(Id, AccountId, MovieId, DateAndTime, ReturnDate) VALUES(?, ?, ?, ?, ?)";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		pstm.setInt(1, movieOrder.getId());
		pstm.setString(2, movieOrder.getAccountId());
		pstm.setInt(3, movieOrder.getMovieId());
		pstm.setString(4, movieOrder.getDateAndTime());
		pstm.setInt(5, movieOrder.getReturnDate());
		
		pstm.executeUpdate();
	}
	
	public static void insertRental(Connection conn, Rental rental) throws SQLException{
		String sql = "INSERT INTO rental(AccountId, CustRepId, OrderId, MovieId) VALUES (?, ?, ?,?)";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		//ResultSet rs = pstm.executeQuery();
		//Rental rental = buildRental(rs);
		
		pstm.setString(1, rental.getCustRepId());
		pstm.setString(2, rental.getAccountId());
		pstm.setInt(3, rental.getOrderId());
		pstm.setInt(4, rental.getMovieId());
		
		pstm.executeUpdate();
	}
	
	public static List<String> getMailingList(Connection conn) throws SQLException{
		String sql = "SELECT C.email FROM Customer C";
		
		List<String> mailingList = new ArrayList<String>();
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		
		while(rs.next()){
			String email = rs.getString("email");
			mailingList.add(email);
		}
		
		return mailingList;
	}
	
	public static void insertActor(Connection conn, Actors actor) throws SQLException{
		String sql = "INSERT INTO Actor(Id, Name, Age, Sex, Rating) VALUES(?, ?, ?, ?, ?)";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		//ResultSet rs = pstm.executeQuery();
		//Actors actor = buildActor(rs);
		
		pstm.setInt(1, actor.getId());
		pstm.setString(2, actor.getName());
		pstm.setInt(3, actor.getAge());
		pstm.setInt(4, actor.getSex());
		pstm.setInt(4, actor.getRating());
		
		pstm.executeUpdate();
		
	}
	
	
	/**
	 *  Customer Level TransActions
	 */
	
	public static HashMap<Integer, String> getCustomerQueue(Connection conn, int id) throws SQLException{
		String sql = "SELECT MovieId, Name FROM MovieQ, Movie WHERE AccountId = ? AND MovieId=Id";
		HashMap<Integer, String> customerQueue = new HashMap<Integer, String>();
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, id);
		
		ResultSet rs = pstm.executeQuery();
		
		while(rs.next()){
			int movieId = rs.getInt("MovieId");
			String movieName = rs.getString("Name");
			
			customerQueue.put(movieId, movieName);
		}
		
		return customerQueue;
		
	}
	
	public static void getCustomerSetting(Connection conn, String id) throws SQLException{
		String sql = "SELECT * FROM Customer WHERE Id = ?";
		
		
	}
	
	public static void getCustomerHistory(Connection conn, int accountId) throws SQLException{
		String sql = "SELECT * FROM MovieOrder  WHERE DateAndTime <= NOW() AND AccountId = ? ORDER BY DateAndTime";
		
	}
	
	public static void getBestSellerList(Connection conn) throws SQLException{
		String sql = "SELECT Name, Rating FROM Movie ORDER BY Rating DESC LIMIT 2";
		
	}
	
	public static void getPersonalizeMovieSuggestions(Connection conn, int accountId) throws SQLException{
		String sql = "SELECT m1.Name FROM Movie m1 WHERE m1.Type =  SELECT m.Type From Movie m, Rental r WHERE m.Id=r.MovieId AND r.AccountId= ?";
		
		
	}


	public static Person loginChoice(Connection conn, String name, String personType) throws SQLException {
		if (personType.equals("Employee")){
			return getEmployee(conn,name);
		}else{
			return getCustomer(conn,name);
		}
	}
	
	public static Customer getCustomer(Connection conn, String name) throws SQLException{
		String sql = "Select * From Customer c JOIN Person p Where c.Id = ? and c.Id = p.SSN;";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, name);
		ResultSet rs = pstm.executeQuery();
		Customer cus=new Customer();
		if(rs.next()){
			cus = buildCustomer(rs);
		}
		System.out.println(cus.getFName());
		return cus;
	}


	private static Customer buildCustomer(ResultSet rs) throws SQLException {
		Customer emp = new Customer();
		emp.setAddress(rs.getString("Address"));
		emp.setFName(rs.getString("FirstName"));
		emp.setLName(rs.getString("LastName"));
		emp.setPhone(rs.getString("Telephone"));
		emp.setZip(rs.getInt("ZipCode"));
		emp.setcCard(rs.getString("CreditCardNumber"));
		emp.setRating(rs.getInt("Rating"));
		emp.setEmail(rs.getString("Email"));
		
		return emp;
	}
	
}
