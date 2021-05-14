$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	var status = validateFundForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	//If valid------------------------
	var type = ($("#hidfundIDSave").val() == "") ? "POST" : "PUT";
	$.ajax({
		url: "FundAPI",
		type: type,
		data: $("#formFund").serialize(),
		dataType: "text",
		complete: function(response, status) {
			onFundSaveComplete(response.responseText, status);
		}
	});
});
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) {
	$("#hidfundIDSave").val($(this).closest("tr").find('#hidfundIDUpdate').val()); 
	$("#name").val($(this).closest("tr").find('td:eq(0)').text());
	$("#nic").val($(this).closest("tr").find('td:eq(1)').text());
	$("#address").val($(this).closest("tr").find('td:eq(2)').text());
	$("#phone").val($(this).closest("tr").find('td:eq(3)').text());
	$("#email").val($(this).closest("tr").find('td:eq(4)').text());
	$("#amount").val($(this).closest("tr").find('td:eq(5)').text());
	$("#description").val($(this).closest("tr").find('td:eq(6)').text());
});

//Delete

$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url: "FundAPI",
		type: "DELETE",
		data: "fundID=" + $(this).data("fundid"),
		dataType: "text",
		complete: function(response, status) {
			onFundDeleteComplete(response.responseText, status);
		}
	});
});
// CLIENT-MODEL================================================================
function validateFundForm() {
	// CODE
// Name
if ($("#name").val().trim() == "") 
 { 
 return "Insert Name."; 
 } 
// NIC
if ($("#nic").val().trim() == "") 
 { 
 return "Insert NIC number."; 

 }
 // address
if ($("#address").val().trim() == "") 
 { 
 return "Insert Address."; 
 }
 // PHONE
if ($("#phone").val().trim() == "") 
 { 
 return "Insert Phone Number."; 
 }
 var phn = $("#phone").val().trim();
 if(!checkPhone(phn)){
     return "Invalid phone number";
 }
 //EMAIL
if ($("#email").val().trim() == "") 
 { 
 return "Insert Email."; 

 }
 var email = $("#email").val().trim();
 if(!valideEmail(email)){
         return "Insert Valid Email";
 }
// Amount-------------------------------
if ($("#amount").val().trim() == "") 
 { 
 return "Insert Amount."; 
 } 
 // is numerical value
var tmpAmount = $("#amount").val().trim(); 
if (!$.isNumeric(tmpAmount)) 
 { 
 return "Insert a numerical value for Amount."; 
 } 
// convert to decimal amount
 $("#amount").val(parseFloat(tmpAmount).toFixed(2)); 
 
// DESCRIPTION------------------------
if ($("#description").val().trim() == "") 
 { 
 return "Insert  Description."; 
 } 
	return true;
}

function onFundSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divFundGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}

	$("#hidfundIDSave").val("");
	$("#formFund")[0].reset();
}

function onFundDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divFundGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

function valideEmail(email){
            
            var x = /\S+@\S+\.\S+/;
            return x.test(email);
}

function checkPhone(phn){

            if(phn.toString().length == 10){
               return true;
            }
            else{
              return false;
            }
}
