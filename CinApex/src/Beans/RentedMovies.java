package Beans;

public class RentedMovies {

	private int accountId;
	private String firstName;
	private String lastName;
	private int custRepId;
	private int orderId;
	private int movieId;
	private String name;
	private String type;
	private int rating;
	private int DistrFee;
	private int numCopies;

	public RentedMovies() {

	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getCustRepId() {
		return custRepId;
	}

	public void setCustRepId(int custRepId) {
		this.custRepId = custRepId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getDistrFee() {
		return DistrFee;
	}

	public void setDistrFee(int distrFee) {
		DistrFee = distrFee;
	}

	public int getNumCopies() {
		return numCopies;
	}

	public void setNumCopies(int numCopies) {
		this.numCopies = numCopies;
	}

}
