# Create a new Database and a new user

      sudo -u postgres psql
      postgres=# create database spring-microservice-db;
      postgres=# create user springuser with encrypted password 'springpass';
      postgres=# grant all privileges on database spring-microservice-db to springuser;
      postgres=# alter database spring-microservice-db owner to springuser;

