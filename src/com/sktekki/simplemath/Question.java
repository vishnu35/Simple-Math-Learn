package com.sktekki.simplemath;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Question implements Serializable 
{
	    private int Question_id;
	    private String Question;	    
	    private String OPTA;
	    private String OPTB;
	    private String OPTC;
	    private String OPTD;
	    private String Answer;
	    private int Category_id;
	    private String YourAnswer;
	    
	    

		public String getYourAnswer() {
			return YourAnswer;
		}

		public void setYourAnswer(String yourAnswer) {
			YourAnswer = yourAnswer;
		}

		public int getQuestion_ID() {
			return Question_id;
		}

		public void setQuestion_ID(int question_ID) {
			Question_id = question_ID;
		}

		public String getQuestion() {
			return Question;
		}

		public void setQuestion(String question) {
			Question = question;
		}

		public String getOPTA() {
			return OPTA;
		}

		public void setOPTA(String oPTA) {
			OPTA = oPTA;
		}

		public String getOPTB() {
			return OPTB;
		}

		public void setOPTB(String oPTB) {
			OPTB = oPTB;
		}

		public String getOPTC() {
			return OPTC;
		}

		public void setOPTC(String oPTC) {
			OPTC = oPTC;
		}

		public String getOPTD() {
			return OPTD;
		}

		public void setOPTD(String oPTD) {
			OPTD = oPTD;
		}

		public String getAnswer() {
			return Answer;
		}

		public void setAnswer(String answer) {
			Answer = answer;
		}

		
		public int getCategory() {
			return Category_id;
		}

		public void setCategory(int category) {
			Category_id = category;
		}
	    
	    
}
