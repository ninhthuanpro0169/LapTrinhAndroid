package com.example.travelgood.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.travelgood.R;
import com.example.travelgood.model.Dulich;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DulichAdapter extends RecyclerView.Adapter<DulichAdapter.ItemHolder> {
    Context context;
    ArrayList<Dulich> arraydulich;

    public DulichAdapter(Context context, ArrayList<Dulich> arraydulich) {
        this.context = context;
        this.arraydulich = arraydulich;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_diadiemhot,null);
        ItemHolder itemHolder = new ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Dulich dulich = arraydulich.get(position);
        holder.txttendulich.setText(dulich.getTendiadiem());
        holder.txtdiachi.setText(dulich.getDiachi());
        Picasso.with(context).load(dulich.getHinhanhdiadiemdulich())
                .placeholder(R.drawable.nui)
                .error(R.drawable.loi)
                .into(holder.imghinhdulich);

    }

    @Override
    public int getItemCount() {

        return arraydulich.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imghinhdulich;
        public TextView txttendulich , txtdiachi;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            imghinhdulich = itemView.findViewById(R.id.imageviewdulich);
            txttendulich = itemView.findViewById(R.id.textviewtendiadiem);
            txtdiachi = itemView.findViewById(R.id.textviewdiachi);
        }
    }
}
