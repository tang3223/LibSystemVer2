<%@page import="com.gcit.libsystem.entity.Branch"%>
<%@page import="com.gcit.libsystem.service.AdminService"%>
<%	AdminService service = new AdminService();
	Integer branchID = Integer.parseInt(request.getParameter("branchId"));
	Branch branch = service.readBranch(branchID);
%>
<div>
	<div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        <span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Delete Branch Details</h4>
      </div>
      <form action="deletebranch" method="post">
	      <div class="modal-body">
			<p>Are you sure you want to delete this branch <%=branch.getBranchName()%> ?</p>
			<input type="hidden" class="form-control" name="branchID" value="<%=branch.getBranchID()%>">
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-danger">Delete!</button>
	      </div>	      
      </form>
</div>
      
      