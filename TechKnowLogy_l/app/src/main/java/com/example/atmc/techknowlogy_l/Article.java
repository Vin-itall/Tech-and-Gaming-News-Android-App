package com.example.atmc.techknowlogy_l;

/**
 * Created by ATMC on 01-05-2017.
 */

public class Article {

    String date = new String();
    String explanation = new String();
    String url = new String();


    public Article(String d, String e, String u)
    {
        date = d;
        explanation = e;
        url = u;
    }

    public String getHumanDate() {
        return date;
    }

    public String getExplanation() {
    return explanation;
    }

    public String getUrl() {

        return url;

    }
}
