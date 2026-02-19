package it.bripobe.FactoryMethodFiles;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Utente implements Persona {
    @Override
    public void apriDocumento(HttpServletResponse response) {
        try {
            response.sendRedirect("cliente.html");
        } catch (IOException e) {
            e.printStackTrace();
            // eventualmente gestisci l'errore in altro modo
        }
    }
}
