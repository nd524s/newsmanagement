package com.epam.newsmanagement.controller;


import com.epam.newsmanagement.command.ActionCommand;
import com.epam.newsmanagement.command.ActionFactory;
import com.epam.newsmanagement.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Никита on 6/17/2016.
 */
@WebServlet("/client")
public class UserController extends HttpServlet {

    @Override
    public void init() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ActionFactory client = ActionFactory.getInstance();
        ActionCommand actionCommand = client.defineCommand(req);
        String page = null;
        try {
            page = actionCommand.execute(req);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher(page).forward(req, resp);
    }

}
