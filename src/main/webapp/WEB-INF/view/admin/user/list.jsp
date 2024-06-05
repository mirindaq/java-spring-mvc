<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>

<body>
  <div class="container">
    <div class="row d-flex justify-content-between mt-5">
      <h2 class="w-50 m-0">Table User</h2>
      <a href="/admin/user/create" class="btn btn-primary" style="width: 150px;">Create a User</a>
    </div>
    <div class="row mt-3">
      <table class="table">
        <thead>
          <th>ID</th>
          <th>Email</th>
          <th>FullName</th>
          <th>Action</th>
        </thead>
        <tbody>
          <c:forEach var="user" items="${listUser}">
            <tr>
              <td>${user.id}</td>
              <td>${user.email}</td>
              <td>${user.fullName}</td>
              <td>
                <a href="/admin/user/${user.id}" class="btn btn-success">View</a>
                <a href="/admin/user/update/${user.id}" class="btn btn-warning">Update</a>
                <a href="/admin/user/${user.id}" class="btn btn-danger">Delete</a>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </div>


</body>

</html>