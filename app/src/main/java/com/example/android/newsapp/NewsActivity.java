package com.example.android.newsapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.widget.TextView;

public class NewsActivity extends AppCompatActivity implements LoaderCallbacks<List<News>> {

    public static final String LOG_TAG = NewsActivity.class.getName();

    /**
     * URL for news data from Guardian API
     */
    private static final String NEWS_URL =
            "http://content.guardianapis.com/search?q=politics&api-key=8ac1e9a6-c353-437b-a123-1b57e12c4bbd";

    /**
     * Constant value for the new loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int NEW_LOADER_ID = 1;

    /**
     * Adapter for the list of news
     */
    private NewsAdapter newsAdapter;

    /** TextView that is displayed when the list is empty */
    private TextView emptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        // Find a reference to the {@link ListView} in the layout
        ListView newListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of news
        newsAdapter = new NewsAdapter(this, new ArrayList<News>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        newListView.setAdapter(newsAdapter);

        // Hook up the TextView as the empty view of the ListView
        emptyStateTextView = (TextView) findViewById(R.id.empty_view);
        newListView.setEmptyView(emptyStateTextView);

        // Add an event listener on each new to open the correspondent new url
        newListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current new that was clicked on
                News currentNew = newsAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri newUri = Uri.parse(currentNew.getWebUrl());

                // Create a new intent to view the new URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

        // Get a reference to the LoaderManager, in order to interact with loaders.
        android.app.LoaderManager loaderManager = getLoaderManager();

        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).
        loaderManager.initLoader(NEW_LOADER_ID, null, this);
        //Log.i(LOG_TAG, "initLoader has loaded");
    }

    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        Log.i(LOG_TAG, "onCreateLoader has loaded" + NEWS_URL);
        // Create a new loader for the given URL
        return new NewsLoader(this, NEWS_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> news) {

        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // Set empty state text to display "No news found."
        emptyStateTextView.setText(R.string.no_news);

        //Log.i(LOG_TAG, "onLoadFinished has loaded");
        // Clear the adapter of previous news data
        newsAdapter.clear();

        // If there is a valid list of {@link News}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (news != null && !news.isEmpty()) {
            newsAdapter.addAll(news);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        //Log.i(LOG_TAG, "onLoaderReset has loaded");
        // Loader reset, so we can clear out our existing data.
        newsAdapter.clear();
    }
}
