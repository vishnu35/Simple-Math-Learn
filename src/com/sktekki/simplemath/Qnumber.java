package com.sktekki.simplemath;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.sktekki.simplemath.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class Qnumber extends Activity 
{
	OnCheckedChangeListener listner = null;
	int tQuestions = 0;
	int level = 0;
	int click = 0;
	RadioGroup difficulty;
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qnumber);

		AdView adView = (AdView)this.findViewById(R.id.adView);
	    AdRequest adRequest = new AdRequest.Builder().build();
	    adView.loadAd(adRequest);
	    
		Bundle b = getIntent().getExtras();
		click = b.getInt("click");
		level = b.getInt("level");

		Typeface custom_font = Typeface.createFromAsset(getAssets(),
				"fonts/SofadiOne-Regular.ttf");
		Typeface custom_font1 = Typeface.createFromAsset(getAssets(),
				"fonts/belligerent.ttf");

		TextView heading1 = (TextView) findViewById(R.id.textView1);
		heading1.setTypeface(custom_font);

		RadioButton ten = (RadioButton) findViewById(R.id.radioButton1);
		ten.setTypeface(custom_font1);

		RadioButton twenty = (RadioButton) findViewById(R.id.radioButton2);
		twenty.setTypeface(custom_font1);
		
		RadioButton thirty = (RadioButton) findViewById(R.id.radioButton3);
		thirty.setTypeface(custom_font1);

		
		difficulty = (RadioGroup) findViewById(R.id.radioGroup1);
		
		
		
		difficulty.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (group.getCheckedRadioButtonId()) {
				case R.id.radioButton1:
					Log.d("info", "one");
					intent = new Intent(Qnumber.this, Third.class);
					tQuestions = 10;
					intent.putExtra("level", level);
					intent.putExtra("click", click);
					intent.putExtra("tQuestions", tQuestions);
					break;

				case R.id.radioButton2:
					Log.d("info", "two");
					intent = new Intent(Qnumber.this, Third.class);
					tQuestions = 20;
					intent.putExtra("level", level);
					intent.putExtra("click", click);
					intent.putExtra("tQuestions", tQuestions);
					break;

				case R.id.radioButton3:
					Log.d("info", "three");
					intent = new Intent(Qnumber.this, Third.class);
					tQuestions = 30;
					intent.putExtra("level", level);
					intent.putExtra("click", click);
					intent.putExtra("tQuestions", tQuestions);
					break;
				}

			}
		});

		Button submit = (Button) findViewById(R.id.button1);
		submit.setTypeface(custom_font);
		submit.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				int selected=difficulty.getCheckedRadioButtonId();;
				if (selected != -1) 
				{
				if (level == 1 || level == 2 || level == 3) 
				{
					startActivity(intent);
				} 
				}
				else
				{
					Toast toast = Toast.makeText(Qnumber.this,
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
