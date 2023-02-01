package com.richardo.finalproject.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.richardo.finalproject.Activity.MovieDetailActivity;
import com.richardo.finalproject.Model.Movie;
import com.richardo.finalproject.Model.Slide;
import com.richardo.finalproject.R;
import com.richardo.finalproject.adapters.MovieAdapter;
import com.richardo.finalproject.adapters.MovieItemClickListener;
import com.richardo.finalproject.adapters.SliderPagerAdapter;
import com.richardo.finalproject.utils.DataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements MovieItemClickListener {

    private List<Slide> slides;
    private ViewPager sliderpager;
    private TabLayout indicator;
    private RecyclerView MoviesRV,MoviesRVTrend;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addMovieToDatabase();
        iniViews();

        iniSlider();

        iniPopularMovies();

        iniTrendMovies();


    }

    private void iniTrendMovies() {

        MovieAdapter trendMovieAdapter = new MovieAdapter(this,DataSource.getTrendMovies(),this);
        MoviesRVTrend.setAdapter(trendMovieAdapter);
        MoviesRVTrend.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    }
    private void addMovieToDatabase()
    {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Movie");
        List<Movie> movies = DataSource.addMovie();
        databaseReference.push().setValue(movies);
    }
    private void iniPopularMovies() {


        MovieAdapter movieAdapter = new MovieAdapter(this, DataSource.getPopularMovies(),this);
        MoviesRV.setAdapter(movieAdapter);
        MoviesRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    }

    private void iniSlider() {
        slides = new ArrayList<>();
        slides.add(new Slide(R.drawable.spiderman,"Spider-Man: No Way Home"));
        slides.add(new Slide(R.drawable.otto,"A Man Called Otto"));
        slides.add(new Slide(R.drawable.blackp,"Black Panther: Wakanda Forever"));
        slides.add(new Slide(R.drawable.antman,"Antman and the wasp Quantumania"));

        SliderPagerAdapter adapter = new SliderPagerAdapter(this,slides);
        sliderpager.setAdapter(adapter);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new sliderTimer(),4000,6000);

        indicator.setupWithViewPager(sliderpager,true);
    }

    private void iniViews() {
        sliderpager = findViewById(R.id.slider_pager);
        indicator = findViewById(R.id.indicator);
        MoviesRV = findViewById(R.id.Rv_movies);
        MoviesRVTrend = findViewById(R.id.Rv_trend);
    }

    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {

        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("title",movie.getTitle());
        intent.putExtra("imgURL",movie.getThumbnail());
        intent.putExtra("imgCover",movie.getCoverPhoto());

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,movieImageView
                ,"sharedName");
        startActivity(intent,options.toBundle());

        Toast.makeText(this, "item clicked : " + movie.getTitle(),Toast.LENGTH_LONG).show();


    }

    class sliderTimer extends TimerTask {

        @Override
        public void run() {

            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (sliderpager.getCurrentItem()<slides.size()-1){
                        sliderpager.setCurrentItem(sliderpager.getCurrentItem()+1);
                    }
                    else{
                        sliderpager.setCurrentItem(0);
                    }
                }
            });
        }
    }
}