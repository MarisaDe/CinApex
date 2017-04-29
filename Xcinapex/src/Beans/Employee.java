package Beans;

public class Employee extends Person{

	
	private String ssn;
	private String startDate;
	private double hourlyRate;
	
	public Employee(String id, String FName, String LName, String address, int zip, String phone, String city, String state) {
		super(id, FName, LName, address, zip, phone, city, state);
		
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
