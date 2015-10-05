package com.sktekki.simplemath;

import com.google.analytics.tracking.android.EasyTracker;
import com.sktekki.simplemath.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Second extends Activity implements View.OnClickListener 
{
	int click=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);

		Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/SofadiOne-Regular.ttf");
		
		Button Addition = (Button) findViewById(R.id.button1);
		Addition.setTypeface(custom_font);
		Button Division = (Button) findViewById(R.id.Button01);
		Division.setTypeface(custom_font);
		Button All = (Button) findViewById(R.id.Button02);
		All.setTypeface(custom_font);
		Button substraction = (Button) findViewById(R.id.Button03);
		substraction.setTypeface(custom_font);
		Button Multilication = (Button) findViewById(R.id.Button04);
		Multilication.setTypeface(custom_font);
		Button Formulas = (Button) findViewById(R.id.Button05);
		Formulas.setTypeface(custom_font);

		Addition.setOnClickListener(this);
		Division.setOnClickListener(this);
		All.setOnClickListener(this);
		substraction.setOnClickListener(this);
		Multilication.setOnClickListener(this);
		Formulas.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.Button02:
			Log.d("button", "All Clicked");
			Intent intent = new Intent(Second.this, Settings.class);
			Bundle All = new Bundle();
			click=6;
			All.putInt("click", click);
			intent.putExtras(All); 
			startActivity(intent);
			break;

		case R.id.Button03:
			Log.d("button", "substraction Clicked");
			Intent intent1 = new Intent(Second.this, Settings.class);
			Bundle substraction = new Bundle();
			click=2;
			substraction.putInt("click", click);
			intent1.putExtras(substraction); 
			startActivity(intent1);
			break;

		case R.id.Button01:
			Log.d("button", "Division Clicked");
			Intent intent2 = new Intent(Second.this, Settings.class);
			Bundle Division = new Bundle();
			click=4;
			Division.putInt("click", click);
			intent2.putExtras(Division); 
			startActivity(intent2);
			break;

		case R.id.button1:
			Log.d("button", "Addition Clicked");
			Intent intent3 = new Intent(Second.this, Settings.class);
			Bundle Addition = new Bundle();
			click=1;
			Addition.putInt("click", click);
			intent3.putExtras(Addition); 
			startActivity(intent3);
			break;

		case R.id.Button04:
			Log.d("button", "Multilication Clicked");
			Intent intent4 = new Intent(Second.this, Settings.class);
			Bundle Multilication = new Bundle();
			click=3;
			Multilication.putInt("click", click);
			intent4.putExtras(Multilication); 
			startActivity(intent4);
			break;

		case R.id.Button05:
			Log.d("button", "Formulas Clicked");
			Intent intent5 = new Intent(Second.this, Settings.class);
			Bundle Formulas = new Bundle();
			click=5;
			Formulas.putInt("click", click);
			intent5.putExtras(Formulas); 
			startActivity(intent5);
			break;
		}

	}
	@Override
	public void onBackPressed() 
	{
		// TODO Auto-generated method stub
		super.onBackPressed();
		//startActivity(new Intent(Second.this, MainActivity.class));
	}
	
	@Override
	  public void onStart() 
	{
	    super.onStart();
	    // The rest of your onStart() code.
	    EasyTracker.getInstance(this).activityStart(this);  // Add this method.
	  }

	  @Override
	  public void onStop() {
	    super.onStop();
	    // The rest of your onStop() code.
	    EasyTracker.getInstance(this).activityStop(this);  // Add this method.
	  }

}
