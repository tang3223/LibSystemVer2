<%@page import="com.gcit.libsystem.entity.Publisher"%>
<%@page import="com.gcit.libsystem.service.AdminService"%>
<%	AdminService service = new AdminService();
	Integer publisherID = Integer.parseInt(request.getParameter("publisherId"));
	Publisher publisher = service.readPublisher(publisherID);
%>
<div>
	<div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        <span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Update Publisher Details</h4>
      </div>
      <form action="editpublisher" method="post">
	      <div class="modal-body">
			<p>Enter the name of your publisher:</p>
			<input type="text" class="form-control" name="publisherName" placeholder="<%=publisher.getPublisherName()%>">
			<p>Enter the address of your publisher:</p>			
			<input type="text" class="form-control" name="publisherAddress" placeholder="<%=publisher.getPublisherAddress()%>">
			<p>Enter the phone number of your publisher:</p>			
			<input type="text" class="form-control" name="publisherPhone" placeholder="<%=publisher.getPublisherPhone()%>">						
			<input type="hidden" class="form-control" name="publisherID" value="<%=publisher.getPublisherId()%>">
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-primary">Save changes</button>
	      </div>	      
      </form>
</div>
      
      