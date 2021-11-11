package com.example.testservlets.dao;

import com.example.testservlets.config.JDBC_Connection;
import com.example.testservlets.entities.Model;
import lombok.SneakyThrows;

import org.junit.Rule;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
class ModelDaoTest {

    @Rule
    private static PostgreSQLContainer postgreSQLContainer;
    private static ModelDao modelDao;
    private static Connection connection;
    private static Model model;

    @BeforeAll
    @SneakyThrows
    public static void init() {
        postgreSQLContainer = (PostgreSQLContainer) new PostgreSQLContainer("postgres")
                .withExposedPorts(5432);
        postgreSQLContainer
                .withDatabaseName("mydb")
                .withUsername("myuser")
                .withPassword("mypass").withInitScript("dbtest.sql");
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


    @SneakyThrows
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

    @SneakyThrows
    @Test
    void update() {

        int id = 2;
        String name = "RazorV4";
        int quantity = 7;

        Model.Company company = new Model.Company();

        company.setName("LADA");
        company.setCountry("Russia");

        Model model = new Model(id,name, quantity, company);

        assertTrue(modelDao.update(model));
    }

    @SneakyThrows
    @Test
    void delete() {

        int id = 3;
        String name = "RazorV5";
        int quantity = 8;

        Model.Company company = new Model.Company();

        company.setName("LADA");
        company.setCountry("Russia");

        Model model = new Model(id,name, quantity, company);

        assertTrue(modelDao.delete(model.getId()));
    }

    @SneakyThrows
    @Test
    void read () {

        int id = 4;
        String name = "RazorV6";
        int quantity = 9;

        Model.Company company = new Model.Company();

        company.setName("LADA");
        company.setCountry("Russia");

        Model model = new Model(id,name, quantity, company);
        assertTrue(modelDao.create(model));
        assertEquals("RazorV6", model.getName());
    }

    @SneakyThrows
    @Test
    void getAll() {
        List<Model> modelList = modelDao.getAllModels();
        assertFalse(modelList.isEmpty());

        int id = 6;
        String name = "RazorV19";
        int quantity = 13;

        Model.Company company = new Model.Company();

        company.setName("LADA");
        company.setCountry("Russia");

        Model model = new Model(id,name, quantity, company);
        assertTrue(modelDao.create(model));
        assertEquals("RazorV19", model.getName());
    }


}