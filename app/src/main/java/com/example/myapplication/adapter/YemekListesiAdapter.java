package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.RecyclerRow4Binding;
import com.example.myapplication.model.YemekListesi;

import java.util.ArrayList;

public class YemekListesiAdapter extends RecyclerView.Adapter<YemekListesiAdapter.YemekListesiHolder>{

    private ArrayList<YemekListesi> yemekListesiArrayList;

    public YemekListesiAdapter(ArrayList<YemekListesi> yemekListesiArrayList){
        this.yemekListesiArrayList = yemekListesiArrayList;
    }

    @NonNull
    @Override
    public YemekListesiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRow4Binding recyclerRow4Binding = RecyclerRow4Binding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new YemekListesiHolder(recyclerRow4Binding);
    }

    @Override
    public void onBindViewHolder(@NonNull YemekListesiAdapter.YemekListesiHolder holder, int position) {
        holder.recyclerRow4Binding.recyclerViewUserEmailText.setText(yemekListesiArrayList.get(position).email);
        holder.recyclerRow4Binding.recyclerViewKahvalti.setText(yemekListesiArrayList.get(position).kahvalti);
        holder.recyclerRow4Binding.recyclerViewOgleText.setText(yemekListesiArrayList.get(position).ogle);
        holder.recyclerRow4Binding.recyclerViewAksamText.setText(yemekListesiArrayList.get(position).aksam);
        holder.recyclerRow4Binding.recyclerViewAciklama2Text.setText(yemekListesiArrayList.get(position).aciklama);

    }

    @Override
    public int getItemCount() {
        return yemekListesiArrayList.size();
    }

    class YemekListesiHolder extends RecyclerView.ViewHolder{

        RecyclerRow4Binding recyclerRow4Binding;
        public YemekListesiHolder(RecyclerRow4Binding recyclerRow4Binding) {
            super(recyclerRow4Binding.getRoot());
            this.recyclerRow4Binding = recyclerRow4Binding;
        }
    }
}
