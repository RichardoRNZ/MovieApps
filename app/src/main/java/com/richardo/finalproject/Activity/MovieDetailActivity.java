package com.richardo.finalproject.Activity;

import static com.richardo.finalproject.R.id.detail_movie_desc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.richardo.finalproject.Model.Cast;
import com.richardo.finalproject.R;
import com.richardo.finalproject.adapters.CastAdapter;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailActivity extends AppCompatActivity {


    private ImageView MovieThumbnailImg,MovieCoverImg;
    private TextView tv_title,tv_description;
    private FloatingActionButton play_fab;
    private RecyclerView RvCast;
    private CastAdapter castAdapter;

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
        String movieTitle = getIntent().getExtras().getString("title");
        int imageResourceId = getIntent().getExtras().getInt("imgURL");
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
        tv_description.setText(getResources().getString(synopsis ));

        MovieCoverImg.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));
        play_fab.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));


    }

    void setupRvCast(){

        List<Cast> mData = new ArrayList<>();
        mData.add(new Cast("Tom Holland", R.drawable.tomholland));
        mData.add(new Cast("Toby Maguire", R.drawable.toby));
        mData.add(new Cast("Keanu Reeves", R.drawable.keanu));
        mData.add(new Cast("Tom Cruises", R.drawable.tomcruise));
        mData.add(new Cast("Antonio Bandreas", R.drawable.antonio));

        castAdapter = new CastAdapter(this,mData);
        RvCast.setAdapter(castAdapter);
        RvCast.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

    }
}