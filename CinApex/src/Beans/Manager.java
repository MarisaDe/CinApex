package Beans;

public class Manager extends Employee{
	private int managerId;

	public Manager(int managerId) {
		super();
		this.managerId = managerId;
	}

	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	
	
}
