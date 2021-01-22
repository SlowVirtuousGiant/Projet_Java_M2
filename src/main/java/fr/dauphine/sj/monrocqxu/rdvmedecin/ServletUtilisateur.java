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
    @Override
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
        
        //userRepository.save(UserRepository.toto());

    }
}
