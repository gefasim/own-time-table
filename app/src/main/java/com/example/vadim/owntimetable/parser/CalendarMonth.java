package com.example.vadim.owntimetable.parser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by vov96 on 06.10.2016.
 *
 */
public class CalendarMonth {
    private List<Integer> calendarList = new ArrayList<>();
    {
        calendarList.add(Calendar.JANUARY);
        calendarList.add(Calendar.FEBRUARY);
        calendarList.add(Calendar.MARCH);
        calendarList.add(Calendar.APRIL);
        calendarList.add(Calendar.MAY);
        calendarList.add(Calendar.JUNE);
        calendarList.add(Calendar.JULY);
        calendarList.add(Calendar.AUGUST);
        calendarList.add(Calendar.SEPTEMBER);
        calendarList.add(Calendar.OCTOBER);
        calendarList.add(Calendar.NOVEMBER);
        calendarList.add(Calendar.DECEMBER);
    }

    public List<Integer> getMonth() {
        return calendarList;
    }
}
