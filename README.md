# Create a new Database and a new user

      sudo -u postgres psql
      postgres=# create database spring_microservice_db;
      postgres=# create user springuser with encrypted password 'springpass';
      postgres=# grant all privileges on database spring_microservice_db to springuser;
      postgres=# alter database spring_microservice_db owner to springuser;

# Script

Execute: `01-postgres-schema.sql`
