package Beans;

public class Person {
 
   private String FName;
   private String LName;
   private String address;
   private int zip;
   private String phone;
   private String city;
   private String state;
   
   public Person(String FName, String LName, String address, int zip, String phone, String city, String state){
	   this.FName = FName;
	   this.LName = LName;
	   this.address = address;
	   this.zip = zip;
	   this.phone = phone;
	   this.city = city;
	   this.state = state;
   }
   
   
   public void setFName( String FName){
	   this.FName = FName;
   }
   
   public void setLName(String LName){
	   this.LName = LName;
   }
   
   public void setAddress( String address){
	   this.address = address;
   }
   
   public void setZip(int zip){
	   this.zip = zip;
   }
   
   public void setPhone(String phone){
	   this.phone = phone;
   }
   
   public void setCity(String city){
	   this.phone = city;
   }
   
   public void setState(String state){
	   this.phone = state;
   }
   
   
   public String getFName(){
	   return FName;
   }
   
   public String getLName(){
	   return LName;
   }
   
   public String getAddress(){
	   return address;
   }
   
   public int getZip(){
	   return zip;
   }
   
   public String getPhone(){
	   return phone;
   }
   
   public String getCity(){
	   return city;
   }
   
   public String getState(){
	   return state;
   }
 
   
   
}