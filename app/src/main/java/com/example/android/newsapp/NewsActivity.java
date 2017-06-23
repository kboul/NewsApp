package com.example.android.newsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    public static final String LOG_TAG = NewsActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        // Create a fake list of news.
        ArrayList<News> news = new ArrayList<>();

        news.add(new News(
                "Theresa May rules out participating in TV debates before election",
                "Politics"
                )
        );
        news.add(new News(
                "Scottish parliament debates call for second independence referendum - Politics live",
                "Politics")
        );

        // Find a reference to the {@link ListView} in the layout
        ListView newsListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of news
        NewsAdapter adapter = new NewsAdapter(this, news);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        newsListView.setAdapter(adapter);
    }
}
