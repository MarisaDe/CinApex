package Beans;

public class Location {
	private int zip;
	private String city;
	private String state;
	
	public Location(){
		
	}
	
	public Location(int zip, String city, String state) {
		this.zip = zip;
		this.city = city;
		this.state = state;
	}
	

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	
}
