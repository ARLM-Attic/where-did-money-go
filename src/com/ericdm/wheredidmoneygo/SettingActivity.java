package com.ericdm.wheredidmoneygo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.ericdm.databaseoperator.DatabaseOperator;
import com.ericdm.timemanager.TimeManager;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

public class SettingActivity extends Activity{
	Calendar mDateCalendar = Calendar.getInstance(Locale.CHINA);
	SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Button mSetFromTimeButton = null;
	Button mOkButton = null;
	Button mCancelButton = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		mSetFromTimeButton = (Button) findViewById(R.id.set_from_time_button);
		mOkButton = (Button) findViewById(R.id.yes_button);
		mCancelButton = (Button) findViewById(R.id.no_button);
		mSetFromTimeButton.setOnClickListener(mSetFromTimeOnClickListener);
	}

	DatePickerDialog.OnDateSetListener setFromTimeOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
		boolean mFired = false;
		
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
	        if (mFired == true) {   //This call back executed twice, so return.
	            return;
	        } else {
	            mFired = true;
	        }
			mDateCalendar.set(Calendar.YEAR, year);
			mDateCalendar.set(Calendar.MONTH, monthOfYear);
			mDateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			DatabaseOperator databaseOperator = new DatabaseOperator(SettingActivity.this);
			String toTimeString = databaseOperator.getColumnValueFromDatabase(AppConstant.TO_TIME_COLUMN_IN_DATABASE_TABLE);
			String fromTtimeString = mDateFormat.format(mDateCalendar.getTime());
			if ((TimeManager.compareDate(fromTtimeString, toTimeString).equals(AppConstant.COMPARE_DATE_RESULT_BIG))) {
				Toast toast = Toast.makeText(SettingActivity.this, "开始日期大于结束日期，请重新选择！", Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				return;
			}
			databaseOperator.insertValueToDatabase(AppConstant.FROM_TIME_COLUMN_IN_DATABASE_TABLE, mDateFormat.format(mDateCalendar.getTime()));
		}
	};	
	
	OnClickListener mOkButtonOnClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
		}
	};
	
	OnClickListener mSetFromTimeOnClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			new DatePickerDialog(SettingActivity.this, setFromTimeOnDateSetListener, mDateCalendar.get(Calendar.YEAR),  mDateCalendar.get(Calendar.MONTH),  mDateCalendar.get(Calendar.DAY_OF_MONTH)).show();
		}
	};
}
