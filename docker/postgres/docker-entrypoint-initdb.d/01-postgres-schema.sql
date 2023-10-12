CREATE USER postgres_user with encrypted password 'postgres_password' REPLICATION LOGIN;

CREATE SCHEMA employee_movement;
CREATE SCHEMA industrial_relation;
CREATE SCHEMA organization_development;
CREATE SCHEMA payroll_compensation;
CREATE SCHEMA performance_management;
CREATE SCHEMA ms_chassis_two;
CREATE SCHEMA ms_chassis_three;

GRANT ALL PRIVILEGES ON DATABASE postgres TO postgres_user;

GRANT ALL PRIVILEGES ON SCHEMA employee_movement TO postgres_user;
GRANT ALL PRIVILEGES ON SCHEMA industrial_relation TO postgres_user;
GRANT ALL PRIVILEGES ON SCHEMA organization_development TO postgres_user;
GRANT ALL PRIVILEGES ON SCHEMA payroll_compensation TO postgres_user;
GRANT ALL PRIVILEGES ON SCHEMA performance_management TO postgres_user;
GRANT ALL PRIVILEGES ON SCHEMA ms_chassis_two TO postgres_user;
GRANT ALL PRIVILEGES ON SCHEMA ms_chassis_three TO postgres_user;

CREATE PUBLICATION dbz_publication FOR ALL TABLES;