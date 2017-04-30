package Beans;

public class AppearedIn {
	
	private int actorId;
	public AppearedIn(int actorId, int movieId) {
		super();
		this.actorId = actorId;
		this.movieId = movieId;
	}

	private int movieId;
	
	public AppearedIn(){
		
	}

	public int getActorId() {
		return actorId;
	}

	public void setActorId(int actorId) {
		this.actorId = actorId;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

}
