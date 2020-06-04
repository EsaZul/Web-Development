<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!-- Carolyn Driving -->
<html> 
	<head>
		 <link rel="stylesheet" href="landing.css" />
	</head>
	<body>
	<%
	String appengineName = request.getParameter("appengineName");

	if (appengineName == null) {
	    appengineName = "default";
	}
	
	pageContext.setAttribute("appengineName", appengineName);
	%>
		<h1>Create Your Own Post</h1>
		
		<div>
			<img src="http://ffrescue.org/images/black-white-cat-banner.jpg" alt="picture">
			<img src="http://ffrescue.org/images/cat-banner-3.jpg" alt="picture">
			<img src="http://ffrescue.org/images/cat-banner-7.jpg" alt="picture">
		</div>
	
		
		<form action="/sign" method="post">
			<h3>Blog Post Title:</h3>
			<div><textarea name="title" rows="1" cols="60"></textarea></div>
			<br>
			<h3>Blog Post Content:</h3>
			<div><textarea name="content" rows="10" cols="60"></textarea></div>
			<br>
			<div>
				<input id="button" type="submit" value="Post Greeting" >
				<input id="button" type="button" onclick="location.href='/appengine.jsp'" value="Cancel"/>
			</div>
			<input type="hidden" name="appengineName" value="${fn:escapeXml(appengineName)}"/>
		</form>
		  
	</body>
</html>
<!-- Carolyn End Driving -->