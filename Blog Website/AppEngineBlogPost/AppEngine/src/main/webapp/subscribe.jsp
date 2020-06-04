<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreService" %>
<%@ page import="com.google.appengine.api.datastore.Query" %>
<%@ page import="com.google.appengine.api.datastore.Entity" %>
<%@ page import="com.google.appengine.api.datastore.FetchOptions" %>
<%@ page import="com.google.appengine.api.datastore.Key" %>
<%@ page import="com.google.appengine.api.datastore.KeyFactory" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!-- Eduardo Driving -->
<html> 
	<head>
		 <link rel="stylesheet" href="landing.css" />
	</head>
	<body>
	
		<h1>Would You Like To Subscribe?</h1>
		
		<input id="links" type="button" onclick="location.href='/appengine.jsp'" value="Home"/>
		
		<br>
		<br>
		
		<div>
			<img src="http://ffrescue.org/images/black-white-cat-banner.jpg" alt="picture">
			<img src="http://ffrescue.org/images/cat-banner-3.jpg" alt="picture">
			<img src="http://ffrescue.org/images/cat-banner-7.jpg" alt="picture">
		</div>
		
		<h3>Subscribe to our daily email to stay up to date on all the most recent posts! With the subscription you will recieve</h3>
		<h3>a daily email that contains all of the content that has been shared in the past 24 hours.</h3>
		<h3>If you feel like the subscription isn't for you we understand, but if you would like to join please press the button below!</h3>
		
		<br>
		
		<form action="/sign" method="post">
			<div>
				<input type="hidden" name="subscribe" value= "true">
				<input id="sbutton" type="submit" value="SUBSCRIBE!">
			</div>
			<input type="hidden" name="appengineName" value="default"/>
		</form>
		

	</body>
</html>
<!-- Eduardo end Driving -->



