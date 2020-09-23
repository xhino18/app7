package com.example.app7;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.app7.RoomDatabase.AddRemoveQuantityClickInterfce;
import com.example.app7.RoomDatabase.MakeOrderClicked;

import java.util.ArrayList;
import java.util.List;

public class AdapterItemsSelected extends RecyclerView.Adapter<AdapterItemsSelected.ViewHolder> {
    LifecycleOwner owner;
    ViewDialog context;
    private List<OrderItemModel> items ;
    Context newContext;

    public AdapterItemsSelected(LifecycleOwner owner,ViewDialog context, List<OrderItemModel> items,  Context newContext) {
        this.context = context;
        this.items = items;
        this.owner = owner;
        this.newContext=newContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_items, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.nameItem.setText(items.get(position).getName());
        holder.descriptionItem.setText(Html.fromHtml(items.get(position).getDescription()));
        holder.priceItem.setText(items.get(position).getPrice().toString());
        holder.textQuantity.setText(items.get(position).getQuantity().toString());
        holder.image_add_quantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AddRemoveQuantityClickInterfce)context).addClicked(items.get(position),position);

            }
        });
        holder.image_remove_quantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AddRemoveQuantityClickInterfce)context).removeClicked(items.get(position),position);
            }
        });
        Glide.with(newContext).load("https://freedesignfile.com/upload/2016/11/Italian-pasta-combinations-HD-picture.jpg").into(holder.image);


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameItem, descriptionItem, priceItem,textQuantity;
        private ImageView image,image_add_quantity,image_remove_quantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameItem = itemView.findViewById(R.id.nameItem);
            descriptionItem = itemView.findViewById(R.id.descriptionItem);
            priceItem = itemView.findViewById(R.id.priceItem);
            image = itemView.findViewById(R.id.image);
            textQuantity=itemView.findViewById(R.id.textQuantity);
            image_add_quantity=itemView.findViewById(R.id.image_add_quantity);
            image_remove_quantity=itemView.findViewById(R.id.image_remove_quantity);
        }
    }
}
