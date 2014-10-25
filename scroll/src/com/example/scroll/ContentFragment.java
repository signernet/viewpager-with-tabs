package com.example.scroll;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class ContentFragment extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		int type = getArguments().getInt("type");
		TextView tv = new TextView(getActivity());
		tv.setGravity(Gravity.CENTER);
		tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
		tv.setText(type+"");
		switch(type){
		case 0:
			tv.setTextColor(Color.parseColor("#ff0000"));
			break;
		case 1:
			tv.setTextColor(Color.parseColor("#00ff00"));
			break;
		case 2:
			tv.setTextColor(Color.parseColor("#0000ff"));
			break;
		default:
			tv.setTextColor(getResources().getColor(R.color.abc_search_url_text_pressed));
		}
		
		tv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		
		return tv;
	}
}
