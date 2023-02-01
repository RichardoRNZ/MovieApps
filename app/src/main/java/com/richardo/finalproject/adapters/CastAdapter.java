package com.richardo.finalproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.richardo.finalproject.Model.Cast;
import com.richardo.finalproject.R;

import java.util.List;


public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastViewHolder> {

    Context mContext;
    List<Cast> mData;

    public CastAdapter(Context mContext, List<Cast> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public CastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_cast,parent,false);

        return new CastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CastViewHolder holder, int position) {

        Glide.with(mContext).load(mData.get(position).getImg_link()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class CastViewHolder extends RecyclerView.ViewHolder{

        ImageView img;

        public CastViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_cast);
        }
    }



}
