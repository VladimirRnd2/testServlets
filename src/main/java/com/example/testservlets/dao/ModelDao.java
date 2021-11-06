package com.example.testservlets.dao;


import com.example.testservlets.config.JDBC_Connection;
import com.example.testservlets.entities.Model;
import org.postgresql.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModelDao implements Dao<Model,Integer>{

    private static Connection connection;


    public ModelDao() {
        connection = JDBC_Connection.getConnection();
    }

    @Override
    public boolean create(Model model) {
        boolean result = false;

        try(PreparedStatement statement = connection.prepareStatement(SQLModel.INSERT.query)) {
            statement.setString(1,model.getName());
            statement.setInt(2,model.getQuantity());
            statement.setInt(3,model.getCompany().getId());
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Model read(Integer integer) {

        Model model = new Model();
        model.setId(-1);

        try(PreparedStatement statement = connection.prepareStatement(SQLModel.GET.query)) {
            statement.setInt(1, integer);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                model.setId(integer);
                model.setName(resultSet.getString("name"));
                model.setQuantity(resultSet.getInt("quantity"));
                model.setCompany(new Model.Company(resultSet.getInt("com_id"),resultSet.getString("com_name"), resultSet.getString("country")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }

    @Override
    public boolean update(Model model) {
        boolean result = false;

        try (PreparedStatement statement = connection.prepareStatement(SQLModel.UPDATE.query)) {
            statement.setString(1, model.getName());
            statement.setInt(2,model.getQuantity());
            statement.setInt(3,model.getId());
            result = statement.executeQuery().next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(Integer id) {
        boolean result = false;

        try (PreparedStatement statement = connection.prepareStatement(SQLModel.DELETE.query)) {
            statement.setInt(1, id);
            result = statement.executeQuery().next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Model> getAllModels() {
        List<Model> modelList = new ArrayList<>();
        Model model = null;

        try (PreparedStatement statement = connection.prepareStatement(SQLModel.GET_ALL_MODELS.query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                model = new Model();
                model.setId(resultSet.getInt("id"));
                model.setName(resultSet.getString("name"));
                model.setQuantity(resultSet.getInt("quantity"));
                model.setCompany(getCompanyByModels(resultSet.getInt("company_id")));

                modelList.add(model);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modelList;
    }

    public List<Model.Company> getAllCompanies () {
        List<Model.Company> companyList = new ArrayList<>();

        Model.Company company = null;

        try (PreparedStatement statement = connection.prepareStatement(SQLModel.GET_ALL_COMPANIES.query)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                company = new Model.Company();

                company.setId(resultSet.getInt("id"));
                company.setName(resultSet.getString("name"));
                company.setCountry(resultSet.getString("country"));

                companyList.add(company);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companyList;
    }

    public Model.Company getCompanyByModels(int id) {
        Model.Company company = new Model.Company();

        try (PreparedStatement statement = connection.prepareStatement(SQLModel.GET_COMPANY_BY_MODELS.query)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                company.setId(id);
                company.setName(resultSet.getString("name"));
                company.setCountry(resultSet.getString("country"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return company;
    }

    public List<Model> getAllModelsByCompany(Model.Company company) {
        List<Model> modelList = new ArrayList<>();

        Model model = null;

        try (PreparedStatement statement = connection.prepareStatement(SQLModel.GET_ALL_MODELS_BY_COMPANY.query)) {
            statement.setInt(1,company.getId());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                model = new Model();

                model.setId(resultSet.getInt("id"));
                model.setName(resultSet.getString("name"));
                model.setQuantity(resultSet.getInt("quantity"));
                model.setCompany(company);

                modelList.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modelList;
    }

    public boolean insertNewCompany(Model.Company company) {
        boolean result = false;

        try (PreparedStatement statement = connection.prepareStatement(SQLModel.INSERT_NEW_COMPANY.query)) {
            statement.setString(1,company.getName());
            statement.setString(2, company.getCountry());

            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean deleteCompanyById(int id) {
        boolean result = false;

        try (PreparedStatement statement = connection.prepareStatement(SQLModel.DELETE_COMPANY_BY_ID.query)) {
            statement.setInt(1, id);

            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Model.Company getCompanyById(int id) {
        Model.Company company = null;

        try (PreparedStatement statement = connection.prepareStatement(SQLModel.GET_COMPANY_BY_ID.query)) {
            statement.setInt(1,id);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                company = new Model.Company();
                company.setId(id);
                company.setName(resultSet.getString("name"));
                company.setCountry(resultSet.getString("country"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return company;
    }

    enum SQLModel {
        GET_COMPANY_BY_ID("SELECT * FROM companies WHERE id = (?)"),
        DELETE_COMPANY_BY_ID("DELETE FROM companies WHERE id = (?)"),
        INSERT_NEW_COMPANY("INSERT INTO companies (id, name, country) VALUES (DEFAULT, (?), (?)) RETURNING id"),
        GET_COMPANY_BY_MODELS("SELECT * FROM companies WHERE id = (?)"),
        GET_ALL_MODELS("SELECT * FROM models"),
        GET_ALL_COMPANIES("SELECT * FROM companies"),
        GET_ALL_MODELS_BY_COMPANY("SELECT * FROM models WHERE company_id = (?)"),
        GET("SELECT m.id, m.name, m.quantity, c.id AS com_id, m.company_id, c.country, c.name AS com_name FROM models AS m LEFT JOIN companies AS c ON m.company_id = c.id WHERE m.id = (?)"),
        INSERT("INSERT INTO models  (id, name, quantity, company_id) VALUES (DEFAULT, (?), (?), (?)) RETURNING id"),
        DELETE("DELETE FROM models WHERE id = (?) RETURNING id"),
        UPDATE("UPDATE models SET name = (?), quantity = (?) WHERE id = (?) RETURNING id");

        String query;

        SQLModel(String query) {
            this.query = query;
        }
    }
}
