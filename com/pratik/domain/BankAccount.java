package com.pratik.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="BankAccount")

public class BankAccount {

	private int id;
    private String name;
    private float balance;
    
    @Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "BankAccount [id=" + id + ", name=" + name + ", balance=" + balance + "]";
	}

    


}
