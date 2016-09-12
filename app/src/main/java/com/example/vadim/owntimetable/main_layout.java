package com.example.vadim.owntimetable;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.vadim.owntimetable.Object.TimeTable_day;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by vadim on 9/2/16.
 */
public class main_layout extends AppCompatActivity {
    private TextView mainTextView;
    CreateListLessons listLessons;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        mainTextView = (TextView)findViewById(R.id.mainTextView);
        mainTextView.setMovementMethod(new ScrollingMovementMethod());
    }

    public void onclick_TimeButton(View v) throws ExecutionException, InterruptedException {
        ApiManager ownapi = new ApiManager();
        String result = ownapi.execute().get();
        LocalParser localParser = new LocalParser(Jsoup.parse(result)); //

        Log.e("sass", "Sasha21");
        Log.e("doc", String.valueOf(result.length()));
        //Log.e("doc", result);


        listLessons = new CreateListLessons(localParser.getTimeTable());
        List<TimeTable_day> tempList =  listLessons.mainCreateList();   // print
        String temp = "";                                               // print

        for (TimeTable_day tableDay : tempList){
            temp += tableDay.getLesson_time() + " ";
            temp += tableDay.getLesson_name() + "\n";
        }


        mainTextView.setText( temp ); // print


    }
}
