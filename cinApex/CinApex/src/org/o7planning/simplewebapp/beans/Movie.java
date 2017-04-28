package org.o7planning.simplewebapp.beans;

public class Movie {

	private int id;
	private String name;
	private String type;
	private int rating;
	private int distrFee;
	private int numCopies;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
		return distrFee;
	}
	public void setDistrFee(int distrFee) {
		this.distrFee = distrFee;
	}
	public int getNumCopies() {
		return numCopies;
	}
	public void setNumCopies(int numCopies) {
		this.numCopies = numCopies;
	}
	
}