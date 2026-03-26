package servlets;

import dao.UtilisateurDao;
import beans.Utilisateur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

// @WebServlet("/list")
public class ListUsers extends HttpServlet {
    private static final String VUE_LIST_USERS = "/WEB-INF/listerUtilisateur.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // 1. Récupérer la liste (UtilisateurDao gère déjà le SQL, pas besoin de try-catch ici)
        ArrayList<Utilisateur> utilisateurs = UtilisateurDao.getUsers();
        
        // 2. Ajouter les utilisateurs en tant qu'attribut pour la vue
        request.setAttribute("utilisateurs", utilisateurs);

        // 3. Rediriger vers la vue Jsp
        getServletContext().getRequestDispatcher(VUE_LIST_USERS).forward(request, response);
    }
}