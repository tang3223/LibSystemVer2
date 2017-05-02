<%@include file="navbar.html" %>
<%@page import="java.util.*" %>
<%@page import="com.gcit.libsystem.entity.Branch" %>
<%@page import="com.gcit.libsystem.service.LibrarianService" %>

<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>GCIT Library System</title>
    <link href="./Jumbotron Template for Bootstrap_files/bootstrap.min.css" rel="stylesheet">
    <link href="./Jumbotron Template for Bootstrap_files/jumbotron.css" rel="stylesheet">
	<link href="./Dashboard Template for Bootstrap_files/dashboard.css" rel="stylesheet">
	<link href="./Jumbotron Template for Bootstrap_files/bootstrap-toggle.min.css" rel="stylesheet">
	<script src="./Jumbotron Template for Bootstrap_files/bootstrap-toggle.min.js"></script>
  </head>

  <body>

    <div class="jumbotron">
      <div class="container">
        <h1>Welcome to GCIT Library System</h1>
      </div>
    </div>

    <div class="container">
      <div class="row">
        <div class="col-md-4">
          <h2>Administrator</h2>
          <p><a class="btn btn-default" href="adminstrator.jsp" role="button">Select</a></p>
        </div>
        <div class="col-md-4">
          <h2>Librarian</h2>     
          <p><button type="button" class="btn btn-default" data-toggle="modal" data-target="#editLibraryModel"
						href="librarian.jsp">Select</button></p>
       </div>
        <div class="col-md-4">
          <h2>Borrower</h2>
          <p><a class="btn btn-default" data-toggle="modal" data-target="#editLibraryModel" 
          				href="brselectbranch.jsp" role="button">Select</a></p>
        </div>
      </div>

      <hr>

      <footer>
        <p>© Charles Tang</p>
      </footer>
    </div>

<div class="modal fade" id="editLibraryModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
    </div>
  </div>
</div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="./Jumbotron Template for Bootstrap_files/jquery.min.js.download"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="./Dashboard Template for Bootstrap_files/holder.min.js.download"></script>
    <script src="./Jumbotron Template for Bootstrap_files/bootstrap.min.js.download"></script>

</body></html>