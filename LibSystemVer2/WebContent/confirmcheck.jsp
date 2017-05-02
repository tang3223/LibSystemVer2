<%@page import="com.gcit.libsystem.entity.Branch"%>
<%@page import="com.gcit.libsystem.entity.Book"%>
<%@page import="com.gcit.libsystem.service.BorrowerService"%>
<%	BorrowerService service = new BorrowerService();
	Integer borrowerID = Integer.parseInt(request.getParameter("bwId"));
	Integer bookID = Integer.parseInt(request.getParameter("bookId"));
	Integer branchID = Integer.parseInt(request.getParameter("branchId"));
	Book book = service.readBook(bookID);
	Branch branch = service.readBranch(branchID);
%>
<div>
	<div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        <span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Confirm Check Out Details</h4>
      </div>
      <form action="confirmcheckbook" method="post">
	      <div class="modal-body">
			<p>Are you sure you want to check this book <%=book.getTitle()%> from <%=branch.getBranchName()%> ?</p>
			<input type="hidden" class="form-control" name="bookId" value="<%=bookID%>">
			<input type="hidden" class="form-control" name="branchId" value="<%=branchID%>">
			<input type="hidden" class="form-control" name="borrowerId" value="<%=borrowerID%>">						
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-primary">Check Out</button>
	      </div>	      
      </form>
</div>
      
      