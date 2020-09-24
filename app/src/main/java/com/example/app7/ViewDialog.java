package com.example.app7;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app7.RoomDatabase.AddRemoveQuantityClickInterfce;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.app7.ActivityProducts.cardView_items_selected;
import static com.example.app7.ActivityProducts.textBasketQuantity;

public class ViewDialog implements AddRemoveQuantityClickInterfce {
    Gson gson;
    List<OrderItemModel> list = new ArrayList<>();
    AdapterItemsSelected adapterItem;
    LifecycleOwner owner;
    RecyclerView recyclerView;
    Context context;
    TextView textTotali;
    Dialog dialog;

    public ViewDialog(Context context) {
        this.context = context;
    }

    public void showDialog(Activity activity, String msg) {
         dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_cart_items);
        list = ItemDatabase.getInstance(context).orderItemDao().getAllItems();
        getTotalQuantity();
        recyclerView = (RecyclerView) dialog.findViewById(R.id.recyclerCartItem);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.VERTICAL, false));

        textTotali=dialog.findViewById(R.id.textTotali);


        Button buttonCancel = (Button) dialog.findViewById(R.id.buttonCancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Button buttonConfirm = (Button) dialog.findViewById(R.id.buttonConfirm);
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent intent= new Intent(context,ActivityPayment.class);
             context.startActivity(intent);


            }
        });
        updateTotal();
        dialog.show();
        getTotalQuantity();

        adapterItem = new AdapterItemsSelected(owner,this, list,context);
        recyclerView.setAdapter(adapterItem);

    }

    @Override
    public void addClicked(OrderItemModel data,int position) {
        updateOrderItem(list.get(position), 1);
        adapterItem.notifyDataSetChanged();
        recyclerView.setAdapter(adapterItem);
        updateTotal();
        getTotalQuantity();


    }

    @Override
    public void removeClicked(OrderItemModel data,int position) {
        if (list.get(position).getQuantity() == 1) {
            if (list.size() == 0) {
                OrderItemModel orderItem = list.get(position);
                ItemDatabase.getInstance(context).orderItemDao().delete(orderItem);
                adapterItem.notifyDataSetChanged();
                recyclerView.setAdapter(adapterItem);
                updateTotal();
                getTotalQuantity();

            } else {
                OrderItemModel orderItem = list.get(position);
                ItemDatabase.getInstance(context).orderItemDao().delete(orderItem);
                list.remove(position);
                adapterItem.notifyDataSetChanged();
                recyclerView.setAdapter(adapterItem);
                updateTotal();
                getTotalQuantity();
            }
        } else {
            updateOrderItem(list.get(position), -1);
            adapterItem.notifyDataSetChanged();

            recyclerView.setAdapter(adapterItem);
            updateTotal();
            getTotalQuantity();

        }

    }

    private void updateOrderItem(OrderItemModel orderItem, int value) {
        int basketPosition = orderItemExistsOnBasket(orderItem);
        list.get(basketPosition).setQuantity(list.get(basketPosition).getQuantity() + value);
        orderItem.setQuantity(list.get(basketPosition).getQuantity());
        ItemDatabase.getInstance(context).orderItemDao().update(orderItem);
        adapterItem = new AdapterItemsSelected(owner, this, list,context);
        recyclerView.setAdapter(adapterItem);

    }

    private int orderItemExistsOnBasket(OrderItemModel productData) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(productData.getId())) {
                return i;
            }
        }
        return -1;
    }

    private int updateTotal(){
    int total=0;
        for (int i =0;i<list.size();i++){
            total=total +list.get(i).getPrice()*list.get(i).getQuantity();
        }
        textTotali.setText(total+" Leke");
        return total;

    }
    private void getTotalQuantity(){
        int totalquantity=0;
        for (int i =0;i<list.size();i++){
        totalquantity=totalquantity+ list.get(i).getQuantity();
        }

        textBasketQuantity.setText(totalquantity+"");


    }



}
