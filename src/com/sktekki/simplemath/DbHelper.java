package com.sktekki.simplemath;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {

	private SQLiteDatabase myDataBase;
	private final Context myContext;

	public static final String Question_id = "Question_id";
	public static final String Question = "Question";
	public static final String OPTA = "A";
	public static final String OPTB = "B";
	public static final String OPTC = "C";
	public static final String OPTD = "D";
	public static final String Answer = "Answer";
	public static final String Category = "Category_id";

	@SuppressLint("SdCardPath")
	private static String DB_PATH = "/data/data/com.sktekki.simplemath/databases/";
	private static final String DATABASE_NAME = "SimpleMath";
	private static final String TABLE_NAME = "Questions";
	private static final int DATABASE_VERSION = 1;

	public DbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.myContext = context;
		Log.d("info", "Sucess");
	}

	public void createDataBase() throws IOException {

		boolean dbExist = checkDataBase();

		if (dbExist) {
			// do nothing - database already exist
		} else {

			// By calling this method and empty database will be created into
			// the default system path
			// of your application so we are gonna be able to overwrite that
			// database with our database.
			this.getReadableDatabase();

			try {

				copyDataBase();
				Log.d("info", "Sucess");

			} catch (IOException e) {

				throw new Error("Error copying database");

			}
		}

	}

	private boolean checkDataBase() {

		SQLiteDatabase checkDB = null;

		try {
			String myPath = DB_PATH + DATABASE_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READONLY);

		} catch (SQLiteException e) {

			// database does't exist yet.

		}

		if (checkDB != null) {

			checkDB.close();

		}

		return checkDB != null ? true : false;
	}

	private void copyDataBase() throws IOException {

		// Open your local db as the input stream
		InputStream myInput = myContext.getAssets().open(DATABASE_NAME);

		// Path to the just created empty db
		String outFileName = DB_PATH + DATABASE_NAME;

		// Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);

		// transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}

		// Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	public void openDataBase() throws SQLException {

		// Open the database
		String myPath = DB_PATH + DATABASE_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READONLY);
	}

	@Override
	public synchronized void close() {
		if (myDataBase != null)
			myDataBase.close();
		super.close();
	}

	public List<Question> getAllQuestions(int x,int y) 
	{
		List<Question> quesList = new ArrayList<Question>();
		// Select All Query
		String selectQuery = "";
		if (x != 6) {
			selectQuery = "SELECT  * FROM " + TABLE_NAME + " where " + Category
					+ "=" + x + " and Dificulty_id = " +y ;
		} else {
			selectQuery = "SELECT  * FROM " + TABLE_NAME + " where Dificulty_id= " +y;
		}

		myDataBase = this.getReadableDatabase();
		Cursor cursor = myDataBase.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Question quest = new Question();
				quest.setQuestion_ID(cursor.getInt(0));
				quest.setQuestion(cursor.getString(1));
				quest.setOPTA(cursor.getString(2));
				quest.setOPTB(cursor.getString(3));
				quest.setOPTC(cursor.getString(4));
				quest.setOPTD(cursor.getString(5));
				quest.setAnswer(cursor.getString(6));
				quest.setCategory(cursor.getInt(7));
				quesList.add(quest);
			} while (cursor.moveToNext());
		}
		//quesList.get(new Random().nextInt(quesList.size()));
		Collections.shuffle(quesList);
		return quesList;
	}

	public int rowcount(int x) 
	{
		int row = 0;
		String selectQuery = "";
		if (x != 6) {
			selectQuery = "SELECT  * FROM " + TABLE_NAME + " where " + Category
					+ "=" + x;
		} else {
			selectQuery = "SELECT  * FROM " + TABLE_NAME;
		}		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		row = cursor.getCount();
		return row;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
}
