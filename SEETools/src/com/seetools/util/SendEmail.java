package com.seetools.util;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
 
 
 
/**
 * 
 * @author Atif Imran 2013
 * @blog codehunk.blogspot.in
 * @website www.strizen.com
 *
 */
 
public class SendEmail {
 private String SMTP_HOST = "smtp.gmail.com";  
 private String FROM_ADDRESS = "ramprasad.pedapatnam@gmail.com";  
 private String FROM_PASSWORD = "laksh789*";
  
 private String FROM_NAME = "SEETOOLS Registration Email Verification";  
 private String SERVER_URL = "http://localhost:8080/EmailServer/EmailServlet?";  
 
 
 //PasswordAuthentication validates the user at first
 class AuthorizeEmail extends Authenticator {  
  @Override 
  protected PasswordAuthentication getPasswordAuthentication() {  
   return new PasswordAuthentication(FROM_ADDRESS, FROM_PASSWORD);  
  }  
 }
 
 public static void main(String[] args){
  SendEmail se = new SendEmail();
  //se.sendEmail();
 }
 
 public void sendEmail(String emailAddress, String token, String mode){
  //verify a user before registering
 
  // System properties
  Properties props = new Properties();  
 
  // Setup our mail server
  props.put("mail.smtp.host", SMTP_HOST);  
  props.put("mail.smtp.user",FROM_NAME); 
  props.put("mail.smtp.ssl.enable", "true"); 
  props.put("mail.smtp.port", "25"); 
  props.put("mail.debug", "true"); 
  props.put("mail.smtp.auth", "true"); 
  props.put("mail.smtp.starttls.enable","true"); 
  props.put("mail.smtp.EnableSSL.enable","true");
  props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
  props.setProperty("mail.smtp.socketFactory.fallback", "false");   
  props.setProperty("mail.smtp.port", "465");   
  props.setProperty("mail.smtp.socketFactory.port", "465"); 
 
 
 
  // Session object.
  Session session = Session.getInstance(props, new AuthorizeEmail());  
 
  try{
   // Get the default Mime object.
   MimeMessage message = new MimeMessage(session);//if only text
   // Set our FromAddress.
   message.setFrom(new InternetAddress(FROM_ADDRESS));
   // Set our ToAddress.
   message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));
   // Set our subject for the mail.
   
   if(mode.equals("REGISTER")){
	   message.setSubject("SEE TOOLS Email Verification Test");
   } else if(mode.equals("FORGOT_PASSWORD")){
	   message.setSubject("SEE TOOLS Password Recovery Email");
   }
   
 
 
   // Now set the actual message
   String verificationID= "verificationID=100";
  // String htmlMessageContent = "<h3 align='center'> In order to proceed with CodeHunk registration click the link.</h3> <h4 align='center'" + 
		   //"background-color:cyan=""> Verification link: <a href="http://www.blogger.com/" verificationid="">Click to verify your email</a></h4>";
  
   
   //for the moment we comment out below line
   //message.setText(htmlMessageContent, "text/html");
   //set "text/plain" if you don't need html in your message            
 
   // Create the message part for SENDING IMAGE
   // This HTML mail have to 2 part, the BODY and the embedded image
   MimeMultipart multipart = new MimeMultipart("related");
 
   // first part  (the html)
   BodyPart messageBodyPart = new MimeBodyPart();
   
   messageBodyPart.setContent(this.createHtmlText(emailAddress, token, mode), "text/html");
 
   // add it
   multipart.addBodyPart(messageBodyPart);
 
/*   // second part (the image)
   messageBodyPart = new MimeBodyPart();
   messageBodyPart.setDisposition(MimeBodyPart.INLINE);// show image with msg content
   DataSource dataSource = new FileDataSource("D:\\hello.jpg");
   messageBodyPart.setDataHandler(new DataHandler(dataSource));
   messageBodyPart.setHeader("Content-ID","<img>");
 
   // add it
   multipart.addBodyPart(messageBodyPart);*/
 
   // put everything together
   message.setContent(multipart);
 
   // Send message
   Transport.send(message);
   System.out.println("sent");
  }catch (MessagingException mex) {
   System.out.println("MessagingException: "+mex.getMessage());
   mex.printStackTrace();
  }
 }
 
 
 private String createHtmlText(String emailAddress, String token, String mode){
	 
	 String htmlText = "";
	 
	 if(mode.equals("REGISTER")){
		 String url = "http://localhost:8080/SEETools/xhtml/login/registrationActivation.xhtml?email=" + emailAddress +"&token=" + token;
		   htmlText = "Hello Visitor. Welcome to SEE Tools website. Please click here to activate your registration";
		   htmlText = htmlText + "<html><body><a href="+url+">Click here to complete your registration</a></body></html>";
	 }else if(mode.equals("FORGOT_PASSWORD")){
		 String url = "http://localhost:8080/SEETools/xhtml/login/changePassword.xhtml?email=" + emailAddress +"&token=" + token;
		  htmlText = "Hi, Its tough to remember so many passwords. No worry. You can recover your password by clicking on below link.";
		   htmlText = htmlText + "<html><body><a href="+url+">Click here to recover your password</a></body></html>";
	 }
	 
	 
	 
	 return htmlText;
 }
 
}