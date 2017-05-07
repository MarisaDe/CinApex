package Beans;

public class UserRatings {
	private String customerId;
	private int MovieId;
	private int Rating;
	
	public UserRatings(){
		
	}

	public UserRatings(String customerId, int movieId, int rating) {
		super();
		this.customerId = customerId;
		MovieId = movieId;
		Rating = rating;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public int getMovieId() {
		return MovieId;
	}

	public void setMovieId(int movieId) {
		MovieId = movieId;
	}

	public int getRating() {
		return Rating;
	}

	public void setRating(int rating) {
		Rating = rating;
	}
	
	
}
