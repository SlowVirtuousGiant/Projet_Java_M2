package fr.dauphine.sj.monrocqxu.appMedecin.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.*;

import fr.dauphine.sj.monrocqxu.appMedecin.minuterie.Rappel;

public class OnStart implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent e) {
		Timer timer = new Timer();
		TimerTask task = new Rappel();

		Date currDate = new Date();

		Calendar cal = Calendar.getInstance(); 
		cal.setTime(currDate);
		cal.add(Calendar.DATE, 1);
		cal.set(Calendar.HOUR_OF_DAY, 3);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date dateMailInit = cal.getTime();

		//SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		long difference = dateMailInit.getTime() - currDate.getTime();

		//String currDateFormated = formatter.format(currDate);
		//String dateProchainMailingCal = formatter.format(dateMailInit);

		//System.out.println(currDateFormated);
		//System.out.println(dateProchainMailingCal);
		System.out.println(difference);
		timer.schedule(task, difference,3600000);// 3.600.000 ms = 24H
		
	}

	


		@Override
		public void contextDestroyed(ServletContextEvent sce) {


		}


	}