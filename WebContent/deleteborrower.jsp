<%@page import="com.gcit.libsystem.entity.Borrower"%>
<%@page import="com.gcit.libsystem.service.AdminService"%>
<%	AdminService service = new AdminService();
	Integer borrowerID = Integer.parseInt(request.getParameter("borrowerId"));
	Borrower borrower = service.readBorrower(borrowerID);
%>
<div>
	<div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        <span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Delete Borrower Details</h4>
      </div>
      <form action="deleteborrower" method="post">
	      <div class="modal-body">
			<p>Are you sure you want to delete this branch <%=borrower.getBorrowerName()%> ?</p>
			<input type="hidden" class="form-control" name="borrowerID" value="<%=borrower.getBorrowerID()%>">
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-danger">Delete!</button>
	      </div>	      
      </form>
</div>
      
      