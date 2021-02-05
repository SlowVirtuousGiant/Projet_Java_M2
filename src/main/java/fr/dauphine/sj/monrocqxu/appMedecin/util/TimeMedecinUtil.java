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
	
	public static ArrayList<String> getNext20Days(){
		ArrayList<String> res = new ArrayList<String>();
		LocalDate currentDate = LocalDate.now();
		for(int i = 0; i < 20; i++) {
			currentDate = currentDate.plusDays(1);
			res.add(currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		}
		return res;
	}
	
	public String getFirstRdvDay() {
		return LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	
	public static String getCurrentWeek() {
		LocalDate currentDate = LocalDate.now().plusDays(1);
		WeekFields weekFields = WeekFields.of(Locale.getDefault()); 
		int weekNumber = currentDate.get(weekFields.weekOfWeekBasedYear());
		return String.valueOf(weekNumber);
		
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
