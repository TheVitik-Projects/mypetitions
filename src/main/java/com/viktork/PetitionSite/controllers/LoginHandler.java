package com.viktork.PetitionSite.controllers;

import com.viktork.PetitionSite.models.UserChecker;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(name = "LoginHandler", value = "/login")
public class LoginHandler extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        if(name!=null && password!=null){
            UserChecker uc = new UserChecker(request);
            try {
                if(uc.isRegistered(name)){
                    if(uc.login(name,password)) {
                        response.sendRedirect("main?page=active");
                    }
                    else{
                        request.setAttribute("message","Wrong password.");
                        request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
                    }

                }
                else{
                    request.setAttribute("message","This name is not registered.");
                    request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
                }
            } catch (SQLException | NoSuchAlgorithmException ex) {
                System.out.println(ex.getMessage());
            }
        }
        else{
            System.out.println("Non-site request. Empty parameters.");
        }
    }
}
