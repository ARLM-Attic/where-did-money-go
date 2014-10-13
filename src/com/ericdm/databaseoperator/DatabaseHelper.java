package com.ericdm.databaseoperator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
	private static final int VERSION = 1;
	private static final String CREATE_TABLE_SQL = "create table if not exists MoneyInfoTable(FROM_TIME char(10), TO_TIME char(10))"; //PLAYED_SONG_POSTION INTEGER, MUST_HAS_HEAD_SET INTEGER

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
		db.execSQL(CREATE_TABLE_SQL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}

}
