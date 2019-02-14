package com.krokodeal.util;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;

import static com.krokodeal.pojos.ParserFields.getFinalHtmlString;
import static com.krokodeal.pojos.EmailData.getLogin;
import static com.krokodeal.pojos.EmailData.getPersonalId;

public class EmailUtil {

    /**
     * Utility method to send simple HTML email
     *
     * @param session
     * @param toEmail
     * @param subject
     * @param body
     */
    public static void sendEmail(Session session, String toEmail, String subject, String body) {
        try {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("deliveryStatus", getLogin());
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "base64");

            msg.setFrom(new InternetAddress(getLogin(), getPersonalId()));

            msg.setReplyTo(InternetAddress.parse(getLogin(), false));

            msg.setSubject(subject, "UTF-8");

            msg.setText(body, "UTF-8");
            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            msg.setDataHandler(new DataHandler(new HTMLDataSource(getFinalHtmlString())));
            System.out.println("Message is ready");
            Transport.send(msg);

            System.out.println("EMail Sent Successfully!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}