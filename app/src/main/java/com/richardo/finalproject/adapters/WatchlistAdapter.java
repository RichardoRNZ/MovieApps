package com.richardo.finalproject.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.richardo.finalproject.Model.WatchList;
import com.richardo.finalproject.R;

import java.util.ArrayList;

public class WatchlistAdapter extends RecyclerView.Adapter<WatchlistAdapter.ViewHolder> {

    private ArrayList<WatchList> watchlistActivities;

    public WatchlistAdapter(ArrayList<WatchList> watchlistActivities) {
        this.watchlistActivities = watchlistActivities;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_watchlist,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.namafilm.setText(watchlistActivities.get(position).getName());
        holder.imgfilm.setImageResource(watchlistActivities.get(position).getPosterimg());
        holder.status.setText(watchlistActivities.get(position).getStatus());

    }

    @Override
    public int getItemCount() {
        return watchlistActivities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView namafilm;
        ImageView imgfilm;
        TextView status;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            namafilm = itemView.findViewById(R.id.poster_name);
            imgfilm = itemView.findViewById(R.id.poster);
            status = itemView.findViewById(R.id.status);
        }
    }
}
