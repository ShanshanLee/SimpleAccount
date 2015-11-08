package com.bingjie.account.provider;

import java.util.ArrayList;
import java.util.List;

import com.bingjie.account.DTO.Account;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AccountDB extends SQLiteOpenHelper {
	public static final String DB_NAME = "Account.db";// 库名
	public static final Object[] DBLOCK = new Object[0];
	public static final String TABLE_ACCOUNT = "account";// account表名
	public static final String ACCOUNT_ACCOUNTID = "_id";// id
	public static final String ACCOUNT_ACCOUNTNAME = "AccountName";// 标签内容
	public static final String ACCOUNT_ACCOUNTCATEGORY = "AccountCategory";// 类别
	public static final String ACCOUNT_ACCOUNTDATE = "AccountDate";// 日期
	public static final String ACCOUNT_ACCOUNTPRICE = "AccountPrice";// 金额
	public static final String ACCOUNT_ACCOUNTDELETE = "AccountIsDelete";// 是否删除
	public static final String ACCOUNT_ACCOUNTNOTE = "AccountIsNote";// 备注

	public AccountDB(Context context) {
		super(context, DB_NAME, null, 1);
	}

	// 创建数据库
	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL(AccountDB.TABLE_ACCOUNT_CREATE);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 创建表
	private static final String TABLE_ACCOUNT_CREATE = "CREATE TABLE "
			+ TABLE_ACCOUNT + " (" + ACCOUNT_ACCOUNTID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT," + ACCOUNT_ACCOUNTNAME
			+ " VARCHAR(50) NOT NULL," + ACCOUNT_ACCOUNTCATEGORY
			+ " INTEGER NOT NULL DEFAULT 0," + ACCOUNT_ACCOUNTDATE
			+ " LONG NOT NULL," + ACCOUNT_ACCOUNTPRICE + " REAl NOT NULL,"
			+ ACCOUNT_ACCOUNTDELETE + " BOOLEAN NOT NULL DEFAULT 0,"
			+ ACCOUNT_ACCOUNTNOTE + " VARCHAR(1000)" + " );";

	/**
	 * 向account表中添加数据
	 * 
	 * @param account
	 * @return
	 */
	public long insertAccount(Account account) {

		ContentValues accountValues = new ContentValues();
		accountValues.put(ACCOUNT_ACCOUNTNAME, account.getAccountName());
		accountValues
				.put(ACCOUNT_ACCOUNTCATEGORY, account.getAccountCategory());
		accountValues.put(ACCOUNT_ACCOUNTDATE, account.getAccountDate());
		accountValues.put(ACCOUNT_ACCOUNTPRICE, account.getAccountPrice());
		accountValues.put(ACCOUNT_ACCOUNTDELETE, account.isAccountIsDelete());
		accountValues.put(ACCOUNT_ACCOUNTNOTE, account.getAccountNote());
		try {
			synchronized (DBLOCK) {

				SQLiteDatabase db = this.getWritableDatabase();
				return db.insert(TABLE_ACCOUNT, null, accountValues);

			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}

	/**
	 * 更新account数据
	 * 
	 * @param account
	 * @return
	 */
	public int updateAccount(Account account) {

		ContentValues accountValues = new ContentValues();
		accountValues.put(ACCOUNT_ACCOUNTNAME, account.getAccountName());
		accountValues
				.put(ACCOUNT_ACCOUNTCATEGORY, account.getAccountCategory());
		accountValues.put(ACCOUNT_ACCOUNTDATE, account.getAccountDate());
		accountValues.put(ACCOUNT_ACCOUNTPRICE, account.getAccountPrice());
		accountValues.put(ACCOUNT_ACCOUNTDELETE, account.isAccountIsDelete());
		accountValues.put(ACCOUNT_ACCOUNTNOTE, account.getAccountNote());

		try {
			synchronized (DBLOCK) {
				SQLiteDatabase db = this.getWritableDatabase();
				return db.update(TABLE_ACCOUNT, accountValues,
						ACCOUNT_ACCOUNTID + "=" + account.getAccountId(), null);

			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	/**
	 * 根据id删除account一项数据
	 * 
	 * @param id
	 * @return
	 */
	public int deleteAccount(int id) {

		ContentValues deletetValues = new ContentValues();

		deletetValues.put(ACCOUNT_ACCOUNTDELETE, 1);

		try {
			synchronized (DBLOCK) {
				SQLiteDatabase db = this.getWritableDatabase();
				db.update(TABLE_ACCOUNT, deletetValues, ACCOUNT_ACCOUNTID + "="
						+ id, null);

			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	/**
	 * 根据id获取account数据
	 * 
	 * @param id
	 * @return
	 */
	public Account getAccountInfo(int id) {
		Cursor c = null;
		try {
			synchronized (DBLOCK) {
				SQLiteDatabase db = this.getReadableDataBase();
				c = db.query(TABLE_ACCOUNT, new String[] { ACCOUNT_ACCOUNTID,
						ACCOUNT_ACCOUNTNAME, ACCOUNT_ACCOUNTPRICE,
						ACCOUNT_ACCOUNTCATEGORY, ACCOUNT_ACCOUNTDATE,
						ACCOUNT_ACCOUNTDELETE, ACCOUNT_ACCOUNTNOTE

				}, ACCOUNT_ACCOUNTID + "=" + id, null, null, null, null);

			}
			return createAccount(c, id);
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		} finally {
			if (c != null) {
				c.close();
			}
		}

	}

	public Account createAccount(Cursor cursor, int accountId) {
		Account account = null;
		cursor.moveToFirst();
		if (cursor != null && cursor.moveToFirst()) {
			account = new Account(accountId, cursor.getString(1),
					cursor.getFloat(2), cursor.getInt(3), cursor.getLong(4),
					cursor.getShort(5) == 0 ? false : true, cursor.getString(6));
		}
		return account;
	}

	public synchronized SQLiteDatabase getWritableDataBase() {

		return super.getWritableDatabase();
	}

	public synchronized SQLiteDatabase getReadableDataBase() {
		return super.getReadableDatabase();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	/**
	 * 获取本月同一类别的数据
	 * 
	 * @param cursor
	 * @param accountId
	 * @return
	 */
	public List<Account> getAccountByCategory(int category) {
		String selecction = ACCOUNT_ACCOUNTDELETE + " = 0" + " AND "
				+ ACCOUNT_ACCOUNTCATEGORY + "=" + category + " AND "
				+ ACCOUNT_ACCOUNTDATE + ">"
				+ TimeFormat.getFirstDateOfThisMonth() + " AND "
				+ ACCOUNT_ACCOUNTDATE + "<"
				+ TimeFormat.getLastDateOfThisMonth();
		Cursor c = null;
		try {
			synchronized (DBLOCK) {
				c = getReadableDataBase().query(
						TABLE_ACCOUNT,
						new String[] { ACCOUNT_ACCOUNTID, ACCOUNT_ACCOUNTNAME,
								ACCOUNT_ACCOUNTPRICE, ACCOUNT_ACCOUNTCATEGORY,
								ACCOUNT_ACCOUNTDATE, ACCOUNT_ACCOUNTDELETE,
								ACCOUNT_ACCOUNTNOTE

						}, selecction, null, null, null, null);

			}
			return getAccountCursor(c);
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		} finally {
			if (c != null) {
				c.close();
			}
		}

	}

	/**
	 * 获取上月全部数据
	 * 
	 * @param cursor
	 * @return
	 */
	public List<Account> getAllAccountsLastMonth() {
		String selection = ACCOUNT_ACCOUNTDELETE + " = 0" + " AND "
				+ ACCOUNT_ACCOUNTDATE + ">"
				+ TimeFormat.getFirstDateOfPreviousMonth() + " AND "
				+ ACCOUNT_ACCOUNTDATE + "<"
				+ TimeFormat.getLastDateOfPreviousMonth();
		Cursor c = null;
		try {
			synchronized (DBLOCK) {

				c = getReadableDataBase().query(
						TABLE_ACCOUNT,
						new String[] { ACCOUNT_ACCOUNTID, ACCOUNT_ACCOUNTNAME,
								ACCOUNT_ACCOUNTPRICE, ACCOUNT_ACCOUNTCATEGORY,
								ACCOUNT_ACCOUNTDATE, ACCOUNT_ACCOUNTDELETE,
								ACCOUNT_ACCOUNTNOTE

						}, selection, null, null, null, null);

			}
			return getAccountCursor(c);
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		} finally {
			if (c != null) {
				c.close();
			}
		}

	}

	/**
	 * 获取本月全部数据
	 * 
	 * @param cursor
	 * @return
	 */
	public List<Account> getAllAccountsOfthisMonth() {
		String selection = ACCOUNT_ACCOUNTDELETE + " = 0" + " AND "
				+ ACCOUNT_ACCOUNTDATE + " < "
				+ TimeFormat.getLastDateOfThisMonth() + " AND "
				+ ACCOUNT_ACCOUNTDATE + " > "
				+ TimeFormat.getFirstDateOfThisMonth();
		Cursor c = null;
		try {

			synchronized (DBLOCK) {
				SQLiteDatabase db = this.getReadableDataBase();
				c = db.query(TABLE_ACCOUNT, new String[] { ACCOUNT_ACCOUNTID,
						ACCOUNT_ACCOUNTNAME, ACCOUNT_ACCOUNTPRICE,
						ACCOUNT_ACCOUNTCATEGORY, ACCOUNT_ACCOUNTDATE,
						ACCOUNT_ACCOUNTDELETE, ACCOUNT_ACCOUNTNOTE

				}, selection, null, null, null, null);

			}
			return getAccountCursor(c);
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		} finally {
			if (c != null) {
				c.close();
			}
		}

	}

	/**
	 * 获取所有账单
	 * 
	 * @param cursor
	 * @return
	 */
	public List<Account> getAllAccounts() {
		Cursor c = null;
		try {
			synchronized (DBLOCK) {
				c = getReadableDataBase().query(
						TABLE_ACCOUNT,
						new String[] { ACCOUNT_ACCOUNTID, ACCOUNT_ACCOUNTNAME,
								ACCOUNT_ACCOUNTPRICE, ACCOUNT_ACCOUNTCATEGORY,
								ACCOUNT_ACCOUNTDATE, ACCOUNT_ACCOUNTDELETE,
								ACCOUNT_ACCOUNTNOTE

						}, null, null, null, null, null);

			}
			return getAccountCursor(c);
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		} finally {
			if (c != null) {
				c.close();
			}
		}

	}

	private List<Account> getAccountCursor(Cursor cursor) {
		List<Account> account = new ArrayList<Account>();

		try {

			if (cursor != null && cursor.moveToFirst()) {
				do {
					Account currentAcount;
					currentAcount = new Account(cursor.getInt(0),
							cursor.getString(1), cursor.getFloat(2),
							cursor.getInt(3), cursor.getLong(4),
							cursor.getShort(5) == 0 ? false : true,
							cursor.getString(6));
					account.add(currentAcount);
				} while (cursor.moveToNext());
			}
			return account;
		} finally {
			cursor.close();
		}
	}

}
