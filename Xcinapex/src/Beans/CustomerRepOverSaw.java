package Beans;

public class CustomerRepOverSaw {
	private int id;
	private String FName;
	private String LName;
	private int count;
	
	public CustomerRepOverSaw(){
		
	}
	
	public CustomerRepOverSaw(int id, String fName, String lName, int count) {
		this.id = id;
		this.FName = fName;
		this.LName = lName;
		this.count = count;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFName() {
		return FName;
	}
	public void setFName(String fName) {
		FName = fName;
	}
	public String getLName() {
		return LName;
	}
	public void setLName(String lName) {
		LName = lName;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
