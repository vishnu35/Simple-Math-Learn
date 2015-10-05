package com.sktekki.simplemath;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.sktekki.simplemath.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.util.Log;

public class Settings extends Activity {
	OnCheckedChangeListener listner = null;
	int level = 0;	
	int click = 0;
	RadioGroup difficulty;
	Intent intent1,intent2,intent3,intent4,intent5,intent6;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);

		AdView adView = (AdView)this.findViewById(R.id.adView);
	    AdRequest adRequest = new AdRequest.Builder().build();
	    adView.loadAd(adRequest);
	    
		Bundle b = getIntent().getExtras();
		click = b.getInt("click");

		Typeface custom_font = Typeface.createFromAsset(getAssets(),
				"fonts/SofadiOne-Regular.ttf");
		Typeface custom_font1 = Typeface.createFromAsset(getAssets(),
				"fonts/belligerent.ttf");

		TextView heading1 = (TextView) findViewById(R.id.textView1);
		heading1.setTypeface(custom_font);
		

		difficulty = (RadioGroup) findViewById(R.id.radioGroup1);
		difficulty.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (group.getCheckedRadioButtonId()) {
				case R.id.radioButton1:
					Log.d("info", "one");
					intent1 = new Intent(Settings.this, Qnumber.class);
					level = 1;
					intent1.putExtra("level", level);
					intent1.putExtra("click", click);
					// startActivity(intent1);
					break;

				case R.id.radioButton2:
					Log.d("info", "two");
					intent2 = new Intent(Settings.this, Qnumber.class);
					level = 2;
					intent2.putExtra("level", level);
					intent2.putExtra("click", click);
					// startActivity(intent2);
					break;

				case R.id.radioButton3:
					Log.d("info", "three");
					intent3 = new Intent(Settings.this, Qnumber.class);
					level = 3;
					intent3.putExtra("level", level);
					intent3.putExtra("click", click);
					// startActivity(intent2);
					break;
				}

			}
		});

		RadioButton easy = (RadioButton) findViewById(R.id.radioButton1);
		easy.setTypeface(custom_font1);
		RadioButton medium = (RadioButton) findViewById(R.id.radioButton2);
		medium.setTypeface(custom_font1);
		RadioButton hard = (RadioButton) findViewById(R.id.radioButton3);
		hard.setTypeface(custom_font1);



		Button submit = (Button) findViewById(R.id.button1);
		submit.setTypeface(custom_font);
		submit.setOnClickListener(new OnClickListener() 
		{

			@Override
			public void onClick(View arg0) 
			{
				int selected=difficulty.getCheckedRadioButtonId();;
				if (selected != -1) 
				{
				if(level==1)
				{
					startActivity(intent1);
				}
				else if(level==2)
				{
					startActivity(intent2);
				}
				else if(level==3)
				{
					startActivity(intent3);
				}	
				}
				else
				{
					Toast toast = Toast.makeText(Settings.this,
							"Please Select Atleast One", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0,70);
					toast.show();
				}
			}
		});

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
