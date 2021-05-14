<%@page import="model.Researcher"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery-3.6.0.js"></script>
<script src="Components/main.js"></script>

<meta charset="ISO-8859-1">
<title>researcher Management System</title>
</head>
<body>

	
<form id="formResearcher" name="formResearcher" method="post" action="Researcher.jsp">  
					Researcher Name:  
					<input id="ResearcherName" name="ResearcherName" type="text" class="form-control form-control-sm">  
					
					<br> 
					Researcher Email:  
					<input id="ResearcherEmail" name="ResearcherEmail" type="text" class="form-control form-control-sm">  
					 
					<br> 
					Researcher Contact:  
					<input id="ResearcherContact" name="ResearcherContact" type="text" class="form-control form-control-sm">  
					 
					<br>
					Researcher Type:  
					<input id="ResearcherType" name="ResearcherType" type="text" class="form-control form-control-sm">  
					 
					 
					<br>  
					<input id="btnSave" name="btnSave" type="button" value="Save Researcher Details" class="btn btn-primary">  
					<input type="hidden" id="hidResearcherIDSave" name="hidResearcherIDSave" value=""> 
					 
					 
				</form> 
    
				<div id="alertSuccess" class="alert alert-success"></div>  
				<div id="alertError" class="alert alert-danger"></div> 
				
				<br>
					
				
            <div class="row">
               

                <div class="container">
                    <h3 class="text-center">Researcher</h3>
                    <hr>
                    <div class="container text-left">

                       
                        
                    </div>
                    <br>
                
                   <div id="divItemsGrid">   
					<%
   					Researcher researcherObj = new Researcher();
   					out.print(researcherObj.readResearcher());
   					%>  
				
					<br>
					<br>
					 
				</div> 
                   
                </div>
            </div>
  
	
</body>
</html>