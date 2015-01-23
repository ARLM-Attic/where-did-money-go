package com.ericdm.databaseoperator;

import java.util.ArrayList;

import com.ericdm.wheredidmoneygo.AppConstant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseOperator {
	ContentValues mContentValues = null;
	DatabaseHelper mDatabaseHelper = null;
	SQLiteDatabase mSqLiteDatabase = null;
	
	public DatabaseOperator(Context context, String tableNameString) {
		if (mContentValues == null) {
			mContentValues = new ContentValues();
		}
		if (mDatabaseHelper == null) {
			mDatabaseHelper = new DatabaseHelper(context, tableNameString);
		}
	}
	
	public void insertValueToDatabase(String columNameInTable, String value) {
		mContentValues.clear();
		mContentValues.put(columNameInTable, value);
		mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
		if (mSqLiteDatabase.update(AppConstant.SQL_TABLE_NAME_SEARCH_TIME_INFO, mContentValues, null, null) == 0) {
			mSqLiteDatabase.insert(AppConstant.SQL_TABLE_NAME_SEARCH_TIME_INFO, null, mContentValues);
		}
		mSqLiteDatabase.close();
	}
	
	public String getColumnValueFromDatabase(String columNameInTable) {
		mSqLiteDatabase = mDatabaseHelper.getReadableDatabase();
		String res = "";
		Cursor cursor = mSqLiteDatabase.query(AppConstant.SQL_TABLE_NAME_SEARCH_TIME_INFO, new String[] {columNameInTable}, null, null, null, null, null);
		while (cursor.moveToNext()) {
			res = cursor.getString(cursor.getColumnIndex(columNameInTable));
		}
		cursor.close();
		mSqLiteDatabase.close();
		return res;
	}
	
	public ArrayList<String> getColumnValueFromDatabaseEX(String tableNameString, String columNameInTable) {
		mSqLiteDatabase = mDatabaseHelper.getReadableDatabase();
		ArrayList<String> resStrings = new ArrayList<String>();
		Cursor cursor = mSqLiteDatabase.query(tableNameString, new String[] {columNameInTable}, null, null, null, null, null);
		while (cursor.moveToNext()) {
			resStrings.add(cursor.getString(cursor.getColumnIndex(columNameInTable)));
		}
		cursor.close();
		mSqLiteDatabase.close();
		return resStrings;
	}
	
	public void insertValueToDatabaseEX(String tableNameString, String columNameInTable, String value) {
		mContentValues.clear();
		mContentValues.put(columNameInTable, value);
		mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
		if (mSqLiteDatabase.update(tableNameString, mContentValues, null, null) == 0) {
			mSqLiteDatabase.insert(tableNameString, null, mContentValues);
		}
		mSqLiteDatabase.close();
	}
}
