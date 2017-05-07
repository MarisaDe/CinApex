package Beans;

import java.util.List;

public class SaleReport {

	private int TotalSales;
	private String Date;
	private List<Account> Accounts;
	
	public SaleReport() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getTotalSales() {
		return TotalSales;
	}
	public void setTotalSales(int totalSales) {
		TotalSales = totalSales;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public List<Account> getAccounts() {
		return Accounts;
	}
	public void setAccounts(List<Account> accounts) {
		Accounts = accounts;
	}
	
	
}
