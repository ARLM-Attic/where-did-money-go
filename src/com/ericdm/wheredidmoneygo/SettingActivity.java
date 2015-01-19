package com.ericdm.wheredidmoneygo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import com.ericdm.databaseoperator.DatabaseOperator;
import com.ericdm.timemanager.TimeManager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

@SuppressLint("SimpleDateFormat")
public class SettingActivity extends Activity{
	Calendar mFromDateCalendar = Calendar.getInstance(Locale.CHINA);
	Calendar mFromDateCalendarToTime = Calendar.getInstance(Locale.CHINA);
	SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Button mSetFromTimeButton = null;
	Button mSetToTimeButton = null;
	Button mOkButton = null;
	Button mCancelButton = null;
	Boolean mIsFromTimeSet = false;
	Boolean mIsToTimeSet = false;
	Button mAddIncomeButton = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		mSetFromTimeButton = (Button) findViewById(R.id.set_from_time_button);
		mSetToTimeButton = (Button) findViewById(R.id.set_to_time_button);
		mOkButton = (Button) findViewById(R.id.yes_button);
		mCancelButton = (Button) findViewById(R.id.no_button);
		mAddIncomeButton = (Button) findViewById(R.id.add_income_button);
		mSetFromTimeButton.setOnClickListener(mSetFromTimeOnClickListener);
		mCancelButton.setOnClickListener(mCancelButtOnClickListener);
		mOkButton.setOnClickListener(mOkButtonOnClickListener);
		mSetToTimeButton.setOnClickListener(msetToTimeOnClickListener);
		mAddIncomeButton.setOnClickListener(mAddIncomeButtonOnClickListener);
	}
	
	DatePickerDialog.OnDateSetListener setToTimeOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
		
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
	        Calendar dateCalendar = Calendar.getInstance(Locale.CHINA);
	        dateCalendar.set(Calendar.YEAR, year);
	        dateCalendar.set(Calendar.MONTH, monthOfYear);
	        dateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			DatabaseOperator databaseOperator = new DatabaseOperator(SettingActivity.this);
			String fromTimeString = databaseOperator.getColumnValueFromDatabase(AppConstant.FROM_TIME_COLUMN_IN_DATABASE_TABLE);	
			String toTimeString = mDateFormat.format(dateCalendar.getTime());
			if ((TimeManager.compareDate(toTimeString, fromTimeString).equals(AppConstant.COMPARE_DATE_RESULT_LESS))) {
				mIsToTimeSet = false;
				Toast toast = Toast.makeText(SettingActivity.this, "结束日期小于开始日期，请重新选择！", Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				return;
			}
			mIsToTimeSet = true;
			mFromDateCalendarToTime.set(Calendar.YEAR, year);
			mFromDateCalendarToTime.set(Calendar.MONTH, monthOfYear);
			mFromDateCalendarToTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		}
	};

	DatePickerDialog.OnDateSetListener setFromTimeOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
		
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
	        Calendar dateCalendar = Calendar.getInstance(Locale.CHINA);
	        dateCalendar.set(Calendar.YEAR, year);
	        dateCalendar.set(Calendar.MONTH, monthOfYear);
	        dateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			DatabaseOperator databaseOperator = new DatabaseOperator(SettingActivity.this);
			String toTimeString = databaseOperator.getColumnValueFromDatabase(AppConstant.TO_TIME_COLUMN_IN_DATABASE_TABLE);
			String fromTtimeString = mDateFormat.format(dateCalendar.getTime());
			if ((TimeManager.compareDate(fromTtimeString, toTimeString).equals(AppConstant.COMPARE_DATE_RESULT_BIG))) {
				mIsFromTimeSet = false;
				Toast toast = Toast.makeText(SettingActivity.this, "开始日期大于结束日期，请重新选择！", Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				return;
			}
			mIsFromTimeSet = true;
			mFromDateCalendar.set(Calendar.YEAR, year);
			mFromDateCalendar.set(Calendar.MONTH, monthOfYear);
			mFromDateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		}
	};	
	
	OnClickListener mCancelButtOnClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			System.exit(0);
		}
	};
	
	OnClickListener mOkButtonOnClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			DatabaseOperator databaseOperator = new DatabaseOperator(SettingActivity.this);
			if (mIsFromTimeSet) {
				databaseOperator.insertValueToDatabase(AppConstant.FROM_TIME_COLUMN_IN_DATABASE_TABLE, mDateFormat.format(mFromDateCalendar.getTime()));
			}
			if (mIsToTimeSet) {
				databaseOperator.insertValueToDatabase(AppConstant.TO_TIME_COLUMN_IN_DATABASE_TABLE, mDateFormat.format(mFromDateCalendarToTime.getTime()));
			}
			System.exit(0);
		}
	};
	
	OnClickListener msetToTimeOnClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			DatabaseOperator databaseOperator = new DatabaseOperator(SettingActivity.this);
			String currentToTimeString = databaseOperator.getColumnValueFromDatabase(AppConstant.TO_TIME_COLUMN_IN_DATABASE_TABLE);
			String[] currentToTimeStrings = currentToTimeString.split("-");
			int year = Integer.parseInt(currentToTimeStrings[0]);
			int month = Integer.parseInt(currentToTimeStrings[1]);
			int day = Integer.parseInt(currentToTimeStrings[2]);
			new DatePickerDialog(SettingActivity.this, setToTimeOnDateSetListener, year,  month - 1,  day).show();
		}
	};
	
	OnClickListener mSetFromTimeOnClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			DatabaseOperator databaseOperator = new DatabaseOperator(SettingActivity.this);
			String currentFromTimeString = databaseOperator.getColumnValueFromDatabase(AppConstant.FROM_TIME_COLUMN_IN_DATABASE_TABLE);
			String[] currentFromTimeStrings = currentFromTimeString.split("-");
			int year = Integer.parseInt(currentFromTimeStrings[0]);
			int month = Integer.parseInt(currentFromTimeStrings[1]);
			int day = Integer.parseInt(currentFromTimeStrings[2]);
			new DatePickerDialog(SettingActivity.this, setFromTimeOnDateSetListener, year,  month -1 ,  day).show();
		}
	};
	
	Boolean checkDatabaseHasRecord(String tableNameString, String columnNameString) {
    	DatabaseOperator databaseOperator = new DatabaseOperator(SettingActivity.this);
    	String valuesString = databaseOperator.getColumnValueFromDatabaseEX(tableNameString, columnNameString);
    	if (valuesString.equals("")) {
    		return false;
		} else {
			return true;
		}
	}
	
	OnClickListener mAddIncomeButtonOnClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (!checkDatabaseHasRecord(AppConstant.SQL_TABLE_NAME_INCOME_COLUMN_NAME, AppConstant.COLUMN_NAME_INCOME_COLUMN_NAME)) {
				//Not has any income category set yet.
			    new AlertDialog.Builder(SettingActivity.this)  
			    .setTitle("修改收入")
			    .setMessage("还没有设置过收入类别，现在就去添加一下吧！")
			    .setIcon(android.R.drawable.ic_dialog_info)  
			    .setPositiveButton("确定", mIncomeAlertDialogOnClickListener)  
			    .setNegativeButton("取消", mIncomeAlertDialogOnClickListener)  
			    .show();  
				
			}
		}
	};
	
	android.content.DialogInterface.OnClickListener mIncomeAlertDialogOnClickListener = new android.content.DialogInterface.OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			switch (which) {
			case -1:	//OK Button
				Intent intent = new Intent(SettingActivity.this, ModifyMoneyCategory.class);
				startActivity(intent);
				break;
			case -2:	//Cancel Button
				dialog.cancel();
				break;
			default:
				break;
			}
		}
	};
}
