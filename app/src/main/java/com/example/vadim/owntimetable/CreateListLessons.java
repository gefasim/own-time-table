package com.example.vadim.owntimetable;

import com.example.vadim.owntimetable.Model.Lesson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vov96 on 12.09.2016.
 */
public class CreateListLessons {
    List<Lesson> timeTableDays = new ArrayList<>();


    String lessons;

    public CreateListLessons(String lessons) {
        this.lessons = lessons;
    }

    List<Lesson> mainCreateList(){
        getFormatText();

        return pushToList(getFormatText());
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

    private String[] getFormatText() {
        String[] secondArr = lessons.split(" ");
        String formatedText = "";
        String[] formatedTextArr;
        for (int i = 0; i < secondArr.length; i++) {
            //System.out.print(secondArr[i] + " ");
            formatedText += secondArr[i] + " " ;
            if (i + 1 == secondArr.length) {
                formatedTextArr = formatedText.split("\n");
                System.out.println(Arrays.toString(formatedTextArr));
                return formatedTextArr;

            }
            try {
                if (secondArr[i + 1].toCharArray()[2] == ':') {
                    //System.out.println();
                    formatedText += "\n" ;
                }
            } catch (Exception ignored){}
        }
        return null;
    }
}
