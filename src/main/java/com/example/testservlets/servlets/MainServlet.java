package com.example.testservlets.servlets;

import com.example.testservlets.dao.ModelDao;
import com.example.testservlets.entities.Model;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MainServlet", value = "/")
public class MainServlet extends HttpServlet {

    private ModelDao dao;

    @Override
    public void init() throws ServletException {
        dao = new ModelDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Model> models = dao.getAllModels();

        List<Model.Company> companies = dao.getAllCompanies();

        request.setAttribute("models", models);
        request.setAttribute("companies", companies);
        request.getRequestDispatcher("main.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final int id = -1;
        final String name = request.getParameter("name");
        final String quantity = request.getParameter("quantity");
        final String company_id = request.getParameter("company_id");

        Model.Company company = dao.getCompanyByModels(Integer.parseInt(company_id));
        Model modelNew = new Model(id, name, Integer.parseInt(quantity), company);

        dao.create(modelNew);
        response.sendRedirect("/");
    }
}
