package it.bripobe.CommandFiles;

import it.bripobe.DBConnection;
import it.bripobe.fileCarrello.ViewCarrello;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/back")
public class CommandServlet  extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String page = request.getParameter("back");

        Command command = new NavigationCommand(page);

        String view = command.execute();

        request.getRequestDispatcher(view).forward(request, response);
    }
}
