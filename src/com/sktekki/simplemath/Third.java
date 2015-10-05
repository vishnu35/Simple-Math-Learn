package com.sktekki.simplemath;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.sktekki.simplemath.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Third extends Activity 
{
	List<Question> quesList;
	int score = 0;
	int qid = 0;
	int RightAnswer = 0;
	int WrongAnswer = 0;
	Question currentQ;
	int NoQuestions=0;
	TextView txtQuestion;
	TextView scores;
	TextView TotalQuestions;
	Dialog d;
	RadioButton rda, rdb, rdc, rdd;
	Button butNext;
	int RemainingQuestions = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		
		Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/belligerent.ttf");
		Typeface custom_font1 = Typeface.createFromAsset(getAssets(), "fonts/SofadiOne-Regular.ttf");
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.third);

		AdView adView = (AdView)this.findViewById(R.id.adView);
	    AdRequest adRequest = new AdRequest.Builder().build();
	    adView.loadAd(adRequest);
		
		scores = (TextView) findViewById(R.id.textView3);
		TotalQuestions = (TextView) findViewById(R.id.textView2);

		DbHelper db = new DbHelper(this);
		try {
			db.createDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Bundle b = getIntent().getExtras();
		int click = b.getInt("click");
		int level = b.getInt("level");
		//int level =2;
		NoQuestions = b.getInt("tQuestions");
		//final int NoQuestions = 1;
		RemainingQuestions=NoQuestions;
		quesList = db.getAllQuestions(click,level);
		quesList.get(new Random().nextInt(quesList.size()));
		currentQ = quesList.get(qid);
		//RowCount = db.rowcount(click);

		
		TotalQuestions.setTypeface(custom_font1);
		scores.setTypeface(custom_font1);
		
		txtQuestion = (TextView) findViewById(R.id.textView1);
		
		txtQuestion.setTypeface(custom_font);
		
		rda = (RadioButton) findViewById(R.id.radio0);
		rda.setTypeface(custom_font);
		
		rdb = (RadioButton) findViewById(R.id.radio1);
		rdb.setTypeface(custom_font);
		rdc = (RadioButton) findViewById(R.id.radio2);
		rdc.setTypeface(custom_font);
		rdd = (RadioButton) findViewById(R.id.radio3);
		rdd.setTypeface(custom_font);
		setQuestionView();
		
		TotalQuestions.setText("Questions:" + RemainingQuestions + "/"
				+ NoQuestions);
		scores.setText("Score:" + Integer.toString(score));

		butNext = (Button) findViewById(R.id.button1);
		
		butNext.setTypeface(custom_font1);
		
		
		butNext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				RadioGroup grp = (RadioGroup) findViewById(R.id.radioGroup1);
				RadioButton answer = (RadioButton) findViewById(grp
						.getCheckedRadioButtonId());

				int selected = grp.getCheckedRadioButtonId();
				if (selected != -1) {
					currentQ.setYourAnswer(answer.getText().toString());
					Log.d("yourans",
							currentQ.getAnswer() + " " + answer.getText());
					TotalQuestions.setText("Questions:" + --RemainingQuestions
							+ "/" + NoQuestions);

					if (currentQ.getAnswer().equals(answer.getText())) {
						score++;
						RightAnswer++;
						scores.setText("Score:" + Integer.toString(score));
						Log.d("score", "Your score" + score);
						d = new Dialog(Third.this);
						// d.requestWindowFeature(Window.FEATURE_NO_TITLE);
						d.getWindow().setBackgroundDrawableResource(R.drawable.colors);
						// d.setContentView(R.layout.dialog);
						d.setTitle("Well done..!");
						d.show();
						grp.clearCheck();
					} else {
						String alert1 = "Oops!..";
						String alert2 = "Answer is: "
								+ currentQ.getAnswer().toString();
						d = new Dialog(Third.this);
						d.getWindow().setBackgroundDrawableResource(R.drawable.colors);
						d.setTitle(Html.fromHtml(alert1 + "\n" + alert2));
						d.show();
						grp.clearCheck();
					}

					if (qid < NoQuestions) {
						currentQ = quesList.get(qid);
						setQuestionView();
					} else {

						WrongAnswer = NoQuestions - RightAnswer;
						Intent intent = new Intent(Third.this, Review.class);
						Bundle b = new Bundle();
						b.putInt("TotalQuestions", NoQuestions);
						b.putInt("RightAnswer", RightAnswer);
						b.putInt("wrongAnswer", WrongAnswer);
						b.putInt("score", score);
						intent.putExtras(b);
						startActivity(intent);
						finish();
					}
				} else {
					Toast toast = Toast.makeText(Third.this,
							"Please Select Atleast One", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
				}

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public void onBackPressed() 
	{
		// TODO Auto-generated method stub
		super.onBackPressed();
		startActivity(new Intent(Third.this, Second.class));
		
	}

	private void setQuestionView() {
		txtQuestion.setText(currentQ.getQuestion());
		rda.setText(currentQ.getOPTA());
		rdb.setText(currentQ.getOPTB());
		rdc.setText(currentQ.getOPTC());
		rdd.setText(currentQ.getOPTD());
		qid++;
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
