package com.example.app7;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.app7.RoomDatabase.MakeOrderClicked;

import java.util.List;

public class AdapterItem extends RecyclerView.Adapter<AdapterItem.ViewHolder> {
    Context context;
    List<ModelItemData> itemList;

    public AdapterItem(Context context, List<ModelItemData> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public AdapterItem.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewholder = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_model, parent, false);
        return new AdapterItem.ViewHolder(viewholder);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.nameItem.setText(itemList.get(position).getName());
        holder.descriptionItem.setText(Html.fromHtml(itemList.get(position).getDescription()));
        holder.priceItem.setText(itemList.get(position).getPrice().toString()+" Leke");
        Glide.with(context).load("https://freedesignfile.com/upload/2016/11/Italian-pasta-combinations-HD-picture.jpg").into(holder.image);

        holder.add_product_Iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MakeOrderClicked)context).productClicked(itemList.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameItem, descriptionItem, priceItem;
        ImageView image,add_product_Iv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=(ImageView) itemView.findViewById(R.id.image);
            nameItem = (TextView) itemView.findViewById(R.id.nameItem);
            descriptionItem = (TextView) itemView.findViewById(R.id.descriptionItem);
            priceItem = (TextView) itemView.findViewById(R.id.priceItem);
            add_product_Iv=(ImageView) itemView.findViewById(R.id.add_product_Iv);


        }
    }

}
