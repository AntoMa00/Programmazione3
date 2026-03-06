package it.bripobe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/pagamentoQuantita")
public class PagamentoQuantita extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int quantita = Integer.parseInt(request.getParameter("quantita"));

        HttpSession session = request.getSession();
        session.setAttribute("quantita", quantita);
        response.sendRedirect("pagamento.jsp");
    }
}
