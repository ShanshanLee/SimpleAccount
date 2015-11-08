package com.bingjie.account.DTO;

public class Account {

	private int accountId;
	private String accountName;
	private float accountPrice;
	private int accountCategory;
	private long accountDate;
	private boolean accountIsDelete;
	private String accountNote;

	public Account() {

	}

	public Account(int accountId, String accountName, float accountPrice,
			int accountCategory, long accountDate, boolean accountIsDelete,
			String accountNote) {
		this.accountId = accountId;
		this.accountName = accountName;
		this.accountPrice = accountPrice;
		this.accountCategory = accountCategory;
		this.accountDate = accountDate;
		this.accountIsDelete = accountIsDelete;
		this.accountNote = accountNote;
		this.accountDate = accountDate;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Float getAccountPrice() {
		return accountPrice;
	}

	public void setAccountPrice(Float accountPrice) {
		this.accountPrice = accountPrice;
	}

	public int getAccountCategory() {
		return accountCategory;
	}

	public void setAccountCategory(int accountCategory) {
		this.accountCategory = accountCategory;
	}

	public long getAccountDate() {
		return accountDate;
	}

	public void setAccountDate(long accountDate) {
		this.accountDate = accountDate;
	}

	public boolean isAccountIsDelete() {
		return accountIsDelete;
	}

	public void setAccountIsDelete(boolean accountIsDelete) {
		this.accountIsDelete = accountIsDelete;
	}

	public String getAccountNote() {
		return accountNote;
	}

	public void setAccountNote(String accountNote) {
		this.accountNote = accountNote;
	}

}
