package fr.dauphine.sj.monrocqxu.appMedecin.minuterie;

import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import fr.dauphine.sj.monrocqxu.appMedecin.dao.RdvDao;
import fr.dauphine.sj.monrocqxu.appMedecin.mail.MailManager;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Rdv;

public class Rappel extends TimerTask {
	@Override
	public void run() {

		List<Rdv> rdvs = RdvDao.getRdvPatientMail2BSent();
		if (rdvs != null) {
			for(Rdv rdv : rdvs) {
				MailManager.envoieRDVRappel(rdv);
				rdv.setEnvoi_mail(true);
				RdvDao.update(rdv);
			}
		}
	}
}
