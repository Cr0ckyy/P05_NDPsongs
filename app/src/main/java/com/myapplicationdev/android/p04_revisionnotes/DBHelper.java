package com.myapplicationdev.android.p04_revisionnotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

	//TODO Define the Database properties
	private static final String DATABASE_NAME = "";
	private static final int DATABASE_VERSION = ;

	private static final String TABLE_TASK = "notes";
	private static final String COLUMN_ID = "_id";
	private static final String COLUMN_NOTE_CONTENT = "noteContent";
	private static final String COLUMN_STARS = "stars";



	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//TODO CREATE TABLE Note

		String createTableSql = "CREATE TABLE " + TABLE_TASK +  "("
				+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ COLUMN_NOTE_CONTENT + " TEXT,"
				+ COLUMN_STARS + " INTEGER )";
		db.execSQL(createTableSql);
		Log.i("info" ,"created tables");

//	The SQL Statement:
//		CREATE TABLE `` (
//	`_id`	INTEGER PRIMARY KEY AUTOINCREMENT,
//	`noteContent`	TEXT,
//	`stars`	INTEGER
//);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);
		onCreate(db);
	}

	public void insertNote(String noteContent, int stars) {
		//TODO insert the data into the database
	}

	public ArrayList<Note> getAllNotes() {
		//TODO return records in Java objects
	}

    public ArrayList<String> getNoteContent() {
        //TODO return records in Strings

		// Create an ArrayList that holds String objects
        ArrayList<String> notes = new ArrayList<String>();
        // Select all the notes' content
        String selectQuery = "";

        // Get the instance of database to read
        SQLiteDatabase db = this.getReadableDatabase();
        // Run the SQL query and get back the Cursor object
        Cursor cursor = db.rawQuery(selectQuery, null);
        // moveToFirst() moves to first row
        if (cursor.moveToFirst()) {
            // Loop while moveToNext() points to next row and returns true;
            // moveToNext() returns false when no more next row to move to
            do {


            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();

        return notes;
    }
}
