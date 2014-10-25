package com.example.scroll;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.scroll.MyHSV.OnClickItemListener;

public class ContainerFragment extends Fragment implements OnPageChangeListener{
	private View thisView;
	private FragmentActivity parentAcvitity;
	private ViewPager vp;
	private LinearLayout tabsLl;
	private String[] tabsArr;
	private int currentTab;
	private TextView currentTabTv;
	private ImageView cursorIv;
	private MyHSV hsv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		parentAcvitity = getActivity();
		
		thisView = inflater.inflate(R.layout.fragment_container, null);
		initializeTabs(inflater);
		
		initializeVp();
		cursorIv = hsv.getCursorIv();
		return thisView;
	}
	
	
	
	private void initializeVp(){
		vp = (ViewPager) thisView.findViewById(R.id.vp);
		vp.setAdapter(new FragmentPagerAdapter(parentAcvitity.getSupportFragmentManager()) {

			@Override
			public Fragment getItem(int pos) {
				Fragment cf = new ContentFragment();
				Bundle b = new Bundle();
				b.putInt("type", pos);
				cf.setArguments(b);
				return cf;
			}

			@Override
			public int getCount() {
				return getResources().getStringArray(R.array.tabs_arr).length;
			}
			
		});
		
		vp.setOnPageChangeListener(this);
	}
	
	
	private void initializeTabs(LayoutInflater inflater){
		tabsLl = (LinearLayout) thisView.findViewById(R.id.tabs_ll);
		hsv = (MyHSV) thisView.findViewById(R.id.hsv);
		hsv.setOnClickItemListener(new OnClickItemListener() {
			@Override
			public void onclick(int pos) {
				vp.setCurrentItem(pos);
			}
		});
	}



	@Override
	public void onPageScrollStateChanged(int state) {
		Log.v("state",state+"");
		if(state==0&&currentTab<hsv.getTabsLinearLayout().getChildCount()){
			hsv.setCurrentTab(currentTab);
		}
	}



	@Override
	public void onPageScrolled(int pos, float offset, int offsetPixel) {
		if(pos<hsv.getTabsLinearLayout().getChildCount()){
			
			Log.v("scrolled",pos+" ,  "+offset+"  ,  "+offsetPixel);
			int width = hsv.getCurrentTabTv().getWidth();
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,20); //The WRAP_CONTENT parameters can be replaced by an absolute width and height or the FILL_PARENT option)
			
			params.leftMargin = (int) (hsv.getTabsLinearLayout().getChildAt(pos).getLeft()+offset*width); //Your X coordinate
			params.topMargin = 0; //Your Y coordinate
			cursorIv.setLayoutParams(params);
		}
	}



	@Override
	public void onPageSelected(int pos) {
		Log.v("seledted",pos+"");
		currentTab = pos;
	}
	
	/**
	 * 
	 */
	
	
}
