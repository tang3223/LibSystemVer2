<%@page import="com.gcit.libsystem.entity.Publisher"%>
<%@page import="com.gcit.libsystem.service.AdminService"%>

<div>
	<div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        <span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Add Publisher Details</h4>
      </div>
      <form action="addpublisher" method="post">
	      <div class="modal-body">
			<p>Enter the name of your publisher:</p>
			<input type="text" class="form-control" name="publisherName" placeholder="Publisher Name...">
			<p>Enter the address of your publisher:</p>			
			<input type="text" class="form-control" name="publisherAddress" placeholder="Publisher Address...">
			<p>Enter the phone number of your publisher:</p>					
			<input type="text" class="form-control" name="publisherPhone" placeholder="Publisher Phone...">						
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-primary">Save changes</button>
	      </div>	      
      </form>
</div>
      
      