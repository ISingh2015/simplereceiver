package com.demo.test.receiver;

public class Customer {
	private int custId;
	private String custName;
	
	public Customer(int custId, String custName) {
		super();
		this.custId = custId;
		this.custName = custName;
	}

	public int getCustId() {
		return custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}
	
}
