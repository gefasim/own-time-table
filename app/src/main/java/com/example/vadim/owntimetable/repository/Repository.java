package com.example.vadim.owntimetable.repository;

import android.content.Context;
import android.os.Environment;
import android.util.JsonReader;

import com.example.vadim.owntimetable.HttpHtmlAsyncGetter;
import com.example.vadim.owntimetable.models.Lesson;
import com.example.vadim.owntimetable.models.TimePeriod;
import com.example.vadim.owntimetable.models.TimeTableDayModel;
import com.example.vadim.owntimetable.models.TrueTimeTable;
import com.example.vadim.owntimetable.parser.HtmlParser;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by root on 9/22/16.
 */
public class Repository {
    private List<TimeTableDayModel> listFor_RecyclerView;
    private List<TrueTimeTable> trueTimeTables;
    private HtmlParser htmlParser = new HtmlParser();

    public List<TrueTimeTable> getValueFromServer(TimePeriod period) {
        HttpHtmlAsyncGetter own_api = new HttpHtmlAsyncGetter(period);
        try {
            String result = own_api.execute().get();
            trueTimeTables = htmlParser.timeTableParser(result);
            return trueTimeTables;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void makeListFor_Recycler() {
        listFor_RecyclerView = new ArrayList<>();
        for(TrueTimeTable table : trueTimeTables){
            for (Lesson lesson : table.getLessons()) {
                listFor_RecyclerView.add(new TimeTableDayModel(lesson.getLessonTime(), lesson.getLessonName()));
            }
        }
    }

    public List<TimeTableDayModel> getListFor_RecyclerView() {
        return listFor_RecyclerView;
    }

    public void writeToJSON() {
        Gson gson = new Gson();
        try {
            String fpath = Environment.getDataDirectory()+"OwnTimeTable.json";

            File file = new File(fpath);
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(gson.toJson(trueTimeTables));
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
