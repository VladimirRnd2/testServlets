package com.example.testservlets.config;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class JDBC_ConnectionTest {

    Connection connection = JDBC_Connection.getConnection();

    @Test
    void getNewConnection() {
        assertNotNull(connection);
    }
}