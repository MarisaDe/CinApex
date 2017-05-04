package Beans;

public class Account extends Customer{
	
	private int id;
	private String date;
	private String type;
	private String customerId;
	
	public Account(){
		super();
	}
	
	public Account(int id, String date, String type, String customerId){
		this.id = id;
		this.date = date;
		this.type = type;
		this.customerId = customerId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
}

