package com.richardo.finalproject.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.richardo.finalproject.Model.WatchList;
import com.richardo.finalproject.R;
import com.richardo.finalproject.adapters.WatchlistAdapter;

import java.util.ArrayList;
import java.util.Objects;

public class WatchListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WatchlistAdapter watchlistAdapter;
    private ArrayList<WatchList> watchlistActivities;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlist);
        databaseReference = FirebaseDatabase.getInstance().getReference("Watchlist");
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        watchlistActivities = new ArrayList<>();


        recyclerView = findViewById(R.id.recycleview);
        watchlistAdapter = new WatchlistAdapter(watchlistActivities);
        getData();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(WatchListActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(watchlistAdapter);
    }

    private void getData() {

        databaseReference.child(Objects.requireNonNull(firebaseUser.getDisplayName())).addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren())
                    {
                        watchlistActivities.add(dataSnapshot.getValue(WatchList.class));
                    }
                }
                watchlistAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public void onBackPressed() {
       startActivity(new Intent(this,MainActivity.class));
    }
}