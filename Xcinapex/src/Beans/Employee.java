package Beans;

public class Employee extends Person{

	private int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String ssn;
	private String startDate;
	private double hourlyRate;
	
	
	public Employee() {
		super();
		
	}
	
	public void setSsn(String ssn){
		   this.ssn = ssn;
	   }
	   
	public void setStartDate(String startDate){
		   this.startDate = startDate;
	   }
	
	public void setHourlyRate(double hourlyRate){
		   this.hourlyRate = hourlyRate;
	   }
	

	
	public String getSsn(){
		   return ssn;
	   }
	   
	public String getStartDate(){
		   return startDate;
	   }
	
	public double getHourlyRate(){
		   return hourlyRate;
	   }
	
	   

}
