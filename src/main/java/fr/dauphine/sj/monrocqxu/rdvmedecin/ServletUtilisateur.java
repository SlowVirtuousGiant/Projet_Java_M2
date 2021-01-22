package fr.dauphine.sj.monrocqxu.rdvmedecin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@WebServlet("/ServletUtilisateur")
public class ServletUtilisateur extends HttpServlet {
	

	
    private static final long serialVersionUID = 1L;
    
    private UtilisateurDao utilisateurDao;
    @Autowired
    private UserRepository userRepository;
    @Autowired
	private JdbcTemplate jdbcTemplate;
    
    private final String INSERT_SQL = "INSERT INTO USERS(nom, prenom, telephone, mail, naissance, adresse,code_postal, ville, role, motdepasse) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    

    	/*
    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.utilisateurDao = daoFactory.getUtilisateurDao();
    }*/
    
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        Utilisateur utilisateur = new Utilisateur();
        System.out.println("Réagit au formulaire");
        utilisateur.setNom(request.getParameter("nom"));
        utilisateur.setPrenom(request.getParameter("prenom"));
        utilisateur.setTelephone(request.getParameter("telephone"));
        utilisateur.setAdresse(request.getParameter("adresse"));
        utilisateur.setMail(request.getParameter("mail"));
        utilisateur.setNaissance(request.getParameter("naissance"));
        utilisateur.setCode_postal(Integer.parseInt(request.getParameter("code_postal")));
        utilisateur.setVille(request.getParameter("ville"));
        utilisateur.setRole("MEDECIN");
        utilisateur.setMdp(BCrypt.hashpw(request.getParameter("mail"),BCrypt.gensalt(12)));
        System.out.println("a catch les infos avec le servlet");
        System.out.println(utilisateur.getNom());
        //String UtilisateurInscrit = utilisateurDao.ajouter(utilisateur);
        //utilisateurDao.create(utilisateur);
        
        
        System.out.println("début create");
    	KeyHolder holder = new GeneratedKeyHolder();
    	System.out.println("after generatekeyholder");
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connexion) throws SQLException {
				System.out.println("connexion établie");
				PreparedStatement ps = connexion.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, utilisateur.getNom());
				ps.setString(2, utilisateur.getPrenom());
				ps.setString(3, utilisateur.getTelephone());
				ps.setString(4, utilisateur.getMail());
				ps.setString(5, utilisateur.getNaissance());
				ps.setString(6, utilisateur.getAdresse());
				ps.setLong(7, utilisateur.getCode_postal());
				ps.setString(8, utilisateur.getVille());
				ps.setString(9, "MEDECIN");
				ps.setString(10, BCrypt.hashpw(utilisateur.getMdp(), BCrypt.gensalt(12)));
				System.out.println("méthode create :" + utilisateur.getMdp());
				return ps;
			}
		}, holder);

		int newUserId = holder.getKey().intValue();
		utilisateur.setId(newUserId);
        System.out.println("a reussi a add un user");
        /*if(UtilisateurInscrit.equals("SUCCESS"))   //On success, you can display a message to user on Home page
        {
           request.getRequestDispatcher("/espace.jsp").forward(request, response);
        }
        else   //On Failure, display a meaningful message to the User.
        {
           request.setAttribute("errMessage", UtilisateurInscrit);
           request.getRequestDispatcher("/inscriptionMedecin.jsp").forward(request, response);
        }
        */
         
        //utilisateurDao.ajouter(utilisateur);
        
        
        
        //this.getServletContext().getRequestDispatcher("/WEB-INF/inscriptionMedecin.jsp").forward(request, response);
    }
}
