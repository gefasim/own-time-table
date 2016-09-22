package com.example.vadim.owntimetable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import java.util.concurrent.ExecutionException;

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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new RecyclerAdapter(listLessons.mainCreateList()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.gefasim:
                Toast.makeText(getApplicationContext(), "Alex best of the best", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.vlmcr:
                Toast.makeText(getApplicationContext(), "Pidor", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.new_game:
                Intent intent = new Intent(getApplicationContext(), TestActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
