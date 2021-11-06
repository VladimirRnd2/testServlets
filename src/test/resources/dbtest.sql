CREATE TABLE companies (id serial PRIMARY KEY , name varchar (64) not null ,country varchar(64) not null);

CREATE TABLE models (id serial PRIMARY KEY , name varchar(64) NOT NULL , quantity INTEGER NOT NULL, company_id INTEGER NOT NULL , FOREIGN KEY (company_id) REFERENCES companies (id) ON DELETE CASCADE );