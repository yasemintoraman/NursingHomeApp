package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.RecyclerRow2Binding;
import com.example.myapplication.model.Ilac;

import java.util.ArrayList;

public class IlacAdapter extends RecyclerView.Adapter<IlacAdapter.IlacHolder>{

    private ArrayList<Ilac> ilacArrayList;

    public IlacAdapter(ArrayList<Ilac> ilacArrayList){
        this.ilacArrayList = ilacArrayList;
    }

    @NonNull
    @Override
    public IlacHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRow2Binding recyclerRow2Binding = RecyclerRow2Binding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new IlacAdapter.IlacHolder(recyclerRow2Binding);
    }

    @Override
    public void onBindViewHolder(@NonNull IlacHolder holder, int position) {
        holder.recyclerRow2Binding.recyclerViewUserEmailText.setText(ilacArrayList.get(position).email);
        holder.recyclerRow2Binding.recyclerViewIlacAdiText.setText(ilacArrayList.get(position).ilacAdi);
        holder.recyclerRow2Binding.recyclerViewAciklamaText.setText(ilacArrayList.get(position).aciklama);
        holder.recyclerRow2Binding.recyclerViewIlacSaatiText.setText(ilacArrayList.get(position).ilacSaati);
        holder.recyclerRow2Binding.recyclerViewGunSayisiText.setText(ilacArrayList.get(position).gun);

    }

    @Override
    public int getItemCount() {
        return ilacArrayList.size();
    }

    class IlacHolder extends RecyclerView.ViewHolder{
        RecyclerRow2Binding recyclerRow2Binding;
        public IlacHolder(RecyclerRow2Binding recyclerRow2Binding) {
            super(recyclerRow2Binding.getRoot());
            this.recyclerRow2Binding = recyclerRow2Binding;
        }
    }

}
