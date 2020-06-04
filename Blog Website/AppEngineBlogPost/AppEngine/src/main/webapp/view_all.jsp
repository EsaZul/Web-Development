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



<html> 
	<head>
		<link rel="stylesheet" href="landing.css" />
	</head>
	<body>
	
		<h1>All Posts. Past, Present, Not Future</h1>
		
		<input id="links" type="button" onclick="location.href='/appengine.jsp'" value="Home"/>
		
		<br>
		<br>
		
		<div>
			<img src="http://ffrescue.org/images/black-white-cat-banner.jpg" alt="picture">
			<img src="http://ffrescue.org/images/cat-banner-3.jpg" alt="picture">
			<img src="http://ffrescue.org/images/cat-banner-7.jpg" alt="picture">
		</div>
		<br>
		  
		  
<%
//Eduardo Driving
	String appengineName = request.getParameter("appengineName");
	
	if (appengineName == null) {
	    appengineName = "default";
	}
	
			pageContext.setAttribute("appengineName", appengineName);
			UserService userService = UserServiceFactory.getUserService();
	    	User user = userService.getCurrentUser();
    	
    	
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	Key appengineKey = KeyFactory.createKey("AppEngine", appengineName);
	
	
	Query query = new Query("Greeting", appengineKey).addSort("date", Query.SortDirection.DESCENDING);
	
	List<Entity> greetings = datastore.prepare(query).asList(FetchOptions.Builder.withOffset(0));
	
	if (greetings.isEmpty()) {
	    %>
	    <%
	} else {
//Eduardo end driving, Carolyn driving
	    %>
	    <%
	    for (Entity greeting : greetings) {
	    	pageContext.setAttribute("the_greeting", greeting);
	        pageContext.setAttribute("greeting_content", greeting.getProperty("content"));
	        pageContext.setAttribute("greeting_title", greeting.getProperty("title"));
	        pageContext.setAttribute("greeting_date", greeting.getProperty("date"));
	        pageContext.setAttribute("greeting_comments", greeting.getProperty("comments"));
	        
	        pageContext.setAttribute("greeting_key", greeting.getKey());
	        
	        if (greeting.getProperty("user") == null) {
	            %>
	            <p>An anonymous person wrote:</p>
	            <%
	        } else {
	            pageContext.setAttribute("greeting_user",
	                                     greeting.getProperty("user"));
	            %>
	            <%
	        }
	
	        %>
	        <div id="unit">
	        <blockquote id="title">${fn:escapeXml(greeting_title)}</blockquote>
	        <blockquote>${fn:escapeXml(greeting_content)}</blockquote>
	        <blockquote id="important">Creator: ${fn:escapeXml(greeting_user.nickname)}</blockquote>
	        <blockquote id="important">${fn:escapeXml(greeting_date)}</blockquote>
	        <blockquote id="commentFormat">Comments:</blockquote>
	        <%
	        if(greeting.getProperty("comments") != null){
	        	List<String> comments = (List<String>) greeting.getProperty("comments");
	            for(String comment: comments){
	            	pageContext.setAttribute("greeting_comments", comment);
	            	%>
	            	<blockquote>${fn:escapeXml(greeting_comments)}</blockquote>
	            	<%
	            }
	        }
	        %>
	         <%
        if (user != null){
        %>
        <form action="/sign" method="post">
			<div><textarea name="comment" rows="1" cols="60"></textarea></div>
			<br>
			<input id="button" type="submit" value="Post Comment" >
			<input type="hidden" name="appengineName" value="${fn:escapeXml(appengineName)}"/>
			<input type="hidden" name="commentForGreeting" value="${fn:escapeXml(greeting_key)}">
		</form>
		<br>
		</div>
	        <%
	    }
//Carolyn end Driving     
	    }
	}
%>
	</body>
</html>