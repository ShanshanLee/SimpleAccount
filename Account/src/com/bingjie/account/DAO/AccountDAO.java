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

	// 初始化
	public AccountDAO(Context context) {
		this.mContext = context;
		if (mAccountDB == null) {
			mAccountDB = new AccountDB(mContext);
		}
	}

	/**
	 * 添加数据
	 * 
	 * @param account
	 * @return
	 */
	public long add(Account account) {

		return mAccountDB.insertAccount(account);

	}

	/**
	 * 更新数据
	 * 
	 * @param account
	 * @return
	 */
	public int save(Account account) {
		return mAccountDB.updateAccount(account);
	}

	/**
	 * 删除数据
	 * 
	 * @param accountId
	 * @return
	 */
	public int delete(int accountId) {

		return mAccountDB.deleteAccount(accountId);
	}

	/**
	 * 查找数据
	 * 
	 * @param accountId
	 * @return
	 */
	public Account fetch(int accountId) {

		return mAccountDB.getAccountInfo(accountId);
	}

	/**
	 * 获取本月对应类别的数据
	 */
	public List<Account> fetchAccountByCategory(int category) {
		return mAccountDB.getAccountByCategory(category);
	}

	/**
	 * 获取所有数据
	 */
	public List<Account> fetchAllAccount() {
		return mAccountDB.getAllAccounts();
	}

	/**
	 * 获取上月的全部数据
	 */
	public List<Account> fetchAccountLastMonth() {
		return mAccountDB.getAllAccountsLastMonth();
	}
	/**
	 * 获取本月的全部数据
	 */
	public List<Account> fetchAccountOfthisMonth() {
		return mAccountDB.getAllAccountsOfthisMonth();
	}

}
