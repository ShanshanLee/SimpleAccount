package com.bingjie.account.provider;

import java.util.ArrayList;
import java.util.List;

import com.bingjie.account.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("ViewHolder")
public class HomeGridViewAdapter extends BaseAdapter {
	private List<PieChartDataItem> items = new ArrayList<PieChartDataItem>();
	private Context context; // 构建对象的context
	private LayoutInflater mInflater;

	public HomeGridViewAdapter(Context context) {
		this.context = context;
		this.mInflater = LayoutInflater.from(context);

	}

	public HomeGridViewAdapter(Context context, List<PieChartDataItem> items) {
		this.context = context;
		this.mInflater = LayoutInflater.from(context);
		this.items = items;

	}

	public void setListAlarmInfo(List<PieChartDataItem> items) {
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
		return 0;
	}

	static class ViewHolder {
		ImageView mColorImage;
		TextView mTypeText;
		TextView mValueText;

	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		final PieChartDataItem currentItem = (PieChartDataItem) getItem(position);
		ViewHolder viewHolder;
		if (view == null) {
			view = mInflater
					.inflate(R.layout.home_gridview_item, parent, false);
		}
		viewHolder = findViews(new ViewHolder(), view);
		if (currentItem.getColor() == Color.WHITE) {
			viewHolder.mColorImage.setVisibility(View.GONE);
		} else {
			viewHolder.mColorImage.setVisibility(View.VISIBLE);
		}
		viewHolder.mColorImage.setColorFilter(currentItem.getColor());
		viewHolder.mTypeText.setText(currentItem.getTitle());
		viewHolder.mValueText.setText(currentItem.getValue() + "");

		return view;
	}

	private ViewHolder findViews(ViewHolder viewHolder, View view) {
		viewHolder.mColorImage = (ImageView) view
				.findViewById(R.id.color_image);
		viewHolder.mTypeText = (TextView) view.findViewById(R.id.type_textview);
		viewHolder.mValueText = (TextView) view
				.findViewById(R.id.value_textview);
		return viewHolder;
	}
}
