package com.loreal.sandbox.server.services;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.loreal.sandbox.client.services.TaggingMailService;

@SuppressWarnings("serial")
public class TaggingMailServiceImpl extends RemoteServiceServlet implements TaggingMailService {

	@Override
	public void sendMail(String mail) {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		String msgBody = "...";

		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("thomas.bianconi@loreal.com", "LOREAL Sand Box Admin"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(mail, "Mr. User"));
			msg.setSubject("Your test tagging mail");
			msg.setText(msgBody);

			// [START multipart_example]
			// @formatter:off
			String htmlBody = "<p><span>Hi,</span></p>"
					+ "<p><span></span></p>"
					+ "<p><span>This email is a test for EmailTagging.</span></p>"
					+ "<p><span></span></p>"
					+ "<p><span>Thanks.</span></p>"
					+ "<p><span></span></p>"
					+ "<p><span>Regards.</span></p>"
					+ "<p><span></span></p>"
					+ "<p><span>Thomas Bianconi</span></p>"
					+ "<p><span>DIGITAL PROJECT MANAGER / TECHNICAL EXPERT FULL STACK</span></p>"
					+ "<p><span>Direction du Digital Groupe</span></p>"
					+ "<p><span>41 Rue Martre - 92110 Clichy</span></p>"
					+ "<p><span>Mob : ...</span></p>"
					+ "<p><span>Phone : +33 (0) 1 47 56 45 95</span></p>"
					+ "<p><span>E-mail : thomas.bianconi@loreal.com</span></p>"
					+ "<img src='http://www.google-analytics.com/collect?v=1&t=event&tid=UA-78569407-1&cid=5d5d918d-1f26-483d-9059-9516d4d48227&uid=12345678&ec=email&ea=open&el=Campaign_Name&dh=loreal-sand-box.appspot.com&dp=/email/campaignname&cn=Campaign_Name&cs=newsletter&cc=Campaign_Content&cm=EML_IMP&cd12=12za22eer456rssqssq565447eezzeaeds45d4sd&cd33=455&il1nm=email&il1pi1id=75889&il1pi1nm=Productname&z=random_number'/>";
			// @formatter:on
			Multipart mp = new MimeMultipart();

			MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(htmlBody, "text/html");
			mp.addBodyPart(htmlPart);

			msg.setContent(mp);
			// [END multipart_example]

			Transport.send(msg);

		} catch (AddressException e) {
			// ...
		} catch (MessagingException e) {
			// ...
		} catch (UnsupportedEncodingException e) {
			// ...
		}
	}

}
