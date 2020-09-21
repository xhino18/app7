package com.example.app7;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterItemsSelected extends RecyclerView.Adapter<AdapterItemsSelected.ViewHolder> {

    private List<Items> items = new ArrayList<>();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_items, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public void setNotes(List<Items> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView nameItem,descriptionItem,priceItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameItem=itemView.findViewById(R.id.nameItem);
            descriptionItem=itemView.findViewById(R.id.descriptionItem);
            priceItem=itemView.findViewById(R.id.priceItem);
        }
    }
}
