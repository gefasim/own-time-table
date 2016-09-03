package com.example.vadim.owntimetable;

import android.provider.DocumentsContract;

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
        Document tempDoc = Jsoup.parse(doc.body().getElementsByClass("container").toString());
        //return doc.body().getElementsByClass("container").toString();
        return tempDoc.body().getElementsByClass("row").toString();
    }
}
