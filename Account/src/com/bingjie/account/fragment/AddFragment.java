package com.bingjie.account.fragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

import com.bingjie.account.MainActivity;
import com.bingjie.account.R;
import com.bingjie.account.DAO.AccountDAO;
import com.bingjie.account.DTO.Account;
import com.bingjie.account.provider.AccountDB;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.renderscript.Sampler.Value;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class AddFragment extends Fragment {

	private View mView;

	private EditText mAccountNameEdit;
	private EditText mAccountMoneyEdit;
	private Spinner mCategorySpinner;
	private Button mPickDateBtn;
	private Button mPickTimeBtn;
	private EditText mAcountNoteEdit;
	private Button mSaveButton;

	private List<String> mCategoryList = new ArrayList<String>();
	private ArrayAdapter<String> adapter;

	private String mAccountName;
	private Float mAccountPrice;
	private int mAccountCategory;
	private long mAccountDate;
	private String mAccountNote;
	private Boolean mAccountIsDlete;

	// 日期选择对话框；
	private DatePickerDialog mDatePicker;
	Calendar mcurrentDate = Calendar.getInstance();
	int mYear = mcurrentDate.get(Calendar.YEAR);
	int mMonth = mcurrentDate.get(Calendar.MONTH);
	int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
	int mHour = mcurrentDate.get(Calendar.HOUR_OF_DAY);
	int mMinute = mcurrentDate.get(Calendar.MINUTE);
	// 时间选择对话框；
	private TimePickerDialog mTimePicker;
	private String nowDateString, nowTimeString;
	private SimpleDateFormat is24formatter;
	private AccountDAO mAccountDAO;
	private Context mContext;

	private AccountDB accountDB;
	private SQLiteDatabase db;

	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
	SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.add_fragment, null);
		mContext = getActivity();
		mAccountDAO = new AccountDAO(mContext);
		is24formatter = new SimpleDateFormat("yyyy/MM/dd " + "HH:mm:ss");
		Date nowDate = new Date();
		nowDateString = simpleDateFormat.format(nowDate);
		nowTimeString = simpleTimeFormat.format(nowDate);
		findViews();
		initViews();
		return mView;
	}

	private void findViews() {
		mAccountNameEdit = (EditText) mView.findViewById(R.id.edit_name);
		mAccountMoneyEdit = (EditText) mView.findViewById(R.id.edit_money);
		mCategorySpinner = (Spinner) mView.findViewById(R.id.category_spinner);
		mAcountNoteEdit = (EditText) mView.findViewById(R.id.account_note);
		mPickTimeBtn = (Button) mView.findViewById(R.id.pick_time);
		mPickDateBtn = (Button) mView.findViewById(R.id.pick_date);
		mSaveButton = (Button) mView.findViewById(R.id.button_save);
		mPickDateBtn.setText(nowDateString);
		mPickTimeBtn.setText(nowTimeString);

	}

	private void initViews() {
		// 使AcountMoneyTextView弹出数字键盘;
		mAccountMoneyEdit.setInputType(EditorInfo.TYPE_CLASS_PHONE);

		// 初始化类别数据；
		mCategoryList.add(getActivity().getResources().getString(
				R.string.food_spending));
		mCategoryList.add(getActivity().getResources().getString(
				R.string.emotion_spending));
		mCategoryList.add(getActivity().getResources().getString(
				R.string.business_spending));
		mCategoryList.add(getActivity().getResources().getString(
				R.string.close_spending));
		mCategoryList.add(getActivity().getResources().getString(
				R.string.goods_spending));
		// 初始化Spinner；
		adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, mCategoryList);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mCategorySpinner.setAdapter(adapter);

		// 日期选择；
		mPickDateBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mDatePicker = new DatePickerDialog(getActivity(),
						new OnDateSetListener() {

							@Override
							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								mPickDateBtn.setText(year + "/"
										+ (monthOfYear + 1) + "/" + dayOfMonth);

							}
						}, mYear, mMonth, mDay);
				mDatePicker.show();
			}
		});

		// 时间选择
		mPickTimeBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mTimePicker = new TimePickerDialog(getActivity(),
						new OnTimeSetListener() {

							@Override
							public void onTimeSet(TimePicker view,
									int hourOfDay, int minute) {
								mPickTimeBtn.setText(hourOfDay + ":" + minute);

							}
						}, mHour, mMinute, true);
				mTimePicker.show();

			}
		});

		// 保存
		mSaveButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Account account = new Account();
				try {
					mAccountName = mAccountNameEdit.getText().toString();
					mAccountCategory = mCategorySpinner
							.getSelectedItemPosition();
					mAccountDate = is24formatter.parse(
							mPickDateBtn.getText().toString() + " "
									+ mPickTimeBtn.getText().toString())
							.getTime();
					mAccountPrice = Float.parseFloat(mAccountMoneyEdit
							.getText().toString());
					mAccountNote = mAcountNoteEdit.getText().toString();
					mAccountIsDlete = false;
					account.setAccountName(mAccountName);
					account.setAccountCategory(mAccountCategory);
					account.setAccountPrice(mAccountPrice);
					account.setAccountDate(mAccountDate);
					account.setAccountIsDelete(mAccountIsDlete);
					account.setAccountNote(mAccountNote);
					mAccountDAO.add(account);
					Toast.makeText(getActivity(), "账单添加成功~~", 1000).show();
					RefreshFragment();
				} catch (Exception e) {
					Log.e("-----------", mAccountName);
					if (mAccountName.equals("")) {
						Toast.makeText(getActivity(), "你没有输入账目名称哦~~", 1000)
								.show();
					} else {
						Toast.makeText(getActivity(), "你还没有输入金额哦~~", 1000)
								.show();
					}
				}
			}
		});
	}

	// 保存成功之后刷新Fragment；
	public void RefreshFragment() {
		Intent intent = new Intent(getActivity(), MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);

	}

}
