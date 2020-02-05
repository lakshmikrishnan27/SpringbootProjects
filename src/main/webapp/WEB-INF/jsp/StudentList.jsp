<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee List</title>
<link href="http://localhost:8080/webjars/bootstrap/4.0.0-2/css/bootstrap.min.css" rel="stylesheet" />
<script src="http://localhost:8080/webjars/bootstrap/4.0.0-2/js/bootstrap.min.js"></script>
<script src="http://localhost:8080/webjars/jquery/3.0.0/js/jquery.min.js"></script>
</head>
<body>
<div class="row">
	<div class="col-3">
	    <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
	      <a class="nav-link active" id="v-pills-home-tab" data-toggle="pill" href="/students" role="tab" aria-controls="v-pills-home" aria-selected="true">Home</a>
	      <a class="nav-link" id="v-pills-profile-tab" data-toggle="pill" href="/students/add" role="tab" aria-controls="v-pills-profile" aria-selected="false">Add Student</a>
	    </div>
  	</div>
    <div class="col-9">
	    <div class="tab-content" id="v-pills-tabContent">
			<div class="container">
			<nav class="navbar navbar-light bg-light">
			<form class="form-inline" action="/students/name">
		      <input type="text" class="form-control" onfocus="this.value=''" placeholder="Enter firstName" name="firstName">
		      <input type="text" class="form-control" onfocus="this.value=''" placeholder="Enter lastName" name="lastName">
		      <button type="submit" class="btn btn-primary">Search</button>
		    </form>
		    </nav>
		
				<h2>Student List</h2>
				<table class="table table-striped">
					<thead>
						<tr>
							<th scope="row">Student Id</th>
							<th scope="row">First Name</th>
							<th scope="row">Last Name</th>
							<th scope="row">age</th>
							<th scope="row">Edit</th>
							<th scope="row">Delete</th>
						</tr>
					</thead>
					<tbody>	
						<c:forEach items="${studentList}" var="student">
							<tr>
								<td>${student.id }</td>
								<td>${student.firstName }</td>
								<td>${student.lastName }</td>
								<td>${student.age }</td>
								<td><spring:url
										value="/students/update/${student.id}" var="updateURL" />
									<a class="btn btn-primary" href="${updateURL}" type="button">Update</a>
								</td>
								<td><spring:url
										value="/students/delete/${student.id}" var="deleteURL" />
									<a class="btn btn-primary" href="${deleteURL}" type="button">Delete</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<spring:url value="/students/add" var="addURL" />
				<a class="btn btn-primary" href="${addURL}" type="button">Add</a>
			</div>
		</div>
	</div>
</div>

</body>
</html>