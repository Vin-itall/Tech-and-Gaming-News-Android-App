package com.example.atmc.techknowlogy_l;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class recycle_2 extends AppCompatActivity implements Callback2 {


    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;

    private RecyclerAdapter mAdapter;

    private ArrayList<Article> mArticlesList = new ArrayList<Article>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);


     /*   Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); */


        mAdapter = new RecyclerAdapter(mArticlesList);
        mRecyclerView.setAdapter(mAdapter);


        if (mArticlesList.size() == 0) {
            (new GetRequestG()).get(this);
        }


    }

    @Override
    public void handleResponse(String err, final String response) {
        if (err != null) {
            Log.d("" +
                    "_A", err);
        } else {
            Log.d("_A", "errorNull");
        }

        if (response != null) {
            Log.d("Jig", response);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    try {
                        JSONObject responseObject = new JSONObject(response);
                        JSONArray articlesInResponse = responseObject.getJSONArray("articles");

                        for (int i = 0; i < articlesInResponse.length(); i++) {
                            JSONObject articleInResponse = articlesInResponse.getJSONObject(i);
                            String title = articleInResponse.getString("title");
                            String description = articleInResponse.getString("description");
                            String url = articleInResponse.getString("urlToImage");

                            mArticlesList.add(new Article(title, description, url));
                            mAdapter.notifyItemInserted(mArticlesList.size());

                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            Log.d("_A", "responseNull");
        }
    }
}

