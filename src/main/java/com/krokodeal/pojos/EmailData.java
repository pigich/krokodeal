package com.krokodeal.pojos;

import java.util.Properties;

public class EmailData {


    private static String login ="junit01@mail.ru";
    private static String password ="bCgmwpmk9dwGAct";
    private static String personalId ="test_123";
    private static String subject ="";
    private static Properties props;

    public EmailData(String login, String password) {
        EmailData.login = login;
        EmailData.password = password;
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

    public static String getSubject() {
        return subject;
    }

    public static void setSubject(String subject) {
        EmailData.subject = subject;
    }

    public static String getPersonalId() {
        return personalId;
    }

    public static void setPersonalId(String personalId) {
        EmailData.personalId = personalId;
    }

    public Properties getProps() {
        return props;
    }

    public static String getLogin() {
        return login;
    }

    public static void setLogin(String login) {
        EmailData.login = login;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        EmailData.password = password;
    }

}
