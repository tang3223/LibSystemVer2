<%@page import="com.gcit.libsystem.entity.Book"%>
<%@page import="com.gcit.libsystem.service.AdminService"%>
<%	AdminService service = new AdminService();
	Integer bookID = Integer.parseInt(request.getParameter("bookId"));
	Book book = service.readBook(bookID);
%>
<div>
	<div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        <span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Delete Book Details</h4>
      </div>
      <form action="deletebook" method="post">
	      <div class="modal-body">
			<p>Are you sure you want to delete this book <%=book.getTitle()%> ?</p>
			<input type="hidden" class="form-control" name="bookID" value="<%=book.getBookId()%>">
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-danger">Delete!</button>
	      </div>	      
      </form>
</div>
      
      