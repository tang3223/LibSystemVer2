<%@page import="com.gcit.libsystem.entity.BookLoan"%>
<%@page import="com.gcit.libsystem.service.AdminService"%>
<%	AdminService service = new AdminService();
	Integer bookID = Integer.parseInt(request.getParameter("bkId"));
	Integer branchID = Integer.parseInt(request.getParameter("bhId"));
	Integer borrowerID = Integer.parseInt(request.getParameter("brId"));
	BookLoan bookLoan = service.readBookLoan(borrowerID, branchID, bookID);
%>
<script src="./bootstrap datepicker/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet" href="./bootstrap datepicker/css/bootstrap-datepicker3.min.css" type="text/css"/>
<div>
	<div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        <span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Update Book Loan Details</h4>
      </div>
      <form action="editbookloan" method="post">
	      <div class="modal-body">
			<p>Enter the new due date of your book loan:</p>
			<input type="text" class="form-control" name="dueDate" id="dueDate" placeholder="mm-dd-yyyy">
			<input type="hidden" class="form-control" name="bookID" value="<%=bookLoan.getBook().getBookId()%>">
			<input type="hidden" class="form-control" name="branchID" value="<%=bookLoan.getBranch().getBranchID()%>">
			<input type="hidden" class="form-control" name="borrowerID" value="<%=bookLoan.getBorrower().getBorrowerID()%>">				
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-primary">Save changes</button>
	      </div>	      
      </form>
</div>
<script>
$('#dueDate').datepicker({
	format: 'yyyy-mm-dd',
	startDate: '-0d'
});
</script>
      
      