package com.example.vadim.owntimetable;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.example.vadim.owntimetable.Object.TimeTable_day;

import org.jsoup.Jsoup;

import java.util.List;
import java.util.concurrent.ExecutionException;

import Repositories.FakeRepository;
import Repositories.IRepository;

/**
 * Created by vadim on 9/2/16.
 */
public class main_layout extends AppCompatActivity {
    private TextView mainTextView;
    CreateListLessons listLessons;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        IRepository repository = new FakeRepository();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        mainTextView = (TextView)findViewById(R.id.mainTextView);

        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);

        OwnTimeAdapter adapter = new OwnTimeAdapter(repository.getCard());
        rv.setAdapter(adapter);

    }

    public void onclick_TimeButton(View v) throws ExecutionException, InterruptedException {
        HttpHtmlAsyncGetter ownapi = new HttpHtmlAsyncGetter();
        String result = ownapi.execute().get();
        HtmlParser localParser = new HtmlParser(Jsoup.parse(result));

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
