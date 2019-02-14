package com.krokodeal;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.net.URLEncoder;

import static com.krokodeal.pojos.ParserFields.*;

public class Parser {


    public static void main(String[] args) throws Exception {
        String search_text_forURL = URLEncoder.encode(getSearch_text(), "UTF-8");
        String query = "http://www.icetrade.by/search/auctions?" +
                "search_text=" + search_text_forURL +
                "&zakup_type%5B1%5D=1" +
                "&zakup_type%5B2%5D=1" +
                "&auc_num=" + getAuc_num() +
                "&okrb=" + getOkrb()  +
                "&company_title=" + getCompany_title() + "" +
                "&establishment=0&industries=&period=" + getPeriod() +
                "&created_from=" + getFrom_date() +
                "&created_to=" + getCreated_to() + "" +
                "&request_end_from=" + getRequest_end_from() +
                "&request_end_to=" + getRequest_end_to() +
                "&t%5BTrade%5D=1" +
                "&t%5BeTrade%5D=1" +
                "&t%5BsocialOrder%5D=1" +
                "&t%5BsingleSource%5D=1" +
                "&t%5BAuction%5D=1" +
                "&t%5BRequest%5D=1" +
                "&t%5BcontractingTrades%5D=1" +
                "&t%5Bnegotiations%5D=1" +
                "&t%5BOther%5D=1" +
                "&r%5B1%5D=1" +
                "&r%5B2%5D=2" +
                "&r%5B7%5D=7" +
                "&r%5B3%5D=3" +
                "&r%5B4%5D=4" +
                "&r%5B6%5D=6" +
                "&r%5B5%5D=5" +
                "&sort=num%3Adesc" +
                "&sbm=1&onPage=" + getEntriesOnPage() + "";

        Document document = Jsoup.connect(query)
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .get();
        Element contentOfTable = document.getElementById("auctions-list");
        System.out.println(contentOfTable.toString());
    }
}
