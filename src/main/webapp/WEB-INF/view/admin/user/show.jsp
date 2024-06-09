
<%@page contentType="text/html" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <!DOCTYPE html>
    <html lang="en">

    <head>
      <meta charset="utf-8" />
      <meta http-equiv="X-UA-Compatible" content="IE=edge" />
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
      <title>Order</title>
      <link href="/css/styles.css" rel="stylesheet" />
      <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    </head>

    <body class="sb-nav-fixed">
      <jsp:include page="../layout/header.jsp" />
      <div id="layoutSidenav">
        <jsp:include page="../layout/sidebar.jsp"/>
        <div id="layoutSidenav_content">
           <main>
            <div class="container-fluid px-4">              
              <div class="row d-flex justify-content-between align-items-center">
                <div class="w-50">
                  <h1 class="mt-4">User</h1>
                  <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item">Dashboard</li>
                    <li class="breadcrumb-item active">User</li>
                  </ol>
                </div>                
                <a href="/admin/user/create" class="btn btn-primary" style="width: 150px; height:45px">Create a User</a>
              </div>
              <div class="row mt-3">
                <table class="table">
                  <thead>
                    <th>ID</th>
                    <th>Email</th>
                    <th>FullName</th>
                    <th>Role</th>
                    <th>Action</th>
                  </thead>
                  <tbody>
                    <c:forEach var="user" items="${listUser}">
                      <tr>
                        <td>${user.id}</td>
                        <td>${user.email}</td>
                        <td>${user.fullName}</td>
                        <td>${user.role.name}</td>
                        <td>
                          <a href="/admin/user/${user.id}" class="btn btn-success">View</a>
                          <a href="/admin/user/update/${user.id}" class="btn btn-warning">Update</a>
                          <a href="/admin/user/delete/${user.id}" class="btn btn-danger">Delete</a>
                        </td>
                      </tr>
                    </c:forEach>
                  </tbody>
                </table>
              </div>
            </div>
          </main>
          <jsp:include page="../layout/footer.jsp"/>
        </div>
      </div>
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
      <script src="/js/scripts.js"></script>
    </body>

    </html>