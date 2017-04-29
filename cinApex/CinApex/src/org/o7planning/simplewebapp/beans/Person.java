package org.o7planning.simplewebapp.beans;
 
public class Person {
 
   private String id;
   private String FName;
   private String LName;
   private String address;
   private int zip;
   private String phone;
   
   public Person(String id, String FName, String LName, String address, int zip, String phone){
	   this.id = id;
	   this.FName = FName;
	   this.LName = LName;
	   this.address = address;
	   this.zip = zip;
	   this.phone = phone;
   }
   
   public void setId( String id){
	   this.id = id;
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
   
   public String getId(){
	   return id;
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
   
   
}