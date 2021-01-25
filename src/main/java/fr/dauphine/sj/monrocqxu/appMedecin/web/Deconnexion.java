package fr.dauphine.sj.monrocqxu.appMedecin.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static fr.dauphine.sj.monrocqxu.appMedecin.util.AppMedecinUtil.*;

public class Deconnexion extends HttpServlet {


    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect( CHEMIN_RACINE );
    }
}