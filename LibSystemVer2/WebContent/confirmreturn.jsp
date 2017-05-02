<%@page import="java.util.*" %>
<%@page import="com.gcit.libsystem.entity.Branch"%>
<%@page import="com.gcit.libsystem.entity.Book"%>
<%@page import="com.gcit.libsystem.service.BorrowerService"%>
<%	BorrowerService service = new BorrowerService();
	Integer borrowerID = Integer.parseInt(request.getParameter("bwId"));
	Integer bookID = Integer.parseInt(request.getParameter("bookId"));
	Integer branchID = Integer.parseInt(request.getParameter("branchId"));
	Book book = service.readBook(bookID);
	List<Branch> branchs = service.readAllBranchs();
%>
<script type="text/javascript" src="./Dashboard Template for Bootstrap_files/bootstrap-multiselect.js"></script>
<link rel="stylesheet" href="./Dashboard Template for Bootstrap_files/bootstrap-multiselect.css" type="text/css"/>
<div>
	<div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        <span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Confirm Return Details</h4>
      </div>
      <form action="confirmreturnbook" method="post">
	      <div class="modal-body">
			<p>Select the library branch you want to return:</p>
			<select id="branchList" name="branchId">
				<% for (Branch branch : branchs) { %>
				<option value="<%=branch.getBranchID()%>"><%=branch.getBranchName()%></option>
			    <% } %>
			</select>
			<input type="hidden" class="form-control" name="bookId" value="<%=bookID%>">
			<input type="hidden" class="form-control" name="borrowerId" value="<%=borrowerID%>">						
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-primary">Return</button>
	      </div>	      
      </form>
</div>
<script type="text/javascript">
    $(document).ready(function() {
        $('#branchList').multiselect();
    });
</script>      
      