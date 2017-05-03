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
        <h4 class="modal-title">Update Borrower Details</h4>
      </div>
      <form action="editborrower" method="post">
	      <div class="modal-body">
			<p>Enter the name of your borrower:</p>
			<input type="text" class="form-control" name="borrowerName" placeholder="<%=borrower.getBorrowerName()%>">
			<p>Enter the address of your borrower:</p>
			<input type="text" class="form-control" name="borrowerAddress" placeholder="<%=borrower.getBorrowerAddress()%>">
			<p>Enter the phone number of your borrower:</p>
			<input type="text" class="form-control" name="borrowerPhone" placeholder="<%=borrower.getBorrowerPhone()%>">
			<input type="hidden" class="form-control" name="borrowerID" value="<%=borrower.getBorrowerID()%>">
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-primary">Save changes</button>
	      </div>	      
      </form>
</div>
      
      