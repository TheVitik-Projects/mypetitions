package com.viktork.PetitionSite.controllers;

import com.viktork.PetitionSite.models.VoteProcessor;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "VoteHandler", value = "/vote")
public class VoteHandler extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String petId=request.getParameter("id");
        String message="";
        boolean redirect=false;
        if(session.getAttribute("id")!=null){
            VoteProcessor vp = new VoteProcessor(request);
            if(vp.isset(petId)){
                message="You have voted already";
            }
            else{
                vp.vote(petId);
                message="Successful";
            }

        }
        else{
            redirect = true;
            response.sendRedirect(getServletContext().getContextPath()+"/main?page=login");
        }
        if(!redirect){
            request.setAttribute("message",message);
            request.getRequestDispatcher("main?page=active").forward(request,response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
