package com.ericdm.wheredidmoneygo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ModifyMoneyCategory extends Activity{
	Button mOKButton = null;
	Button mCancelButton = null;
	Button mAddOutcomeCatgoryButton = null;
	Button mAddOutcomeMoneyButton = null;
	TextView mCanModifyMoneyTextView = null;
	TextView mTotalOutcomeMoney = null;
	EditText mInputAddedCategoryEditText = null; 
	int mCanModifyMoneyInt;
	int mTotalMoneyInt;
	static ProgressDialog mProgressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modify_money_category);
		mCanModifyMoneyInt = 0;
		mTotalMoneyInt = 0;
		mOKButton = (Button) findViewById(R.id.yes_button_modify);
		mCancelButton = (Button) findViewById(R.id.no_button_modify);
		mAddOutcomeCatgoryButton = (Button) findViewById(R.id.add_outcome_category_button);
		mAddOutcomeMoneyButton = (Button) findViewById(R.id.add_outcome_money_button);
		mCanModifyMoneyTextView = (TextView) findViewById(R.id.can_modify_money_textview2);
		mTotalOutcomeMoney = (TextView) findViewById(R.id.total_money_textview2);
		mCancelButton.setOnClickListener(mCancelButtonOnClickListener);
		mAddOutcomeCatgoryButton.setOnClickListener(mAddOutcomeCategoryButtonOnClickListener);
	}

	OnClickListener mCancelButtonOnClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			System.exit(0);
		}
	};
	
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
	
	android.content.DialogInterface.OnClickListener mAddCategoryButtononClickListener = new android.content.DialogInterface.OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			switch (which) {
			case -1:	//OK Button
				System.out.println(mInputAddedCategoryEditText.getText().toString());
				break;
			case -2 :	//Cancel Button
				
				break;
			default:
				dialog.cancel();
				break;
			}
		}
	};
}
