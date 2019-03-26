CREATE TABLE employee (
  id SERIAL PRIMARY KEY,
  department_id integer NOT NULL,
  name varchar,
  position varchar
);

CREATE TABLE company (
  id SERIAL PRIMARY KEY,
  name varchar
);

CREATE TABLE department (
  id SERIAL PRIMARY KEY,
  head_id integer references employee(id),
  company_id integer references company(id) NOT NULL,
  name varchar
);

ALTER TABLE public.employee
  ADD CONSTRAINT employee_department_id_fk
FOREIGN KEY (department_id) REFERENCES public.department (id);

alter table employee
  add work_start timestamp with time zone;

