<%@page import="com.gcit.libsystem.entity.Branch"%>
<%@page import="com.gcit.libsystem.service.AdminService"%>

<div>
	<div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        <span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Add Library Branch Details</h4>
      </div>
      <form action="addbranch" method="post">
	      <div class="modal-body">
			<p>Enter the name of your library branch:</p>
			<input type="text" class="form-control" name="branchName" placeholder="Library Branch Name...">
			<p>Enter the address of your library branch:</p>
			<input type="text" class="form-control" name="branchAddress" placeholder="Library Branch Address...">
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-primary">Save changes</button>
	      </div>	      
      </form>
</div>
      
      