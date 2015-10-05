package com.sktekki.simplemath;

import com.google.analytics.tracking.android.EasyTracker;
import com.sktekki.simplemath.R;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener 
{
	
	private long lastPressedTime;
	private static final int PERIOD = 2000;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/SofadiOne-Regular.ttf");
		Typeface custom_font1 = Typeface.createFromAsset(getAssets(), "fonts/KaushanScript-Regular.otf");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		AppRater.app_launched(this);
		//AppRater.showRateDialog(this, null);
		Button Start = (Button) findViewById(R.id.button1);
		Start.setTypeface(custom_font);
		Button Scores = (Button) findViewById(R.id.button2);
		Scores.setTypeface(custom_font);
		//Button Rating = (Button) findViewById(R.id.button3);
		
		TextView tv=(TextView) findViewById(R.id.textView1);
		tv.setTypeface(custom_font1);
		Start.setOnClickListener(this);
		Scores.setOnClickListener(this);
		//Rating.setOnClickListener(this);
		
		TextView bottomheading=(TextView) findViewById(R.id.textView2);
		bottomheading.setTypeface(custom_font);
	}

	@Override
	public void onClick(View v) 
	{
		switch (v.getId()) 
		{
		case R.id.button1:
			startActivity(new Intent(MainActivity.this, Second.class));
			break;
			
		case R.id.button2:
			//startActivity(new Intent(MainActivity.this, ResultActivity.class));
			Intent intent = new Intent(Intent.ACTION_VIEW);
			//Try Google play
			intent.setData(Uri.parse("market://details?id=com.sktekki.simplemath"));
			if (!MyStartActivity(intent)) {
			    //Market (Google play) app seems not installed, let's try to open a webbrowser
			    intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.sktekki.simplemath"));
			    if (!MyStartActivity(intent)) {
			        //Well if this also fails, we have run out of options, inform the user.
			        Toast.makeText(this, "Could not open Android market, please install the market app.", Toast.LENGTH_SHORT).show();
			    }
			}
			break;
			
		}
	}

	private boolean MyStartActivity(Intent aIntent) 
	{
		 try
		    {
		        startActivity(aIntent);
		        return true;
		    }
		    catch (ActivityNotFoundException e)
		    {
		        return false;
		    }
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
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
	        switch (event.getAction()) {
	        case KeyEvent.ACTION_DOWN:
	            if (event.getDownTime() - lastPressedTime < PERIOD) {
	                finish();
	            } else {
	                Toast.makeText(getApplicationContext(), "Press again to exit.",
	                        Toast.LENGTH_SHORT).show();
	                lastPressedTime = event.getEventTime();
	            }
	            return true;
	        }
	    }
	    return false;
	}
}



