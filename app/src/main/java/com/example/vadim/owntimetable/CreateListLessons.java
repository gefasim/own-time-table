package com.example.vadim.owntimetable;

import com.example.vadim.owntimetable.Model.Lesson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CreateListLessons {
    private List<Lesson> timeTableDays = new ArrayList<>();
    private String lessons;

    CreateListLessons(String lessons) {
        this.lessons = lessons;
    }

    List<Lesson> mainCreateList(){
        getFormatText();
        return pushToList(getFormatText());
    }

    private String[] getFormatText() {
        String[] secondArr = lessons.split(" ");
        String formattedText = "";
        String[] formattedTextArr;
        for (int i = 0; i < secondArr.length; i++) {
            formattedText += secondArr[i] + " " ;
            if (i + 1 == secondArr.length) {
                formattedTextArr = formattedText.split("\n");
                System.out.println(Arrays.toString(formattedTextArr));
                return formattedTextArr;
            }
            try {
                if (secondArr[i + 1].toCharArray()[2] == ':') {
                    formattedText += "\n" ;
                }
            } catch (Exception ignored){}
        }
        return null;
    }

    private List<Lesson> pushToList(String[] formatText) {
        for (String lesson : formatText){
            if (lesson.trim().length() < 12) {
                timeTableDays.add(new Lesson(lesson, ""));
            } else {
                System.out.println("writeLessonName-- "+ writeLessonName(lesson));
                timeTableDays.add(new Lesson(writeDate(lesson), writeLessonName(lesson)));
            }
        }
        return timeTableDays;
    }

    private static String writeLessonName(String lesson) {
        char[] lessonName = lesson.toCharArray();
        String lessonString = "";

        for (int i = 12; i<lessonName.length; i++){
            lessonString +=  lessonName[i];
        }
        return lessonString;
    }

    private static String writeDate(String lesson) {
        char[] date = lesson.toCharArray();
        String dataString = "";

        for (int i =0; i<11; i++){
            dataString +=  date[i];
        }
        return dataString;
    }
}
