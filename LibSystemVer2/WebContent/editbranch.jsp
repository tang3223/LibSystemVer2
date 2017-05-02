<%@page import="com.gcit.libsystem.entity.Branch"%>
<%@page import="com.gcit.libsystem.service.AdminService"%>
<%	AdminService service = new AdminService();
	Integer branchID = Integer.parseInt(request.getParameter("branchId"));
	String type = "";
	if (request.getParameter("type") != null){
		type = request.getParameter("type");
	}
	Branch branch = service.readBranch(branchID);
%>
<div>
	<div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        <span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Update Library Branch Details</h4>
      </div>
      <form action="editbranch" method="post">
	      <div class="modal-body">
			<p>Enter the name of your library branch:</p>
			<input type="text" class="form-control" name="branchName" placeholder="<%=branch.getBranchName()%>">
			<p>Enter the address of your library branch:</p>
			<input type="text" class="form-control" name="branchAddress" placeholder="<%=branch.getBranchAddress()%>">
			<input type="hidden" class="form-control" name="branchID" value="<%=branch.getBranchID()%>">
			<input type="hidden" class="form-control" name="type" value="<%=type%>">
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-primary">Save changes</button>
	      </div>	      
      </form>
</div>
      
      