package com.sktekki.simplemath;

import com.sktekki.simplemath.R;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ListView;

public class ResultActivity extends Activity 
{
	ListView lv;
    Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		
		SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		//no need for an editor when retrieving
		String mPlayerScore = Integer.toString(mPrefs.getInt("score", 0));
		String name = mPrefs.getString("name", "xcsxsx");

		String [] prgmNameList={name};
		String [] prgmImages={mPlayerScore};
	    
		context=this;
		lv=(ListView) findViewById(R.id.listView1);
		lv.setAdapter(new CustomAdapter(this, prgmNameList,prgmImages));
		}
}

