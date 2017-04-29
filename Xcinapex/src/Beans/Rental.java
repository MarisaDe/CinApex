package Beans;

public class Rental {
	
	private String accountId;
	private String custRepId;
	private int OrderId;
	private int movieId;
	
	public Rental(){
		
	}

	public Rental(String accountId, String custRepId, int orderId, int movieId) {
		this.accountId = accountId;
		this.custRepId = custRepId;
		OrderId = orderId;
		this.movieId = movieId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getCustRepId() {
		return custRepId;
	}

	public void setCustRepId(String custRepId) {
		this.custRepId = custRepId;
	}

	public int getOrderId() {
		return OrderId;
	}

	public void setOrderId(int orderId) {
		OrderId = orderId;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
}
