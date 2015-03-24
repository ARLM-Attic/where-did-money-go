package com.ericdm.databaseoperator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
	private static final int VERSION = 1;
	private static final String CREATE_TABLE_SQL_SEARCH_TIME =  "create table if not exists SearchTimeTable(FROM_TIME char(10), TO_TIME char(10))"; //PLAYED_SONG_POSTION INTEGER, MUST_HAS_HEAD_SET INTEGER
	//private static final String CREATE_TABLE_SQL_INCOME_COLUMN_NAME = "create table if not exists IncomeColumnNameTable(INCOME_COLUMN_NAME nvarchar(512))";
	private static final String CREATE_TABLE_SQL_OUTCOME_COLUMN_NAME = "create table if not exists OutcomeColumnNameTable(OUTCOME_COLUMN_NAME nvarchar(512))";
	private static final String CREATE_TABLE_SQL_OUTCOME = "create table if not exists OutcomeTable(OUTCOME_NAME nvarchar(512), OUTCOME_VALUE INTEGER)";
	
	public DatabaseHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	
	public DatabaseHelper(Context context, String name)
	{
		this(context, name, null, VERSION);  
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//db.execSQL(CREATE_TABLE_SQL_INCOME_COLUMN_NAME);
		db.execSQL(CREATE_TABLE_SQL_OUTCOME_COLUMN_NAME);
		db.execSQL(CREATE_TABLE_SQL_SEARCH_TIME);
		db.execSQL(CREATE_TABLE_SQL_OUTCOME);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}

}
