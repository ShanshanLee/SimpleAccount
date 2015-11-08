package com.bingjie.account.DAO;

import java.util.List;

import com.bingjie.account.DTO.Account;
import com.bingjie.account.provider.AccountDB;

import android.accounts.AccountsException;
import android.content.Context;
import android.util.Log;

public class AccountDAO {

	private static AccountDB mAccountDB;
	private Context mContext;

	// ��ʼ��
	public AccountDAO(Context context) {
		this.mContext = context;
		if (mAccountDB == null) {
			mAccountDB = new AccountDB(mContext);
		}
	}

	/**
	 * �������
	 * 
	 * @param account
	 * @return
	 */
	public long add(Account account) {

		return mAccountDB.insertAccount(account);

	}

	/**
	 * ��������
	 * 
	 * @param account
	 * @return
	 */
	public int save(Account account) {
		return mAccountDB.updateAccount(account);
	}

	/**
	 * ɾ������
	 * 
	 * @param accountId
	 * @return
	 */
	public int delete(int accountId) {

		return mAccountDB.deleteAccount(accountId);
	}

	/**
	 * ��������
	 * 
	 * @param accountId
	 * @return
	 */
	public Account fetch(int accountId) {

		return mAccountDB.getAccountInfo(accountId);
	}

	/**
	 * ��ȡ���¶�Ӧ��������
	 */
	public List<Account> fetchAccountByCategory(int category) {
		return mAccountDB.getAccountByCategory(category);
	}

	/**
	 * ��ȡ��������
	 */
	public List<Account> fetchAllAccount() {
		return mAccountDB.getAllAccounts();
	}

	/**
	 * ��ȡ���µ�ȫ������
	 */
	public List<Account> fetchAccountLastMonth() {
		return mAccountDB.getAllAccountsLastMonth();
	}
	/**
	 * ��ȡ���µ�ȫ������
	 */
	public List<Account> fetchAccountOfthisMonth() {
		return mAccountDB.getAllAccountsOfthisMonth();
	}

}
