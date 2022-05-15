<%@page import="model.Invoice" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inquiry Management</title>
<link rel="stylesheet" href="Views/bootstrap.css">

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Invoice Management</h1>
				<form id="formItem" name="formItem">
					Customer Name: <input id="customerName"
						name="customerName" type="text" class="form-control form-control-sm"> <br>
					E-mail: <input id="email" name="email"
						type="text" class="form-control form-control-sm"> <br> Units
					: <input id="units" name="units"
						type="text" class="form-control form-control-sm"> <br> Amount:
					<input id="amount" name="amount" type="text"
						class="form-control form-control-sm"> <br> Month: <input
						id="month" name="month" type="text"
						class="form-control form-control-sm"> <br> <input id="btnSave"
						name="btnSave" type="button" value="Save" class="btn btn-primary"> <input type="hidden" id="hidInvoiceIDSave" name="hidInvoiceIDSave"
						value="">
				</form>
				<br>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divItemsGrid">
				<%
				Invoice invoiceObj = new Invoice();
 					out.print(invoiceObj.readInvoices());
 					%>
				
	
				</div>
			</div>
		</div>
	</div>
</body>
<script src="Components/jquery.min.js" type="text/javascript"></script>
<script src="Components/invoices.js" type="text/javascript"></script>
</html>