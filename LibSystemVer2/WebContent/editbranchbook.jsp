<%@page import="java.util.*" %>
<%@page import="com.gcit.libsystem.entity.Branch"%>
<%@page import="com.gcit.libsystem.entity.Book"%>
<%@page import="com.gcit.libsystem.service.LibrarianService"%>
<%	
	LibrarianService service = new LibrarianService();
	Integer branchID = Integer.parseInt(request.getParameter("branchId"));
	Integer bookID   = Integer.parseInt(request.getParameter("bookId"));
	Book oldBook     = service.readBook(bookID);
	Branch branch = service.readBranch(branchID);
%>
<script type="text/javascript" src="./Dashboard Template for Bootstrap_files/bootstrap-multiselect.js"></script>
<link rel="stylesheet" href="./Dashboard Template for Bootstrap_files/bootstrap-multiselect.css" type="text/css"/>
<div>
	<div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        <span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Update Book Copies in Branch</h4>
      </div>
      <form action="editbranchbook" method="post">    	
	      <div class="modal-body">
			<p>You have selected <%=oldBook.getTitle()%></p>
			<p>Enter the number of your book:</p>
			<input type="text" class="form-control" name="bookCopies" placeholder=" Number of Copies...">
			<input type="hidden" class="form-control" name="branchId" value="<%=branch.getBranchID()%>">
			<input type="hidden" class="form-control" name="bookId" value="<%=bookID%>">
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-primary">Save changes</button>
	      </div>	      
      </form>
</div>
<script type="text/javascript">
    $(document).ready(function() {
        $('#bookList').multiselect();
    });
</script>      
      