package com.ericdm.timemanager;
import com.ericdm.wheredidmoneygo.AppConstant;
import android.text.format.Time;

public class TimeManager {
	public String[] getCurrentQueryTime(){
		Time currentTime = new Time();
		currentTime.setToNow();
		int currentYear = currentTime.year;
		int currentMonth = currentTime.month + 1;
		int currentDay = currentTime.monthDay;
		String[] timeStrings = new String[2];
		timeStrings[0] = currentYear + "-" + addZeroToTime(currentMonth) + "-" + addZeroToTime(currentDay);
		timeStrings[1] = (currentYear + 1) +  "-" + addZeroToTime(currentMonth) + "-" + addZeroToTime(currentDay);
		return timeStrings;
	}

	private String addZeroToTime(int time) {
		String res = "";
		if (time >= 1 && time <= 9) {
			res = "0" + time;
		} else {
			res = time + "";
		}
		return res;
	}
	
	//The date format must like 2014-07-01
	public static String compareDate(String date1, String date2) {
		String[] date1Strs = date1.split("-");
		String[] date2Strs = date2.split("-");
		for (int i = 0; i < date2Strs.length - 1; i++) {
			int time1 = Integer.parseInt(date1Strs[i]);
			int time2 = Integer.parseInt(date2Strs[i]);
			if (time1 > time2) {
				return AppConstant.COMPARE_DATE_RESULT_BIG;
			} else {
				return AppConstant.COMPARE_DATE_RESULT_LESS;
			}
		}
		return AppConstant.COMPARE_DATE_RESULT_SAME;
	}
	
	private boolean isLeapYear(int theYear) {
		if (theYear % 4 == 0 && (theYear % 100) != 0) {
			return true;
		} else if (theYear % 400 == 0) {
			return false;
		}
		return false;
	}
}
