package com.jamestiotio.sutdpokedex;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CharaAdapter extends RecyclerView.Adapter<CharaAdapter.CharaViewHolder> {
    Context context;
    LayoutInflater mInflater;
    DataSource dataSource;

    CharaAdapter(Context context, DataSource dataSource) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.dataSource = dataSource;
    }

    @NonNull
    @Override
    public CharaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.pokemon, viewGroup, false);
        return new CharaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CharaViewHolder charaViewHolder, int i) {
        charaViewHolder.textViewName.setText(dataSource.getName(i));
        charaViewHolder.imageViewChara.setImageBitmap(dataSource.getImage(i));
    }

    @Override
    public int getItemCount() {
        return dataSource.getSize();
    }

    static class CharaViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewChara;
        TextView textViewName;
        CardView cardItem;

        CharaViewHolder(View view) {
            super(view);
            imageViewChara = view.findViewById(R.id.cardViewImage);
            textViewName = view.findViewById(R.id.cardViewTextName);
            cardItem = view.findViewById(R.id.cardViewItem);

            cardItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String position = String.valueOf(getAdapterPosition());
                    Toast.makeText(v.getContext(),
                            "This is the ViewHolder's position on the RecyclerView: " + position,
                            Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}