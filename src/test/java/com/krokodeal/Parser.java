package com.krokodeal;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static org.junit.Assert.assertEquals;


public class Parser {


    private static String search_text = "перчатки";
    private static String created_to = "29.03.2019";
    private static String from_date = "30.11.2018";

    @Test
    public void testUTF8_to_URL() {
        String search_text_forURL = null;
        try {
            search_text_forURL = URLEncoder.encode(search_text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println("Unsupported encoding!!!");
        }
        assertEquals("%D0%BF%D0%B5%D1%80%D1%87%D0%B0%D1%82%D0%BA%D0%B8", search_text_forURL);
    }
    @Test
    public void testURLString() {
        String search_text_forURL = null;
        try {
            search_text_forURL = URLEncoder.encode(search_text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println("Unsupported encoding!!!");
        }
        assertEquals("%D0%BF%D0%B5%D1%80%D1%87%D0%B0%D1%82%D0%BA%D0%B8", search_text_forURL);
        String query = "http://www.icetrade.by/search/auctions?" +
                "search_text="+ search_text_forURL + "&zakup_type%5B1%5D=1" +
                "&zakup_type%5B2%5D=1&auc_num=&okrb=&company_title=" +
                "&establishment=0&industries=&period=&created_from=" + from_date + "&created_to=" +created_to+"" +
                "&request_end_from=&request_end_to=&t%5BTrade%5D=1&t%5BeTrade%5D=1&t%5BsocialOrder%5D=1" +
                "&t%5BsingleSource%5D=1&t%5BAuction%5D=1&t%5BRequest%5D=1&t%5BcontractingTrades%5D=1" +
                "&t%5Bnegotiations%5D=1&t%5BOther%5D=1&r%5B1%5D=1&r%5B2%5D=2&r%5B7%5D=7&r%5B3%5D=3" +
                "&r%5B4%5D=4&r%5B6%5D=6&r%5B5%5D=5&sort=num%3Adesc&sbm=1&onPage=20";

        assertEquals("http://www.icetrade.by/search/auctions?" +
                "search_text=%D0%BF%D0%B5%D1%80%D1%87%D0%B0%D1%82%D0%BA%D0%B8" +
                "&zakup_type%5B1%5D=1&zakup_type%5B2%5D=1&auc_num=&okrb=&company_title=" +
                "&establishment=0&industries=&period=&created_from=30.11.2018&created_to=29.03.2019" +
                "&request_end_from=&request_end_to=&t%5BTrade%5D=1&t%5BeTrade%5D=1&t%5BsocialOrder%5D=1" +
                "&t%5BsingleSource%5D=1&t%5BAuction%5D=1&t%5BRequest%5D=1&t%5BcontractingTrades%5D=1" +
                "&t%5Bnegotiations%5D=1&t%5BOther%5D=1&r%5B1%5D=1&r%5B2%5D=2&r%5B7%5D=7&r%5B3%5D=3" +
                "&r%5B4%5D=4&r%5B6%5D=6&r%5B5%5D=5&sort=num%3Adesc&sbm=1&onPage=20", query);
    }
}

