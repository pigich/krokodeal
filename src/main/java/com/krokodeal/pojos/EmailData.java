package com.krokodeal.pojos;

import java.util.Properties;

public class EmailData {


    private  String login ="junit01@mail.ru";
    private  String password ="bCgmwpmk9dwGAct";
    private  String personalId ="test_123";
    private  String emailWhomToSend ="mybigoblako@gmail.com";
    private static String subject ="";
    private  Properties props;

    public EmailData() {
        this.login = login;
        this.password = password;
        String smtpMailProvider = "smtp." + login.substring(login.indexOf("@") + 1, login.length());
        props = System.getProperties();
        props.put("mail.smtp.host", smtpMailProvider); //SMTP Host
        props.put("mail.debug", "true");
        props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
        props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
        props.put("mail.smtp.port", "465"); //SMTP Port
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.ssl.trust", smtpMailProvider);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public String getEmailWhomToSend() {
        return emailWhomToSend;
    }

    public void setEmailWhomToSend(String emailWhomToSend) {
        this.emailWhomToSend = emailWhomToSend;
    }

    public static String getSubject() {
        return subject;
    }

    public static void setSubject(String subject) {
        EmailData.subject = subject;
    }

    public Properties getProps() {
        return props;
    }

    public void setProps(Properties props) {
        this.props = props;
    }

}
