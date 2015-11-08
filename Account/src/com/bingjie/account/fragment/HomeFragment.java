package com.bingjie.account.fragment;

import java.util.ArrayList;
import java.util.List;

import com.bingjie.account.MainActivity;
import com.bingjie.account.R;
import com.bingjie.account.DAO.AccountDAO;
import com.bingjie.account.DTO.Account;
import com.bingjie.account.customview.PieChart;
import com.bingjie.account.provider.HomeGridViewAdapter;
import com.bingjie.account.provider.PieChartDataItem;
import com.bingjie.account.provider.RefreshFragmentListener;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class HomeFragment extends Fragment implements RefreshFragmentListener {
	private View mView;
	private PieChart pieChart;
	private GridView gridView;
	private HomeGridViewAdapter mAdapter;
	private List<PieChartDataItem> items = new ArrayList<PieChartDataItem>();
	private AccountDAO mAccountDAO;
	private String category[] = { "�ճ�ʳƷ", "��������", "��������", "����Ьñ", "�ճ���Ʒ" };
	private int color[] = { Color.GRAY, Color.CYAN, Color.YELLOW, Color.BLUE,
			Color.MAGENTA };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.e("-------------", "HomeFragmentOncreateView");
		mView = inflater.inflate(R.layout.home_fragment, null);
		pieChart = (PieChart) mView.findViewById(R.id.piechart);
		gridView = (GridView) mView.findViewById(R.id.home_gridview);
		mAccountDAO = new AccountDAO(getContext());
		float totalMoney = 0;
		items.clear();
		for (int i = 0; i < 5; i++) {
			List<Account> accounts = mAccountDAO.fetchAccountByCategory(i);
			float money = 0;
			String currentCategory = category[i];
			if (accounts != null) {
				for (Account a : accounts) {
					money += a.getAccountPrice();
				}
			}
			totalMoney += money;
			pieChart.addItem(new PieChartDataItem(currentCategory, money,
					color[i]));
			items.add(new PieChartDataItem(currentCategory, money, color[i]));
		}
		pieChart.cal();
		pieChart.setText("������֧�� " + totalMoney + "Ԫ");

		// ������ͳ�ƣ�����ͳ�ƣ�ȫ��ͳ��Ҳ���뵽Adapter List�У�
		items.add(new PieChartDataItem("������֧��", totalMoney, Color.WHITE));
		items.add(new PieChartDataItem("������֧��", fetchAllMoneyOfLastMonth(),
				Color.WHITE));
		items.add(new PieChartDataItem("������֧��", fetchAllMoney(), Color.WHITE));
		mAdapter = new HomeGridViewAdapter(getActivity(), items);
		gridView.setAdapter(mAdapter);
		return mView;
	}

	// ��ȡ���µĽ��������
	public float fetchAllMoneyOfLastMonth() {
		List<Account> accountsLastMonth = mAccountDAO.fetchAccountLastMonth();
		float moneyLastMonth = 0;
		if (accountsLastMonth != null) {
			for (Account a : accountsLastMonth) {
				Log.e("---------", a.getAccountPrice() + "");
				moneyLastMonth += a.getAccountPrice();
			}
		}
		return moneyLastMonth;
	}

	// ��ȡȫ���Ľ��������
	public float fetchAllMoney() {
		List<Account> accountsAll = mAccountDAO.fetchAllAccount();
		float moneyAll = 0;
		if (accountsAll != null) {
			for (Account a : accountsAll) {
				moneyAll += a.getAccountPrice();
			}
		}
		return moneyAll;
	}

	@Override
	public void RefreshFragment() {
		Intent intent = new Intent(getActivity(), MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);

	}
}
