package fr.eni.eniencheres.ihm.mainIHM;


import fr.eni.eniencheres.bll.ArticleVenduBLL.ArticleVenduManager;
import fr.eni.eniencheres.Exceptions.BLLException;
import fr.eni.eniencheres.bll.CategoriesBLL.CategoriesManager;
import fr.eni.eniencheres.bll.EnchereBLL.EnchereManager;
import fr.eni.eniencheres.bll.FactoryBLL;
import fr.eni.eniencheres.bo.ArticleVendu;
import fr.eni.eniencheres.bo.Enchere;
import fr.eni.eniencheres.dal.EncheresDAL.EncheresDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet({"/eniencheres",""})
public class EncheresServlet extends HttpServlet {
    private ArticleVenduManager articleVenduManager;
    private CategoriesManager categoriesManager;
    private EnchereManager enchereManager;
    HttpSession session;

    public EncheresServlet (){
        articleVenduManager = FactoryBLL.getArticleVenduManager();
        categoriesManager = FactoryBLL.getCategoriesManager();
        enchereManager = FactoryBLL.getEnchereManager();

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {
            session = request.getSession();
            session.setAttribute("categorie",categoriesManager.getAllCategories());
            session.setAttribute("article",articleVenduManager.selectAll());
            // session.setAttribute("prixVente", enchereManager.getMontant(id)); // Afficher dernier enchere sur l'accueil JSP
            request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);

        } catch (BLLException e) {
            e.printStackTrace();
        }
    }

}
