package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp.*;
import java.sql.Date.*;
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
	

	public static List<Movie> listAllMovies(Connection conn) throws SQLException {
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
	
	
	public static List<Movie> queryMovies(Connection conn) throws SQLException {
		String sql = "Select * From movie where numcopies>0;";
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

	public static Movie findMovieById(Connection conn, int id) throws SQLException{
		String sql = "Select * from Movie where Id = ?";


		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, id);

		ResultSet rs = pstm.executeQuery();
		
		Movie movie = new Movie();
		movie = buildMovie(rs);
		
		return movie;
	}

	public static List<Movie> findMovieByType(Connection conn, String type) throws SQLException {
		String sql = "Select * from Movie where Type=? and numcopies>0";


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
		String sql = "SELECT *  FROM Movie WHERE Name LIKE ? and numcopies>0;";

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

	// Return an employee to build a list
	public static UserRatings buildUserRating(ResultSet rs) throws SQLException {
		UserRatings ur = new UserRatings ();
		ur.setCustomerId(rs.getString("CustomerId"));
		ur.setMovieId(rs.getInt("MovieId"));
		ur.setRating(rs.getInt("Rating"));

		return ur;
	}
	
	public static void addUserRating(Connection conn, String custId, int movieId) throws SQLException {
		String sql = "INSERT INTO userratings VALUES(?,?,NULL)";

	    System.out.println(custId);
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, custId);
		pstm.setInt(2, movieId);

	    pstm.executeUpdate();
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
			String sql2 = "SELECT * from Movie WHERE Id = ? and numcopies>0";
			
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
	
	public static void deleteMovieOrderByMovieId(Connection conn, int id)throws SQLException {
		String sql = "DELETE FROM  movieOrder WHERE MovieId = ?";
		
		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setInt(1, id);

		pstm.executeUpdate();
	}
	
	public static int  getZipBySSN(Connection conn, String ssn) throws SQLException{
		String sql = "SELECT ZipCode from person where ssn = ?";
		int zip = -1;
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, ssn);
		
		ResultSet rs = pstm.executeQuery();
		
		if(rs.next()){
			zip = rs.getInt("ZipCode");
		}
		
		return zip;
	}
	
	public static void deleteLocation(Connection conn, int zip) throws SQLException{
		// TODO Auto-generated method stub
		String sql = "DELETE FROM Location where ZipCode = ?";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, zip);
		
		pstm.executeUpdate();
		
	}
	
	public static void deleteRentalByMovieId(Connection conn, int id)throws SQLException {
		String sql = "DELETE FROM  rental WHERE MovieId = ?";
		
		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setInt(1, id);

		pstm.executeUpdate();
	}
	
	public static void deleteMovieQByMovieId(Connection conn, int id)throws SQLException {
		String sql = "DELETE FROM movieq WHERE MovieId = ?";
		
		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setInt(1, id);

		pstm.executeUpdate();
	}
	
	public static void deleteAppearedInByMovieId(Connection conn, int id)throws SQLException {
		String sql = "DELETE FROM appearedIn WHERE MovieId = ?";
		
		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setInt(1, id);

		pstm.executeUpdate();
	}
	
	public static void deleteMovie(Connection conn, int id) throws SQLException {
		String sql = "DELETE FROM Movie WHERE Id = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setInt(1, id);

		pstm.executeUpdate();
	}
	
	public static void updateMovieName(Connection conn, String name, int id) throws SQLException {
		String sql = "UPDATE Movie SET Name = ? WHERE  Id = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, name);
		pstm.setInt(2, id);

		pstm.executeUpdate();

	}

	public static void updateMovieType(Connection conn, String type, int id) throws SQLException {
		String sql = "UPDATE Movie SET Type = ? WHERE  Id = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, type);
		pstm.setInt(2, id);

		pstm.executeUpdate();

	}

	public static void updateMovieRating(Connection conn, int rating, int id) throws SQLException {
		String sql = "UPDATE Movie SET Rating = ? WHERE  Id = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setInt(1, rating);
		pstm.setInt(2, id);

		pstm.executeUpdate();
	}

	public static void updateMovieDistrFee(Connection conn, int fee, int id) throws SQLException {
		String sql = "UPDATE Movie SET DistrFee = ? WHERE  Id = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setInt(1, fee);
		pstm.setInt(2, id);

		pstm.executeUpdate();
	}

	public static void updateMovieNumCopies(Connection conn, int NumCopies, int id) throws SQLException {
		String sql = "UPDATE Movie SET NumCopies = ? WHERE  Id = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setInt(1, NumCopies);
		pstm.setInt(2, id);

		pstm.executeUpdate();
	}
	public static void updateCustomer(Connection conn, String id, String first, String last, String address, int zip, String phone, String email, String cCard, int rating )throws SQLException{
		String sql="UPDAte person p,customer c SEt p.lastname=? ,p.firstname=?,p.address=?, p.zipcode=?"
				+ " , p.telephone=?, c.email=?,c.rating=?,creditcardnumber=? where p.ssn=c.id and c.id=? ;";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, last);
		pstm.setString(2, first);
		pstm.setString(3, address);
		pstm.setInt(4,zip);
		pstm.setString(5, phone);
		pstm.setString(6, email);
		pstm.setInt(7, rating);
		pstm.setString(8, cCard);
		pstm.setString(9, id);
		pstm.executeUpdate();
	}
	
	public static void updateEmployeeAll(Connection conn, String ssn, String first, String last, String address, int zip, String phone, String startDate, int hourRate )throws SQLException{
		String sql="UPDATE person p,employee e SET p.lastname=? ,p.firstname=?,p.address=?, p.zipcode=?"
				+ " , p.telephone=?, e.startdate=?,e.hourlyrate=? WHERE p.ssn= ? and e.ssn = p.ssn";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, last);
		pstm.setString(2, first);
		pstm.setString(3, address);
		pstm.setInt(4,zip);
		pstm.setString(5, phone);
		pstm.setString(6, startDate);
		pstm.setInt(7, hourRate);
		pstm.setString(8, ssn);
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
	
	public static List<Account> AccountsForAGivenMonth(Connection conn, String Date) throws SQLException{
		String sql = "SELECT * FROM Account where DateOpened > ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		java.sql.Date date = java.sql.Date.valueOf(Date);
		pstm.setDate(1, date);
		
		List<Account> list = new ArrayList<Account>();
		ResultSet rs = pstm.executeQuery();
		
		while(rs.next()){
			Account acc = new Account();
			int id = rs.getInt("Id");
			String dateOpened = rs.getString("DateOpened");
			String customerId = rs.getString("CustomerId");
			String type = rs.getString("AccType");
			
			acc.setId(id);
			acc.setDate(dateOpened);
			acc.setCustomerId(customerId);
			acc.setType(type);
			
			list.add(acc);
		}
		
		return list;
		
	}
	// Obtain a Sales Report
	public static int obtainSalesReport(Connection conn, String Date) throws SQLException {
		String sql = "SELECT SUM(C.MonthlyFee) FROM Account A, Cost C WHERE A.DateOpened >? AND A.AccType = C.AcctType";

		PreparedStatement pstm = conn.prepareStatement(sql);
		// DATE FORMAT 2004-11-1
		System.out.println(Date);
		java.sql.Date date = java.sql.Date.valueOf(Date);
		pstm.setDate(1, date);

		ResultSet rs = pstm.executeQuery();
		int sum = 0;
		if(rs.next()){
			sum = rs.getInt("SUM(C.MonthlyFee)");
		}
		System.out.println("MONTHLY SUM : " + sum);

		return sum;
	}

	
		// Return an employee to build a list
		public static Employee buildEmployee(ResultSet rs) throws SQLException {
			Employee emp = new Employee();
			emp.setAddress(rs.getString("Address"));
			emp.setFirstName(rs.getString("FirstName"));
			emp.setLastName(rs.getString("LastName"));
			emp.setTelephone(rs.getString("Telephone"));
			emp.setZipcode(rs.getInt("ZipCode"));
			emp.setHourlyRate(rs.getInt("HourlyRate"));

			emp.setId(rs.getInt("Id"));
			emp.setSsn(rs.getString("Ssn"));
			emp.setStartDate(rs.getString("startDate"));
			
			return emp;
		}
		
		
		//Get all Employees
		public static List<Employee> getEmployees(Connection conn) throws SQLException {
			String sql = "Select * From Employee e JOIN Person p Where e.SSN = p.SSN;";
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
				System.out.println(emp.getFirstName());
				return emp;
			}else{
				return null;
			}
			
		}
		
	public static void insertEmployee(Connection conn, Employee employee) throws SQLException {
		String sql = "INSERT INTO Employee VALUES (?, ?, ?, ?)";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setInt(1, employee.getId());
		pstm.setString(2, employee.getSsn());
		pstm.setString(3, employee.getStartDate());
		pstm.setDouble(4, employee.getHourlyRate());

		pstm.executeUpdate();

	}

	public static void deleteEmployee(Connection conn, String ssn, int custId) throws SQLException {
		//String sql = "SELECT custRepId FROM Rental r WHERE ?= r.custRepId";
		//PreparedStatement pstm = conn.prepareStatement(sql);
		
		//pstm.setInt(1, custId);
		//ResultSet rs = pstm.executeQuery();
			
		deleteRental(conn, custId);
		
		String sql2 = "DELETE FROM Employee WHERE SSN=?";

		PreparedStatement pstm2 = conn.prepareStatement(sql2);

		pstm2.setString(1, ssn);

		pstm2.executeUpdate();
		
		deletePerson(conn, ssn);

	}
	
	public static void deleteMovieOrderByAccount(Connection conn, int acctId) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM  movieOrder WHERE AccountId  = ?";
		
		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setInt(1, acctId);

		pstm.executeUpdate();
	}


	public static void deleteMovieQByAcctId(Connection conn, int acctId) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM  movieq WHERE AccountId = ?";
		
		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setInt(1, acctId);

		pstm.executeUpdate();
	}
	
	public static void deleteRental(Connection conn, int custId) throws SQLException {
		String sql = "DELETE FROM Rental WHERE CustRepId=?";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setInt(1, custId);

		pstm.executeUpdate();

	}
	
	
	public static void deleteAccount(Connection conn, String custId) throws SQLException {
		String sql = "DELETE FROM Account WHERE CustomerId=?";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, custId);

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
	
	public static RentedMovies buildRentedMovies(ResultSet rs) throws SQLException{
		RentedMovies rentedMovies = new RentedMovies();
				
		int accountId = rs.getInt("AccountId");
		String firstName = rs.getString("FirstName");
		String lastName= rs.getString("LastName");
		int custRepId = rs.getInt("CustRepId");
		int orderId = rs.getInt("OrderId");
		int movieId = rs.getInt("MovieId");
		String name = rs.getString("Name");
		String type = rs.getString("Type");
		int rating = rs.getInt("Rating");
		int distrFee = rs.getInt("DistrFee");
		int numCopies = rs.getInt("NumCopies");

		rentedMovies.setAccountId(accountId);
		rentedMovies.setFirstName(firstName);
		rentedMovies.setLastName(lastName);
		rentedMovies.setCustRepId(custRepId);
		rentedMovies.setOrderId(orderId);
		rentedMovies.setMovieId(movieId);
		rentedMovies.setName(name);
		rentedMovies.setType(type);
		rentedMovies.setRating(rating);
		rentedMovies.setDistrFee(distrFee);
		rentedMovies.setNumCopies(numCopies);
		
		return rentedMovies;
	}
	
	public static List<RentedMovies> findRentals(Connection conn, String keyword, String selector) throws SQLException{
		if(selector.equals("Title")){
			String percent="%";
			String name=percent+keyword+percent;
			return ListOfRentalMoviesByName(conn,name);
		}else if (selector.equals("Id")){
			return ListOfRentalMoviesByType(conn,keyword);
		}else if(selector.equals("Customer")){
			return ListOfRentalMoviesByCustName(conn, keyword);
		}
		
		return null;
	}
	
	public static List<RentedMovies> ListOfRentalMoviesByName(Connection conn, String name) throws SQLException{
		String sql = "SELECT  R.AccountId, P.FirstName, P.LastName, R.CustRepId , R.OrderId ,R.MovieId, M.Name, M.Type, M.Rating, M.DistrFee, M.NumCopies  FROM    Rental R, Movie M, Person P, Account A WHERE   R.MovieId = M.Id AND M.Name LIKE ? AND A.CustomerId = P.SSN AND R.AccountId = A.Id";
	
		PreparedStatement pstm = conn.prepareStatement(sql);
		name = "%" + name + "%";
		pstm.setString(1, name);
		ResultSet rs = pstm.executeQuery();
		
		List<RentedMovies> list = new ArrayList<RentedMovies>();
		while(rs.next()){
			RentedMovies RM = buildRentedMovies(rs);
			list.add(RM);
		}
		
		return list;
	}
	
	public static List<RentedMovies> ListOfRentalMoviesByType(Connection conn, String type) throws SQLException{
		String sql = "SELECT  R.AccountId, P.FirstName, P.LastName, R.CustRepId , R.OrderId ,R.MovieId, M.Name, M.Type, M.Rating, M.DistrFee, M.NumCopies  FROM    Rental R, Movie M, Person P, Account A WHERE   R.MovieId = M.Id AND M.Type LIKE ?  AND A.CustomerId = P.SSN AND R.AccountId = A.Id";
	
		PreparedStatement pstm = conn.prepareStatement(sql);
		type = "%" + type + "%";
		pstm.setString(1, type);
		ResultSet rs = pstm.executeQuery();
		
		List<RentedMovies> list = new ArrayList<RentedMovies>();
		while(rs.next()){
			RentedMovies RM = buildRentedMovies(rs);
			list.add(RM);
		}
		
		return list;
	}
	
	
	public static List<RentedMovies> ListOfRentalMoviesByCustName(Connection conn, String name) throws SQLException{
		String sql = "SELECT  R.AccountId, P.FirstName, P.LastName, R.CustRepId , R.OrderId ,R.MovieId, M.Name, M.Type, M.Rating, M.DistrFee, M.NumCopies  FROM    Rental R, Movie M, Person P, Account A WHERE   R.MovieId = M.Id AND P.FirstName LIKE ? OR P.LastName LIKE ? AND A.CustomerId = P.SSN AND R.AccountId = A.Id";
	
		PreparedStatement pstm = conn.prepareStatement(sql);
		name = "%" + name + "%";
		pstm.setString(1, name);
		pstm.setString(2, name);
		//pstm.setString(2, lastName);
		ResultSet rs = pstm.executeQuery();
		
		List<RentedMovies> list = new ArrayList<RentedMovies>();
		while(rs.next()){
			RentedMovies RM = buildRentedMovies(rs);
			list.add(RM);
		}
		
		return list;
	}
	
	public static List<ActiveRented> listOfActivelyRentedMovies(Connection conn) throws SQLException{
		String sql = "SELECT M.ID, M.Name, M.Rating, O.NumOrders FROM OrderList O, Movie M WHERE O.MovieId = M.ID AND O.NumOrders >= (SELECT MAX(R.NumOrders) FROM OrderList R)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		
		List<ActiveRented> list = new ArrayList<ActiveRented>();
		while(rs.next()){
			int id = rs.getInt("Id");
			String name = rs.getString("Name");
			int rating = rs.getInt("Rating");
			int numOrders = rs.getInt("NumOrders");
			
			ActiveRented AR = new ActiveRented();
			
			AR.setId(id);
			AR.setName(name);
			AR.setRating(rating);
			AR.setNumOrders(numOrders);
			
			list.add(AR);
		}
		
		return list;
	}
	
	/**
	 * END OF Manager Level Transaction
	 * @throws SQLException 
	 */

	public static int getAccountIdFromCustomerId(Connection conn, String custid) throws SQLException{
		String msql="SELECT a.id from customer c JOIN account a  where a.customerid = c.id and c.id =?";
		PreparedStatement ps = conn.prepareStatement(msql);
		ps.setString(1, custid);
		ResultSet rs= ps.executeQuery();
		int i=0;
		while (rs.next()){
			i=rs.getInt(1);
		}
		return i;
		
	}
	
	public static String getCustomerIdFromAccountId(Connection conn, String accid) throws SQLException{
		String msql="SELECT a.Customerid FROM account a JOIN customer c  where a.customerid = c.id and a.id =?";
		PreparedStatement ps = conn.prepareStatement(msql);
		ps.setString(1, accid);
		ResultSet rs= ps.executeQuery();
		String i = null;
		while (rs.next()){
			i=rs.getString(1);
		}
		return i;
		
	}
	
	
	public static List<Movie> getCustomersHeldMovies(Connection conn, String custid) throws SQLException {
		int id=getAccountIdFromCustomerId(conn,custid);
		
		String sql = "SELECT * FROM Rental r JOIN movie m WHERE AccountId =? AND EXISTS( SELECT"+
				 " ReturnDate FROM MovieOrder WHERE OrderId = Id AND ReturnDate > NOW()) and r.mov"+
				"ieid = m.id;";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, id);

		ResultSet rs = pstm.executeQuery();

		List<Movie> list = new ArrayList<Movie>();

		while (rs.next()) {
			Movie movie = buildMovie(rs);
			list.add(movie);
		}

		return list;

	}

	
	// PERSON QUERIES /////////
	
	public static void insertLocation(Connection conn, Location location) throws SQLException{
		String sql = "INSERT INTO Location(ZipCode, City, State) VALUES (?, ?, ?);";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		pstm.setInt(1, location.getZip());
		pstm.setString(2, location.getCity());
		pstm.setString(3, location.getState());
		
		pstm.executeUpdate();
	}
	
	public static void insertPerson(Connection conn, Person person) throws SQLException {
		String sql = "INSERT INTO Person VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		pstm.setString(1, person.getSSN());
		pstm.setString(2, person.getFirstName());
		pstm.setString(3, person.getLastName());
		pstm.setString(4, person.getAddress());
		pstm.setInt(5, person.getZipcode());
		pstm.setString(6, person.getTelephone());

		pstm.executeUpdate();
	}

	public static void deletePerson(Connection conn, String SSN) throws SQLException {
		String sql = "DELETE FROM Person WHERE SSN = ?";

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
		
		int custRepId = rs.getInt("CustRepId");
		String accountId = rs.getString("AccountId");
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
		
		System.out.println(" DNT :" + movieOrder.getDateAndTime());
		System.out.println(" RD  :" + movieOrder.getReturnDate());
		
		java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		java.sql.Date returnDate = java.sql.Date.valueOf(movieOrder.getReturnDate());
		
		pstm.setTimestamp(4, date);
		pstm.setDate(5, returnDate);
		
		pstm.executeUpdate();
	}
	
	public static void decrementMovie(Connection conn, int movieId,MovieOrder movieO) throws SQLException {
		// TODO Auto-generated method stub
		String sql="UPDATE movie set numcopies=numcopies-1 where id=? and numcopies>0;";
		PreparedStatement pstm=conn.prepareStatement(sql);
		
		pstm.setInt(1, movieId);
		pstm.executeUpdate();
		insertMovieOrder(conn,movieO);
		
	}


	public static void insertRental(Connection conn, Rental rental) throws SQLException{
		String sql = "INSERT INTO rental(AccountId, CustRepId, OrderId, MovieId) VALUES (?, ?, ?,?)";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		//ResultSet rs = pstm.executeQuery();
		//Rental rental = buildRental(rs);
		
		pstm.setString(1, rental.getAccountId());
		pstm.setInt(2, rental.getCustRepId());
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
	
	public static List<Movie> getCustomerQueue(Connection conn, String custid) throws SQLException{
		int id = getAccountIdFromCustomerId(conn,custid);
		
		String sql = "SELECT * FROM MovieQ JOIN Movie WHERE AccountId = ? AND MovieId=Id";
		List<Movie> customerQueue = new ArrayList<Movie>();
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, id);
		
		ResultSet rs = pstm.executeQuery();
		
		while (rs.next()) {
			Movie movie = buildMovie(rs);
			customerQueue.add(movie);
		}

		return customerQueue;
		
	}
	
	public static void deleteCustomer(Connection conn, String ssn) throws SQLException {
		String sql2 = "DELETE FROM Customer WHERE Id=?";
		PreparedStatement pstm2 = conn.prepareStatement(sql2);

		pstm2.setString(1, ssn);
		pstm2.executeUpdate();

	}
	
	public static void insertCustomer(Connection conn, Customer customer) throws SQLException{
		String sql = "INSERT INTO Customer VALUES (?, ?, ?, ?)";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, customer.getCustId());
		pstm.setString(2, customer.getEmail());
		pstm.setInt(3, customer.getRating());
		pstm.setString(4, customer.getCCard());

		pstm.executeUpdate();

		}
	
	public static void getCustomerSetting(Connection conn, String id) throws SQLException{
		String sql = "SELECT * FROM Customer WHERE Id = ?";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, id);
		
		
	}
	
	public static List<Movie> getCustomerHistory(Connection conn, String custId) throws SQLException{
		int id = getAccountIdFromCustomerId(conn,custId);
		String sql = "SELECT * FROM Rental r JOIN movie m JOIN movieorder b WHERE r.AccountId =? AND EXISTS( SELECT ReturnDate FROM MovieOrder WHERE OrderId = Id) and r.movieid = m.id and b.id=r.orderid ORDER BY b.dateandtime desc;";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, id);

		ResultSet rs = pstm.executeQuery();

		List<Movie> list = new ArrayList<Movie>();

		while (rs.next()) {
			Movie movie = buildMovie(rs);
			System.out.println(movie.getName());
			list.add(movie);
		}
		return list;
	}
	
	public static HashMap<String,Integer> getBestSellerList(Connection conn) throws SQLException{
		String sql = "SELECT Name, Rating FROM Movie ORDER BY Rating DESC LIMIT 2";
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		HashMap<String, Integer> bestSellerList = new HashMap<String, Integer>();
		
		ResultSet rs = pstm.executeQuery();
		
		while(rs.next()){
			int movieRating = rs.getInt("Name");
			String movieName = rs.getString("Rating");
			
			bestSellerList.put(movieName, movieRating);
		}
		return bestSellerList;
	}
	
	public static List<Movie> getPersonalizeMovieSuggestions(Connection conn, String custId) throws SQLException{
		int id=getAccountIdFromCustomerId(conn,custId);
		String sql = "select *  from movie where id not in( select MovieId from rental where accountid=?) and type in( select type from rental, movie where accountid =? and id=movieid );";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, id);
		pstm.setInt(2, id);

		ResultSet rs = pstm.executeQuery();

		List<Movie> list = new ArrayList<Movie>();

		while (rs.next()) {
			Movie movie = buildMovie(rs);
			list.add(movie);
		}

		return list;
		
	}


	public static Person loginChoice(Connection conn, String name, String personType) throws SQLException {
		if (personType.equals("Employee")){
			System.out.println("empl");
			return getEmployee(conn,name);
		}else if (personType.equals("Customer")){
			System.out.print("Cust");
			return getCustomer(conn,name);
		}else{
			System.out.println("wrong");
			return null;
		}
	}
	
	// Return an employee to build a list
	public static Customer buildCustomers(ResultSet rs) throws SQLException {
		Customer cust = new Customer();
		cust.setAddress(rs.getString("Address"));
		cust.setFirstName(rs.getString("FirstName"));
		cust.setLastName(rs.getString("LastName"));
		cust.setTelephone(rs.getString("Telephone"));
		cust.setZipcode(rs.getInt("ZipCode"));
		cust.setEmail(rs.getString("Email"));
		cust.setRating(rs.getInt("Rating"));
		cust.setCCard(rs.getString("CreditCardNumber"));
		cust.setCustId(rs.getString("ID"));

		return cust;
	}
	
	
	//Get all Customers
	public static List<Customer> getCustomers(Connection conn) throws SQLException {
		String sql = "Select * From Customer e JOIN Person p Where e.Id = p.SSN;";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();

		List<Customer> allCust = new ArrayList<Customer>();
		while (rs.next()) {
			Customer emp = buildCustomers(rs);
			allCust.add(emp);
		}
		System.out.println(allCust.size());
		return allCust;
	}
	
	public static Customer getCustomer(Connection conn, String name) throws SQLException{
		String sql = "Select * From Customer c JOIN Person p Where c.Id = ? and c.Id = p.SSN;";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, name);
		ResultSet rs = pstm.executeQuery();
		Customer cus=new Customer();
		if(rs.next()){
			cus = buildCustomer(rs);
			System.out.println(cus.getFirstName()+cus.getLastName());
			return cus;
		}else{
			return null;
		}
		
	}


	private static Customer buildCustomer(ResultSet rs) throws SQLException {
		Customer emp = new Customer();
		emp.setAddress(rs.getString("Address"));
		emp.setFirstName(rs.getString("FirstName"));
		emp.setLastName(rs.getString("LastName"));
		emp.setTelephone(rs.getString("Telephone"));
		emp.setZipcode(rs.getInt("ZipCode"));
		emp.setCCard(rs.getString("CreditCardNumber"));
		emp.setRating(rs.getInt("Rating"));
		emp.setEmail(rs.getString("Email"));
		emp.setCustId(rs.getString("Id"));
		
		return emp;
	}


	public static void editUserRating(Connection conn, String id, int movId, int userrating) throws SQLException {
		// TODO Auto-generated method stub
		String sql= "update userratings set rating=? where customerid=? and movieid=?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		pstm.setInt(1, userrating);
		pstm.setString(2, id);
		pstm.setInt(3, movId);
		pstm.executeUpdate();
		changeMoviesRating(conn, movId);
		
	}


	private static void changeMoviesRating(Connection conn, int movId) throws SQLException {
		// TODO Auto-generated method stub\
		String sql= "update movie m, (select avg(rating) avgrate from userratings where userratings.movieid=?) s SET m"
				+ ".rating = s.avgrate where m.id=? ";
		PreparedStatement pstm=conn.prepareStatement(sql);
		pstm.setInt(1, movId);
		pstm.setInt(2, movId);
		pstm.executeUpdate();
	}
	public static void insertAccount(Connection conn, Account acct) throws SQLException {
		
		String sql = "INSERT INTO Account VALUES(?, ?, ?, ?);";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setInt(1, acct.getId());
		pstm.setString(2, acct.getDate());
		pstm.setString(3, acct.getType());
		pstm.setString(4, acct.getCustomerId());

		pstm.executeUpdate();
	}

	
}
