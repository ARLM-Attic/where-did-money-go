package com.ericdm.wheredidmoneygo;

import com.ericdm.databaseoperator.DatabaseOperator;
import com.ericdm.timemanager.TimeManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {
	TextView mFromTimeTextView = null;
	TextView mToTimeTextView = null;
	String mCurrentFromTime = "";
	String mCurrentToTime = "";
	Button mSettingButton = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFromTimeTextView = (TextView) findViewById(R.id.from_time_text_view);
        mToTimeTextView = (TextView) findViewById(R.id.to_time_text_view);
        mSettingButton = (Button) findViewById(R.id.setting_button);
        initTimeValueForActivity();
        mSettingButton.setOnClickListener(settingButtonOnClickListener);
    }

    private void initTimeValueForActivity() {
    	TimeManager timeManager = new TimeManager();
    	DatabaseOperator databaseOperator = new DatabaseOperator(MainActivity.this, AppConstant.SQL_TABLE_NAME_SEARCH_TIME_INFO);
    	mCurrentFromTime = databaseOperator.getColumnValueFromDatabase(AppConstant.FROM_TIME_COLUMN_IN_DATABASE_TABLE);
    	mCurrentToTime = databaseOperator.getColumnValueFromDatabase(AppConstant.TO_TIME_COLUMN_IN_DATABASE_TABLE);
        if (mCurrentFromTime.equals("") || mCurrentToTime.equals("")) {  //First time running, query time has not been set yet.
        	String timesString[] = timeManager.getCurrentQueryTime();
        	String fromTimesString = timesString[0];
        	String toTimeString = timesString[1];
        	mFromTimeTextView.setText(AppConstant.START_DATE_IN_CHINESE + fromTimesString);
        	mToTimeTextView.setText(AppConstant.END_DATE_IN_CHINESE + toTimeString);
        	databaseOperator.insertValueToDatabase(AppConstant.FROM_TIME_COLUMN_IN_DATABASE_TABLE, fromTimesString);
        	databaseOperator.insertValueToDatabase(AppConstant.TO_TIME_COLUMN_IN_DATABASE_TABLE, toTimeString);
		} else {
        	mFromTimeTextView.setText(AppConstant.START_DATE_IN_CHINESE + mCurrentFromTime);
        	mToTimeTextView.setText(AppConstant.END_DATE_IN_CHINESE + mCurrentToTime);
		} 
    }
    
    OnClickListener settingButtonOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(MainActivity.this, SettingActivity.class);
			startActivityForResult(intent, 1);
		}
	};   
	
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
