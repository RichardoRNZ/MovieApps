package com.richardo.finalproject.utils;


import com.richardo.finalproject.Model.Movie;
import com.richardo.finalproject.R;

import java.util.ArrayList;
import java.util.List;

public class DataSource {


    public static List<Movie> getPopularMovies(){
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Moana", R.drawable.moana,R.drawable.moanacover));
        movies.add(new Movie("John Wick ",R.drawable.johnwick,R.drawable.johncover));
        movies.add(new Movie("Puss in Boots",R.drawable.puss,R.drawable.pusscover));
        movies.add(new Movie("Mission Impossible:Rogue Nation",R.drawable.misimpo,R.drawable.misimpocover));
        movies.add(new Movie("The Martian",R.drawable.martian,R.drawable.martiancover));

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



}
