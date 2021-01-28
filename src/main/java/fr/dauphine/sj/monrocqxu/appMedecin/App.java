package fr.dauphine.sj.monrocqxu.appMedecin;

import java.util.Properties;

import fr.dauphine.sj.monrocqxu.appMedecin.mail.MailManager;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	MailManager mailManager = new MailManager();
    	mailManager.sendTestMessage("pierre.monrocq@dauphine.eu","Bienvenue sur rdvmedecin.fr");
    	
    	
    }
}
