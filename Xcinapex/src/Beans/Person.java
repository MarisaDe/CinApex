package Beans;

public class Person {
 
   private String firstName;
   private String lastName;
   private String address;
   private int zipcode;
   private String telephone;
   
   public Person(){
	   super();
   }
   
   
   public void setFName( String FName){
	   this.firstName = FName;
   }
   
   public void setLName(String LName){
	   this.lastName = LName;
   }
   
   public void setAddress( String address){
	   this.address = address;
   }
   
   public void setZip(int zip){
	   this.zipcode = zip;
   }
   
   public void setPhone(String phone){
	   this.telephone = phone;
   }
   
   
   public String getFName(){
	   return firstName;
   }
   
   public String getLName(){
	   return lastName;
   }
   
   public String getAddress(){
	   return address;
   }
   
   public int getZip(){
	   return zipcode;
   }
   
   public String getPhone(){
	   return telephone;
   
   }
 
   
   
}