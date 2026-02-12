package it.bripobe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    private final Decorator decorator = new StarDecorator();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String nome = req.getParameter("nome");
        if (nome == null || nome.isEmpty()) nome = "ospite";

        String decorato = decorator.decorate(nome);

//        out.println("<!DOCTYPE html>");
//        out.println("<html><head><title>Saluto Decorato</title></head><body>");
//        out.println("<h2>Ciao, " + decorato + "!</h2>");
//        out.println("<a href='index.html'>Torna indietro</a>");
//        out.println("</body></html>");
        resp.sendRedirect("preferiti.html");
    }
}