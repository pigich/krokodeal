package com.krokodeal.util;

import com.krokodeal.pojos.EmailData;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;

import static com.krokodeal.pojos.EmailData.getSubject;
import static com.krokodeal.pojos.ParserFields.*;

public class EmailUtil {
    private EmailData emailData = new EmailData();

    /**
     * Utility method to send simple HTML email
     *
     * @param session  new email session
     * @param toEmail  email where to send message
     * @param subject  the subject of email
     * @param body     message body
     */
    private void emailProps(Session session, String toEmail, String subject, String body) {

        try {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("deliveryStatus", emailData.getLogin());
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "base64");

            msg.setFrom(new InternetAddress(emailData.getLogin(), emailData.getPersonalId()));

            msg.setReplyTo(InternetAddress.parse(emailData.getLogin(), false));

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

    public void sendEmail() {
        try {
            setParsedHtmlString();
            System.out.println(getParsedHtmlString());
        } catch (IOException e) {
            System.out.println(" Can not parse IceTrade");
        }
        setFinalHtmlString();
        System.out.println(getFinalHtmlString());
//        listOfUEmails.add("mybigoblako@gmail.com");
//        if (listOfUEmails.size() > 0) {
//            for (String toEmail : listOfUEmails
//                    ) {
        System.out.println("SSLEmail Start");
        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailData.getLogin(), emailData.getPassword());
            }
        };
        Session session = Session.getDefaultInstance(emailData.getProps(), auth);
        System.out.println("Session created");
        emailProps(session, emailData.getEmailWhomToSend(), getSubject(), getFinalHtmlString());
//            }
//            listOfUEmails.clear();
//        }
    }
}