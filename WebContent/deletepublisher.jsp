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
        <h4 class="modal-title">Delete Publisher Details</h4>
      </div>
      <form action="deletepublisher" method="post">
	      <div class="modal-body">
			<p>Are you sure you want to delete this publisher <%=publisher.getPublisherName()%> ?</p>
			<input type="hidden" class="form-control" name="publisherID" value="<%=publisher.getPublisherId()%>">
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-danger">Delete!</button>
	      </div>	      
      </form>
</div>
      
      