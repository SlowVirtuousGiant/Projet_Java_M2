package fr.dauphine.sj.monrocqxu.appMedecin.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class TimeMedecinUtil {

	
	public TimeMedecinUtil() {
	}
	
	public static HashMap<Integer,ArrayList<String>> getNext20Days(){
		HashMap<Integer,ArrayList<String>> res = new HashMap<Integer,ArrayList<String>>();
		LocalDate currentDate = LocalDate.now();
		WeekFields weekFields = WeekFields.of(Locale.getDefault());
		ArrayList<String> week = new ArrayList<String>();
		int weekNumber = currentDate.get(weekFields.weekOfWeekBasedYear());
		for(int i = 0; i < 20; i++) {
			currentDate = currentDate.plusDays(1);
			int currentWeekNumber = currentDate.get(weekFields.weekOfWeekBasedYear());
			String date = currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			week.add(date);
			
			if(currentWeekNumber != weekNumber) {
				week.remove(week.size() - 1 );
				res.put(weekNumber, week);
				week = new ArrayList<String>();
				weekNumber = currentWeekNumber;
				currentDate = currentDate.minusDays(1);
			}
		}
		res.put(weekNumber, week);
		return res;
	}
	
	public static ArrayList<String> getNext4weeks(){
		ArrayList<String> res = new ArrayList<String>();
		LocalDate currentDate = LocalDate.now();
		WeekFields weekFields = WeekFields.of(Locale.getDefault()); 
		for(int i=0; i < 4; i++) {
			int weekNumber = currentDate.get(weekFields.weekOfWeekBasedYear());
			res.add(String.valueOf(weekNumber));
			currentDate = currentDate.plusWeeks(1);
		}
		return res;
		
	}
	
	public static String getRdvDay(int offset) {
		return LocalDate.now().plusDays(offset).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	
	public static String getCurrentWeek() {
		LocalDate currentDate = LocalDate.now();
		WeekFields weekFields = WeekFields.of(Locale.getDefault()); 
		int weekNumber = currentDate.get(weekFields.weekOfWeekBasedYear());
		return String.valueOf(weekNumber);
	}
	
	public static int getWeekFromDate(String string) {
		LocalDate date = LocalDate.parse(string, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		WeekFields weekFields = WeekFields.of(Locale.getDefault()); 
		int weekNumber = date.get(weekFields.weekOfWeekBasedYear());
		return weekNumber;
	}
	
	public static HashMap<String, ArrayList<String>> getDatesByWeekNumber(int nb_weeks){
		WeekFields weekFields = WeekFields.of(Locale.getDefault()); 
		LocalDate currentDate = LocalDate.now();
		HashMap<String, ArrayList<String>> res = new HashMap<String, ArrayList<String>>();
		for(int i=0; i < nb_weeks; i++) {
			int weekNumber = currentDate.get(weekFields.weekOfWeekBasedYear());
			LocalDate firstDay = currentDate.with(DayOfWeek.MONDAY);
			LocalDate lastDay = currentDate.with(DayOfWeek.SUNDAY);
			ArrayList<String> days = new ArrayList<String>();
			while(!firstDay.isAfter(lastDay)) {
				days.add(firstDay.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
				firstDay = firstDay.plusDays(1);
			}
			res.put(String.valueOf(weekNumber), days);
			currentDate = currentDate.plusWeeks(1);
		}
		return res;
	}
	
	
	
}
