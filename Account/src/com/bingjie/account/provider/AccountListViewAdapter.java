package com.bingjie.account.provider;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bingjie.account.R;
import com.bingjie.account.DTO.Account;

public class AccountListViewAdapter extends BaseAdapter {

	private List<Account> items = new ArrayList<Account>();
	private Context context; // 构建对象的context
	private LayoutInflater mInflater;
	private String[] category = new String[] { "日常食品", "人情世故", "出差旅行", "服饰鞋帽",
			"日常物品" };

	public AccountListViewAdapter() {
		this.context = context;
		this.mInflater = LayoutInflater.from(context);
	}

	public AccountListViewAdapter(Context context, List<Account> items) {
		this.context = context;
		this.mInflater = LayoutInflater.from(context);
		this.items = items;

	}

	public void setListAlarmInfo(List<Account> items) {
		this.items = items;
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	static class ViewHolder {
		TextView mAccountName;
		TextView mAccountMoney;
		TextView mAccountType;
		TextView mAccountTime;
	}

	@SuppressLint({ "SimpleDateFormat", "ResourceAsColor" })
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		// final Account currentAcount = (Account) getItem(position);
		final Account currentAccount = items.get(position);

		ViewHolder viewHolder;
		if (view == null) {
			view = mInflater.inflate(R.layout.account_listview_item, parent,
					false);
		}
		viewHolder = findViews(new ViewHolder(), view);

		viewHolder.mAccountName.setText(currentAccount.getAccountName());
		viewHolder.mAccountMoney.setText(currentAccount.getAccountPrice() + "");
		viewHolder.mAccountMoney.setTextColor(Color.RED);
		int categoryId = currentAccount.getAccountCategory();
		switch (categoryId) {
		case 0:
			viewHolder.mAccountType.setText(category[0]);
			break;
		case 1:
			viewHolder.mAccountType.setText(category[1]);
			break;
		case 2:
			viewHolder.mAccountType.setText(category[2]);
			break;
		case 3:
			viewHolder.mAccountType.setText(category[3]);
			break;
		case 4:
			viewHolder.mAccountType.setText(category[4]);
			break;
		default:
			break;

		}
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm");
		Date date = new Date(currentAccount.getAccountDate());
		String time = simpleDateFormat.format(date);
		viewHolder.mAccountTime.setText(time);
		return view;
	}

	private ViewHolder findViews(ViewHolder viewHolder, View view) {
		viewHolder.mAccountName = (TextView) view
				.findViewById(R.id.account_name);
		viewHolder.mAccountType = (TextView) view
				.findViewById(R.id.account_type);
		viewHolder.mAccountMoney = (TextView) view
				.findViewById(R.id.account_money);
		viewHolder.mAccountTime = (TextView) view
				.findViewById(R.id.account_time);
		return viewHolder;
	}

}
