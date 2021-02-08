package fr.dauphine.sj.monrocqxu.appMedecin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class App 
{
	
    public static void main( String[] args )
    {
    	Date currDate = new Date();
    	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    	String currDateFormated = formatter.format(currDate);
    	System.out.println(currDateFormated);
    }
}
