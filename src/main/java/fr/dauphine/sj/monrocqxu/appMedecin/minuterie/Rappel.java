package fr.dauphine.sj.monrocqxu.appMedecin.minuterie;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import fr.dauphine.sj.monrocqxu.appMedecin.dao.RdvDao;
import fr.dauphine.sj.monrocqxu.appMedecin.mail.MailManager;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Rdv;

public class Rappel extends TimerTask {
	@Override
	public void run() {

		Date currDate = new Date();

		Calendar cal = Calendar.getInstance(); 
		cal.setTime(currDate);
		cal.add(Calendar.DATE, 1);
		Date dateRepere = cal.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String currDateFormated = formatter.format(currDate);



		List<Rdv> rdvs = RdvDao.getRdvPatientMail2BSent();
		if (rdvs != null) {
			for(Rdv rdv : rdvs) {
				Date date;
				try {
					date = formatter.parse(rdv.getDate());
				} catch (ParseException e) {
					throw new IllegalStateException(e);
				}
				if(date.before(currDate)) { // Si date rdv avant date courante, alors pas besoin d'envoyer un mail on remet juste à J la bdd
					rdv.setEnvoi_mail(true);
					RdvDao.update(rdv);
				}
				
				if(rdv.getDate().equals(currDateFormated)) { // si date RDV == date courante +1J, alors on envoie un mail 
					MailManager.envoieRDVRappel(rdv);
					rdv.setEnvoi_mail(true);
					RdvDao.update(rdv);
				}
			}
		}
	}
}
