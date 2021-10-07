package com.viktork.PetitionSite.controllers;

import com.viktork.PetitionSite.models.Petition;
import com.viktork.PetitionSite.models.PetitionProcessor;
import com.viktork.PetitionSite.models.PetitionsContent;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet("/main")
public class MainPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList petitions = new ArrayList<Petition>();
        String page = request.getParameter("page");
        request.setAttribute("page",page);
        String html="main";
        boolean redirect=false;
        HttpSession session = request.getSession();
        try{
            PetitionsContent pc = new PetitionsContent();
            switch (page) {
                case "active":
                    petitions = pc.getActive();
                    break;
                case "closed":
                    petitions = pc.getClosed();
                    break;
                case "my":
                    if (session.getAttribute("id") != null) {
                        petitions = pc.getMyPetitions((String) session.getAttribute("id"));
                        request.setAttribute("path",request.getRequestURL());
                    } else {
                        redirect = true;
                        response.sendRedirect(getServletContext().getContextPath() + "/main?page=active");
                    }
                    break;
                case "search":
                    if (request.getParameter("find") != null) {
                        try {
                            int findid = Integer.parseInt(request.getParameter("find"));
                            petitions = pc.getSearch(findid);
                        } catch (NumberFormatException ex) {
                            request.setAttribute("message", "Please, enter valid type of id.");
                        }
                    }

                    break;
                case "petition":
                    if (request.getParameter("id") != null) {
                        try {
                            int id = Integer.parseInt(request.getParameter("id"));
                            petitions = pc.getSearch(id);
                        } catch (NumberFormatException ex) {
                            request.setAttribute("message", "Please, enter valid type of id.");
                        }
                    } else {
                        redirect = true;
                        response.sendRedirect(getServletContext().getContextPath() + "/main?page=active");
                    }

                    break;
                case "new":
                    if (session.getAttribute("id") != null) {
                        html = "new";
                    } else {
                        petitions = pc.getActive();
                    }
                    break;
                case "login":
                    if (session.getAttribute("id") == null) {
                        html = "login";
                    } else {
                        redirect = true;
                        response.sendRedirect(getServletContext().getContextPath() + "/main?page=active");
                    }
                    break;
                case "register":
                    if (session.getAttribute("id") == null) {
                        html = "register";
                    } else {
                        redirect = true;
                        response.sendRedirect(getServletContext().getContextPath() + "/main?page=active");
                    }
                    break;
                case "exit":
                    if (session.getAttribute("id") != null) {
                        session.removeAttribute("id");
                    }
                    redirect = true;
                    response.sendRedirect(getServletContext().getContextPath() + "/main?page=active");
                    break;
                default:
                    redirect = true;
                    response.sendRedirect(getServletContext().getContextPath() + "/main?page=active");
                    break;
            }
        }
        catch(SQLException ex) {
            request.setAttribute("message", "MySQL server error. No connection.");
        }
        if(!redirect) {
            if(html.equals("main")) {
                request.setAttribute("petitions", petitions);
            }
            request.getRequestDispatcher("WEB-INF/" + html + ".jsp").forward(request, response);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
