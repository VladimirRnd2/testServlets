package com.example.testservlets.entities;

import java.util.Objects;

public class Model {

    private int id;

    private String name;

    private int quantity;

    private Company company;

    public Model(int id, String name, int quantity, Company company) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.company = company;
    }

    public Model() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", company=" + company +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Model model = (Model) o;
        return quantity == model.quantity && Objects.equals(name, model.name) && Objects.equals(company, model.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, quantity, company);
    }

    public static class Company {

        private int id;

        private String name;

        private String country;

        public Company(int id, String name, String country) {
            this.id = id;
            this.name = name;
            this.country = country;
        }

        public Company() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        @Override
        public String toString() {
            return "Company{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", country='" + country + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Company company = (Company) o;
            return id == company.id && Objects.equals(name, company.name) && Objects.equals(country, company.country);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, country);
        }
    }
}
