package Beans;

public class Movie {

	private int Id;
	private String Name;
	private String Type;
	private int Rating;
	private int DistrFee;
	private int NumCopies;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public int getRating() {
		return Rating;
	}
	public void setRating(int rating) {
		Rating = rating;
	}
	public int getDistrFee() {
		return DistrFee;
	}
	public void setDistrFee(int distrFee) {
		DistrFee = distrFee;
	}
	public int getNumCopies() {
		return NumCopies;
	}
	public void setNumCopies(int numCopies) {
		NumCopies = numCopies;
	}
	
	
}
