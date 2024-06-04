<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Tạo tài khoản</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>

<body>
  <div class="container mt-5">
    <div class="row">
      <div class="col-md-6 col-12 mx-auto">
        <form:form action="/admin/user/create" method="post" modelAttribute="newUser">
          <h1 class="mb-3">Create a user</h1>
          <div class="mb-3">
            <label class="form-label">Email:</label>
            <form:input type="email" path="email" class="form-control" />
          </div>
          <div class="mb-3">
            <label class="form-label">Password:</label>
            <form:input type="password" path="password" class="form-control" />
          </div>
          <div class="mb-3">
            <label class="form-label">Phone number:</label>
            <form:input type="number" path="phone" class="form-control" />
          </div>
          <div class="mb-3">
            <label class="form-label">Full name</label>
            <form:input type="text" path="fullName" class="form-control" />
          </div>
          <div class="mb-3">
            <label class="form-label">Address</label>
            <form:input type="text"  path="address" class="form-control" />
          </div>
          <div class="mb-3">
            <button type="submit" class="btn btn-primary">Create</button>
          </div>
        </form:form>
      </div>
    </div>

  </div>
</body>

</html>