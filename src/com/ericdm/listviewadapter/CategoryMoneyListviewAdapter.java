package com.ericdm.listviewadapter;

import java.util.ArrayList;

import com.ericdm.wheredidmoneygo.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CategoryMoneyListviewAdapter extends BaseAdapter{
	LayoutInflater mLayoutInflater = null;
	Context mContext = null;
	ArrayList<String> mCategoryName = new ArrayList<String>();
	ArrayList<String> mMoneyCount = new ArrayList<String>();
	
	public CategoryMoneyListviewAdapter(Context context, ArrayList<String> pCategoryName, ArrayList<String> pMoneyCount) {
		mContext = context;
		mLayoutInflater = LayoutInflater.from(context);
		mCategoryName = pCategoryName;
		mMoneyCount = pMoneyCount;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mCategoryName.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mCategoryName.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHoler viewHoler;
		if (convertView == null) {
			viewHoler = new ViewHoler();
			convertView = mLayoutInflater.inflate(R.layout.list_view_detail_money_info, null);
			viewHoler.categoryNameTV = (TextView) convertView.findViewById(R.id.category_name_text_view);
			viewHoler.moneyCountTV = (TextView) convertView.findViewById(R.id.money_count_text_view);
			convertView.setTag(viewHoler);
		} else {
			viewHoler = (ViewHoler) convertView.getTag();
		}
		viewHoler.categoryNameTV.setText(mCategoryName.get(position));
		viewHoler.moneyCountTV.setText(mMoneyCount.get(position));
		return convertView;
	}
	
	static class ViewHoler {
		public TextView categoryNameTV;
		public TextView moneyCountTV;
	}

}
