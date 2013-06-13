package com.example.lrctest;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class LrcTest extends Activity {
	private static final String TAG = "LrcTest";
	private LrcView mLrcView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lrc_test);
	
		init();
	}

	private void init() {
		mLrcView = (LrcView)findViewById(R.id.lrc_view);
		mLrcView.setList(getLrcList());
		mLrcView.updateUI();
	}

	private List<String> getLrcList() {
		List<String> list = new ArrayList<String>();
		list.add("第1句：试试是东方闪电j");
		list.add("第2句:how are you?");
		list.add("第3句:i'm fine, and you?");
		list.add("第4句:me, too");
		list.add("第5句:how are you?");
		list.add("第6句:how are you?");
		list.add("第7句:how are you?");
		list.add("第8句:how are you?");
		list.add("第9句:i'm fine, and you?");
		list.add("第10句:me, too");
		list.add("第11句:how are you?");
		list.add("第12句:how are you?");
		return list ;
	}
}
