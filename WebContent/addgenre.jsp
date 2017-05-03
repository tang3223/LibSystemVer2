<%@page import="com.gcit.libsystem.entity.Genre"%>
<%@page import="com.gcit.libsystem.service.AdminService"%>

<div>
	<div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        <span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Add Genre Details</h4>
      </div>
      <form action="addgenre" method="post">
	      <div class="modal-body">
			<p>Enter the name of your genre:</p>
			<input type="text" class="form-control" name="genreName" placeholder="Genre Name...">
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-primary">Save changes</button>
	      </div>	      
      </form>
</div>
      
      