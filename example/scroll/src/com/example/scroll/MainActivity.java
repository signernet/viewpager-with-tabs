package com.example.scroll;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends FragmentActivity implements OnClickListener{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ContainerFragment()).commit();
	}
	@Override
	public void onClick(View v) {
		
	}
	




}
