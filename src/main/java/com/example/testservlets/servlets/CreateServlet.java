package com.example.testservlets.servlets;

import com.example.testservlets.dao.ModelDao;
import com.example.testservlets.entities.Model;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CreateServlet", value = "/create")
public class CreateServlet extends HttpServlet {

    private ModelDao dao;

    @Override
    public void init() throws ServletException {
        dao = new ModelDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Model.Company> companies = dao.getAllCompanies();
        request.setAttribute("companies", companies);

        request.getRequestDispatcher("create.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Model.Company company ;

        int id = -1;
        final String name = request.getParameter("name");
        final String country = request.getParameter("country");

        company = new Model.Company(id, name, country);

        dao.insertNewCompany(company);

        response.sendRedirect("/");
    }
}
