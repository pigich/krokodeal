package com.krokodeal.pojos;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ParserFields {

    public static File f;
    public static String src = System.getProperty("user.dir");
    public static List<String> listOfUEmails = new ArrayList<>();

    private static String parsedString = "";
    private static String search_text = "перчатки";

    private static String html = "";
    private static String from_date = "30.11.2018";
    private static String created_to = "29.03.2019";
    private static String entriesOnPage = "30";
    private static String auc_num = "";
    private static String okrb = "";
    private static String company_title = "";
    private static String period = "";
    private static String request_end_from = "";
    private static String request_end_to = "";

    public ParserFields() {
    }

    public static String getSearch_text() {
        return search_text;
    }

    public static void setSearch_text(String search_text) {
        ParserFields.search_text = search_text;
    }

    public static String getFrom_date() {
        return from_date;
    }

    public static void setFrom_date(String s) {
        ParserFields.from_date = s;
    }

    public static void setFrom_date(LocalDate date) {
        ParserFields.from_date = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String getCreated_to() {
        return created_to;
    }

    public static void setCreated_to(String s) {
        ParserFields.created_to = s;
    }

    public static void setCreated_to(LocalDate date) {
        ParserFields.created_to = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String getRequest_end_to() {
        return request_end_to;
    }

    public static void setRequest_end_to(LocalDate date) {
        ParserFields.request_end_to = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String getEntriesOnPage() {
        return entriesOnPage;
    }

    public static void setEntriesOnPage(String entriesOnPage) {
        ParserFields.entriesOnPage = entriesOnPage;
    }

    public static String getAuc_num() {
        return auc_num;
    }

    public static void setAuc_num(String auc_num) {
        ParserFields.auc_num = auc_num;
    }

    public static String getOkrb() {
        return okrb;
    }

    public static void setOkrb(String okrb) {
        ParserFields.okrb = okrb;
    }

    public static String getCompany_title() {
        return company_title;
    }

    public static void setCompany_title(String company_title) {
        ParserFields.company_title = company_title;
    }

    public static String getPeriod() {
        return period;
    }

    public static void setPeriod(String period) {
        ParserFields.period = period;
    }

    public static String getRequest_end_from() {
        return request_end_from;
    }

    public static void setRequest_end_from(LocalDate date) {
        ParserFields.request_end_from = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String getFinalHtmlString() {
        return html;
    }

    public static void setFinalHtmlString() {
        //todo: transferred data is not encrypted.
        html = "<html><head><style>\n" +
                "table.auctions th {border: 2px solid #dcddde;\n" +
                "padding: 10px;}\n" +
                "th {font-weight: bold; background: #d1d5d9; text-align: -internal-center;}\n" +
                "td, th {display: table-cell;\n" +
                "border: 1px solid grey;" +
                "vertical-align: inherit;}\n" +
                "table {border-collapse: collapse; border-spacing: 0;}\n" +
                "table {display: table; border-collapse: collapse;\n" +
                "border-color: grey;}\n" +
                "td {vertical-align: top;}\n" +
                "#content td {padding: 5px 10px 7px 10px;}\n" +
                "table.auctions td.lst {border-left: 1px solid #ececec;}\n" +
                "table.auctions td.top {padding-top: 18px;\n" +
                "border: 1px solid #ececec;}\n" +
                "table.auctions td {padding: 10px 15px; vertical-align: top;}\n" +
                "* {margin: 0; padding: 1;}\n" +
                "</style></head> <body><div>\n" +
                getParsedHtmlString() +
                "</div></body></html>";
    }

    public static String getParsedHtmlString() {
        return ParserFields.parsedString;
    }

    public static void setParsedHtmlString() throws IOException {
        Document document = Jsoup.connect(getUrlQuery())
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .get();
        Element contentOfTable = document.getElementById("auctions-list");
        ParserFields.parsedString = contentOfTable.toString();
    }

    private static String getConvertedSearchText() throws UnsupportedEncodingException {
        return URLEncoder.encode(getSearch_text(), "UTF-8");
    }

    private static String getUrlQuery() throws UnsupportedEncodingException {
        String search_text_forURL = getConvertedSearchText();
        return "http://www.icetrade.by/search/auctions?" +
                "search_text=" + search_text_forURL +
                "&zakup_type%5B1%5D=1" +
                "&zakup_type%5B2%5D=1" +
                "&auc_num=" + getAuc_num() +
                "&okrb=" + getOkrb() +
                "&company_title=" + getCompany_title() +
                "&establishment=0&industries=&period=" + getPeriod() +
                "&created_from=" + getFrom_date() +
                "&created_to=" + getCreated_to() +
                "&request_end_from=" + getRequest_end_from() +
                "&request_end_to=" + getRequest_end_to() +
                "&t%5BTrade%5D=1&t%5BeTrade%5D=1&t%5BsocialOrder%5D=1" +
                "&t%5BsingleSource%5D=1&t%5BAuction%5D=1&t%5BRequest%5D=1&t%5BcontractingTrades%5D=1" +
                "&t%5Bnegotiations%5D=1&t%5BOther%5D=1&r%5B1%5D=1&r%5B2%5D=2&r%5B7%5D=7&r%5B3%5D=3" +
                "&r%5B4%5D=4&r%5B6%5D=6&r%5B5%5D=5&sort=num%3Adesc&sbm=1&onPage=" + getEntriesOnPage() + "";
    }

    public enum PeriodEnum {
        ZERO("Все"),
        ONEDAY("1 день"),
        THREEDAYS("3 дня"),
        ONEWEEK("1 неделя"),
        TWOWEEKS("2 недели"),
        ONEMONTH("1 месяц");

        private String period;

        PeriodEnum(String period) {
            this.period = period;
        }

        public String toString() {
            return period;
        }
    }

}


