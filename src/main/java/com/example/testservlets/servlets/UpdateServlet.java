package com.example.testservlets.servlets;

import com.example.testservlets.dao.ModelDao;
import com.example.testservlets.entities.Model;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UpdateServlet", value = "/update")
public class UpdateServlet extends HttpServlet {

    private ModelDao dao;

    @Override
    public void init() throws ServletException {
        dao = new ModelDao();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final String id = request.getParameter("id");

        List<Model.Company> companies = dao.getAllCompanies();

        Model model = dao.read(Integer.parseInt(id));
        request.setAttribute("model", model);
        request.setAttribute("companies", companies);

        request.getRequestDispatcher("update.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        final String id = request.getParameter("id");
        final String name = request.getParameter("name");
        final String quantity = request.getParameter("quantity");
        final String company_id = request.getParameter("com_id");

        int com_id = Integer.parseInt(company_id);

        Model.Company company = dao.getCompanyByModels(com_id);

        Model modelNew = new Model(Integer.parseInt(id), name, Integer.parseInt(quantity), company);

        dao.update(modelNew);

        response.sendRedirect("/");

    }
}
