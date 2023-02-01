package com.richardo.finalproject.adapters;

import android.widget.ImageView;


import com.richardo.finalproject.Model.Movie;

public interface MovieItemClickListener {

    void onMovieClick(Movie movie, ImageView movieImageView);
}
