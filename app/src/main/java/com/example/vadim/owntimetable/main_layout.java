package com.example.vadim.owntimetable;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.concurrent.ExecutionException;

/**
 * Created by vadim on 9/2/16.
 */
public class main_layout extends AppCompatActivity {
    private TextView mainTextView;
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

        mainTextView.setText( localParser.getTimeTable() );
    }
}
