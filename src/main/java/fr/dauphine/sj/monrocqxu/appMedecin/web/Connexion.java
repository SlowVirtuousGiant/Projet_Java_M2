package fr.dauphine.sj.monrocqxu.appMedecin.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.dauphine.sj.monrocqxu.appMedecin.dao.UtilisateurDao;
import fr.dauphine.sj.monrocqxu.appMedecin.model.Utilisateur;

public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_SESSION_USER = "utilisateur";
    public static final String ERREUR = "erreur";
    public static final String ATT_ERREUR = "erreur";
    
    private UtilisateurDao utilisateurDao;
    private Map<String, String> erreurs = new HashMap<String, String>();

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/connexion.jsp").forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    	String email = request.getParameter("email");
		String password = request.getParameter("motdepasse");
    	
        if(!validationEmail(email)) {
        	erreurs.put(ERREUR, "Email non valide.");
        }
        
        if(!validationMotDePasse(password)) {
        	erreurs.put(ERREUR, "Mot non valide.");
        }
       
        HttpSession session = request.getSession();
        
        if ( erreurs.isEmpty() ) {
        	Utilisateur utilisateur = utilisateurDao.validate(email, password);
        	if(utilisateur != null) {
        		System.out.println("login succes");
        	    session.setAttribute( ATT_SESSION_USER, utilisateur);
        	}else {
        		System.out.println("login erreur");
        		erreurs.put(ERREUR, "Erreur d'authentification.");
        	}
        }
        request.setAttribute( ATT_ERREUR, erreurs );
        this.getServletContext().getRequestDispatcher("/connexion.jsp").forward( request, response );
    }
    
    
    private boolean validationEmail( String email ) {
        if ( email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
           return false;
        }
        return true;
    }
    
    private boolean validationMotDePasse( String motDePasse ) {
        if ( motDePasse != null ) {
            if ( motDePasse.length() < 3 ) {
            	return false;
            }
        }
        return true;
    }
    
}