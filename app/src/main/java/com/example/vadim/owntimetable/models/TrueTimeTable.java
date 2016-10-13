package com.example.vadim.owntimetable.models;

import java.util.List;

/**
 * Created by vov96 on 13.10.2016.
 */

public class TrueTimeTable {
    private int day_of_year;
    private String day_of_week;
    List<Lesson> lessons;

    public TrueTimeTable(int day_of_year, String day_of_week, List<Lesson> lessons) {
        this.day_of_year = day_of_year;
        this.day_of_week = day_of_week;
        this.lessons = lessons;
    }

    public int getDay_of_year() {
        return day_of_year;
    }

    public String getDay_of_week() {
        return day_of_week;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }
}
