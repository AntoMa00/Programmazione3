package it.bripobe.FactoryMethodFiles;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Persona {
    void apriDocumento(HttpServletResponse response) throws IOException;
}
