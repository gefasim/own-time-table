package com.example.vadim.owntimetable;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;

import org.jsoup.Jsoup;

import java.util.concurrent.ExecutionException;

import Repositories.FakeRepository;
import Repositories.IRepository;

/**
 * Created by vadim on 9/2/16.
 */
public class main_layout extends AppCompatActivity {

    CreateListLessons listLessons;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);


        HttpHtmlAsyncGetter own_api = new HttpHtmlAsyncGetter();
        String result = null;
        try {   result = own_api.execute().get();   }
            catch (InterruptedException | ExecutionException e) {   e.printStackTrace();    }

        listLessons = new CreateListLessons(result);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        RecyclerAdapter mAdapter = new RecyclerAdapter(listLessons.mainCreateList());
        mRecyclerView.setAdapter(mAdapter);

    }

}
