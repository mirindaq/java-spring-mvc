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
              <h1 class="mt-4">Product</h1>
              <ol class="breadcrumb mb-4">
                <li class="breadcrumb-item">Dashboard</li>
                <li class="breadcrumb-item active">Product</li>
              </ol>
              <div class="row mt-3">
                <div class="card" style="width: 90%;">
                  <div class="card-header">
                  Product information
                  </div>
                  <ul class="list-group list-group-flush">
                  <li class="list-group-item">ID: ${product.id}</li>
                  <li class="list-group-item">Name: ${product.name}</li>
                  <li class="list-group-item">Price: ${product.price}</li>
                  <li class="list-group-item">Quantity: ${product.quantity}</li>
                  <li class="list-group-item">Sold: ${product.sold}</li>
                  <li class="list-group-item">Factory: ${product.factory}</li>
                  <li class="list-group-item">Short Desc: ${product.productDetail.shortDesc}</li>
                  <li class="list-group-item">Detail Desc: ${product.productDetail.detailDesc}</li>
                  <li class="list-group-item">Image: 
                  <div>
                  <img src="/images/product/${product.image}" alt="" style="width: 90%;"></li>
                  </div>
                  
                  </ul>
                </div>
              </div>
              <div class="row mt-3">
                <a href="/admin/user" class="btn btn-success w-25">Back</a>
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