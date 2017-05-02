<%@page import="java.util.*" %>
<%@page import="com.gcit.libsystem.entity.Branch"%>
<%@page import="com.gcit.libsystem.service.BorrowerService"%>
<%	BorrowerService service = new BorrowerService();
	List<Branch> branchs = new ArrayList<>();
	branchs = service.readAllBranchs();
%>
<link href="./Jumbotron Template for Bootstrap_files/bootstrap-toggle.min.css" rel="stylesheet">
<script src="./Jumbotron Template for Bootstrap_files/bootstrap-toggle.min.js"></script>
<script>
	function checkCardID(){
		$.ajax({
			url: "checkid",
			data:{
				cardID: $('#cardId').val()
			}
		}).done(function (data){
			$('#message').html(data)
		})
	};
</script>
<div>
	<div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        <span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Select Library Branch</h4>
      </div>
      <form action="brselectbranch" method="post">
	      <div class="modal-body">
      		<div class="input-group">
		      <input id="cardId" type="text" name="cardNum" class="form-control" placeholder="Card ID...">
		      <span class="input-group-btn">
		        <button class="btn btn-default" type="button" onclick="checkCardID()">Check ID</button>
		      </span>
		    </div>
	      <div class="row placeholders">
            <div class="col-xs-6 col-sm-3 placeholder">
			  <div id ="message">
			  </div>
            </div>
            <div class="col-xs-6 col-sm-3 placeholder">
				<label class="checkbox-inline">
	  				<input id="chkOrRtn" name="borrowerType" type="checkbox" data-toggle="toggle" data-on="Return" data-off="CheckOut">
				</label>
            </div>
            <div class="col-xs-6 col-sm-3 placeholder">
	 			<select id="branchList" name="branchId">
					<% for (int i = 0; i < branchs.size(); i++) { %>
					<option value="<%=branchs.get(i).getBranchID()%>"><%=branchs.get(i).getBranchName()%></option>
				    <% } %>
				</select>
            </div>
          </div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-primary">Select</button>
	      </div>	      
      </form>
</div>
<script>
  $(function() {
    $('#chkOrRtn').bootstrapToggle();
  })
</script>