package appengine;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import javax.servlet.http.*;



@SuppressWarnings("serial")
	public class CronServlet extends HttpServlet {
		@SuppressWarnings("deprecation")
		public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//Eduardo driving
			String strCallResult = "";
			resp.setContentType("text/plain");
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	        LocalDateTime now = LocalDateTime.now().minusHours(6);
	        now = now.minusDays(1);
	        String past = dtf.format(now);
	        LocalDateTime pastDate = LocalDateTime.parse(past, dtf);
		
			try {
				
				String appengineName = req.getParameter("appengineName");

				if (appengineName == null) {
				    appengineName = "default";
				}
				

				//pageContext.setAttribute("appengineName", appengineName);
				UserService userService = UserServiceFactory.getUserService();
		    	User user = userService.getCurrentUser();
				
		    	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
				Key appengineKey = KeyFactory.createKey("AppEngine", appengineName);
				
				Query query_date = new Query("Greeting", appengineKey).addSort("date", Query.SortDirection.DESCENDING);
				//query_date = query_date.addFilter("date", Query.FilterOperator.GREATER_THAN, pastDate);
				
				List<Entity> greetings = datastore.prepare(query_date).asList(FetchOptions.Builder.withOffset(0));
				
				for (int i = 0; i < greetings.size(); i++){
					String d = greetings.get(i).getProperty("date").toString();
					LocalDateTime blogDate = LocalDateTime.parse(d, dtf);
					if (blogDate.isBefore(pastDate)) {
						greetings.remove(i);
						i--;
					}	
					
				}
				
				Query query_1 = new Query("Subscriber", appengineKey);
				List<Entity> subscribers = datastore.prepare(query_1).asList(FetchOptions.Builder.withOffset(0));
					
			    for (Entity subscriber: subscribers){
			    	String strTo= subscriber.getProperty("user").toString();
			    	System.out.println(strTo);
					
					//Extract out the To, Subject and Body of the Email to be sent
					//String strSubject = req.getParameter("email_subject");
					//String strBody = req.getParameter("content");
					
					//Do validations here. Only basic ones i.e. cannot be null/empty
					//Currently only checking the To Email field
					
					//Call the Email Service
					Properties props = new Properties();
					Session session = Session.getDefaultInstance(props, null);
					Message msg = new MimeMessage(session);
					msg.setFrom(new InternetAddress("esruiz39@gmail.com"));
					msg.addRecipient(Message.RecipientType.TO, new InternetAddress(strTo));
					msg.setSubject("NEW DAILY POSTS");
					msg.setText(greetings.toString() + "PAST DATE: " + past);
					if(greetings.size() == 0) {
						msg.setText("NO NEW POST AS OF " + past);
					}
					Transport.send(msg);
					
					strCallResult = "Success: " + "Email has been delivered.";
					resp.getWriter().println(strCallResult);
					//}
			    }
			}
			catch (Exception ex) {
				strCallResult = "Fail: " + ex.getMessage();
				resp.getWriter().println(strCallResult);
			}
		}
		@Override
		public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doGet(req, resp);
		}
	}

//Eduardo end driving


