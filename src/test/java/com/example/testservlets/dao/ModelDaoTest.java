package com.example.testservlets.dao;

import com.example.testservlets.config.JDBC_Connection;
import com.example.testservlets.entities.Model;
import lombok.SneakyThrows;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
class ModelDaoTest {

    private static PostgreSQLContainer postgreSQLContainer;
    private static ModelDao modelDao;
    private static Connection connection;
    private static Model model;


    @BeforeAll
    @SneakyThrows
    public static void init() {
        postgreSQLContainer = (PostgreSQLContainer) new PostgreSQLContainer("postgres:13-alpine")
                .withExposedPorts(5432);
        postgreSQLContainer
                .withDatabaseName("test")
                .withUsername("test")
                .withPassword("test")
                .withInitScript("dbtest.sql");
        postgreSQLContainer.start();

        assertTrue(postgreSQLContainer.isRunning());
        System.out.println(postgreSQLContainer.getJdbcUrl());
        System.out.println(postgreSQLContainer.getUsername());
        System.out.println(postgreSQLContainer.getPassword());

        try {
            connection = DriverManager.getConnection(postgreSQLContainer.getJdbcUrl(),
                    postgreSQLContainer.getUsername(),
                    postgreSQLContainer.getPassword());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        modelDao = new ModelDao();

        Model.Company company = new Model.Company(2,"Siemens","Germany");

        model = new Model();
        model.setId(1);
        model.setName("M65");
        model.setQuantity(12);
        model.setCompany(company);

    }

    @Test
    void some() {
        assertTrue(postgreSQLContainer.isRunning());
    }

//    @SneakyThrows
    @Test
    void create() {
        String name = "M65";
        int quantity = 12;
        int id = -1;

        String company_name = "Ubuntu";
        String company_country = "Angola";

        Model.Company company = new Model.Company(id,company_name,company_country);

        Model model = new Model(id,name,quantity,company);

        assertTrue(postgreSQLContainer.isCreated());

        assertTrue(modelDao.create(model));
    }

    @Test
    void update() {
        String one = "one";
        String first = "one";

        assertEquals(one,first);
    }


}