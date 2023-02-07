package com.richardo.finalproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.richardo.finalproject.Model.Cast;
import com.richardo.finalproject.R;

import java.util.List;

public class ActorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Cast> actorlist;
    Context context;

    public ActorAdapter(List<Cast> actorlist, Context context) {
        this.actorlist = actorlist;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.actor_card,parent,false);
        return new ActorViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ActorViewHolder)holder).description.setText(actorlist.get(position).getDescription());
        Glide.with(context).load(actorlist.get(position).getImg_link()).into(((ActorViewHolder) holder).image);

    }

    @Override
    public int getItemCount() {
        return actorlist.size();
    }
}
