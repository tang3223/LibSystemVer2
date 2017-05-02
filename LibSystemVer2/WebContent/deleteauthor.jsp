<%@page import="com.gcit.libsystem.entity.Author"%>
<%@page import="com.gcit.libsystem.service.AdminService"%>
<%	AdminService service = new AdminService();
	Integer authorID = Integer.parseInt(request.getParameter("authorId"));
	Author author = service.readAuthor(authorID);
%>
<div>
	<div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        <span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Delete Author Details</h4>
      </div>
      <form action="deleteauthor" method="post">
	      <div class="modal-body">
			<p>Are you sure you want to delete this author <%=author.getAuthorName()%> ?</p>
			<input type="hidden" class="form-control" name="authorID" value="<%=author.getAuthorID()%>">
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-danger">Delete!</button>
	      </div>	      
      </form>
</div>
      
      