package Beans;

public class Customer extends Person{

	private String CustId;
	private String email;
	private String CCard;
	private int rating;
	

	public Customer() {
		super();
	}
	
	
	public void setCustId(String custId){
		   this.CustId = custId;
	   }
	   
	public void setEmail(String email){
		   this.email = email;
	   }
	
	public void setcCard(String cCard){
		   this.CCard = cCard;
	   }
	
	public void setRating(int rating){
		   this.rating = rating;
	   }
	
	public String getCustId(){
		   return CustId;
	   }
	   
	public String getEmail(){
		   return email;
	   }
	
	public String getcCard(){
		   return CCard;
	   }
	
	public int getRating(){
		   return rating;
	   }
	

}
