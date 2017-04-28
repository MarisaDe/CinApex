package org.o7planning.simplewebapp.servlet;
 
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.o7planning.simplewebapp.beans.Movie;
import org.o7planning.simplewebapp.utils.DBUtils;
import org.o7planning.simplewebapp.utils.MyUtils;
 
@WebServlet(urlPatterns = { "/editProduct" })
public class EditProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public EditProductServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String id = (String) request.getParameter("Id");
 
        Movie movie = null;
 
        String errorString = null;
 
        try {
        	movie = DBUtils.findMovie(conn, id);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
 
         
        // If no error.
        // The product does not exist to edit.
        // Redirect to productList page.
        if (errorString != null && movie == null) {
            response.sendRedirect(request.getServletPath() + "/productList");
            return;
        }
 
        // Store errorString in request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("product", movie);
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/Views/editProductView.jsp");
        dispatcher.forward(request, response);
 
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}