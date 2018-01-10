<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>welcome</title>
</head>
<body>

    <% 
    	if (session.getAttribute("userName") != null) {
    	    response.sendRedirect("success");
    	}
    %>
    <div style="text-align: center">
        WELCOME !!! <br> PLEASE CHOOSE LOG IN OR SEGISTER <br>
    </div>

    <div style ="text-align: center">
        <a href ="login"><button type="button" style="text-align:center"> Log in</button></a>
        <a href ="register"><button type="button" style="text-align:center"> Register</button></a>
    </div>
    
</body>
</html>