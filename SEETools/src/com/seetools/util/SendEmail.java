package com.seetools.util;

import java.util.Properties;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.seetools.framework.exceptions.EmailException;

public class SendEmail {

	private String SMTP_HOST = "smtp.gmail.com";
	private String FROM_ADDRESS = "ramprasad.pedapatnam@gmail.com";
	private String FROM_PASSWORD = "laksh789*";

	private String FROM_NAME = "SEETOOLS Registration Email Verification";

	// PasswordAuthentication validates the user at first
	class AuthorizeEmail extends Authenticator {
		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(FROM_ADDRESS, FROM_PASSWORD);
		}
	}

	public void sendEmail(String toEmailAddress, String token, String mode) throws EmailException {

		final Logger logger = LoggerFactory.getLogger(SendEmail.class);
		Session session = Session.getInstance(getMailServerDetails(), new AuthorizeEmail());

		try {
			logger.info("Sending email message");
			MimeMessage message = new MimeMessage(session);// if only text

			initializeSourceAndDestinationForMessage(message, toEmailAddress);
			addSubjectDetails(message, mode);
			addMessageDetails(message, toEmailAddress, token, mode);

			Transport.send(message);
			logger.info("Successfully sent mail to : {} ", toEmailAddress);

		} catch (MessagingException mex) {
			throw new EmailException("Error while sending email", mex);
			
		} finally{
			
		}
	}

	private void initializeSourceAndDestinationForMessage(MimeMessage message,
			String toEmailAddress) throws MessagingException {
		message.setFrom(new InternetAddress(FROM_ADDRESS));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmailAddress));
	}

	private void addSubjectDetails(MimeMessage message, String mode)
			throws MessagingException {

		if (mode.equals("REGISTER")) {
			message.setSubject("SEE TOOLS Email Verification Test");
		} else if (mode.equals("FORGOT_PASSWORD")) {
			message.setSubject("SEE TOOLS Password Recovery Email");
		}
	}

	private void addMessageDetails(MimeMessage message, String emailAddress,
			String token, String mode) throws MessagingException {

		MimeMultipart multipart = new MimeMultipart("related");
		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(this.createHtmlText(emailAddress, token, mode), "text/html");
		multipart.addBodyPart(messageBodyPart);
		message.setContent(multipart);
	}

	private Properties getMailServerDetails() {

		// System properties
		Properties props = new Properties();

		// Setup our mail server
		props.put("mail.smtp.host", SMTP_HOST);
		props.put("mail.smtp.user", FROM_NAME);
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.port", "25");
		props.put("mail.debug", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.EnableSSL.enable", "true");
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		return props;
	}

	private String createHtmlText(String emailAddress, String token, String mode) {

		String htmlText = "";

		if (mode.equals("REGISTER")) {
			String url = "http://localhost:8080/SEETools/xhtml/login/registrationActivation.xhtml?email=" + emailAddress + "&token=" + token;
			htmlText = "Hello Visitor. Welcome to SEE Tools website. Please click here to activate your registration";
			htmlText = htmlText + "<html><body><a href=" + url + ">Click here to complete your registration</a></body></html>";
		} else if (mode.equals("FORGOT_PASSWORD")) {
			String url = "http://localhost:8080/SEETools/xhtml/login/changePassword.xhtml?email=" + emailAddress + "&token=" + token;
			htmlText = "Hi, Its tough to remember so many passwords. No worry. You can recover your password by clicking on below link.";
			htmlText = htmlText + "<html><body><a href=" + url + ">Click here to recover your password</a></body></html>";
		}

		return htmlText;
	}

	
}