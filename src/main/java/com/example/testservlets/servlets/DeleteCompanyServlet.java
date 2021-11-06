package com.example.testservlets.servlets;

import com.example.testservlets.dao.ModelDao;
import com.example.testservlets.entities.Model;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteCompanyServlet", value = "/deleteCompany")
public class DeleteCompanyServlet extends HttpServlet {

    private ModelDao dao;

    @Override
    public void init() throws ServletException {
        dao = new ModelDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String id = request.getParameter("id");

        Model.Company company = dao.getCompanyById(Integer.parseInt(id));

        request.setAttribute("company", company);

        request.getRequestDispatcher("deleteCompany.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final String id = request.getParameter("id");

        dao.deleteCompanyById(Integer.parseInt(id));

        response.sendRedirect("/create");
    }
}
