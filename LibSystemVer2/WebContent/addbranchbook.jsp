<%@page import="java.util.*" %>
<%@page import="com.gcit.libsystem.entity.Branch"%>
<%@page import="com.gcit.libsystem.entity.Book"%>
<%@page import="com.gcit.libsystem.service.LibrarianService"%>
<%	
	LibrarianService service = new LibrarianService();
	Integer branchID = Integer.parseInt(request.getParameter("branchId"));
	Branch branch = service.readBranch(branchID);
	List<Book> books = service.readAllBook();
%>
<script type="text/javascript" src="./Dashboard Template for Bootstrap_files/bootstrap-multiselect.js"></script>
<link rel="stylesheet" href="./Dashboard Template for Bootstrap_files/bootstrap-multiselect.css" type="text/css"/>
<div>
	<div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        <span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Add Book to Branch</h4>
      </div>
      <form action="addbranchbook" method="post">
	      <div class="modal-body">
			<p>Select the name of your book:</p>
			<select id="bookList" name="bookId">
				<% for (int i = 0; i < books.size(); i++) { %>
				<option value="<%=books.get(i).getBookId()%>"><%=books.get(i).getTitle()%></option>
			    <% } %>
			</select>
			<p>Enter the number of your book:</p>
			<input type="text" class="form-control" name="bookCopies" placeholder=" Number of Copies...">
			<input type="hidden" class="form-control" name="branchId" value="<%=branch.getBranchID()%>">
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
      