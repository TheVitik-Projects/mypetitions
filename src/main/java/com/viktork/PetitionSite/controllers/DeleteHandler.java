package com.viktork.PetitionSite.controllers;

import com.viktork.PetitionSite.models.PetitionProcessor;
import com.viktork.PetitionSite.models.VoteProcessor;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteHandler", value = "/delete")
public class DeleteHandler extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("id") != null && request.getParameter("id") !=null) {
            PetitionProcessor cp = new PetitionProcessor();
            try {
                cp.deletePetition(request.getParameter("id"), (String) session.getAttribute("id"));
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            response.sendRedirect(getServletContext().getContextPath() + "/main?page=active");
        }
        else{
            response.sendRedirect(getServletContext().getContextPath()+"/main?page=login");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
