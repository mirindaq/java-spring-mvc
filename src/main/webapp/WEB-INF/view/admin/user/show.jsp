<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
      <h2 class="w-50 m-0">Details User</h2>
    </div>
    <div class="row mt-3">
      <div class="card" style="width: 50rem;">
        <div class="card-header">
        User information
        </div>
        <ul class="list-group list-group-flush">
        <li class="list-group-item">ID: ${user.id}</li>
        <li class="list-group-item">Email: ${user.email}</li>
        <li class="list-group-item">Full name: ${user.fullName}</li>
        <li class="list-group-item">Address: ${user.address}</li>
        </ul>
       </div>
    </div>
    <div class="row mt-3">
      <a href="/admin/user" class="btn btn-success w-25">Back</a>
    </div>
  </div>


</body>

</html>