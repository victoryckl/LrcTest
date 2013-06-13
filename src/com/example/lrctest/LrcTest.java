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
		list.add("��1�䣺�����Ƕ�������j");
		list.add("��2��:how are you?");
		list.add("��3��:i'm fine, and you?");
		list.add("��4��:me, too");
		list.add("��5��:how are you?");
		list.add("��6��:how are you?");
		list.add("��7��:how are you?");
		list.add("��8��:how are you?");
		list.add("��9��:i'm fine, and you?");
		list.add("��10��:me, too");
		list.add("��11��:how are you?");
		list.add("��12��:how are you?");
		return list ;
	}
}
