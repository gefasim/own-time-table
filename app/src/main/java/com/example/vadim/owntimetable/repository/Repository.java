package com.example.vadim.owntimetable.repository;

import android.content.Context;
import android.util.Log;

import com.example.vadim.owntimetable.HttpHtmlAsyncGetter;
import com.example.vadim.owntimetable.models.Lesson;
import com.example.vadim.owntimetable.models.TimePeriod;
import com.example.vadim.owntimetable.models.TimeTableDayModel;
import com.example.vadim.owntimetable.models.TrueTimeTable;
import com.example.vadim.owntimetable.parser.HtmlParser;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by root on 9/22/16.
 *
 */
public class Repository {
    private List<TimeTableDayModel> lessonList;
    private List<TrueTimeTable> trueTimeTables;
    private HtmlParser htmlParser = new HtmlParser();
    private File file;
    private Context context;

    public Repository(Context context) {
        this.context = context;
        file = new File(context.getFilesDir(), "OwnTimeTable.json");
    }

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
        lessonList = new ArrayList<>();
        for(TrueTimeTable table : trueTimeTables){
            for (Lesson lesson : table.getLessons()) {
                lessonList.add(new TimeTableDayModel(lesson.getLessonTime(), lesson.getLessonName()));
            }
        }
    }

    public List<TimeTableDayModel> getListFor_RecyclerView() {
        return lessonList;
    }

    public void writeToJSON() {
        Gson gson = new Gson();
        try {
            Log.v(".JSON path ", file.getAbsolutePath());
            FileWriter fw = new FileWriter(file);
            fw.append(gson.toJson(trueTimeTables));
            fw.flush();
            fw.close();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<TimeTableDayModel> getFromJSON() {
        lessonList = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            JsonParser jsonParser = new JsonParser();
            JsonArray array = jsonParser.parse(reader.readLine()).getAsJsonArray();

            TrueTimeTable timeTable;
            Gson gson = new Gson();
            for (int i = 0; i < array.size(); i++) {
                timeTable = gson.fromJson(array.get(i), TrueTimeTable.class);
                for (Lesson item: timeTable.getLessons()) {
                    lessonList.add(new TimeTableDayModel(
                            item.getLessonTime(),
                            item.getLessonName()
                    ));
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return lessonList;
    }
}
