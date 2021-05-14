<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Fund Request Form</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/fund.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Fund Request Form</h1>
				<form id="formFund" name="formFund">

					Name: <input id="name" name="name" type="text" class="form-control form-control-sm">
					 <br> National Identity Card Number: <input id="nic" name="nic" type="text"
						class="form-control form-control-sm"> <br> Address: <input
						id="address" name="address" type="text"
						class="form-control form-control-sm"> 
						<br> Phone Number: <input id="phone" name="phone" type="text"
						class="form-control form-control-sm"> 
						<br> Email Address: <input id="email" name="email" type="text"
						class="form-control form-control-sm"> 
						<br> Amount: <input
						id="amount" name="amount" type="text"
						class="form-control form-control-sm"> 
						<br>Description(why are you request fund): <input id="description"
						name="description" type="text"
						class="form-control form-control-sm"> 
						<br> <input
						id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hidfundIDSave" name="hidfundIDSave" value="">

				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divFundGrid">
					<%
					Fund itemObj = new Fund();
					out.print(itemObj.readFund());
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
