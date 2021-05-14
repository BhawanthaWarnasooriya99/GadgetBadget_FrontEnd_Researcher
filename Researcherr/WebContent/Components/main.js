$(document).ready(function() 
{  
	if ($("#alertSuccess").text().trim() == "")  
	{   
		$("#alertSuccess").hide();  
	} 
	$("#alertError").hide(); 
}); 

//SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 

	// Form validation-------------------  
	var status = validateResearcherForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 

	// If valid------------------------  
	var t = ($("#hidResearcherIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "ResearcherApi",
		type : t,
		data : $("#formResearcher").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onResearcherSaveComplete(response.responseText, status);
		}
	});
}); 

function onResearcherSaveComplete(response, status){
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Saved.");
			$("#alertSuccess").show();
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Saving.");
		$("#slertError").show();
	}else{
		$("#alertError").text("Unknown Error while Saving.");
		$("#alertError").show();
	}
	$("#hidResearcherIDSave").val("");
	$("#formResearcher")[0].reset();
}

//UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
		{     
	$("#hidResearcherIDSave").val($(this).closest("tr").find('#hidResearcherUpdate').val());     
	$("#ResearcherName").val($(this).closest("tr").find('td:eq(0)').text());    
	$("#ResearcherEmail").val($(this).closest("tr").find('td:eq(1)').text());         
	$("#ResearcherContact").val($(this).closest("tr").find('td:eq(2)').text());
	$("#ResearcherType").val($(this).closest("tr").find('td:eq(3)').text()); 
	

});


//Remove Operation
$(document).on("click", ".btnRemove", function(event){
	$.ajax(
	{
		url : "ResearcherApi",
		type : "DELETE",
		data : "ResearcherID=" + $(this).data("researcherid"),
		dataType : "text",
		complete : function(response, status)
		{
			onResearcherDeletedComplete(response.responseText, status);
		}
	});
});

function onResearcherDeletedComplete(response, status)
{
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Deleted.");
			$("#alertSuccess").show();
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Deleting.");
		$("#alertError").show();
	}else{
		$("#alertError").text("Unknown Error While Deleting.");
		$("#alertError").show();
	}
}

//CLIENTMODEL
function validateResearcherForm() {  
	// NAME  
	if ($("#ResearcherName").val().trim() == "")  {   
		return "Insert ResearcherName.";  
		
	} 
	
	 // Email 
	if ($("#ReasearcherEmail").val().trim() == "")  {   
		return "Insert ResearcherEmail.";  
	} 
	
	 // is numerical value  
	var tmpMobile = $("#ResearcherContact").val().trim();  
	if (!$.isNumeric(tmpMobile))  {   
		return "Insert a numerical value for Mobile Number.";  
		
	}
	 	
	//Type
	if ($("#ResearcherType").val().trim() == "")  {   
		return "Insert ResearcherType."; 
		 
	}

	 
	 return true; 
	 
}