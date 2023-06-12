package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.RecyclerRow3Binding;
import com.example.myapplication.model.Duyuru;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DuyuruAdapter extends RecyclerView.Adapter<DuyuruAdapter.DuyuruHolder> {

    private ArrayList <Duyuru> duyuruArrayList;

    public DuyuruAdapter(ArrayList<Duyuru> duyuruArrayList){
        this.duyuruArrayList = duyuruArrayList;
    }

    @NonNull
    @Override
    public DuyuruHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRow3Binding recyclerRow3Binding = RecyclerRow3Binding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new DuyuruHolder(recyclerRow3Binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DuyuruHolder holder, int position) {
        holder.recyclerRow3Binding.recyclerviewRowUseremailText.setText(duyuruArrayList.get(position).email);
        holder.recyclerRow3Binding.recyclerviewRowDuyuruText.setText(duyuruArrayList.get(position).duyuru);
        Picasso.get().load(duyuruArrayList.get(position).downloadUrl).into(holder.recyclerRow3Binding.recyclerviewRowImageview2);

    }

    @Override
    public int getItemCount() {
        return duyuruArrayList.size();
    }

    class DuyuruHolder extends RecyclerView.ViewHolder{
        RecyclerRow3Binding recyclerRow3Binding;

        public DuyuruHolder(RecyclerRow3Binding recyclerRow3Binding){
            super(recyclerRow3Binding.getRoot());
            this.recyclerRow3Binding = recyclerRow3Binding;
        }
    }
}
