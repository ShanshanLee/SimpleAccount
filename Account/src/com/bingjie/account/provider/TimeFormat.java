package com.bingjie.account.provider;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeFormat {

	public static long getLastDateOfThisMonth() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1 号
		lastDate.add(Calendar.MONTH, 1);// 加一个月，变为下月的1 号
		lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天
		long lastTimeThisMonth = lastDate.getTime().getTime();
		return lastTimeThisMonth;
	}

	public static long getFirstDateOfThisMonth() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar firstDate = Calendar.getInstance();
		firstDate.set(Calendar.DATE, 1);// 设为当前月的1 号
		long firstDateThisMonth = firstDate.getTime().getTime();
		return firstDateThisMonth;
	}

	public static long getFirstDateOfPreviousMonth() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar firstDate = Calendar.getInstance();
		firstDate.set(Calendar.DATE, 1);// 设为当前月的1 号
		firstDate.add(Calendar.MONTH, -1);// 减一个月，变为下月的1 号
		// lastDate.add(Calendar.DATE,-1);//减去一天，变为当月最后一天
		long firstDatePreviousMonth = firstDate.getTime().getTime();
		return firstDatePreviousMonth;
	}

	public static long getLastDateOfPreviousMonth() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1 号
		lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天
		long lastDatePreviousMonth = lastDate.getTime().getTime();
		return lastDatePreviousMonth;
	}

}
