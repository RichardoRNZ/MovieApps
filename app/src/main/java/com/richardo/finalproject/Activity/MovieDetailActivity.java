package com.richardo.finalproject.Activity;

import static com.richardo.finalproject.R.id.detail_movie_desc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.richardo.finalproject.Model.Cast;
import com.richardo.finalproject.Model.WatchList;
import com.richardo.finalproject.R;
import com.richardo.finalproject.adapters.ActorClickListener;
import com.richardo.finalproject.adapters.CastAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class MovieDetailActivity extends AppCompatActivity implements ActorClickListener {


    private ImageView MovieThumbnailImg,MovieCoverImg;
    private TextView tv_title,tv_description;
    private FloatingActionButton play_fab;
    private RecyclerView RvCast;
    private CastAdapter castAdapter;
    private Button addToWatchlist;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Watchlist");
    private String movieTitle;
    private int imageResourceId;
    private FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private final String[] STATUS ={"Listed", "Watched"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);


        iniViews();

        setupRvCast();


    }

    @SuppressLint("ResourceType")
    void iniViews(){
        RvCast = findViewById(R.id.rv_cast);
        play_fab =  findViewById(R.id.play_fab);
        movieTitle = getIntent().getExtras().getString("title");
        imageResourceId = getIntent().getExtras().getInt("imgURL");
        int imagecover = getIntent().getExtras().getInt("imgCover");
        int synopsis = Integer.parseInt(getIntent().getExtras().getString("description"));
        MovieThumbnailImg = findViewById(R.id.detail_movie);
        Glide.with(this).load(imageResourceId).into(MovieThumbnailImg);
        MovieThumbnailImg.setImageResource(imageResourceId);
        MovieCoverImg = findViewById(R.id.detail_movie_cover);
        Glide.with(this).load(imagecover).into(MovieCoverImg);
        tv_title = findViewById(R.id.detail_movie_title);
        tv_title.setText(movieTitle);
        getSupportActionBar().setTitle(movieTitle);
        tv_description = findViewById(detail_movie_desc);
        tv_description.setText(getResources().getString(synopsis));
        String streaminglink = getIntent().getExtras().getString("link");

        MovieCoverImg.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));
        play_fab.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));
        play_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(streaminglink);
                startActivity(new Intent(Intent.ACTION_VIEW,uri));
            }
        });


        AddToWatchlist();


    }

    void setupRvCast(){

        List<Cast> mData = new ArrayList<>();
        mData.add(new Cast("Tom Holland", R.drawable.tomholland));
        mData.add(new Cast("Toby Maguire", R.drawable.toby));
        mData.add(new Cast("Keanu Reeves", R.drawable.keanu));
        mData.add(new Cast("Tom Cruises", R.drawable.tomcruise));
        mData.add(new Cast("Antonio Bandreas", R.drawable.antonio));

        castAdapter = new CastAdapter(this,mData,this);
        RvCast.setAdapter(castAdapter);
        RvCast.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

    }
    private void AddToWatchlist()
    {
        addMovieToDatabase();
        addToWatchlist = findViewById(R.id.btn_add_watchlist);
        addToWatchlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MovieDetailActivity.this,WatchListActivity.class);
                startActivity(intent);
            }
        });
    }
    private String setSTATUS()
    {
        Random random = new Random();
        int index = random.nextInt(STATUS.length);
        return STATUS[index];
    }
    private void addMovieToDatabase()
    {
        String status = setSTATUS();
        WatchList watchList = new WatchList(movieTitle,status,imageResourceId);
        databaseReference.child(Objects.requireNonNull(firebaseUser.getDisplayName())).push().setValue(watchList);
    }

    @Override
    public void onClick(Cast cast, ImageView imageView) {
        startActivity(new Intent(MovieDetailActivity.this,ActorDetailActivity.class));
    }
}