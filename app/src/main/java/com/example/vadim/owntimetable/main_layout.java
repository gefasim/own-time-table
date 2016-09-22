package com.example.vadim.owntimetable;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;

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

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        RecyclerAdapter mAdapter = new RecyclerAdapter(listLessons.mainCreateList());
        mRecyclerView.setAdapter(mAdapter);

    }

}
