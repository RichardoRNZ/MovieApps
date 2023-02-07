package com.richardo.finalproject.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.richardo.finalproject.R;

public class ActorViewHolder extends RecyclerView.ViewHolder {
    TextView description;
    ImageView image;
    public ActorViewHolder(@NonNull View itemView) {
        super(itemView);
        description = itemView.findViewById(R.id.actor_detail);
        image = itemView.findViewById(R.id.actor_image);
    }
}
