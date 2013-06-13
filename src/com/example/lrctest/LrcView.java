package com.example.lrctest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

public class LrcView extends TextView {

	private static final String TAG = "MyView";
	private float float1 = 0.0f;
	private float float2 = 0.01f;
	private Handler handler;
	private String text;
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
		handler = new Handler();
		update.run();
	}

	protected void onDraw(Canvas canvas) {
		Paint paint = getPaint();
		float1 += 0.01f;
		float2 += 0.01f;
		count++;
		if (float2 > 1.0) {
			float1 = 0.0f;
			float2 = 0.01f;
		}
//		Paint paint = new Paint();
		text = getText().toString();
		// 获得字符串的"长度"
		float len = getTextSize() * text.length();
		// 参数color数组表示参与渐变的集合
		// 参数float数组表示对应颜色渐变的位置
		int[] a = new int[] { Color.YELLOW, Color.RED };
		// Shader shader = new LinearGradient(0, 0, len, 0, new
		// int[]{Color.YELLOW, Color.RED},
		// new float[]{float1, float2}, TileMode.CLAMP);
		float[] f = new float[] { float1, float2 };
		Shader shader = new LinearGradient(
				getLayout().getLineLeft(0), 0, 
				getLayout().getLineLeft(0) + len, 0, 
				a, f, TileMode.CLAMP);
		paint.setShader(shader);
		super.onDraw(canvas);
	}

	private Runnable update = new Runnable() {
		@Override
		public void run() {
			LrcView.this.update();
			handler.postDelayed(update, 100);
		}
	};

	private void update() {
		// 更新界面
		postInvalidate();
	}
}
