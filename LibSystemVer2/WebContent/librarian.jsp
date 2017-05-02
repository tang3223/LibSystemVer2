<%@page import="java.util.*" %>
<%@page import="com.gcit.libsystem.entity.Branch"%>
<%@page import="com.gcit.libsystem.service.LibrarianService"%>
<%	LibrarianService service = new LibrarianService();
	List<Branch> branchs = new ArrayList<>();
	branchs = service.readAllBranchs();
%>
<script type="text/javascript" src="./Dashboard Template for Bootstrap_files/bootstrap-multiselect.js"></script>
<link rel="stylesheet" href="./Dashboard Template for Bootstrap_files/bootstrap-multiselect.css" type="text/css"/>
<div>
	<div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        <span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Select Library Branch</h4>
      </div>
      <form action="selectbranch" method="post">
	      <div class="modal-body">
			<p>Select the library branch to manage:</p>
 			<select id="branchList" name="branchId">
				<% for (int i = 0; i < branchs.size(); i++) { %>
				<option value="<%=branchs.get(i).getBranchID()%>"><%=branchs.get(i).getBranchName()%></option>
			    <% } %>
			</select>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-primary">Select</button>
	      </div>	      
      </form>
</div>