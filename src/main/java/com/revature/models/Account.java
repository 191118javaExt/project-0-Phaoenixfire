package com.revature.models;

public class Account {

	private int account_id;
	private int owner_id;
	private int account_balance;
	private String account_Type;
	private boolean account_approved;
	
	
	@Override
	public String toString() {
		return "Account [account_id=" + account_id + ", owner_id=" + owner_id + ", account_balance=" + account_balance
				+ ", account_Type=" + account_Type + ", account_approved=" + account_approved + "]";
	}


	public int getAccount_id() {
		return account_id;
	}


	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}


	public int getOwner_id() {
		return owner_id;
	}


	public void setOwner_id(int owner_id) {
		this.owner_id = owner_id;
	}


	public int getAccount_balance() {
		return account_balance;
	}


	public void setAccount_balance(int account_balance) {
		this.account_balance = account_balance;
	}


	public String getAccount_Type() {
		return account_Type;
	}


	public void setAccount_Type(String account_Type) {
		this.account_Type = account_Type;
	}


	public boolean isAccount_approved() {
		return account_approved;
	}


	public void setAccount_approved(boolean account_approved) {
		this.account_approved = account_approved;
	}


	public Account(int account_id, int owner_id, int account_balance, String account_Type, boolean account_approved) {
		super();
		this.account_id = account_id;
		this.owner_id = owner_id;
		this.account_balance = account_balance;
		this.account_Type = account_Type;
		this.account_approved = account_approved;
	}
}
