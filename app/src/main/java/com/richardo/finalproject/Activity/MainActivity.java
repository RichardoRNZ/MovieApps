package com.richardo.finalproject.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.richardo.finalproject.Activity.MovieDetailActivity;
import com.richardo.finalproject.Model.Movie;
import com.richardo.finalproject.Model.Slide;
import com.richardo.finalproject.R;
import com.richardo.finalproject.adapters.MovieAdapter;
import com.richardo.finalproject.adapters.MovieItemClickListener;
import com.richardo.finalproject.adapters.SliderPagerAdapter;


import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements MovieItemClickListener, NavigationBarView.OnItemSelectedListener {

    private List<Slide> slides;
    private ViewPager sliderpager;
    private TabLayout indicator;
    private RecyclerView MoviesRV,MoviesRVTrend;
    private DatabaseReference databaseReference;
    private BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseReference = FirebaseDatabase.getInstance().getReference("Movie");
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navbar);

//        addMovieToDatabase();
        iniViews();

        iniSlider();

        iniPopularMovies();

        iniTrendMovies();


    }

    private void iniTrendMovies() {
        List<Movie> trend = new ArrayList<>();
       MovieAdapter movieAdapter2 =  new MovieAdapter(this,trend,this);
        databaseReference.child("Trend Movie").addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    for(DataSnapshot dataSnapshot : snapshot.getChildren())
                    {
                        for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                        {
                            trend.add(dataSnapshot1.getValue(Movie.class));
                        }
                    }

                }
                movieAdapter2.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Unkwon Error",Toast.LENGTH_SHORT).show();
            }
        });



        MoviesRVTrend.setAdapter(movieAdapter2);
        MoviesRVTrend.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

    }
//    private void addMovieToDatabase()
//    {
//
//
//        databaseReference.child("Popular Movie").push().setValue(DataSource.addMovie());
//        databaseReference.child("Trend Movie").push().setValue(DataSource.addTrendMovie());
//    }
    private void iniPopularMovies() {
        List<Movie> listmovies = new ArrayList<>();
        MovieAdapter movieAdapter =  new MovieAdapter(this,listmovies,this);
        databaseReference.child("Popular Movie").addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    for(DataSnapshot dataSnapshot : snapshot.getChildren())
                    {
                        for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                        {
                            listmovies.add(dataSnapshot1.getValue(Movie.class));

                        }
                    }

                }
                movieAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Unkwon Error",Toast.LENGTH_SHORT).show();
            }
        });
        Log.d("tag",""+listmovies);
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
        intent.putExtra("description",movie.getDescription());
        intent.putExtra("link",movie.getStreamingLink());

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,movieImageView
                ,"sharedName");
        startActivity(intent,options.toBundle());

        Toast.makeText(this, "item clicked : " + movie.getTitle(),Toast.LENGTH_LONG).show();


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.btn_home:
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                return true;

            case R.id.btn_watch_list:
                startActivity(new Intent(getApplicationContext(),WatchListActivity.class));
                return true;

            case R.id.btn_dashboard_user:
                startActivity(new Intent(getApplicationContext(),DashboardActivity.class));

        }

        return false;
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