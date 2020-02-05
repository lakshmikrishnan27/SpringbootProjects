# SpringbootProjects
A web application to:

Display a list of students with the ability to search by last name and/or first name.
  Edit a Student
  View Student Information
  Left Menu
  Implement Pagination
  Spring Boot JPA Custom Query. Dynamic query based on what  search criteria is used (first name, last name or both).
  Swagger & lombok depenency used
  
Development Environment:
  Java
  Spring Boot
  UI – Spring MVC, Bootstrap
  Relational Database (Postgres) - postgres used with schema name postgres
  NTier – UI Interacts (frontend application) with Rest (Backend application)- bootstrap,jquery

Rest endpoint:
http://localhost:8080/students

http://localhost:8080/students/add

To test pagination:
http://localhost:8080/students?page=0&size=6&sort=id,DESC
(default pagination:value = 2, page = 0, sort="id")
