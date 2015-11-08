package com.bingjie.account;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.w3c.dom.Text;

import com.bingjie.account.DAO.AccountDAO;
import com.bingjie.account.DTO.Account;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ViewAccount extends AppCompatActivity {
	private AccountDAO mAccountDao;
	private ActionBar mActionBar;
	private Account mAccount;
	private String category[] = { "日常食品", "人情世故", "出差旅行", "服饰鞋帽", "日常物品" };

	private TextView mTextName;
	private TextView mTextMoney;
	private TextView mTextCategory;
	private TextView mTextTime;
	private TextView mTextNote;
	private TextView mAccountNoteText;

	private String mAccountName;
	private String mAccountMoney;
	private String mAccountCategory;
	private String mAccountTime;
	private String mAccountNote;

	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_account);
		mAccountDao = new AccountDAO(this);
		Intent intent = getIntent();
		int accountId = intent.getIntExtra("Acount_Id", 0);
		mAccount = mAccountDao.fetch(accountId);
		findViews();
		initActionbar();
		initViews();
	}

	private void initViews() {
		mAccountName = mAccount.getAccountName();
		mAccountMoney = mAccount.getAccountPrice() + "元";
		mAccountCategory = category[mAccount.getAccountCategory()];
		Date date = new Date();
		date.setTime(mAccount.getAccountDate());
		mAccountTime = simpleDateFormat.format(date);
		mAccountNote = mAccount.getAccountNote();

		mTextName.setText(mAccountName);
		mTextMoney.setText(mAccountMoney);
		mTextCategory.setText(mAccountCategory);
		mTextTime.setText(mAccountTime);
		mTextNote.setText(mAccountNote);
		Log.e("------------", mAccountNote);
		if (mAccountNote.equals("")) {
			mAccountNoteText.setVisibility(View.GONE);
		}

	}

	private void initActionbar() {
		mActionBar = getSupportActionBar();
		mActionBar.setDisplayShowHomeEnabled(true);
		mActionBar.setDisplayUseLogoEnabled(true);
		mActionBar.setDisplayHomeAsUpEnabled(true);
		mActionBar.setIcon(getResources().getDrawable(R.drawable.ic_icon));

	}

	private void findViews() {
		mTextName = (TextView) findViewById(R.id.text_name);
		mTextMoney = (TextView) findViewById(R.id.text_money);
		mTextCategory = (TextView) findViewById(R.id.text_category);
		mTextTime = (TextView) findViewById(R.id.text_time);
		mTextNote = (TextView) findViewById(R.id.text_note);
		mAccountNoteText = (TextView) findViewById(R.id.account_note);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			this.finish();
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
