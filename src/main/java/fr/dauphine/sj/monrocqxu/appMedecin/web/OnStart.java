package fr.dauphine.sj.monrocqxu.appMedecin.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import javax.servlet.*;

import fr.dauphine.sj.monrocqxu.appMedecin.minuterie.Rappel;
import fr.dauphine.sj.monrocqxu.appMedecin.minuterie.RappelLogger;

public class OnStart implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent e) {
		

		
		
		Timer timer = new Timer();
		TimerTask task = new Rappel();

		Date currDate = new Date();

		Calendar cal = Calendar.getInstance(); 
		cal.setTime(currDate);
		cal.add(Calendar.DATE, 0);
		cal.set(Calendar.HOUR_OF_DAY, 3);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date dateRepere = cal.getTime();


		boolean nextDay = (dateRepere.getTime() < currDate.getTime())? true : false;
		long longDiffInitCurr;

		if(nextDay) {
			cal = Calendar.getInstance(); 
			cal.setTime(currDate);
			cal.add(Calendar.DATE, 1);
			cal.set(Calendar.HOUR_OF_DAY, 3);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			Date dateMailInit = cal.getTime();
			longDiffInitCurr = dateMailInit.getTime()-currDate.getTime();
		}else {
			cal = Calendar.getInstance(); 
			cal.setTime(currDate);
			cal.add(Calendar.DATE, 0);
			cal.set(Calendar.HOUR_OF_DAY, 3);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			Date dateMailInit = cal.getTime();
			longDiffInitCurr = dateMailInit.getTime()-currDate.getTime();
		}


		System.out.println("Initialisation des rappel à compter de "+ longDiffInitCurr + " ms ");
		timer.schedule(task, longDiffInitCurr,86400000);// 86.400.000 ms = 24H

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		//TODO();

	}


}