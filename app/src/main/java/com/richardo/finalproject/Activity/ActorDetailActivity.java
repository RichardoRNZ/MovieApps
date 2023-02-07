package com.richardo.finalproject.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.richardo.finalproject.Model.Cast;
import com.richardo.finalproject.R;
import com.richardo.finalproject.adapters.ActorAdapter;

import java.util.ArrayList;
import java.util.List;

public class ActorDetailActivity extends AppCompatActivity {

    private ImageView ActorImg;
    private TextView ActorBio;
    private ActorAdapter actorAdapter;
    private RecyclerView recyclerView;
    private List<Cast>mData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor_detail);
        mData = new ArrayList<>();
        recyclerView = findViewById(R.id.Rv_actor);
        addActor();
        showActor();
    }
    @SuppressLint("ResourceType")
    private void addActor()
    {

        mData.add(new Cast("Tom Holland", R.drawable.tomholland,getResources().getString(R.string.text_desc)));
        mData.add(new Cast("Keanu Reeves", R.drawable.keanu,getResources().getString(R.string.text_desc)));
        mData.add(new Cast("Tom Cruises", R.drawable.tomcruise,getResources().getString(R.string.text_desc)));
        mData.add(new Cast("Antonio Bandreas", R.drawable.antonio,getResources().getString(R.string.text_desc)));
    }
    private void showActor()
    {

        actorAdapter = new ActorAdapter(mData,this);
        recyclerView.setAdapter(actorAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }

}