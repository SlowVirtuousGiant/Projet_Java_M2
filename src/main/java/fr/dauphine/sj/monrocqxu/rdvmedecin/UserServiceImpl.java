package fr.dauphine.sj.monrocqxu.rdvmedecin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
		
		@Autowired
		private PersonneDao personneDao;

		public List getUserDetails() {
			return personneDao.getUserDetails();
		}
}
