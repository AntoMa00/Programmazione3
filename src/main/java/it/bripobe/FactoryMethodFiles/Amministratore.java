package it.bripobe.FactoryMethodFiles;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Amministratore implements Persona {
    @Override
    public void apriDocumento(HttpServletResponse response) {
        try {
            response.sendRedirect("amministratore.html");
        } catch (IOException e) {
            e.printStackTrace();
            // eventualmente gestisci l'errore in altro modo
        }
    }
}
