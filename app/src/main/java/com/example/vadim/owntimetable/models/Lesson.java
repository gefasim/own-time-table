package com.example.vadim.owntimetable.models;

/**
 * Created by vov96 on 13.10.2016.
 *
 */
public class Lesson {
    private String lessonTime;
    private String lessonName;

    public Lesson(String lessonTime, String lessonName) {
        this.lessonTime = lessonTime;
        this.lessonName = lessonName;
    }

    public String getLessonTime() {
        return lessonTime;
    }

    public String getLessonName() {
        return lessonName;
    }
}
