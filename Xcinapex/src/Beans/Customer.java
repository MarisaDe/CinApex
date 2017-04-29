package Beans;

public class Customer extends Person{

	private int custId;
	private String email;
	private String cCard;
	private int rating;
	

	public Customer() {
		super();
	}
	
	
	public void setCustId(int custId){
		   this.custId = custId;
	   }
	   
	public void setStartDate(String email){
		   this.email = email;
	   }
	
	public void setcCard(String cCard){
		   this.cCard = cCard;
	   }
	
	public void setRating(int rating){
		   this.rating = rating;
	   }
	
	public int getCustId(){
		   return custId;
	   }
	   
	public String getEmail(){
		   return email;
	   }
	
	public String getcCard(){
		   return cCard;
	   }
	
	public int getRating(){
		   return rating;
	   }
	

}
