package com.example.app7;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterCategories extends RecyclerView.Adapter<AdapterCategories.ViewHolder> {
    Context context;
    List<ModelCategoriesData> categoriesList;

    public AdapterCategories(Context context, List<ModelCategoriesData> categoriesList) {
        this.context = context;
        this.categoriesList = categoriesList;
    }

    @NonNull
    @Override
    public AdapterCategories.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewholder = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_model, parent, false);
        return new AdapterCategories.ViewHolder(viewholder);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCategories.ViewHolder holder, final int position) {
        holder.textCategories.setText(categoriesList.get(position).getName());
        holder.textCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ItemClickInterface)context).categoryClicked(categoriesList.get(position));
            }
        });


    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textIndex,textCategories;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textIndex = (TextView) itemView.findViewById(R.id.textIndex);
            textCategories = (TextView) itemView.findViewById(R.id.textCategories);
        }


    }
}
