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
		System.out.println(allMovies.size());
		return allMovies;
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

	public static Movie findMovie(Connection conn, int id) throws SQLException {
		String sql = "Select * from Movie where id=?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, id);

		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			Movie movie = buildMovie(rs);

			if (movie != null) {
				return movie;
			}
		}
		return null;
	}

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

	public static void deleteMovie(Connection conn, int id) throws SQLException {
		String sql = "DELETE FROM Movie WHERE Id = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setInt(1, id);

		pstm.executeUpdate();
	}

	// PERSON DATA

	public static void insertPerson(Connection conn, Person person) throws SQLException {
		String sql = "INSERT INTO Person VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, person.getId());
		pstm.setString(2, person.getFName());
		pstm.setString(3, person.getLName());
		pstm.setString(4, person.getAddress());
		pstm.setInt(5, person.getZip());
		pstm.setString(6, person.getPhone());

		pstm.executeUpdate();
	}

}
