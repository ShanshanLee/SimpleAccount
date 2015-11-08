package com.bingjie.account.customview;

import java.util.ArrayList;

import com.bingjie.account.R;
import com.bingjie.account.provider.PieChartDataItem;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class PieChart extends View {
	private Paint mPaint, mCenterPaint;
	private RectF mRectF;
	private float mRadius;
	private int mBackgroundColor;
	private String mText;

	private int mTotalValue = 0;
	private ArrayList<PieChartDataItem> mValues;

	public PieChart(Context context, AttributeSet attrs) {
		super(context, attrs);
		mPaint = new Paint();
		mCenterPaint = new Paint();
		mRectF = new RectF();
		mValues = new ArrayList<PieChartDataItem>();

		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.PieChart);
		mRadius = typedArray.getDimension(R.styleable.PieChart_Radius, 0);
		typedArray.recycle();

		mPaint.setAntiAlias(true);
		mCenterPaint.setTextSize(20);
		mCenterPaint.setTextAlign(Paint.Align.CENTER);
	}

	/*
	 * ����View
	 * 
	 * @param canvas#����
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(mBackgroundColor);
		int degrees = 0;
		for (PieChartDataItem pieChartDataItem : mValues) {
			mPaint.setColor(pieChartDataItem.getColor());
			canvas.drawArc(mRectF, degrees, pieChartDataItem.getDegrees(),
					true, mPaint);
			degrees += pieChartDataItem.getDegrees();
		}
		canvas.translate(mRadius / 2, mRadius / 2);
		mCenterPaint.setColor(Color.WHITE);
		canvas.drawCircle(0, 0, mRadius / 4, mCenterPaint);
		mCenterPaint.setColor(Color.BLACK);
		canvas.drawText(this.getText(), 0, 0, mCenterPaint);
	}

	/*
	 * ����View�����ı�����
	 * 
	 * @param text#�ı�����
	 */
	public void setText(String text) {
		this.mText = text;
	}

	/*
	 * ���View�����ı�
	 * 
	 * @return text
	 */
	public String getText() {
		if (this.mText == null) {
			return "";
		} else {
			return this.mText;
		}
	}

	/*
	 * ���Item
	 * 
	 * @param itemTitle#item����
	 * 
	 * @param itemValue#itemֵ
	 * 
	 * @param itemColor#item��ɫ
	 * 
	 * @see #addItem(PieChartDataItem)
	 */
	public void addItem(String itemTitle, int itemValue, int itemColor) {
		addItem(new PieChartDataItem(itemTitle, itemValue, itemColor));
	}

	/*
	 * ���item
	 * 
	 * @param pieChartDataItem#PieChartDataItemʵ��
	 */
	public void addItem(PieChartDataItem pieChartDataItem) {
		if (pieChartDataItem.getValue() < 0) {
			throw new NumberFormatException("itemValue must big than zero");
		}
		mTotalValue += pieChartDataItem.getValue();
		mValues.add(pieChartDataItem);
	}

	/*
	 * ����View����
	 */
	public void reset() {
		mTotalValue = 0;
		mValues.clear();
	}

	/*
	 * ���View��ȫ������
	 * 
	 * @return PieChartDataItem[]
	 */
	public PieChartDataItem[] getValues() {
		PieChartDataItem[] pieChartDataItems = new PieChartDataItem[mValues
				.size()];
		mValues.toArray(pieChartDataItems);
		return pieChartDataItems;
	}

	/*
	 * ��VIew�����ݽ��д������ɶ�Ӧ�Ƕ�
	 */
	public void cal() {
		for (PieChartDataItem pieChartDataItem : mValues) {
			pieChartDataItem.setDegrees(360 * pieChartDataItem.getValue()
					/ mTotalValue);
		}
		this.invalidate();
	}

	/*
	 * ��ʼ��View��С
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int width = MeasureSpec.getSize(widthMeasureSpec);
		int height = MeasureSpec.getSize(heightMeasureSpec);
		if (mRadius == 0) {
			mRadius = width > height ? height : width;
		}
		mRectF = new RectF(0, 0, mRadius, mRadius);
		setMeasuredDimension((int) mRadius, (int) mRadius);
	}
}
