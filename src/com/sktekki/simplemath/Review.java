package com.sktekki.simplemath;

import com.google.analytics.tracking.android.EasyTracker;
import com.sktekki.simplemath.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Review extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.review);

		Bundle b = getIntent().getExtras();
		int TotalQuestions = b.getInt("TotalQuestions");
		int RightAnswer = b.getInt("RightAnswer");
		int WrongAnswer = b.getInt("wrongAnswer");
		final int score = b.getInt("score");

		Typeface custom_font = Typeface.createFromAsset(getAssets(),
				"fonts/SofadiOne-Regular.ttf");

		TextView totalQuestions = (TextView) findViewById(R.id.textView1);
		totalQuestions.setText("Total Questions: " + TotalQuestions);
		totalQuestions.setTypeface(custom_font);

		TextView rightAnswer = (TextView) findViewById(R.id.textView2);
		rightAnswer.setText("Correct Answers: " + RightAnswer);
		rightAnswer.setTypeface(custom_font);

		TextView wrongAnswer = (TextView) findViewById(R.id.textView3);
		wrongAnswer.setText("Wrong Answers: " + WrongAnswer);
		wrongAnswer.setTypeface(custom_font);

		TextView Scores = (TextView) findViewById(R.id.textView5);
		Scores.setText("Your Score: " + score);
		Scores.setTypeface(custom_font);

		TextView heading = (TextView) findViewById(R.id.textView4);
		heading.setTypeface(custom_font);

		Button savescores = (Button) findViewById(R.id.button1);
		Typeface custom_font1 = Typeface.createFromAsset(getAssets(),
				"fonts/SofadiOne-Regular.ttf");
		savescores.setTypeface(custom_font1);
		savescores.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) 
			{
				startActivity(new Intent(Review.this, Second.class));
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
