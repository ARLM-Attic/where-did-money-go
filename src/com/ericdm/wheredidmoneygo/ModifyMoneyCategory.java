package com.ericdm.wheredidmoneygo;

import java.util.ArrayList;

import com.ericdm.databaseoperator.DatabaseOperator;
import com.ericdm.listviewadapter.CategoryMoneyListviewAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ModifyMoneyCategory extends Activity{
	Button mOKButton = null;
	Button mCancelButton = null;
	Button mAddOutcomeCatgoryButton = null;
	Button mAddOutcomeMoneyButton = null;
	TextView mCanModifyMoneyTextView = null;
	TextView mTotalOutcomeMoneyTextView = null;
	EditText mInputAddedCategoryEditText = null; 
	int mCanModifyMoneyInt;
	int mTotalOutcomeMoneyInt;
	static ProgressDialog mProgressDialog;
	String mInputedCategoryNameString;
	ArrayList<String> mOutcomeColumnNameArrayList;
	ArrayList<String> mOutcomeMoneyCountArrayList;
	ListView mCotegoryMoneyListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modify_money_category);
		mCanModifyMoneyInt = 0;
		mTotalOutcomeMoneyInt = 0;
		mOKButton = (Button) findViewById(R.id.yes_button_modify);
		mCancelButton = (Button) findViewById(R.id.no_button_modify);
		mAddOutcomeCatgoryButton = (Button) findViewById(R.id.add_outcome_category_button);
		mAddOutcomeMoneyButton = (Button) findViewById(R.id.add_outcome_money_button);
		mCanModifyMoneyTextView = (TextView) findViewById(R.id.can_modify_money_textview2);
		mTotalOutcomeMoneyTextView = (TextView) findViewById(R.id.total_money_textview2);
		mCotegoryMoneyListView = (ListView) findViewById(R.id.category_money_list_view);
		mCancelButton.setOnClickListener(mCancelButtonOnClickListener);
		mAddOutcomeCatgoryButton.setOnClickListener(mAddOutcomeCategoryButtonOnClickListener);
		mCanModifyMoneyTextView.setText(mCanModifyMoneyInt + "" );
		mTotalOutcomeMoneyTextView.setText(mTotalOutcomeMoneyInt + "");
		mInputedCategoryNameString = "";
		mOutcomeColumnNameArrayList = new ArrayList<String>();
		mOutcomeMoneyCountArrayList = new ArrayList<String>();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mProgressDialog = new ProgressDialog(ModifyMoneyCategory.this);
		AppConstant.showProgressModelDialog(mProgressDialog, ModifyMoneyCategory.this);
		new Thread(new GetMoneyDataFromDataBase()).start();
	}

	OnClickListener mCancelButtonOnClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			System.exit(0);
		}
	};
	
	Handler mUpdateUIHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);		
			CategoryMoneyListviewAdapter categoryMoneyListviewAdapter = new CategoryMoneyListviewAdapter(ModifyMoneyCategory.this, mOutcomeColumnNameArrayList, mOutcomeColumnNameArrayList);
			mCotegoryMoneyListView.requestLayout();
			mCotegoryMoneyListView.setAdapter(categoryMoneyListviewAdapter);
			categoryMoneyListviewAdapter.notifyDataSetChanged();		
			mProgressDialog.cancel(); 
			
		}
	};
	
	class GetMoneyDataFromDataBase implements Runnable {
		
		GetMoneyDataFromDataBase() {
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg = new Message(); 
			ArrayList<String> outcomeCategoryNameArrayList = new ArrayList<String>();
			DatabaseOperator databaseOperator = new DatabaseOperator(ModifyMoneyCategory.this, AppConstant.SQL_TABLE_NAME_OUTCOME_COLUMN_NAME);
			outcomeCategoryNameArrayList = databaseOperator.getColumnValueFromDatabaseEX(AppConstant.SQL_TABLE_NAME_OUTCOME_COLUMN_NAME, AppConstant.COLUMN_NAME_OUTCOME_COLUMN_NAME);
			if (!outcomeCategoryNameArrayList.isEmpty()) {
				mOutcomeColumnNameArrayList = outcomeCategoryNameArrayList;	
				mUpdateUIHandler.sendMessage(msg);   
			}
		}
	}
	
	OnClickListener mAddOutcomeCategoryButtonOnClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			mInputAddedCategoryEditText = new EditText(ModifyMoneyCategory.this);
		    new AlertDialog.Builder(ModifyMoneyCategory.this)  
		    .setTitle("请输入新增类别的名称")
		    .setIcon(android.R.drawable.ic_dialog_info) 
		    .setView(mInputAddedCategoryEditText)  
		    .setPositiveButton("确定", mAddCategoryButtononClickListener)  
		    .setNegativeButton("取消", mAddCategoryButtononClickListener)  
		    .show();  
		}
	};
	
	Handler mGetDataFromDatabaseHandler = new Handler() {
		
	};
	
	android.content.DialogInterface.OnClickListener mAddCategoryButtononClickListener = new android.content.DialogInterface.OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			ArrayList<String> resStrings = new ArrayList<String>();
			switch (which) {
			case -1:	//OK Button
				mInputedCategoryNameString = mInputAddedCategoryEditText.getText().toString();
				if (mInputedCategoryNameString.equals("")) {
					Toast.makeText(ModifyMoneyCategory.this, "类别名称不能为空，请重新输入！", Toast.LENGTH_SHORT).show();
				}else {
					AppConstant.showProgressModelDialog(mProgressDialog, ModifyMoneyCategory.this);
					DatabaseOperator databaseOperator = new DatabaseOperator(ModifyMoneyCategory.this, AppConstant.SQL_TABLE_NAME_OUTCOME_COLUMN_NAME);
					resStrings = databaseOperator.getColumnValueFromDatabaseEX(AppConstant.SQL_TABLE_NAME_OUTCOME_COLUMN_NAME, AppConstant.COLUMN_NAME_OUTCOME_COLUMN_NAME);
					if (resStrings.contains(mInputedCategoryNameString)) {
						mProgressDialog.cancel();
						Toast.makeText(ModifyMoneyCategory.this, "输入的类别名称已经存在，请重新输入！", Toast.LENGTH_SHORT).show();
						mInputedCategoryNameString = "";
					} else {
						databaseOperator.insertValueToDatabaseEX(AppConstant.SQL_TABLE_NAME_OUTCOME_COLUMN_NAME, AppConstant.COLUMN_NAME_OUTCOME_COLUMN_NAME, mInputedCategoryNameString);
						mProgressDialog.cancel();
					}
				}
				
				break;
			case -2 :	//Cancel Button
				mInputedCategoryNameString = "";
				break;
			default:
				dialog.cancel();
				break;
			}
		}
	};
}
