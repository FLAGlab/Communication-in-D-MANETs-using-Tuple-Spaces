package com.example.jcbages.happnts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class PersonMatchesActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_matches);

        this.mRecyclerView = findViewById(R.id.matches_recycler_view);

        this.mLayoutManager = new LinearLayoutManager(this);
        this.mRecyclerView.setLayoutManager(mLayoutManager);

        this.mAdapter = new MatchesAdapter(PeopleData.matches());
        this.mRecyclerView.setAdapter(this.mAdapter);
        PeopleData.setMatchesAdapter(this.mAdapter);
    }
}
