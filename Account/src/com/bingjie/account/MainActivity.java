package com.bingjie.account;

import java.util.ArrayList;
import java.util.List;

import com.bingjie.account.DAO.AccountDAO;
import com.bingjie.account.DTO.Account;
import com.bingjie.account.fragment.AccountFragment;
import com.bingjie.account.fragment.AddFragment;
import com.bingjie.account.fragment.HomeFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
	private ActionBar mActionBar;

	private ViewPager mViewPager;
	private List<Fragment> fragmentList;
	private FragmentPagerAdapter adapter;
	private TextView home, add, account;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initActionBar();
		initViews();
		setListener();
	}

	@SuppressWarnings("deprecation")
	private void initActionBar() {
		mActionBar = getSupportActionBar();
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setDisplayUseLogoEnabled(true);
		mActionBar.setIcon(getResources().getDrawable(R.drawable.ic_icon));

	}

	private void initViews() {

		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		home = (TextView) findViewById(R.id.home);
		add = (TextView) findViewById(R.id.add);
		account = (TextView) findViewById(R.id.account);

		HomeFragment homeFragment = new HomeFragment();
		AddFragment addFragment = new AddFragment();
		AccountFragment accountFragment = new AccountFragment();

		fragmentList = new ArrayList<Fragment>();
		fragmentList.add(homeFragment);
		fragmentList.add(addFragment);
		fragmentList.add(accountFragment);

		// adapter
		FragmentManager fm = getSupportFragmentManager();
		adapter = new FragmentPagerAdapter(fm) {
			@Override
			public Fragment getItem(int position) {
				return fragmentList.get(position);
			}

			@Override
			public int getCount() {
				return fragmentList.size();
			}

		};
		mViewPager.setAdapter(adapter);
	}

	@SuppressWarnings("deprecation")
	private void setListener() {
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				switch (arg0) {
				case 0:
					home.setBackgroundColor(getResources().getColor(
							R.color.white));
					add.setBackgroundColor(getResources()
							.getColor(R.color.gray));
					account.setBackgroundColor(getResources().getColor(
							R.color.gray));
					getSupportActionBar().setTitle("����֧��");
					break;
				case 1:
					add.setBackgroundColor(getResources().getColor(
							R.color.white));
					account.setBackgroundColor(getResources().getColor(
							R.color.gray));
					home.setBackgroundColor(getResources().getColor(
							R.color.gray));
					getSupportActionBar().setTitle("������Ŀ");
					break;
				case 2:
					account.setBackgroundColor(getResources().getColor(
							R.color.white));
					add.setBackgroundColor(getResources()
							.getColor(R.color.gray));
					home.setBackgroundColor(getResources().getColor(
							R.color.gray));
					getSupportActionBar().setTitle("��Ŀ��¼");
					break;

				default:
					break;
				}

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

		home.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mViewPager.setCurrentItem(0);
				home.setBackgroundColor(getResources().getColor(R.color.white));
				add.setBackgroundColor(getResources().getColor(R.color.gray));
				account.setBackgroundColor(getResources()
						.getColor(R.color.gray));

			}
		});
		add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mViewPager.setCurrentItem(1);
				add.setBackgroundColor(getResources().getColor(R.color.white));
				account.setBackgroundColor(getResources()
						.getColor(R.color.gray));
				home.setBackgroundColor(getResources().getColor(R.color.gray));
			}
		});
		account.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mViewPager.setCurrentItem(2);
				account.setBackgroundColor(getResources().getColor(
						R.color.white));
				add.setBackgroundColor(getResources().getColor(R.color.gray));
				home.setBackgroundColor(getResources().getColor(R.color.gray));

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	// ���ؼ������˳�����
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// �����˳��Ի���
			AlertDialog isExit = new AlertDialog.Builder(this).create();
			isExit.setIcon(getResources().getDrawable(R.drawable.ic_icon));
			// ���öԻ������
			isExit.setTitle("���˱�");
			// ���öԻ�����Ϣ
			isExit.setMessage("ȷ��Ҫ�˳��˱���?");
			// ���ѡ��ť��ע�����
			isExit.setButton(AlertDialog.BUTTON_POSITIVE, "ȷ��", listener);
			isExit.setButton(AlertDialog.BUTTON_NEGATIVE, "ȡ��", listener);
			// ��ʾ�Ի���
			isExit.show();

		}

		return false;

	}

	/** �����Ի��������button����¼� */
	DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			switch (which) {
			case AlertDialog.BUTTON_POSITIVE:// "ȷ��"��ť�˳�����
				finish();
				break;
			case AlertDialog.BUTTON_NEGATIVE:// "ȡ��"�ڶ�����ťȡ���Ի���
				break;
			default:
				break;
			}
		}
	};
}
