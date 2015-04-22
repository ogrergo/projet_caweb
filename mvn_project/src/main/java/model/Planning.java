package model;

import java.util.ArrayList;
import java.util.Calendar;
public class Planning {
	
	static Calendar calendar;
	
	public Planning(){
		calendar=Calendar.getInstance();
	}
	
	public int getNbWeeksInCurrentMonth(){
		return calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
	}
	
	public int getCurrentMonth(){
		return calendar.get(Calendar.MONTH);
	}
	
	public int getWeeksInYear(){
		return calendar.getWeeksInWeekYear();
	}

	public ArrayList<Integer> getWeeksOfMonth(){
		
		ArrayList<Integer> listWeeks = new ArrayList<Integer>();
		int numWeekInMonth = calendar.get(Calendar.WEEK_OF_MONTH);
		int numWeek = calendar.get(Calendar.WEEK_OF_YEAR);
		for (int i=1; i<this.getNbWeeksInCurrentMonth(); i++){
			listWeeks.add(numWeek-numWeekInMonth + 1);
		}
		return listWeeks;
		
	}
}
