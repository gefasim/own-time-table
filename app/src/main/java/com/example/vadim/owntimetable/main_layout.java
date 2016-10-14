package com.example.vadim.owntimetable;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.vadim.owntimetable.fragments.DateRangePickerFragment;
import com.example.vadim.owntimetable.models.TimePeriod;
import com.example.vadim.owntimetable.models.TimeTableDayModel;
import com.example.vadim.owntimetable.repository.Repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * Created by group on 9/2/16.
 *
 */
public class main_layout extends AppCompatActivity implements DateRangePickerFragment.OnDateRangeSelectedListener {
    Repository rep;



    Calendar c = Calendar.getInstance();

    RecyclerView mRecyclerView;
    GridLayoutManager mLayoutManager;
    RecyclerAdapter mAdapter;

    String startPeriod;
    String endPeriod;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        rep = new Repository(getApplicationContext());

        startPeriod = c.get(Calendar.DAY_OF_MONTH) + "." + (c.get(Calendar.MONTH) + 1) + "." + c.get(Calendar.YEAR);
        endPeriod = c.get(Calendar.DAY_OF_MONTH) + "." + (c.get(Calendar.MONTH) + 2) + "." + c.get(Calendar.YEAR);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mLayoutManager = new GridLayoutManager(this, 2);

        if (isConn()) {
            makeRecyclerView();
        } else {
            CoordinatorLayout a =new CoordinatorLayout(this);
            Toast.makeText(getApplicationContext(), "I need the Internet connection ):", Toast.LENGTH_SHORT).show();
            makeEmptyRecyclerView();
        }

        Log.e("Dir main", getApplicationContext().getFilesDir() + "");

        {
    /*    try {
            // отрываем поток для записи
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(openFileOutput("FFile", MODE_PRIVATE)));
            // пишем данные
            bw.write("Содержимое файла");
            // закрываем поток

            bw.close();
            Log.d("dd", "Файл записан");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

        /*writeToFile(null);
        writeToFile2();*/

            /*  private void writeToFile2() {
        String filename = "myfile222";
        String string = "Hello world!";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_APPEND);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(View v) {

        Log.d("FFF", "writeToFile");

        String string = "My test string";

        try {
            FileOutputStream outputStream = openFileOutput("mFileName.txt", MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();

        } catch (Exception e) {
            Log.d("FFF", "Произошла ошибка при записи");
        }
    }
*/

        }

    }

    private void makeEmptyRecyclerView() {
        List<TimeTableDayModel> emptyList = new ArrayList<>();
        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RecyclerAdapter(rep.getFromJSON(), R.layout.card);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void makeRecyclerView() {
        rep.getValueFromServer(new TimePeriod(startPeriod, endPeriod));
        rep.makeListFor_Recycler();

        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerAdapter(rep.getListFor_RecyclerView(), R.layout.card);
        mRecyclerView.setAdapter(mAdapter);
    }


    public boolean isConn() {
        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity.getActiveNetworkInfo() != null) {
            if (connectivity.getActiveNetworkInfo().isConnected())
                return true;
        }
        return false;
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
                Toast.makeText(getApplicationContext(), "Alex", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.vlmcr:
                Toast.makeText(getApplicationContext(), "Vadim", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.new_game:
                DateRangePickerFragment dateRangePickerFragment= DateRangePickerFragment.newInstance(main_layout.this, false);
                dateRangePickerFragment.show(getSupportFragmentManager(),"datePicker");
                break;
            case R.id.updateJSON:
                rep.writeToJSON();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDateRangeSelected(int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear) {
        Toast.makeText(getApplicationContext(), "Sone", Toast.LENGTH_SHORT).show();
    }

}
