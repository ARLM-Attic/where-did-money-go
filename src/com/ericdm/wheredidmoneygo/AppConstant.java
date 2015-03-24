package com.ericdm.wheredidmoneygo;

import android.app.ProgressDialog;
import android.content.Context;

public class AppConstant {
	public static final String SQL_TABLE_NAME_SEARCH_TIME_INFO = "SearchTimeTable";
	public static final String SQL_TABLE_NAME_INCOME_COLUMN_NAME = "IncomeColumnNameTable";
	public static final String SQL_TABLE_NAME_OUTCOME_COLUMN_NAME = "OutcomeColumnNameTable";
	public static final String SQL_TABLE_NAME_OUTCOME = "OutcomeTable";
	public static final String COLUMN_NAME_INCOME_COLUMN_NAME = "INCOME_COLUMN_NAME";
	public static final String COLUMN_NAME_OUTCOME_COLUMN_NAME = "OUTCOME_COLUMN_NAME";
	public static final String COLUMN_NAME_OUTCOME_NAME = "OUTCOME_NAME";
	public static final String COLUMN_NAME_OUTCOME_VALUE = "OUTCOME_VALUE";
	public static final String FROM_TIME_COLUMN_IN_DATABASE_TABLE = "FROM_TIME";
	public static final String TO_TIME_COLUMN_IN_DATABASE_TABLE = "TO_TIME";
	public static final String START_DATE_IN_CHINESE = "开始查询日期：";
	public static final String END_DATE_IN_CHINESE = "截止查询日期：";
	public static final String COMPARE_DATE_RESULT_BIG = "Bigger";
	public static final String COMPARE_DATE_RESULT_LESS = "Less";
	public static final String COMPARE_DATE_RESULT_SAME = "Same";
	
	public static void showProgressModelDialog(ProgressDialog dialog, Context context) {
		dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		dialog.setTitle("检索数据");
		dialog.setMessage("正在查询数据库中的数据，请稍后");
		dialog.setIcon(android.R.drawable.ic_dialog_map);
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
	}
}
