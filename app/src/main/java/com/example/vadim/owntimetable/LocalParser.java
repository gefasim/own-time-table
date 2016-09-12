package com.example.vadim.owntimetable;

import android.provider.DocumentsContract;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Created by vov96 on 03.09.2016.
 */
public class LocalParser {
    Document doc;

    public LocalParser(Document doc) {
        this.doc = doc;
    }


    public String getTimeTable() {
        Document tempDoc = Jsoup.parse(doc.body().getElementsByClass("col-md-6").toString());

        Log.v("H44",tempDoc.body().getElementsByTag("h4").text()); // All Day
        Log.v("tableRow", tempDoc.body().getElementsByTag("table").text()); // All Lessons with date

        // I don`t know what it is
        // tempDoc.body().getElementsByClass("row").toString()

        return tempDoc.body().getElementsByTag("table").text();
    }
}
