package Beans;

public class Rental {
	
	private String accountId;
	private int custRepId;
	private int OrderId;
	private int movieId;
	
	public Rental(){
		
	}

	public Rental(String accountId, int custRepId, int orderId, int movieId) {
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

	public int getCustRepId() {
		return custRepId;
	}

	public void setCustRepId(int custRepId2) {
		this.custRepId = custRepId2;
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
