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
        <h4 class="modal-title">Update Author Details</h4>
      </div>
      <form action="editauthor" method="post">
	      <div class="modal-body">
			<p>Enter the name of your author:</p>
			<input type="text" class="form-control" name="authorName" placeholder="<%=author.getAuthorName()%>">
			<input type="hidden" class="form-control" name="authorID" value="<%=author.getAuthorID()%>">
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-primary">Save changes</button>
	      </div>	      
      </form>
</div>
      
      