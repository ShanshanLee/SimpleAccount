package com.bingjie.account.provider;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeFormat {

	public static long getLastDateOfThisMonth() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// ��Ϊ��ǰ�µ�1 ��
		lastDate.add(Calendar.MONTH, 1);// ��һ���£���Ϊ���µ�1 ��
		lastDate.add(Calendar.DATE, -1);// ��ȥһ�죬��Ϊ�������һ��
		long lastTimeThisMonth = lastDate.getTime().getTime();
		return lastTimeThisMonth;
	}

	public static long getFirstDateOfThisMonth() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar firstDate = Calendar.getInstance();
		firstDate.set(Calendar.DATE, 1);// ��Ϊ��ǰ�µ�1 ��
		long firstDateThisMonth = firstDate.getTime().getTime();
		return firstDateThisMonth;
	}

	public static long getFirstDateOfPreviousMonth() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar firstDate = Calendar.getInstance();
		firstDate.set(Calendar.DATE, 1);// ��Ϊ��ǰ�µ�1 ��
		firstDate.add(Calendar.MONTH, -1);// ��һ���£���Ϊ���µ�1 ��
		// lastDate.add(Calendar.DATE,-1);//��ȥһ�죬��Ϊ�������һ��
		long firstDatePreviousMonth = firstDate.getTime().getTime();
		return firstDatePreviousMonth;
	}

	public static long getLastDateOfPreviousMonth() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// ��Ϊ��ǰ�µ�1 ��
		lastDate.add(Calendar.DATE, -1);// ��ȥһ�죬��Ϊ�������һ��
		long lastDatePreviousMonth = lastDate.getTime().getTime();
		return lastDatePreviousMonth;
	}

}
