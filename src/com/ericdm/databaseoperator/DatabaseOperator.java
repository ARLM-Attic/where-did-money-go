package com.ericdm.databaseoperator;

import com.ericdm.wheredidmoneygo.AppConstant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseOperator {
	ContentValues mContentValues = null;
	DatabaseHelper mDatabaseHelper = null;
	SQLiteDatabase mSqLiteDatabase = null;
	
	public DatabaseOperator(Context context) {
		if (mContentValues == null) {
			mContentValues = new ContentValues();
		}
		if (mDatabaseHelper == null) {
			mDatabaseHelper = new DatabaseHelper(context, AppConstant.SQL_TABLE_NAME);
		}
	}
	
	public void insertValueToDatabase(String columNameInTable, String value) {
		mContentValues.clear();
		mContentValues.put(columNameInTable, value);
		mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
		if (mSqLiteDatabase.update(AppConstant.SQL_TABLE_NAME, mContentValues, null, null) == 0) {
			mSqLiteDatabase.insert(AppConstant.SQL_TABLE_NAME, null, mContentValues);
		}
		mSqLiteDatabase.close();
	}
	
	public String getColumnValueFromDatabase(String columNameInTable) {
		mSqLiteDatabase = mDatabaseHelper.getReadableDatabase();
		String res = "";
		Cursor cursor = mSqLiteDatabase.query(AppConstant.SQL_TABLE_NAME, new String[] {columNameInTable}, null, null, null, null, null);
		while (cursor.moveToNext()) {
			res = cursor.getString(cursor.getColumnIndex(columNameInTable));
		}
		cursor.close();
		mSqLiteDatabase.close();
		return res;
	}
}