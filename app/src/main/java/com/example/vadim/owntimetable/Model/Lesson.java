package com.example.vadim.owntimetable.Model;

public class Lesson {
    private String lesson_time;
    private String lesson_name;

    public Lesson(String lesson_time, String lesson_name) {
        this.lesson_time = lesson_time;
        this.lesson_name = lesson_name;
    }

    public String getLesson_time() {
        return lesson_time;
    }

    public String getLesson_name() {
        return lesson_name;
    }
}
