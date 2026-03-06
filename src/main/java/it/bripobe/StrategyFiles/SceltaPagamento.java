package it.bripobe.StrategyFiles;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tipopagamento")
public class SceltaPagamento extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String tipo = request.getParameter("tipologia");
        int quantita = Integer.parseInt(request.getParameter("quantita")); // prende la quantità dal form
        PontePagamento ponte = new PontePagamento();

        if ("contanti".equals(tipo)) {
            ponte.setMetodoPagamento(new PagamentoContanti());
        }
        else if ("cartadicredito".equals(tipo)) {
            ponte.setMetodoPagamento(new PagamentoCartaCredito());
        }
        else if ("bancomat".equals(tipo)) {
            ponte.setMetodoPagamento(new PagamentoBancomat());
        }

        String pagina = ponte.checkout();

        // puoi anche salvare in sessione se serve alle JSP
        request.getSession().setAttribute("quantita", quantita);

        response.sendRedirect(request.getContextPath() + "/" + pagina);
    }
}