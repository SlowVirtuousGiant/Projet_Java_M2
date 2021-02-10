package fr.dauphine.sj.monrocqxu.appMedecin.minuterie;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class RappelLogger {
	public Logger logger;
	FileHandler fh;
	
	public RappelLogger(String nom_fichier) throws SecurityException, IOException{
		File f = new File(nom_fichier);
		if(!f.exists())
		{
			f.createNewFile();
		}
		fh = new FileHandler(nom_fichier,true);
		logger = Logger.getLogger("test");
		logger.addHandler(fh);
		SimpleFormatter formatter = new SimpleFormatter();
		fh.setFormatter(formatter);
	}
}
