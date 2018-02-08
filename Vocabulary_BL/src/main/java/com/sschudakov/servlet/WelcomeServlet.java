package com.sschudakov.servlet;

import com.sschudakov.entity.Word;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WelcomeServlet extends HttpServlet {
    //constants
    private static final String LINES_JSP_PATH = "/welcome.jsp";
    private static final String ATTRIBUTE_NAME = "words";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Word> words = new ArrayList<>();

        req.setAttribute(ATTRIBUTE_NAME, words);
        req.getRequestDispatcher(LINES_JSP_PATH).forward(req, resp);
    }
}