package com.viktork.PetitionSite.controllers;

import com.viktork.PetitionSite.models.PetitionProcessor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "create", value = "/create")
public class CreatePetitionHandler extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String text = request.getParameter("text");
        HttpSession session = request.getSession();
        try {
            if(session.getAttribute("id")!=null) {
                PetitionProcessor cp = new PetitionProcessor();
                cp.addPetition(text, (String) session.getAttribute("id"));
            }
            response.sendRedirect(getServletContext().getContextPath() + "/main?page=active");
        } catch (Exception se) {
            se.printStackTrace();
        }
    }
}
