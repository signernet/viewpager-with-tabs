package com.example.scroll;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyHSV extends HorizontalScrollView{
	
	private OnScrollListener onScrollListener;
	private LinearLayout ll;
	private ImageView cursorIv;
	private int currentTab;
	private TextView currentTabTv;
	private Context mContext;
	private OnClickItemListener onClickItemListener;
	private String[] tabsArr;
	
	public interface OnClickItemListener{
		void onclick(int pos);
	}
	public MyHSV(Context context) {
		super(context);
		this.mContext = context;
	}
	public MyHSV(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
	}
	
	public void setOnClickItemListener(OnClickItemListener onClickItemListener) {
		this.onClickItemListener = onClickItemListener;
	}
	public void setOnScrollListener(OnScrollListener onScrollListener) {
		this.onScrollListener = onScrollListener;
	}
	public String[] getTabsArr() {
		return tabsArr;
	}
	public ImageView getCursorIv() {
		// TODO Auto-generated method stub
		return this.cursorIv;
	}
	public LinearLayout getTabsLinearLayout(){
		return this.ll;
	}
	public int getCurrentTab() {
		return currentTab;
	}
	public TextView getCurrentTabTv() {
		return currentTabTv;
	}
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		if(onScrollListener!=null){
			onScrollListener.scroll(l, t, oldl, oldt);
		}
	}
	public interface OnScrollListener{
		void scroll(int l, int t, int oldl, int oldt);
	}
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		ll = (LinearLayout) findViewById(R.id.tabs_ll);
		System.out.println(ll);
		cursorIv = (ImageView) findViewById(R.id.cursor_iv);
		
		
		
		tabsArr = getResources().getStringArray(R.array.tabs_arr);
		for(int i=-1;++i<tabsArr.length;){
			TextView tab = (TextView) LayoutInflater.from(mContext).inflate(R.layout.tab, null);
			tab.setTag(i);
			tab.setVisibility(View.VISIBLE);
			tab.setText(tabsArr[i]);
			 LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				        ViewGroup.LayoutParams.WRAP_CONTENT,
				        ViewGroup.LayoutParams.MATCH_PARENT);
			 tab.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					System.out.println("click"+v.getTag());
					setCurrentTab((int) v.getTag());
					if(onClickItemListener!=null){
						onClickItemListener.onclick((int) v.getTag());
					}
				}
			});
			ll.addView(tab,params);
		}
		
		currentTabTv = (TextView) ll.getChildAt(0);
	}
	public void setCurrentTab(int currentTab) {
		
		this.currentTab = currentTab;
		currentTabTv = (TextView) ll.getChildAt(currentTab);
		
		int count = ll.getChildCount();
		for(int i=-1; ++i<count;){
			((TextView)ll.getChildAt(i)).setTextColor(Color.parseColor("#d0d0d0"));
		}
		currentTabTv.setTextColor(Color.parseColor("#ff0000"));
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(currentTabTv.getWidth(),20); //The WRAP_CONTENT parameters can be replaced by an absolute width and height or the FILL_PARENT option)
		params.leftMargin = (int) (currentTabTv.getLeft()); //Your X coordinate
		params.topMargin = 0; //Your Y coordinate
		cursorIv.setLayoutParams(params);
		scrollIfExceed();
		
	}
	
	private void scrollIfExceed(){
		WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		int width = display.getWidth();  // deprecated
		
		if(currentTab<ll.getChildCount()-1&&currentTab>0){
			
			int temp = 0;
			if((temp = currentTabTv.getLeft()+currentTabTv.getWidth()+ll.getChildAt(currentTab+1).getWidth()-this.getScrollX()-width)>0){
				this.smoothScrollBy(temp, 0);
			}else if((temp=this.getScrollX()-ll.getChildAt(currentTab-1).getLeft())>0){
				this.smoothScrollBy(-temp, 0);
			}
		}
		
	}
	

}
