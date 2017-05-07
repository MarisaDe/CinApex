package Beans;

public class MovieQ {
	
	private String accountId;
	private int movieId;
	
	//Default constructor
	public MovieQ(){
		
	}

	public MovieQ(String accountId, int movieId) {
		this.accountId = accountId;
		this.movieId = movieId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	
	
	
}
