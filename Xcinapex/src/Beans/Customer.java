package Beans;

public class Customer extends Person{

	private String Id;
	private String email;
	private String cCard;
	private int rating;
	

	public Customer() {
		super();
	}
	
	
	public void setCustId(String custId){
		   this.Id = custId;
	   }
	   
	public void setEmail(String email){
		   this.email = email;
	   }
	
	public void setcCard(String cCard){
		   this.cCard = cCard;
	   }
	
	public void setRating(int rating){
		   this.rating = rating;
	   }
	
	public String getCustId(){
		   return Id;
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
