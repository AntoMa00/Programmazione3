package it.bripobe.FactoryMethodFiles;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Utente implements Persona {
    @Override
    public void apriDocumento(HttpServletResponse response) {
        try {
            // redirect verso la Servlet, non direttamente al JSP
            response.sendRedirect("salva"); // la Servlet farà forward a cliente.jsp, passando da ProdottoServlet.java
        } catch (IOException e) {
            e.printStackTrace();
            // eventualmente gestisci l'errore in altro modo
        }
    }
}
