package com.example.testservlets.servlets;

import com.example.testservlets.dao.ModelDao;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class DeleteCompanyServletTest {

    static private HttpServletRequest request;
    static private HttpServletResponse response;
    static private DeleteCompanyServlet deleteCompanyServlet;
    static private ModelDao modelDao;
    static private RequestDispatcher dispatcher;

    @BeforeEach
    public void startUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        deleteCompanyServlet = mock(DeleteCompanyServlet.class);
        modelDao = mock(ModelDao.class);
        dispatcher = mock(RequestDispatcher.class);
    }

    @SneakyThrows
    @Test
    void doPostCall() {
        Mockito.doCallRealMethod().when(deleteCompanyServlet).doPost(request,response);
        deleteCompanyServlet.doGet(request,response);
        Mockito.verify(deleteCompanyServlet).doGet(request,response);
    }

    @SneakyThrows
    @Test
    void doGetCall() {
        Mockito.when(request.getRequestDispatcher("deleteCompany.jsp")).thenReturn(dispatcher);
        Mockito.doCallRealMethod().when(deleteCompanyServlet).doGet(request, response);
        deleteCompanyServlet.doPost(request,response);

    }

}