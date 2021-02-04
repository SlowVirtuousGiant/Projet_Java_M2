package fr.dauphine.sj.monrocqxu.appMedecin.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TimeMedecinUtil {

	
	public TimeMedecinUtil() {
	}
	
	public ArrayList<String> getNext20Days(){
		ArrayList<String> res = new ArrayList<String>();
		LocalDate currentDate =  LocalDate.now();
		for(int i = 0; i < 20; i++) {
			currentDate = currentDate.plusDays(1);
			res.add(currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		}
		return res;
	}
	
}
