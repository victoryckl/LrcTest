package com.example.lrctest;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

public class LrcView extends TextView {

	private static final String TAG = "MyView";
	private float float1 = 0.0f;
	private float float2 = 0.01f;
	private Handler handler;
	private Paint mPaint;
	private Paint mLightPaint;
	private float mOffsetY = 0.0f;
	private float mDy = 2.0f;
	private double mDf;
	private int mLightIndex = 0;
	public static int count = 0;

	public LrcView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public LrcView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public LrcView(Context context) {
		super(context);
		init();
	}

	private void init() {
		setFocusable(true);
		if(mList==null){
			mList=new ArrayList<String>();
			mList.add(0, "什么都木有啊");
		}
		
		// 非高亮部分
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setTextSize(30);
		mPaint.setTextAlign(Paint.Align.CENTER);
		
		// 高亮部分 当前歌词
		mLightPaint = new Paint(mPaint);
		mLightPaint.setColor(Color.YELLOW);
		
		float count = mPaint.getTextSize() * 2 / mDy;
		mDf = 1.0/count;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (mList == null || mList.size() == 0) {
			return ;
		}
		int size = mList.size();
		int vWidth = getWidth();
		int vHeight = getHeight();
		float textW = 0;
		int light = mLightIndex;
		String text = null;
		float lineLeft = getLayout().getLineLeft(0);
		
		for (int i = 0; i < size; i++) {
			if (i != light) {
				text = mList.get(i);
				textW = mPaint.measureText(text);
				float x = (vWidth - textW)/2;
				float y = vHeight/2 + (i * 2) * mPaint.getTextSize() - mOffsetY;
				canvas.drawText(text, lineLeft, y, mPaint);
			}
		}
		
		calcFloat();
		
		text = mList.get(light);
		textW = mLightPaint.measureText(text);
		float x = (vWidth - textW)/2;
		float y = vHeight/2 + (light * 2) * mPaint.getTextSize() - mOffsetY;
		
		int[] a = new int[] { Color.YELLOW, Color.BLACK };
		float[] f = new float[] { float1, float2 };
		Shader shader = new LinearGradient(
				x, 0, 
				x + textW +10, 0, 
				a, f, TileMode.CLAMP);
		mLightPaint.setShader(shader);
		String msg = String.format("vW:%d, vH:%d, textW:%f, (%f,%f)", vWidth, vHeight, textW, x, y);
		Log.i(TAG, msg);
		canvas.drawText(mList.get(light), lineLeft, y, mLightPaint);
		
		mOffsetY += mDy;
		if (mOffsetY >= getHeight()/2 + mList.size() * mPaint.getTextSize()) {
			mOffsetY = 0;
			mLightIndex = 0;
		}
	}
	
	private int heightLightIndex() {
		int index = 0;
		if (mList.size() != 0) {
			index = (int)(mOffsetY / (mPaint.getTextSize() * 2) - 0.5f);
			if (index < 0) {
				index = 0;
			} else if (index >= mList.size()) {
				index = mList.size() -1;
			}
		}
		return index;
	}
	
	private void calcFloat() {
		float1 += mDf;
		float2 += mDf;
		if (float2 > 1.0) {
			float1 = 0.0f;
			float2 = 0.01f;
			mLightIndex++;
			if (mLightIndex < 0) {
				mLightIndex = 0;
			} else if (mLightIndex >= mList.size()) {
				mLightIndex = mList.size() -1;
			}
		}
	}
	
	private void update() {
		// 更新界面
		postInvalidate();
	}
	
	private List<String> mList;
	public List<String> getList() {
		return mList;
	}
	
	public void setList(List<String> list) {
		mList = list;
	}
	
	public void updateUI(){
		new Thread(new updateThread()).start();
	}
	class updateThread implements Runnable {
		public void run() {
			while (true) {
				postInvalidate();
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {					
					e.printStackTrace();
				}
			}
		}
	}
}
