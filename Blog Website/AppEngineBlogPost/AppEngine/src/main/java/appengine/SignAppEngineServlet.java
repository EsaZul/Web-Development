package appengine;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.servlet.http.Part;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 

public class SignAppEngineServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//Carolyn driving
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

 
        String appengineName = req.getParameter("appengineName");
        System.out.println(appengineName);
        Key appengineKey = KeyFactory.createKey("AppEngine", appengineName);
        String content = req.getParameter("content");
        String title = req.getParameter("title");
        
        String comment = req.getParameter("comment");
        String commentForGreeting = req.getParameter("commentForGreeting");
        
     
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now().minusHours(6);
        
        String date = dtf.format(now); 
        System.out.println(date);
        
        
        String subscribe = req.getParameter("subscribe");
        
        if (subscribe==null) {
        	        	
        	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        	
        	Query query = new Query("Greeting", appengineKey).addSort("date", Query.SortDirection.DESCENDING);
        	List<Entity> greetings = datastore.prepare(query).asList(FetchOptions.Builder.withOffset(0));
        	boolean belongs = false;
        	
        	if (comment != null) {
        	
	        	for (Entity greeting : greetings) {
	        		if (greeting.getKey().toString().equals(commentForGreeting.toString())) {
	        			if(greeting.getProperties().containsKey("comments")) {
	        				List<String> comments = (List<String>) greeting.getProperty("comments");
	        				String commentComplete= user + ": " + comment;
	        				comments.add(commentComplete);
	        				greeting.setProperty("comments", comments);
	        				belongs=true;
	        				datastore.put(greeting);
	        			}
	        			else {
	        				List<String> comments = new ArrayList<String>();
	        				String commentComplete= user + ": " + comment;
	        				comments.add(commentComplete);
	        				greeting.setProperty("comments", comments);
		        			belongs=true;
		        			datastore.put(greeting);
	        			}
	        		}
	        	}
        	}
  	
        	if(!belongs) {
		        Entity greeting = new Entity("Greeting", appengineKey);
		        greeting.setProperty("user", user);
		        greeting.setProperty("date", date);
		        greeting.setProperty("content", content);
		        greeting.setProperty("title", title);
		
		        datastore.put(greeting);
        	}
        }
        else {
        	
        	if (subscribe.equals("true")) {
        		Entity user_subscribed = new Entity("Subscriber", appengineKey);
            	user_subscribed.setProperty("subscribe", subscribe);
            	user_subscribed.setProperty("user", user);
    	        
    	        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    	    	
    	        datastore.put(user_subscribed);
        	}
        	else {
        		
        		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
				
				Query query_1 = new Query("Subscriber", appengineKey);

				List<Entity> subscribers = datastore.prepare(query_1).asList(FetchOptions.Builder.withOffset(0));
			
			    for (Entity subscriber: subscribers){
			    
			    	if (subscriber.getProperty("user").toString().equals(user.toString())){
			    		datastore.delete(subscriber.getKey());
			    	}
			    }
			    
        	}
        	
        }

        resp.sendRedirect("/appengine.jsp?appengineName=" + appengineName);
        
    }
}
//Carolyn end driving
