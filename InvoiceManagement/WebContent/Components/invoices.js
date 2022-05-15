//Hide the alters on page load
$(document).ready(function()
{

	$("#alertSuccess").hide();

 	$("#alertError").hide();

}); 

$(document).on("click", "#btnSave", function(event)
{
	console.log($("#hidInvoiceIDSave").val());
	
	// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();

// Form validation-------------------
var status = validateItemForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
 var type = ($("#hidInvoiceIDSave").val() == "") ? "POST" : "PUT";
console.log(type); 
 $.ajax(
 {
 url : "InvoicesAPI",
 type : type,
 data : $("#formItem").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onInvoiceSaveComplete(response.responseText, status);
 }
 });
});

// CLIENT-MODEL================================================================
function validateItemForm()
{
//Customer Name
if ($("#customerName").val().trim() == "")
 {
 return "Insert Customer Name.";
 }
//E-mail 
if ($("#email").val().trim() == "")
 {
 return "Insert Customer E-mail.";
 }
//Units
if ($("#units").val().trim() == "")
 {
 return "Insert Number Of Units.";
 }
// is numerical value
var tmpunit = $("#units").val().trim();
if (!$.isNumeric(tmpunit))
 {
 return "Insert a numerical value for Units.";
 }

// Amount
if ($("#amount").val().trim() == "")
 {
 return "Insert Bill Amount.";
 }
//Month
if ($("#month").val().trim() == "")
 {
 return "Insert Month.";
 }
return true;
}

$(document).on("click", ".btnUpdate", function()
{
 $("#hidInvoiceIDSave").val($(this).data("invoiceid"));
 $("#customerName").val($(this).closest("tr").find('td:eq(0)').text());
 $("#email").val($(this).closest("tr").find('td:eq(1)').text());
 $("#units").val($(this).closest("tr").find('td:eq(2)').text());
 $("#month").val($(this).closest("tr").find('td:eq(3)').text());
 $("#invoiceDet").val($(this).closest("tr").find('td:eq(4)').text());
});

function onInvoiceSaveComplete(response, status)
{
	if (status == "success")
 	{
 		var resultSet = JSON.parse(response);
 		if (resultSet.status.trim() == "success")
 		{
 			$("#alertSuccess").text("Successfully saved.");
 			$("#alertSuccess").show();
			
 			$("#divItemsGrid").html(resultSet.data);
			
 		} else if (resultSet.status.trim() == "error")
 			{
			 	$("#alertError").text(resultSet.data);
 			 	$("#alertError").show();
 			}	
 	} else if (status == "error")
 		{
 			$("#alertError").text("Error while saving.");
 			$("#alertError").show();
 		} else
 			{
 				$("#alertError").text("Unknown error while saving..");
 				$("#alertError").show();
 			} 
		
 $("#hidInvoiceIDSave").val("");
 $("#formItem")[0].reset();
}


$(document).on("click", ".btnRemove", function()
{
	var id = $(this).data("invoiceid");
	console.log("id is :"+id)
 $.ajax(
 {
 url : "InvoicesAPI",
 type : "DELETE",
 data : "invoiceID=" + id,
 dataType : "text",
 complete : function(response, status)
 {
	console.log(id)
 onInvoiceDeleteComplete(response.responseText, status);
 }
 });
});

function onInvoiceDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divItemsGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
	$("#hidItemIDSave").val(""); 
	$("#formItem")[0].reset(); 
}










