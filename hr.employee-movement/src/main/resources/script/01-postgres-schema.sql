# sudo -u postgres psql
create database spring_microservice_db;
create user springuser with encrypted password 'springpass';
grant all privileges on database spring_microservice_db to springuser;
alter database spring_microservice_db owner to springuser;

CREATE SCHEMA employee_movement;
CREATE SCHEMA industrial_relation;
CREATE SCHEMA organization_development;
CREATE SCHEMA payroll_compensation;
CREATE SCHEMA performance_management;
CREATE SCHEMA ms_chassis_two;
CREATE SCHEMA ms_chassis_three;

GRANT ALL PRIVILEGES ON SCHEMA employee_movement TO springuser;
GRANT ALL PRIVILEGES ON SCHEMA industrial_relation TO springuser;
GRANT ALL PRIVILEGES ON SCHEMA organization_development TO springuser;
GRANT ALL PRIVILEGES ON SCHEMA payroll_compensation TO springuser;
GRANT ALL PRIVILEGES ON SCHEMA performance_management TO springuser;
GRANT ALL PRIVILEGES ON SCHEMA ms_chassis_two TO springuser;
GRANT ALL PRIVILEGES ON SCHEMA ms_chassis_three TO springuser;

# sudo -u postgres psql
CREATE PUBLICATION dbz_publication FOR ALL tables;

