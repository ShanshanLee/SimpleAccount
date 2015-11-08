package com.bingjie.account.fragment;

import java.util.List;

import com.bingjie.account.MainActivity;
import com.bingjie.account.R;
import com.bingjie.account.ViewAccount;
import com.bingjie.account.DAO.AccountDAO;
import com.bingjie.account.DTO.Account;
import com.bingjie.account.provider.AccountListViewAdapter;
import com.bingjie.account.provider.RefreshFragmentListener;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class AccountFragment extends Fragment implements
		RefreshFragmentListener {
	private View mView;
	private ListView mListView;
	private AccountListViewAdapter listAdapter;
	private List<Account> accountList;
	private AccountDAO mAccountDAO;
	private Context mContext;
	private Button allAccountBtn;
	private TextView thisMonthAccount;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.account_fragment, null);
		mContext = getActivity();
		mAccountDAO = new AccountDAO(mContext);
		accountList = mAccountDAO.fetchAccountOfthisMonth();
		findViews();
		setListener();
		listAdapter = new AccountListViewAdapter(mContext, accountList);
		mListView.setAdapter(listAdapter);
		return mView;
	}

	private void findViews() {
		mListView = (ListView) mView.findViewById(R.id.account_list);
		allAccountBtn = (Button) mView.findViewById(R.id.all_account_btn);
		thisMonthAccount = (TextView) mView
				.findViewById(R.id.this_month_account);
	}

	private void setListener() {

		// 全部账单的点击事件
		allAccountBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (allAccountBtn.getText().toString().equals("全部账单")) {
					allAccountBtn.setText("本月账单");
					thisMonthAccount.setText("全部账单");
					accountList = mAccountDAO.fetchAllAccount();
					listAdapter = new AccountListViewAdapter(mContext,
							accountList);
					mListView.setAdapter(listAdapter);
				} else if (allAccountBtn.getText().toString().equals("本月账单")) {
					allAccountBtn.setText("全部账单");
					thisMonthAccount.setText("本月账单");
					accountList = mAccountDAO.fetchAccountOfthisMonth();
					listAdapter = new AccountListViewAdapter(mContext,
							accountList);
					mListView.setAdapter(listAdapter);
				}

			}
		});
		// ListItem的点击事件
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Account mAccount = accountList.get(arg2);
				Log.e("------------", mAccount + "");
				int mAccountId = accountList.get(arg2).getAccountId();
				Intent intent = new Intent();
				intent.putExtra("Acount_Id", mAccountId);
				intent.setClass(getActivity(), ViewAccount.class);
				startActivity(intent);
			}
		});

		// 长按删除item
		mListView
				.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

					public boolean onItemLongClick(AdapterView<?> arg0, View v,
							final int index, long arg3) {
						final int mAccountId = accountList.get(index)
								.getAccountId();
						AlertDialog.Builder builder = new AlertDialog.Builder(
								getActivity());
						builder.setMessage(R.string.delete_dialog);
						builder.setTitle(R.string.promot);
						builder.setPositiveButton(R.string.ok,
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {

										mAccountDAO.delete(mAccountId);
										listAdapter.notifyDataSetChanged();
										dialog.dismiss();
										accountList.remove(index);

									}
								});
						builder.setNegativeButton(R.string.cancle,
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.dismiss();

									}
								});
						Dialog dialog = builder.create();
						dialog.show();

						return true;
					}
				});

	}

	@Override
	public void RefreshFragment() {
		Intent intent = new Intent(getActivity(), MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);

	}
}
