package fr.dauphine.sj.monrocqxu.appMedecin.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Deconnexion extends HttpServlet {
    public static final String URL_REDIRECTION = "/appMedecin/";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Récupération et destruction de la session en cours */
        HttpSession session = request.getSession();
        session.invalidate();
        System.out.println("Deconnexion effective");

        /* Redirection vers le Site du Zéro ! */
        response.sendRedirect( URL_REDIRECTION );
    }
}