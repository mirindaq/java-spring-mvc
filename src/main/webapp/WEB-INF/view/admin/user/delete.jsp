<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>User Detail ${id}</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>

<body>
  <div class="container">
    <div class="row mt-5">
      <h2 class="w-50 m-0">Delete the User with id = ${id}</h2>
    </div>
    <div class="row mt-3">
      <div class="alert alert-danger" role="alert">
        Are you sure delete the user?
      </div>
    </div>
    <form:form action="/admin/user/delete" method="post" modelAttribute="user">
      <div class="mb-3" style="display: none;">
        <label class="form-label">ID:</label>
        <form:input type="text" path="id" class="form-control" value="${id}" />
      </div>
      <button class="btn btn-success" type="submit">Confirm</button>
    </form:form>
    <a href="/admin/user" class="btn btn-primary" style="width: 120px;">Back</a> 
  </div>


</body>

</html>