package com.example.vadim.owntimetable.parser;


import com.example.vadim.owntimetable.models.Lesson;
import com.example.vadim.owntimetable.models.TrueTimeTable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by root on 9/22/16.
 *
 */
public class HtmlParser {

    private Calendar calendar = Calendar.getInstance();
    private CalendarMonth monthList = new CalendarMonth();
    private ArrayList<TrueTimeTable> trueTimeTables = new ArrayList<>();

    public ArrayList<TrueTimeTable> timeTableParser(String html) {

        List<Lesson> lessonList;

        Elements tempDoc = Jsoup.parse(html).select(".container .container .col-md-6");
        for (Element tables: tempDoc) {
            lessonList = new ArrayList<>();
            Elements table = tables.select("tr");
            Elements h4 = tables.select("h4");
            for (Element row: table) {
                Elements td = row.children();
                lessonList.add(new Lesson(
                        td.get(0).text(), td.get(1).text()
                ));
            }
            trueTimeTables.add(new TrueTimeTable(
                    toIntDayOfYear(h4.text()),
                    h4.text(),
                    lessonList
            ));
        }

        //show list
        /*for (TrueTimeTable tr: trueTimeTables) {
            Log.v("OpaAA",  "\t\t\t"+tr.getDay_of_year()+" --> "+tr.getDay_of_week());
            for (Lesson les: tr.getLessons()) {
                Log.v("OpaAA",les.getLessonTime() +"\t"+les.getLessonName());
            }
        }
*/
        return trueTimeTables;
    }

    // date  03.10.2016 Понеділок
    private int toIntDayOfYear(String s){
        int year = Integer.valueOf(s.split(" ")[0].split("\\.")[2]);
        int month = Integer.valueOf(s.split(" ")[0].split("\\.")[1]);
        int day = Integer.valueOf(s.split(" ")[0].split("\\.")[0]);

        calendar.set(year, monthList.getMonth().get(month-1), day);
        return calendar.get(Calendar.DAY_OF_YEAR);
    }

}
