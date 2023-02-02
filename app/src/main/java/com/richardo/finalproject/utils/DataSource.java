package com.richardo.finalproject.utils;


import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.richardo.finalproject.Activity.MainActivity;
import com.richardo.finalproject.Model.Movie;
import com.richardo.finalproject.R;
import com.richardo.finalproject.adapters.MovieAdapter;

import java.util.ArrayList;
import java.util.List;

public class DataSource {
    private static MovieAdapter movieAdapter;

    private static DatabaseReference  databaseReference = FirebaseDatabase.getInstance().getReference("Movie");
    public static List<Movie> getMovies(){
        List<Movie> movies = new ArrayList<>();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                  for(DataSnapshot dataSnapshot : snapshot.getChildren())
                  {
                     for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                     {
                        movies.add(dataSnapshot1.getValue(Movie.class));

                     }
                  }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("tag", "Error");
            }
        });
        Log.d("tag", "Value :" +movies);
        return movies;
    }

    public static List<Movie> getTrendMovies(){
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Alice in Borderland", R.drawable.alice,R.drawable.alicecover));
        movies.add(new Movie("Avengers :End Game",R.drawable.avenger,R.drawable.avengercover));
        movies.add(new Movie("Money Heist",R.drawable.moneyheist,R.drawable.moneyheistcover));
        movies.add(new Movie("Spiderman: No way Home",R.drawable.spider,R.drawable.spiderman));
        movies.add(new Movie("X-Men: Apocalypse",R.drawable.xmen,R.drawable.xmencover));

        return movies;
    }
    public static List<Movie> addMovie(){
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Moana", R.drawable.moana,R.drawable.moanacover,String.valueOf(R.string.moana_desc),"Disney","SU","https://www.youtube.com/watch?v=cPAbx5kgCJo"));
        movies.add(new Movie("John Wick ",R.drawable.johnwick,R.drawable.johncover,String.valueOf(R.string.john_desc),"Maxnut","17+","https://www.youtube.com/watch?v=ImDWEf0Y9rc"));
        movies.add(new Movie("Puss in Boots",R.drawable.puss,R.drawable.pusscover,String.valueOf(R.string.push_desc),"Dreamworks","SU","https://www.youtube.com/watch?v=RqrXhwS33yc"));
        movies.add(new Movie("Mission Impossible:Rogue Nation",R.drawable.misimpo,R.drawable.misimpocover,String.valueOf(R.string.mission_desc),"Studio C","17+","https://www.youtube.com/watch?v=2m1drlOZSDw"));
        movies.add(new Movie("The Martian",R.drawable.martian,R.drawable.martiancover,String.valueOf(R.string.martian_desc),"20th Century Fox","13+","https://www.youtube.com/watch?v=ej3ioOneTy8"));

        return movies;
    }
    public static List<Movie> addTrendMovie(){
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Alice in Borderland", R.drawable.alice,R.drawable.alicecover,String.valueOf(R.string.alice_desc),"Netflix","17+","https://www.youtube.com/watch?v=49_44FFKZ1M"));
        movies.add(new Movie("Avengers :End Game",R.drawable.avenger,R.drawable.avengercover,String.valueOf(R.string.avenger_desc),"Marvel Studio","13+","https://www.youtube.com/watch?v=79uhQ85n0YU"));
        movies.add(new Movie("Money Heist",R.drawable.moneyheist,R.drawable.moneyheistcover,String.valueOf(R.string.money_desc),"Netflix","17+","https://www.youtube.com/watch?v=L9giOct92Js"));
        movies.add(new Movie("Spiderman: No way Home",R.drawable.spider,R.drawable.spiderman,String.valueOf(R.string.spiderman_desc),"Marvel Studio","13+","https://www.youtube.com/watch?v=JfVOs4VSpmA"));
        movies.add(new Movie("X-Men: Apocalypse",R.drawable.xmen,R.drawable.xmencover,String.valueOf(R.string.x_men_desc),"20th Century Fox","13+","https://www.youtube.com/watch?v=COvnHv42T-A"));
        return movies;
    }



}
