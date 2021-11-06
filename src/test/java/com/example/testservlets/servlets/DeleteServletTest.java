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

class DeleteServletTest {

    static private HttpServletRequest request;
    static private HttpServletResponse response;
    static private DeleteServlet deleteServlet;
    static private ModelDao modelDao;
    static private RequestDispatcher dispatcher;

    @BeforeEach
    public void startUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        deleteServlet = mock(DeleteServlet.class);
        modelDao = mock(ModelDao.class);
        dispatcher = mock(RequestDispatcher.class);
    }

    @SneakyThrows
    @Test
    void doPostCall() {
        Mockito.doCallRealMethod().when(deleteServlet).doPost(request,response);
        deleteServlet.doGet(request,response);
        Mockito.verify(deleteServlet).doGet(request,response);
    }

    @SneakyThrows
    @Test
    void doGetCall() {
        Mockito.when(request.getRequestDispatcher("delete.jsp")).thenReturn(dispatcher);
        Mockito.doCallRealMethod().when(deleteServlet).doGet(request, response);
        deleteServlet.doPost(request,response);

    }
}